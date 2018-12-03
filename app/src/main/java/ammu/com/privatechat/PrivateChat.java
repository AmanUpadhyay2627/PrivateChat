package ammu.com.privatechat;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import ammu.com.privatechat.di.components.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class PrivateChat extends Application implements HasActivityInjector {
    private static PrivateChat sInstance;


    public static PrivateChat getAppContext() {
        return sInstance;
    }



    private static synchronized void setInstance(PrivateChat app) {
        sInstance = app;
    }
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        setInstance(this);
    }

    private void initializeComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }
}

