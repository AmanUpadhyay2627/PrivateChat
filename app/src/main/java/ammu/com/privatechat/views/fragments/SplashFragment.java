package ammu.com.privatechat.views.fragments;

import android.support.v4.app.Fragment;

import ammu.com.privatechat.R;
import ammu.com.privatechat.databinding.FragmentSplashBinding;
import ammu.com.privatechat.views.base.BaseFragment;
import ammu.com.privatechat.views.viewmodel.SplashViewModel;


public class SplashFragment extends BaseFragment<SplashViewModel, FragmentSplashBinding> {
    public static SplashFragment getInstance() {
        return new SplashFragment();
    }

    @Override
    protected Class<SplashViewModel> getViewModel() {
        return SplashViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_splash;
    }
}
