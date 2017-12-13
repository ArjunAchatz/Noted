package innovations.doubleeh.com.noted.dagger

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import innovations.doubleeh.com.noted.NotedApplication
import innovations.doubleeh.com.noted.repository.NotedDatabase
import javax.inject.Singleton


/**
 * Created by arjunachatz on 2017-12-09.
 */

@Module
class NotedApplicationModule {

    @Provides
    @Singleton
    fun providesNotedDatabase(app: Application): NotedDatabase {
        return Room.databaseBuilder(app, NotedDatabase::class.java, "noted_db").build()
    }

}
