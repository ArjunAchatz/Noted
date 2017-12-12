package innovations.doubleeh.com.noted.ui.notedAdd

import android.app.Activity
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class NotedAddActivityModule {

    internal abstract fun activity(activity: NotedAddActivity): Activity

    @Binds
    internal abstract fun activityContext(activity: Activity): Context
}