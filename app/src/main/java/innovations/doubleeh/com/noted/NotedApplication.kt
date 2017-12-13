package innovations.doubleeh.com.noted

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import innovations.doubleeh.com.noted.dagger.DaggerNotedApplicationComponent
import javax.inject.Inject

/**
 * Created by arjunachatz on 2017-12-09.
 */

class NotedApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var mActivityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return mActivityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerNotedApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }
}