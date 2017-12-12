package innovations.doubleeh.com.noted.ui.notedDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dagger.android.AndroidInjection
import innovations.doubleeh.com.noted.R
import innovations.doubleeh.com.noted.repository.NotedDatabase
import javax.inject.Inject

class NotedDetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var notedDatabase: NotedDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_noted_details)
    }

}
