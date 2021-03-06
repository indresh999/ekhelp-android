package com.ionic.ekhelp.viewmodel.itemcondition;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.ionic.ekhelp.repository.itemcondition.ItemConditionRepository;
import com.ionic.ekhelp.utils.AbsentLiveData;
import com.ionic.ekhelp.utils.Utils;
import com.ionic.ekhelp.viewmodel.common.PSViewModel;
import com.ionic.ekhelp.viewobject.ItemCondition;
import com.ionic.ekhelp.viewobject.common.Resource;
import com.ionic.ekhelp.viewobject.holder.ManufacturerParameterHolder;

import java.util.List;

import javax.inject.Inject;

public class ItemConditionViewModel extends PSViewModel {


    //region Variables

    private final LiveData<Resource<List<ItemCondition>>> itemTypeListData;
    private MutableLiveData<ItemConditionViewModel.TmpDataHolder> itemTypeListObj = new MutableLiveData<>();

    public ManufacturerParameterHolder manufacturerParameterHolder = new ManufacturerParameterHolder();

    //endregion

    //region Constructors

    @Inject
    ItemConditionViewModel(ItemConditionRepository repository) {

        Utils.psLog("ItemConditionViewModel");

        itemTypeListData = Transformations.switchMap(itemTypeListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            Utils.psLog("ItemConditionViewModel : categories");
            return repository.getAllItemConditionList(obj.limit, obj.offset);
        });

    }

    //endregion

    public void setItemConditionListObj(String limit, String offset) {
        if (!isLoading) {
            TmpDataHolder tmpDataHolder = new TmpDataHolder();
            tmpDataHolder.offset = offset;
            tmpDataHolder.limit = limit;
            itemTypeListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<ItemCondition>>> getItemConditionListData() {
        return itemTypeListData;
    }


    class TmpDataHolder {
        public String limit = "";
        public String offset = "";
        public String cityId = "";
    }
}
