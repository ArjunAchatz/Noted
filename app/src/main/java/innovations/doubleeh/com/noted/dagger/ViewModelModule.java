package innovations.doubleeh.com.noted.dagger;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import innovations.doubleeh.com.noted.ui.NotedViewModelFactory;
import innovations.doubleeh.com.noted.ui.notedAdd.NotedAddViewModel;

/**
 * Created by arjunachatz on 2017-12-12.
 */

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NotedAddViewModel.class)
    public abstract ViewModel bindRepoViewModel(NotedAddViewModel repoViewModel);

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(NotedViewModelFactory factory);
}