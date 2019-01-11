package ammu.com.privatechat.views.activities;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import ammu.com.privatechat.R;
import ammu.com.privatechat.common.NavigationConstants;
import ammu.com.privatechat.data.livedatabus.LiveDataBus;
import ammu.com.privatechat.databinding.ActivityMainBinding;
import ammu.com.privatechat.model.user.UserDetail;
import ammu.com.privatechat.utils.FragmentUtils;
import ammu.com.privatechat.utils.firebase.FireBaseHelper;
import ammu.com.privatechat.views.base.BaseActivity;
import ammu.com.privatechat.views.login.LoginFragment;
import ammu.com.privatechat.views.register.RegisterFragment;
import ammu.com.privatechat.views.splash.SplashFragment;

import static ammu.com.privatechat.common.NavigationConstants.LAUNCH_LOGIN;
import static ammu.com.privatechat.common.NavigationConstants.LAUNCH_REGISTER;
import static ammu.com.privatechat.common.NavigationConstants.LAUNCH_SCREEN_SUBJECT;

public class LaunchScreen extends BaseActivity<ActivityMainBinding> {

    private DatabaseReference mDatabase;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentUtils.replaceFragment(this, SplashFragment.getInstance(), R.id.fragContainer, false,
                FragmentUtils.TRANSITION_NONE);
        LiveDataBus.subscribe(LAUNCH_SCREEN_SUBJECT, this, observer);
    }

    Observer observer = (Observer<Object>) object -> {
        if (object instanceof Integer) {
            int value = (int) object;
            switch (value) {
                case LAUNCH_LOGIN:
                    FragmentUtils.replaceFragment(LaunchScreen.this, LoginFragment.getInstance(), R.id.fragContainer, false,
                            FragmentUtils.TRANSITION_NONE);
                    break;
                case LAUNCH_REGISTER:
                    FragmentUtils.replaceFragment(LaunchScreen.this, RegisterFragment.getInstance(), R.id.fragContainer, false,
                            FragmentUtils.TRANSITION_NONE);
                    break;
            }
        }
    };
}
