package innovations.doubleeh.com.noted.dagger

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import innovations.doubleeh.com.noted.NotedApplication
import innovations.doubleeh.com.noted.repository.NotedDatabaseModule
import innovations.doubleeh.com.noted.ui.notedAdd.NotedAddActivity
import innovations.doubleeh.com.noted.ui.notedAdd.NotedAddActivityModule
import innovations.doubleeh.com.noted.ui.notedDetail.NotedDetailsActivityModule
import innovations.doubleeh.com.noted.ui.notedDetail.NotedDetailsActivity
import innovations.doubleeh.com.noted.ui.notedList.NotedListActivity
import innovations.doubleeh.com.noted.ui.notedList.NotedListActivityModule

/**
 * Created by arjunachatz on 2017-12-09.
 */

@Module(includes = arrayOf(AndroidInjectionModule::class, NotedDatabaseModule::class))
internal abstract class NotedApplicationModule {

    @ContributesAndroidInjector(modules = arrayOf(NotedDetailsActivityModule::class))
    internal abstract fun notedDetailsActivityInjector(): NotedDetailsActivity


    @ContributesAndroidInjector(modules = arrayOf(NotedListActivityModule::class))
    internal abstract fun notedListActivityInjector(): NotedListActivity


    @ContributesAndroidInjector(modules = arrayOf(NotedAddActivityModule::class))
    internal abstract fun NotedAddActivityInjector(): NotedAddActivity

    @Binds
    internal abstract fun application(app: NotedApplication): Application

}
