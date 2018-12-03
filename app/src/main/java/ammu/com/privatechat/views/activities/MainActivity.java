package ammu.com.privatechat.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ammu.com.privatechat.R;
import ammu.com.privatechat.databinding.ActivityMainBinding;
import ammu.com.privatechat.utils.FragmentUtils;
import ammu.com.privatechat.views.base.BaseActivity;
import ammu.com.privatechat.views.fragments.SplashFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding>{

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentUtils.replaceFragment(this, SplashFragment.getInstance(),R.id.fragContainer,false, FragmentUtils.TRANSITION_NONE);
    }
}
