package innovations.doubleeh.com.noted.ui.notedList

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View

import dagger.android.AndroidInjection
import innovations.doubleeh.com.noted.R
import innovations.doubleeh.com.noted.repository.NotedDatabase
import innovations.doubleeh.com.noted.ui.notedAdd.NotedAddActivity
import innovations.doubleeh.com.noted.ui.notedDetail.NotedDetailsActivity
import javax.inject.Inject

class NotedListActivity : AppCompatActivity() {

    @Inject
    lateinit var notedDatabase: NotedDatabase

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

        ViewModelProviders.of(this).get(NotedListViewModel::class.java)
                .getLiveDataListOfNotes(notedDatabase)
                .observe(this, Observer {
                    Log.d("asdf", "Length on list is now ${it?.size}")
                })
    }

}
