package com.ionic.ekhelp.viewmodel.item;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.ionic.ekhelp.repository.item.ItemRepository;
import com.ionic.ekhelp.utils.AbsentLiveData;
import com.ionic.ekhelp.utils.Utils;
import com.ionic.ekhelp.viewmodel.common.PSViewModel;
import com.ionic.ekhelp.viewobject.ItemSpecs;

import java.util.List;

import javax.inject.Inject;

public class SpecsViewModel extends PSViewModel {
    //for product specs list

    public boolean isSpecsData = false;
    private final LiveData<List<ItemSpecs>> specsListData;
    private MutableLiveData<SpecsViewModel.TmpDataHolder> specsObj = new MutableLiveData<>();

    //region Constructor

    @Inject
    SpecsViewModel(ItemRepository itemRepository) {
        //  product specs List
        specsListData = Transformations.switchMap(specsObj, (SpecsViewModel.TmpDataHolder obj) -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("product color List.");
            return itemRepository.getAllSpecificaions(obj.productId);
        });

    }

    //endregion
    //region setSpecsListObj

    public void setSpecsListObj(String productId) {

        SpecsViewModel.TmpDataHolder tmpDataHolder = new SpecsViewModel.TmpDataHolder();
        tmpDataHolder.productId = productId;
        specsObj.setValue(tmpDataHolder);

    }

    public LiveData<List<ItemSpecs>> getSpecsListData() {
        return specsListData;
    }

    //endregion

    //region Holder

    class TmpDataHolder {
        public String offset = "";
        public String productId = "";
        public Boolean isConnected = false;
    }
    //endregion
}
