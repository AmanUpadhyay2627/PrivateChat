package ammu.com.privatechat.views.login;

import android.support.v4.app.Fragment;

import ammu.com.privatechat.BR;
import ammu.com.privatechat.R;
import ammu.com.privatechat.common.NavigationConstants;
import ammu.com.privatechat.data.livedatabus.LiveDataBus;
import ammu.com.privatechat.databinding.FragmentLoginBinding;
import ammu.com.privatechat.views.base.BaseFragment;

public class LoginFragment extends BaseFragment<LoginViewModel, FragmentLoginBinding> {
    public static Fragment getInstance() {
        return new LoginFragment();
    }

    @Override
    protected Class<LoginViewModel> getViewModel() {
        return LoginViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    protected int getBindingVariable() {
        return BR.loginViewModel;
    }

    @Override
    protected void bindUIComponents() {

    }

    @Override
    protected void bindNavigationComponents() {
        viewModel.register.observe(this, aVoid -> {
            LiveDataBus.publish(NavigationConstants.LAUNCH_SCREEN_SUBJECT, NavigationConstants.LAUNCH_REGISTER);
        });
    }
}
