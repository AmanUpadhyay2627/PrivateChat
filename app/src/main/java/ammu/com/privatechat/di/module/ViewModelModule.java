package ammu.com.privatechat.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import ammu.com.privatechat.views.login.LoginViewModel;
import ammu.com.privatechat.views.register.RegisterViewModel;
import ammu.com.privatechat.views.splash.SplashViewModel;
import ammu.com.privatechat.views.viewmodel.ViewModelFactory;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Alloes us to inject dependencies via constructor injection
 * <p>
 * Author: Lajesh D
 * Email: lajeshds2007@gmail.com
 * Created: 7/24/2018
 * Modified: 7/24/2018
 */
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsSplashViewModel(SplashViewModel splashViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsLoginViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsRegisterViewModel(RegisterViewModel registerViewModel);


    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
