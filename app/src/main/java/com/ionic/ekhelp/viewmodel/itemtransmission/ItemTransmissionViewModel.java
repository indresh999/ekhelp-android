package com.ionic.ekhelp.viewmodel.itemtransmission;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.ionic.ekhelp.repository.itemtransmission.ItemTransmissionRepository;
import com.ionic.ekhelp.utils.AbsentLiveData;
import com.ionic.ekhelp.utils.Utils;
import com.ionic.ekhelp.viewmodel.common.PSViewModel;
import com.ionic.ekhelp.viewobject.Transmission;
import com.ionic.ekhelp.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

public class ItemTransmissionViewModel extends PSViewModel {


    //region Variables

    private final LiveData<Resource<List<Transmission>>> transmissionListData;
    private MutableLiveData<ItemTransmissionViewModel.TmpDataHolder> transmissionListObj = new MutableLiveData<>();
    
    //endregion

    //region Constructors

    @Inject
    ItemTransmissionViewModel(ItemTransmissionRepository repository) {

        Utils.psLog("ItemTransmissionViewModel");

        transmissionListData = Transformations.switchMap(transmissionListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            Utils.psLog("ItemTransmissionViewModel : transmission");
            return repository.getAllTransmissionList(obj.limit, obj.offset);
        });
        
    }

    //endregion

    public void setTransmissionListObj(String limit, String offset) {
        if (!isLoading) {
            ItemTransmissionViewModel.TmpDataHolder tmpDataHolder = new ItemTransmissionViewModel.TmpDataHolder();
            tmpDataHolder.offset = offset;
            tmpDataHolder.limit = limit;
            transmissionListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<Transmission>>> getTransmissionListData() {
        return transmissionListData;
    }
    

    class TmpDataHolder {
        public String limit = "";
        public String offset = "";
    }
}
