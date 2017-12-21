package innovations.doubleeh.com.noted.ui.notedList

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import dagger.android.AndroidInjection
import innovations.doubleeh.com.noted.R
import innovations.doubleeh.com.noted.repository.Note
import innovations.doubleeh.com.noted.ui.notedAdd.NotedAddActivity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_noted_list.*
import kotlinx.android.synthetic.main.content_noted_list.*
import javax.inject.Inject


class NotedListActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var notedListViewModel:NotedListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_noted_list)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            startActivity(Intent(this@NotedListActivity, NotedAddActivity::class.java))
        }
        notesList.layoutManager = LinearLayoutManager(this)

        notedListViewModel = ViewModelProviders.of(this, factory).get(NotedListViewModel::class.java)
        notedListViewModel
                .getLiveDataListOfNotes()
                .observe(this, Observer {
                    if (notesList.adapter == null) {
                        notesList.adapter = NotedListAdapter(ArrayList(it))
                    } else {
                        (notesList.adapter as NotedListAdapter).handleNewData(it ?: ArrayList())
                    }
                })

        ItemTouchHelper(RecyclerViewItemSwipeHelper({ position ->
            if (position != null) {
                val noteRemoved = (notesList.adapter as NotedListAdapter).listOfNotes.get(position)
                showUndoRemoveSnackBar(noteRemoved)
                Observable.just(0).observeOn(Schedulers.io()).subscribe {
                    notedListViewModel.notedDatabase.notesDao().delete(noteRemoved)
                }
            }
        })).attachToRecyclerView(notesList)
    }

    private fun showUndoRemoveSnackBar(noteRemoved: Note) {
        val snackbar = Snackbar.make(notedListCoordinatorLayout, "Deleted note", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Undo", {
            Observable.just(0).observeOn(Schedulers.io()).subscribe {
                notedListViewModel.notedDatabase.notesDao().insert(noteRemoved)
            }
        })
        snackbar.show()
    }

}
