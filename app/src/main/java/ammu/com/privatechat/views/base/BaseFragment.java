package ammu.com.privatechat.views.base;

import android.Manifest;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import ammu.com.privatechat.data.SharedViewModel;
import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment<V extends ViewModel, D extends ViewDataBinding> extends Fragment {


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    protected V viewModel;

    protected D dataBinding;

    protected abstract Class<V> getViewModel();

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract int getBindingVariable();

    public SharedViewModel sharedViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel());
        if (null == sharedViewModel)
            sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        bindUIComponents();
        bindNavigationComponents();
    }

    protected void bindUIComponents() {
        // Bind all the view models related data here
    }

    protected void bindNavigationComponents() {
        // Bind all the navigations related data here
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        dataBinding.setVariable(getBindingVariable(), viewModel);
        return dataBinding.getRoot();
    }

    public void showProgress() {

    }

    public boolean checkPermissions(String[] perms, int requestCode) {
        ArrayList<String> permsArray = new ArrayList<>();
        boolean hasPerms = true;
        for (String perm : perms) {
            if (ContextCompat.checkSelfPermission(getActivity(), perm) != PackageManager.PERMISSION_GRANTED) {
                permsArray.add(perm);
                hasPerms = false;
            }
        }
        if (!hasPerms) {
            String[] permsString = new String[permsArray.size()];
            for (int i = 0; i < permsArray.size(); i++) {
                permsString[i] = permsArray.get(i);
            }
            ActivityCompat.requestPermissions(getActivity(), permsString, 99);
            return false;
        } else
            return true;
    }
}

