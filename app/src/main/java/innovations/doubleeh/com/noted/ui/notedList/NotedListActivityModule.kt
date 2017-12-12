package innovations.doubleeh.com.noted.ui.notedList

import android.app.Activity
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * Created by arjunachatz on 2017-12-09.
 */

@Module
abstract class NotedListActivityModule {

    internal abstract fun activity(activity: NotedListActivity): Activity

    @Binds
    internal abstract fun activityContext(activity: Activity): Context
}