package ammu.com.privatechat.data;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


import ammu.com.privatechat.model.ModelBase;
import ammu.com.privatechat.views.base.BaseViewModel;

/**
 * If you want to share the data between components.
 * Use shared view model.
 * <p>
 * Author: Aman upadhyay
 * Email: lajesh@farabi.ae
 * Created: 8/30/2018
 * Modified: 8/30/2018
 */
public class SharedViewModel extends BaseViewModel {
    private int serviceId;
    private MutableLiveData<ModelBase> modelBaseMutableLiveData = new MutableLiveData<>();
    private ViewModel viewModel;

    public MutableLiveData<ModelBase> getModel() {
        return modelBaseMutableLiveData;
    }

    public void setModel(ModelBase modelBaseMutableLiveData) {
        this.modelBaseMutableLiveData.setValue(modelBaseMutableLiveData);
    }

    public ViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

   /* @NavigationConstants
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(@NavigationConstants int serviceId) {
        this.serviceId = serviceId;
    }*/
}