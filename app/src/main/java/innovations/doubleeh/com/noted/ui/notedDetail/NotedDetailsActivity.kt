package innovations.doubleeh.com.noted.ui.notedDetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import dagger.android.AndroidInjection
import innovations.doubleeh.com.noted.R
import javax.inject.Inject

class NotedDetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_noted_details)

        val noteID = intent.getLongExtra("id", -1)

        if (noteID <= 0) {
            giveToast("Note not found")
            finish()
        }

        ViewModelProviders
                .of(this, factory)
                .get(NotedDetailViewModel::class.java)
                .getNoteDetails(noteID)
                .observe(this, Observer {
                    giveToast(it?.msg)
                })
    }

    fun giveToast(msg: String?) {
        msg
                .takeIf { msg != null }
                ?.run { Toast.makeText(this@NotedDetailsActivity, msg, Toast.LENGTH_LONG).show() }

    }

}
