package ammu.com.privatechat.views.splash;

import ammu.com.privatechat.BR;
import ammu.com.privatechat.R;
import ammu.com.privatechat.common.NavigationConstants;
import ammu.com.privatechat.data.livedatabus.LiveDataBus;
import ammu.com.privatechat.databinding.FragmentSplashBinding;
import ammu.com.privatechat.views.base.BaseFragment;


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

    @Override
    protected int getBindingVariable() {
        return BR.splashViewModel;
    }

    @Override
    protected void bindNavigationComponents() {
        viewModel.userLoggedIn.observe(this, isLoggedIn -> {
            if (isLoggedIn) {
                // TODO navigate to main dashboard
            } else {
                goToLoginScreen();
            }
        });
    }

    private void goToLoginScreen() {
        LiveDataBus.publish(NavigationConstants.LAUNCH_SCREEN_SUBJECT, NavigationConstants.LAUNCH_LOGIN);
    }

    @Override
    protected void bindUIComponents() {
        super.bindUIComponents();
    }
}
