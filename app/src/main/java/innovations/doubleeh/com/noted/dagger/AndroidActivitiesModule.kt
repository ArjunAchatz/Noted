package innovations.doubleeh.com.noted.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import innovations.doubleeh.com.noted.ui.notedAdd.NotedAddActivity
import innovations.doubleeh.com.noted.ui.notedAdd.NotedAddActivityModule
import innovations.doubleeh.com.noted.ui.notedList.NotedListActivity
import innovations.doubleeh.com.noted.ui.notedList.NotedListActivityModule

/**
 * Created by arjunachatz on 2017-12-12.
 */

@Module
abstract class AndroidActivitiesModule {

    @ContributesAndroidInjector(modules = arrayOf(NotedListActivityModule::class))
    abstract fun notedListActivityInjector(): NotedListActivity

    @ContributesAndroidInjector(modules = arrayOf(NotedAddActivityModule::class))
    abstract fun NotedAddActivityInjector(): NotedAddActivity

}