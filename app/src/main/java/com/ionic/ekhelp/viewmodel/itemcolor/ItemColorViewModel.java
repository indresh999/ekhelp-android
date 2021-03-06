package com.ionic.ekhelp.viewmodel.itemcolor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.ionic.ekhelp.repository.itemcolor.ItemColorRepository;
import com.ionic.ekhelp.utils.AbsentLiveData;
import com.ionic.ekhelp.utils.Utils;
import com.ionic.ekhelp.viewmodel.common.PSViewModel;
import com.ionic.ekhelp.viewobject.Color;
import com.ionic.ekhelp.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

public class ItemColorViewModel extends PSViewModel {


    //region Variables

    private final LiveData<Resource<List<Color>>> itemColorListData;
    private MutableLiveData<ItemColorViewModel.TmpDataHolder> itemColorListObj = new MutableLiveData<>();

    //endregion

    //region Constructors

    @Inject
    ItemColorViewModel(ItemColorRepository repository) {

        Utils.psLog("ItemColorViewModel");

        itemColorListData = Transformations.switchMap(itemColorListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            Utils.psLog("ItemColorViewModel : transmission");
            return repository.getAllItemColorList(obj.limit, obj.offset);
        });

    }

    //endregion

    public void setItemColorListObj(String limit, String offset) {
        if (!isLoading) {
            ItemColorViewModel.TmpDataHolder tmpDataHolder = new ItemColorViewModel.TmpDataHolder();
            tmpDataHolder.offset = offset;
            tmpDataHolder.limit = limit;
            itemColorListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<Color>>> getItemColorListData() {
        return itemColorListData;
    }


    class TmpDataHolder {
        public String limit = "";
        public String offset = "";
    }
}

