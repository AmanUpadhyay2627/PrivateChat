package ammu.com.privatechat.di.components;

import android.app.Application;

import javax.inject.Singleton;

import ammu.com.privatechat.PrivateChat;
import ammu.com.privatechat.di.builder.ActivityBuilderModule;
import ammu.com.privatechat.di.module.AppModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * The main application component which initializes all the dependent modules
 * Author: Lajesh D
 * Email: lajeshds2007@gmail.com
 * Created: 7/24/2018
 * Modified: 7/24/2018
 */
@Singleton
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(PrivateChat privateChat);
}