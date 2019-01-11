package ammu.com.privatechat.views.register;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.io.File;

import javax.inject.Inject;

import ammu.com.privatechat.model.user.UserDetail;
import ammu.com.privatechat.utils.HUtils;
import ammu.com.privatechat.utils.SingleLiveEvent;
import ammu.com.privatechat.utils.firebase.FireBaseHelper;
import ammu.com.privatechat.views.base.BaseViewModel;

public class RegisterViewModel extends BaseViewModel {

    public ObservableField<String> displayName = new ObservableField<>();
    public ObservableField<File> profileImage = new ObservableField<>();
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    SingleLiveEvent<Void> pickImage = new SingleLiveEvent<>();

    @Inject
    RegisterViewModel() {
        displayName.set("Android");
        email.set("Android@gmail.com");
        password.set("amankruy@1408");
    }

    public void pickImageFile() {
        pickImage.call();
    }

    public void register() {
        if (HUtils.isNullOrEmpty(email.get()) || HUtils.isNullOrEmpty(password.get()) || HUtils.isNullOrEmpty(displayName.get())) {

        } else {
            FireBaseHelper.getInstance().registerUser(email.get(), password.get(), new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        loginUser();
                    }
                }
            }, e -> {

            });
        }
    }

    private void loginUser() {
        FireBaseHelper.getInstance().loginUser(email.get(), password.get(),
                task -> {
                    if (task.isSuccessful()) {
                        createUserNode();
                    }
                }, e -> {

                });
    }

    private void createUserNode() {
        UserDetail userDetail = new UserDetail();
        userDetail.displayName = displayName.get();
        userDetail.email = email.get();
        userDetail.profileImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSO1Awoj9EJMFEIRo0EAs6GnR4Xsulbgefvh6XFVckdPA43yarwUw";
        FireBaseHelper.getInstance().createUser(userDetail, task -> {
            if (task.isSuccessful()) {

            }
        });
    }
}
