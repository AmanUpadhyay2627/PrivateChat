package ammu.com.privatechat.data.remote;

import ammu.com.privatechat.data.local.entity.UserEntity;

public interface ResponseListener {

    void onSuccess(UserEntity data);
    void onFailure(String message);
}
