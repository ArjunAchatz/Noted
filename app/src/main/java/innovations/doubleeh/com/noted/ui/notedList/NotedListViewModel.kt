package innovations.doubleeh.com.noted.ui.notedList

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import innovations.doubleeh.com.noted.repository.Note
import innovations.doubleeh.com.noted.repository.NotedDatabase

/**
 * Created by arjunachatz on 2017-12-11.
 */

class NotedListViewModel : ViewModel() {

    private var listOfNotes: LiveData<List<Note>>? = null

    fun getLiveDataListOfNotes(notedDatabase: NotedDatabase): LiveData<List<Note>> {
        if(listOfNotes == null) {
            listOfNotes = notedDatabase.notesDao().allNotes()
        }
        return listOfNotes!!
    }


}
