package innovations.doubleeh.com.noted.ui.notedAdd

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import innovations.doubleeh.com.noted.repository.Note
import innovations.doubleeh.com.noted.repository.NotedDatabase
import javax.inject.Inject

/**
 * Created by arjunachatz on 2017-12-12.
 */

@SuppressWarnings("unchecked")
class NotedAddViewModel @Inject constructor(val notedDatabase: NotedDatabase) : ViewModel() {

    private var msg: MutableLiveData<String>? = null
    private var isPriority: MutableLiveData<Boolean>? = null
    private var date: MutableLiveData<String>? = null
    private var time: MutableLiveData<String>? = null

    fun getIsPriorityLiveData(): MutableLiveData<Boolean> {
        if (isPriority == null) {
            isPriority = MutableLiveData()
            isPriority?.value = false
        }
        return isPriority!!
    }

    fun setIsPriorityLiveData(newValue: Boolean) {
        isPriority?.value = newValue
    }

    fun getDateLiveData(): MutableLiveData<String> {
        if (date == null) {
            date = MutableLiveData()
            date?.value = ""
        }
        return date!!
    }

    fun setDateLiveData(newValue: String) {
        date?.value = newValue
    }

    fun getTimeLiveData(): MutableLiveData<String> {
        if (time == null) {
            time = MutableLiveData()
            time?.value = ""
        }
        return time!!
    }

    fun setTimeLiveData(newValue: String) {
        time?.value = newValue
    }

    fun saveNote(): Long {
        return notedDatabase
                .notesDao()
                .insert(
                        Note(
                                0,
                                msg?.value ?: "",
                                isPriority?.value ?: false,
                                date?.value ?: "",
                                time?.value ?: ""
                        )
                )
    }

    fun setMsg(newMsg: String?) {
        msg?.value = newMsg
    }
}