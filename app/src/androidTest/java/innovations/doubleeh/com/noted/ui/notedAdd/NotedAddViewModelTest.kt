package innovations.doubleeh.com.noted.ui.notedAdd

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.annotation.UiThreadTest
import android.support.test.rule.UiThreadTestRule
import innovations.doubleeh.com.noted.repository.NotedDatabase
import org.junit.*

import org.junit.Assert.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Created by arjunachatz on 2017-12-13.
 */
class NotedAddViewModelTest {

    private lateinit var notedDatabase: NotedDatabase
    private lateinit var notedAddViewModel: NotedAddViewModel

    @Before
    @UiThreadTest
    fun setUp() {
        notedDatabase = Room
                .inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), NotedDatabase::class.java)
                .build()

        notedAddViewModel = NotedAddViewModel(notedDatabase)

        notedAddViewModel.getIsPriorityLiveData().getValueBlocking()
        notedAddViewModel.getTimeLiveData().getValueBlocking()
        notedAddViewModel.getDateLiveData().getValueBlocking()

        notedAddViewModel.msg = "Say hello world"
        notedAddViewModel.setIsPriorityLiveData(false)
        notedAddViewModel.setDateLiveData("13/12/17")
        notedAddViewModel.setTimeLiveData("12:13")
    }

    @After
    fun cleanUp(){
        notedDatabase.close()
    }

    @Test
    @UiThreadTest
    fun set_and_get_IsPriorityLiveData() {
        notedAddViewModel.setIsPriorityLiveData(false)
        Assert.assertFalse(notedAddViewModel.getIsPriorityLiveData().getValueBlocking() ?: true)
    }

    @Test
    @UiThreadTest
    fun set_and_get_DateLiveData() {
        notedAddViewModel.setDateLiveData("13/12/2017")
        Assert.assertEquals(notedAddViewModel.getDateLiveData().getValueBlocking() ?: "", "13/12/2017")
    }

    @Test
    @UiThreadTest
    fun set_and_get_TimeLiveData() {
        notedAddViewModel.setTimeLiveData("10:20")
        Assert.assertEquals(notedAddViewModel.getTimeLiveData().getValueBlocking() ?: "", "10:20")
    }

    @Test
    fun set_and_get_Msg() {
        notedAddViewModel.msg = "Some msg"
        Assert.assertEquals(notedAddViewModel.msg ?: "", "Some msg")
    }

    @Test
    fun save_note_test() {
        val id = notedAddViewModel.saveNote()
        Assert.assertTrue(id > 0)
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