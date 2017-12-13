package innovations.doubleeh.com.noted.dagger

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import innovations.doubleeh.com.noted.NotedApplication
import javax.inject.Singleton

/**
 * Created by arjunachatz on 2017-12-09.
 */

@Singleton
@Component
(modules = arrayOf(
        AndroidInjectionModule::class,
        NotedApplicationModule::class,
        AndroidActivitiesModule::class)
)
interface NotedApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): NotedApplicationComponent
    }
    fun inject(notedApplication: NotedApplication)
}