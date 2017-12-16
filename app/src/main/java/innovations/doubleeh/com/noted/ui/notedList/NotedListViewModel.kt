package innovations.doubleeh.com.noted.ui.notedList

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import innovations.doubleeh.com.noted.repository.Note
import innovations.doubleeh.com.noted.repository.NotedDatabase
import javax.inject.Inject

/**
 * Created by arjunachatz on 2017-12-11.
 */

class NotedListViewModel @Inject constructor(val notedDatabase: NotedDatabase) : ViewModel() {

    private var listOfNotes: LiveData<List<Note>>? = null

    fun getLiveDataListOfNotes(): LiveData<List<Note>> {
        if(listOfNotes == null) {
            listOfNotes = notedDatabase.notesDao().allNotes()
        }
        return listOfNotes!!
    }


}
