package innovations.doubleeh.com.noted.ui.notedAdd


import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.text.format.DateFormat
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import dagger.android.AndroidInjection
import innovations.doubleeh.com.noted.R
import innovations.doubleeh.com.noted.repository.Note
import innovations.doubleeh.com.noted.repository.NotedDatabase
import innovations.doubleeh.com.noted.ui.notedList.NotedListActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_noted_add.*
import java.util.*
import javax.inject.Inject


class NotedAddActivity : AppCompatActivity() {

    @Inject
    lateinit var notedDatabase: NotedDatabase

    enum class BUNDLE_KEYS {
        IS_PRIORITY,
        DATE,
        TIME
    }

    var isPriority: Boolean = false

    var hourOfDay: Int = 0
    var minute: Int = 0

    var year: Int = 0
    var month: Int = 0
    var day: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_noted_add)

        isPriority = savedInstanceState?.getBoolean(BUNDLE_KEYS.IS_PRIORITY.name) ?: false
        setDateValues(savedInstanceState?.getString(BUNDLE_KEYS.DATE.name) ?: "")
        setTimeValues(savedInstanceState?.getString(BUNDLE_KEYS.TIME.name) ?: "")

        reminderTime.setOnClickListener {
            val timePicker = TimePickerFragment()
            timePicker.saveTimeListener = object : TimePickerFragment.onSaveTimeListener {
                override fun onSaveTime(hourOfDay: Int, minute: Int) {
                    this@NotedAddActivity.hourOfDay = hourOfDay
                    this@NotedAddActivity.minute = minute
                    reminderTime.text = getTime(hourOfDay, minute)
                }
            }
            timePicker.show(supportFragmentManager, "TimePicker")
        }

        reminderDate.setOnClickListener {
            val datePicker = DatePickerFragment()
            datePicker.dateSetListener = object : DatePickerFragment.onDateSetListener {
                override fun onDateSet(year: Int, month: Int, day: Int) {
                    this@NotedAddActivity.year = year
                    this@NotedAddActivity.month = month
                    this@NotedAddActivity.day = day
                    reminderDate.text = getDate(year, month, day)
                }
            }
            datePicker.show(supportFragmentManager, "TimePicker")
        }

        notePriority.setOnCheckedChangeListener { button, isChecked ->
            this@NotedAddActivity.isPriority = isChecked
        }

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putBoolean(BUNDLE_KEYS.IS_PRIORITY.name, isPriority)
        outState?.putString(BUNDLE_KEYS.DATE.name, getDate(year, month, day))
        outState?.putString(BUNDLE_KEYS.TIME.name, getTime(hourOfDay, minute))
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.noted_add_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId?.equals(R.id.action_save_note) == true) {

            val validMsg = !noteMsg.text.toString().isEmpty()
            val validDate = reminderDate?.text?.isNullOrEmpty() == false && reminderDate?.text?.contains("/") == true
            val validTime = reminderTime?.text?.isNullOrEmpty() == false && reminderTime?.text?.contains(":") == true

            if (validMsg && validDate && validTime) {
                Observable
                        .just(1)
                        .observeOn(Schedulers.io())
                        .map {
                            notedDatabase.notesDao().insert(
                                    Note(
                                            msg = noteMsg.text.toString(),
                                            highPriority = isPriority,
                                            dateToRemind = getDate(year, month, day),
                                            timeToRemind = getTime(hourOfDay, minute)
                                    )
                            )
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Observer<Long> {
                            var disposable: Disposable? = null
                            override fun onSubscribe(d: Disposable) {
                                disposable = d
                            }

                            override fun onError(e: Throwable) {
                                Toast.makeText(this@NotedAddActivity,
                                        "Save failed",
                                        Toast.LENGTH_LONG).show()
                                disposable?.dispose()
                            }

                            override fun onNext(id: Long) {
                                if (id <= 0) {
                                    onError(Throwable("INSERT NOTE INTO DB FAILED"))
                                }
                            }

                            override fun onComplete() {
                                startActivity(Intent(this@NotedAddActivity,
                                        NotedListActivity::class.java))
                                finish()
                            }
                        })
            } else {
                Toast.makeText(this@NotedAddActivity,
                        "Missing some info",
                        Toast.LENGTH_LONG).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

        var dateSetListener: onDateSetListener? = null

        interface onDateSetListener {
            fun onDateSet(year: Int, month: Int, day: Int)
        }

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            // Use the current date as the default date in the picker
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // Create a new instance of DatePickerDialog and return it
            return DatePickerDialog(activity, this, year, month, day)
        }

        override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
            dateSetListener?.onDateSet(year, month, day)
        }
    }

    class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

        var saveTimeListener: onSaveTimeListener? = null

        interface onSaveTimeListener {
            fun onSaveTime(hourOfDay: Int, minute: Int)
        }

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            // Use the current time as the default values for the picker
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)


            // Create a new instance of TimePickerDialog and return it
            return TimePickerDialog(activity, this, hour, minute,
                    DateFormat.is24HourFormat(activity))
        }

        override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
            saveTimeListener?.onSaveTime(hourOfDay, minute)
        }

    }

    fun getDate(year: Int, month: Int, day: Int) = "$day/$month/$year"

    fun setDateValues(date: String) {
        val split = date.split("/")
        if (split.size == 3) {
            year = split[0].toInt()
            month = split[1].toInt()
            day = split[2].toInt()
            reminderDate.text = getDate(year, month, day)
        }
    }

    fun getTime(hourOfDay: Int, minute: Int) = "$hourOfDay:$minute"

    fun setTimeValues(time: String) {
        val split = time.split(":")
        if (split.size == 2) {
            hourOfDay = split[0].toInt()
            minute = split[1].toInt()
            reminderTime.text = getTime(hourOfDay, minute)
        }
    }
}
