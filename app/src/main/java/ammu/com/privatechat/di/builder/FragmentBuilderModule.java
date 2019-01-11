package ammu.com.privatechat.di.builder;

import ammu.com.privatechat.views.login.LoginFragment;
import ammu.com.privatechat.views.register.RegisterFragment;
import ammu.com.privatechat.views.splash.SplashFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This builder provides android injector service to fragments
 * Author: Lajesh D
 * Email: lajeshds2007@gmail.com
 * Created: 7/24/2018
 * Modified: 7/24/2018
 */
@Module
public abstract class FragmentBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract SplashFragment contributeSplashFragment();

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract LoginFragment contributeLoginFragment();

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract RegisterFragment contributeRegisterFragment();
}
