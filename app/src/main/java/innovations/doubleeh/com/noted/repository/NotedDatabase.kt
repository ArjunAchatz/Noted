package innovations.doubleeh.com.noted.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by arjunachatz on 2017-12-09.
 */

@Database(entities = arrayOf(Note::class), version = 1)
abstract class NotedDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}