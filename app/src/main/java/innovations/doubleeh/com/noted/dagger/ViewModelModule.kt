package innovations.doubleeh.com.noted.dagger

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import innovations.doubleeh.com.noted.NotedViewModelFactory
import innovations.doubleeh.com.noted.ui.notedAdd.NotedAddViewModel
import innovations.doubleeh.com.noted.ui.notedDetail.NotedDetailViewModel
import innovations.doubleeh.com.noted.ui.notedList.NotedListViewModel

/**
 * Created by arjunachatz on 2017-12-12.
 */

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NotedAddViewModel::class)
    abstract fun bindNotedAddViewModel(notedAddViewModel: NotedAddViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NotedDetailViewModel::class)
    abstract fun bindNotedDetailViewModel(notedDetailViewModel: NotedDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NotedListViewModel::class)
    abstract fun bindNotedListViewModel(notedListViewModel: NotedListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: NotedViewModelFactory): ViewModelProvider.Factory
}