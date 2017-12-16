package innovations.doubleeh.com.noted.ui.notedDetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import innovations.doubleeh.com.noted.repository.Note
import innovations.doubleeh.com.noted.repository.NotedDatabase
import javax.inject.Inject

/**
 * Created by arjunachatz on 2017-12-15.
 */

@SuppressWarnings("unchecked")
class NotedDetailViewModel @Inject constructor(val notedDatabase: NotedDatabase) : ViewModel() {

    private var isPriority: LiveData<Note>? = null

    fun getNoteDetails(id: Long): LiveData<Note> {
        if(isPriority == null){
            isPriority = notedDatabase.notesDao().getNoteById(id)
        }
        return isPriority!!
    }

}