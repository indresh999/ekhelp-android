package com.ionic.ekhelp.viewmodel.itempricetype;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.ionic.ekhelp.repository.itempricetype.ItemPriceTypeRepository;
import com.ionic.ekhelp.utils.AbsentLiveData;
import com.ionic.ekhelp.utils.Utils;
import com.ionic.ekhelp.viewmodel.common.PSViewModel;
import com.ionic.ekhelp.viewobject.ItemPriceType;
import com.ionic.ekhelp.viewobject.common.Resource;
import com.ionic.ekhelp.viewobject.holder.ManufacturerParameterHolder;
import com.ionic.ekhelp.viewobject.holder.ItemParameterHolder;

import java.util.List;

import javax.inject.Inject;

public class ItemPriceTypeViewModel extends PSViewModel {


    //region Variables

    private final LiveData<Resource<List<ItemPriceType>>> itemTypeListData;
    private MutableLiveData<ItemPriceTypeViewModel.TmpDataHolder> itemTypeListObj = new MutableLiveData<>();

//    private final LiveData<Resource<Boolean>> nextPageLoadingStateData;
//    private MutableLiveData<ItemPriceTypeViewModel.TmpDataHolder> nextPageLoadingStateObj = new MutableLiveData<>();

    public ItemParameterHolder productParameterHolder = new ItemParameterHolder();

    public ManufacturerParameterHolder manufacturerParameterHolder = new ManufacturerParameterHolder();

    //endregion

    //region Constructors

    @Inject
    ItemPriceTypeViewModel(ItemPriceTypeRepository repository) {

        Utils.psLog("ItemPriceTypeViewModel");

        itemTypeListData = Transformations.switchMap(itemTypeListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            Utils.psLog("ItemPriceTypeViewModel : categories");
            return repository.getAllItemPriceTypeList(obj.limit, obj.offset);
        });

//        nextPageLoadingStateData = Transformations.switchMap(nextPageLoadingStateObj, obj -> {
//            if (obj == null) {
//                return AbsentLiveData.create();
//            }
//
//            Utils.psLog("Category List.");
//            return repository.getNextSearchCityManufacturer(obj.limit, obj.offset);
//        });

    }

    //endregion

    public void setItemPriceTypeListObj(String limit, String offset) {
        if (!isLoading) {
            TmpDataHolder tmpDataHolder = new TmpDataHolder();
            tmpDataHolder.offset = offset;
            tmpDataHolder.limit = limit;
            itemTypeListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<ItemPriceType>>> getItemPriceTypeListData() {
        return itemTypeListData;
    }

    //Get Latest Category Next Page
//    public void setNextPageLoadingStateObj(String limit, String offset) {
//
//        if (!isLoading) {
//            TmpDataHolder tmpDataHolder = new TmpDataHolder();
//            tmpDataHolder.offset = offset;
//            tmpDataHolder.limit = limit;
//            nextPageLoadingStateObj.setValue(tmpDataHolder);
//
//            // start loading
//            setLoadingState(true);
//        }
//    }
//
//    public LiveData<Resource<Boolean>> getNextPageLoadingStateData() {
//        return nextPageLoadingStateData;
//    }


    class TmpDataHolder {
        public String limit = "";
        public String offset = "";
        public String cityId = "";
    }
}
