package innovations.doubleeh.com.noted.dagger

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import innovations.doubleeh.com.noted.NotedViewModelFactory
import innovations.doubleeh.com.noted.ui.notedAdd.NotedAddViewModel

/**
 * Created by arjunachatz on 2017-12-12.
 */

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NotedAddViewModel::class)
    abstract fun bindRepoViewModel(repoViewModel: NotedAddViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: NotedViewModelFactory): ViewModelProvider.Factory
}