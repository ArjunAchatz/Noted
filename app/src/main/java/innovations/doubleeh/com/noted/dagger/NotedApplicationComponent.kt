package innovations.doubleeh.com.noted.dagger

import dagger.Component
import dagger.android.AndroidInjector
import innovations.doubleeh.com.noted.NotedApplication
import javax.inject.Singleton

/**
 * Created by arjunachatz on 2017-12-09.
 */

@Singleton
@Component(modules = arrayOf(NotedApplicationModule::class))
interface NotedApplicationComponent : AndroidInjector<NotedApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<NotedApplication>()

}