package innovations.doubleeh.com.noted.ui

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.format.DateFormat
import android.widget.TimePicker
import java.util.*

/**
 * Created by arjunachatz on 2017-12-13.
 */


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