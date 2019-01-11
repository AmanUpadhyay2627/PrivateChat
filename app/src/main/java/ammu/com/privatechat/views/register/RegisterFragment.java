package ammu.com.privatechat.views.register;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.io.File;

import ammu.com.privatechat.BR;
import ammu.com.privatechat.R;
import ammu.com.privatechat.common.Constants;
import ammu.com.privatechat.databinding.FragmentRegisterBinding;
import ammu.com.privatechat.utils.HUtils;
import ammu.com.privatechat.views.base.BaseActivity;
import ammu.com.privatechat.views.base.BaseFragment;

import static android.app.Activity.RESULT_OK;

public class RegisterFragment extends BaseFragment<RegisterViewModel, FragmentRegisterBinding> {
    @Override
    protected Class<RegisterViewModel> getViewModel() {
        return RegisterViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_register;
    }

    @Override
    protected int getBindingVariable() {
        return BR.registerViewModel;
    }

    public static RegisterFragment getInstance() {
        return new RegisterFragment();
    }

    @Override
    protected void bindNavigationComponents() {
        viewModel.pickImage.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable Void aVoid) {

                if(checkPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA}, 1)){
                    HUtils.selectGalleryImage(RegisterFragment.this);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.SELECT_GALLERY_IMAGE && resultCode == RESULT_OK) {
            File filePath = new File(HUtils.getRealPath(getContext(),data.getData()));
            viewModel.profileImage.set(filePath);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
