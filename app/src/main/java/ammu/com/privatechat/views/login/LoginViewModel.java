package ammu.com.privatechat.views.login;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.Observable;

import javax.inject.Inject;

import ammu.com.privatechat.utils.HUtils;
import ammu.com.privatechat.utils.SingleLiveEvent;
import ammu.com.privatechat.utils.firebase.FireBaseHelper;
import ammu.com.privatechat.views.base.BaseViewModel;

public class LoginViewModel extends BaseViewModel {

    public ObservableField<String> userEmail = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    SingleLiveEvent<Void> register = new SingleLiveEvent<>();

    @Inject
    LoginViewModel() {

    }

    public void login() {
        if (HUtils.isNullOrEmpty(userEmail.get()) || HUtils.isNullOrEmpty(password.get())) {
            return;
        }
        FireBaseHelper.getInstance().loginUser(userEmail.get(), password.get(), new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void register() {
        register.call();
    }
}
