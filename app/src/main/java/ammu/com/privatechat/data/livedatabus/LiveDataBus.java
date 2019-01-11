package ammu.com.privatechat.data.livedatabus;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.util.SparseArray;

/**
 * Eventbus implementation using LiveData.
 * This livedata bus should only be used for navigation events
 * Author: Lajesh D
 * Email: lajeshds2007@gmail.com
 * Created: 7/29/2018
 * Modified: 7/29/2018
 */
public final class LiveDataBus {
    private static SparseArray<EventLiveData> sSubjectMap = new SparseArray<>();
    private LiveDataBus() {
        // hidden constructor
    }
    /**
     * Get the live data or create it if it's not already in memory.
     */
    @NonNull
    private static EventLiveData getLiveData(int subjectCode) {
        EventLiveData liveData = sSubjectMap.get(subjectCode);
        if (liveData == null) {
            liveData = new EventLiveData(subjectCode);
            sSubjectMap.put(subjectCode, liveData);
        }
        return liveData;
    }
    /**
     * Subscribe to the specified subject and listen for updates on that subject.
     */
    public static void subscribe( int subject, @NonNull LifecycleOwner lifecycle, @NonNull Observer<Object> action) {
        getLiveData(subject).observe(lifecycle, action);
    }
    /**
     * Removes this subject when it has no observers.
     */
    public static void unregister( int subject) {
        sSubjectMap.remove(subject);
    }
    /**
     * Publish an object to the specified subject for all subscribers of that subject.
     */
    public static void publish( int subject, @NonNull Object message) {
        getLiveData(subject).update(message);
    }
}