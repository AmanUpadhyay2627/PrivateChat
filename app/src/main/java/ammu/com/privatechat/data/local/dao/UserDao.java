package ammu.com.privatechat.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ammu.com.privatechat.data.local.entity.UserEntity;

/**
 * File Description
 * <p>
 * Author: Lajesh D
 * Email: lajeshds2007@gmail.com
 * Created: 7/24/2018
 * Modified: 7/24/2018
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<UserEntity>> loadUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveUser(List<UserEntity> articleEntities);

}
