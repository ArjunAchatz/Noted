package innovations.doubleeh.com.noted.repository

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by arjunachatz on 2017-12-09.
 */

@Module
class NotedDatabaseModule {

    @Provides
    @Singleton
    fun providesNotedDatabase(context: Application): NotedDatabase {
        return Room.databaseBuilder(context, NotedDatabase::class.java, "noted_db").build()
    }
}