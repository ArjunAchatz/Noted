package innovations.doubleeh.com.noted.ui.notedDetail

import android.app.Activity
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * Created by arjunachatz on 2017-12-09.
 */

@Module
abstract class NotedDetailsActivityModule {

    internal abstract fun activity(activity: NotedDetailsActivity): Activity

    @Binds
    internal abstract fun activityContext(activity: Activity): Context
}