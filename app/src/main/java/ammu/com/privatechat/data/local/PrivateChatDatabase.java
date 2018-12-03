package ammu.com.privatechat.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ammu.com.privatechat.data.local.dao.UserDao;
import ammu.com.privatechat.data.local.entity.UserEntity;

/**
 * File Description
 * <p>
 * Author: Lajesh D
 * Email: lajeshds2007@gmail.com
 * Created: 7/24/2018
 * Modified: 7/24/2018
 */
@Database(entities = {UserEntity.class}, version = 2)
public abstract class PrivateChatDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}