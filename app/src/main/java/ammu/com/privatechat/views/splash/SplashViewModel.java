package ammu.com.privatechat.views.splash;

import javax.inject.Inject;

import ammu.com.privatechat.utils.SingleLiveEvent;
import ammu.com.privatechat.utils.firebase.FireBaseHelper;
import ammu.com.privatechat.views.base.BaseViewModel;

public class SplashViewModel extends BaseViewModel {

    SingleLiveEvent<Boolean> userLoggedIn = new SingleLiveEvent<>();

    @Inject()
    SplashViewModel() {
        if (FireBaseHelper.checkIsLoggedIn()) {
            userLoggedIn.postValue(true);
        } else {
            userLoggedIn.postValue(false);
        }
    }
}
