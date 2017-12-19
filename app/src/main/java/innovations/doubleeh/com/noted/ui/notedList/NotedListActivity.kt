package innovations.doubleeh.com.noted.ui.notedList

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import dagger.android.AndroidInjection
import innovations.doubleeh.com.noted.R
import innovations.doubleeh.com.noted.ui.notedAdd.NotedAddActivity
import innovations.doubleeh.com.noted.ui.notedDetail.NotedDetailsActivity
import kotlinx.android.synthetic.main.content_noted_list.*
import javax.inject.Inject


class NotedListActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    val itemClickFn = { noteID: Long ->
        val intent = Intent(this, NotedDetailsActivity::class.java)
        intent.putExtra("id", noteID)
        startActivity(intent)
    }

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

        ViewModelProviders.of(this, factory).get(NotedListViewModel::class.java)
                .getLiveDataListOfNotes()
                .observe(this, Observer {
                    if(notesList.adapter == null) {
                        notesList.adapter = NotedListAdapter(ArrayList(it), itemClickFn)
                    } else {
                        (notesList.adapter as NotedListAdapter).handleNewData(it ?: ArrayList())
                    }
                })
    }

}
