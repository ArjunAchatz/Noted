package innovations.doubleeh.com.noted.ui.notedList

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import innovations.doubleeh.com.noted.repository.Note
import innovations.doubleeh.com.noted.repository.NotedDatabase
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by arjunachatz on 2017-12-11.
 */

class NotedListViewModel @Inject constructor(val notedDatabase: NotedDatabase) : ViewModel() {

    private var listOfNotes: LiveData<List<Note>>? = null

    fun getLiveDataListOfNotes(): LiveData<List<Note>> {
        if (listOfNotes == null) {
            listOfNotes = notedDatabase.notesDao().allNotes()
        }
        return listOfNotes!!
    }

    fun deleteNote(note: Note) {
        Observable
                .just(0)
                .observeOn(Schedulers.io())
                .subscribe(object: Observer<Int>{
                    override fun onError(e: Throwable) {}
                    override fun onSubscribe(d: Disposable) {}
                    override fun onComplete() {}
                    override fun onNext(t: Int) {
                        notedDatabase.notesDao().delete(note)
                    }

                })
    }

    fun insertNote(note: Note) {
        Observable
                .just(0)
                .observeOn(Schedulers.io())
                .subscribe(object: Observer<Int>{
                    override fun onError(e: Throwable) {}
                    override fun onSubscribe(d: Disposable) {}
                    override fun onComplete() {}
                    override fun onNext(t: Int) {
                        notedDatabase.notesDao().insert(note)
                    }
                })
    }


}
