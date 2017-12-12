package innovations.doubleeh.com.noted.repository

import android.arch.core.executor.TaskExecutor
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import org.junit.*

import org.junit.Assert.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Created by arjunachatz on 2017-12-09.
 */
class NotedDatabaseTest {

    private lateinit var notedDatabase: NotedDatabase

    @Before
    fun setUp() {
        notedDatabase = Room
                .inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), NotedDatabase::class.java)
                .build()
    }

    @After
    fun cleanUp(){
        notedDatabase.close()
    }

    @Test
    fun add_NotesTest(){
        val exampleNote = Note()
        val exampleNote2 = Note()
        exampleNote.id = notedDatabase.notesDao().insert(exampleNote)
        exampleNote2.id = notedDatabase.notesDao().insert(exampleNote2)
        Assert.assertNotEquals(exampleNote.id, 0)
        Assert.assertNotEquals(exampleNote2.id, 0)
    }

    @Test
    fun add_AndRemove_NoteTest(){
        val exampleNote = Note()
        exampleNote.id = notedDatabase.notesDao().insert(exampleNote)
        Assert.assertNotEquals(exampleNote.id, 0)
        val rowsDeleted = notedDatabase.notesDao().delete(exampleNote)
        Assert.assertEquals(rowsDeleted, 1)
    }

    @Test
    fun add_AndUpdate_NoteTest(){
        val msgOld = "Hello world"
        val msgNew = "Hello universe"
        val exampleNote = Note(msg = msgOld)

        exampleNote.id = notedDatabase.notesDao().insert(exampleNote)
        Assert.assertNotEquals(exampleNote.id, 0)

        val allNotes = notedDatabase.notesDao().allNotes().getValueBlocking()
        Assert.assertTrue(allNotes?.size ?: -1 == 1)
        Assert.assertEquals(allNotes?.get(0)?.msg ?: "", msgOld)
        Assert.assertNotEquals(allNotes?.get(0)?.msg ?: "", msgNew)

        exampleNote.msg = msgNew
        val rowsAffected = notedDatabase.notesDao().update(exampleNote)
        Assert.assertNotEquals(rowsAffected, 0)

        val allNotesAfterUpdate = notedDatabase.notesDao().allNotes().getValueBlocking()
        Assert.assertTrue(allNotesAfterUpdate?.size ?: -1 == 1)
        Assert.assertNotEquals(allNotesAfterUpdate?.get(0)?.msg ?: "", msgOld)
        Assert.assertEquals(allNotesAfterUpdate?.get(0)?.msg ?: "", msgNew)
    }

    @Throws(InterruptedException::class)
    fun <T> LiveData<T>.getValueBlocking(): T? {
        var value: T? = null
        val latch = CountDownLatch(1)
        val innerObserver = Observer<T> {
            value = it
            latch.countDown()
        }
        observeForever(innerObserver)
        latch.await(2, TimeUnit.SECONDS)
        return value
    }

}