package ammu.com.privatechat.data.livedatabus;

import android.arch.lifecycle.Observer;

import ammu.com.privatechat.utils.SingleLiveEvent;

/**
 * This class represents event model class which is lifecycle aware
 * <p>
 * Author: Lajesh D
 * Email: lajeshds2007@gmail.com
 * Created: 7/29/2018
 * Modified: 7/29/2018
 */
public class EventLiveData extends SingleLiveEvent<Object> {
    private final int mSubject;
    public EventLiveData(int subject) {
        mSubject = subject;
    }
    public void update(Object object) {
        postValue(object);
    }
    @Override
    public void removeObserver(Observer<Object> observer) {
        super.removeObserver(observer);
        if (!hasObservers()) {
            LiveDataBus.unregister(mSubject);
        }
    }
}