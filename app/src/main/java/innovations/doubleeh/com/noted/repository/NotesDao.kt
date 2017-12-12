package innovations.doubleeh.com.noted.repository

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

/**
 * Created by arjunachatz on 2017-12-09.
 */

@Dao
interface NotesDao {
    @Insert
    fun insert(note: Note): Long
    @Update
    fun update(note: Note): Int
    @Delete
    fun delete(note: Note): Int

    @Query("SELECT * FROM $NotesTable")
    fun allNotes(): LiveData<List<Note>>
}