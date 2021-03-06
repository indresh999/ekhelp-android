package com.ionic.ekhelp.viewmodel.sellertype;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.ionic.ekhelp.repository.sellertype.SellerTypeRepository;
import com.ionic.ekhelp.utils.AbsentLiveData;
import com.ionic.ekhelp.utils.Utils;
import com.ionic.ekhelp.viewmodel.common.PSViewModel;
import com.ionic.ekhelp.viewobject.SellerType;
import com.ionic.ekhelp.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

public class SellerTypeViewModel extends PSViewModel {


    //region Variables

    private final LiveData<Resource<List<SellerType>>> sellerTypeListData;
    private MutableLiveData<SellerTypeViewModel.TmpDataHolder> sellerTypeListObj = new MutableLiveData<>();

    //endregion

    //region Constructors

    @Inject
    SellerTypeViewModel(SellerTypeRepository repository) {

        Utils.psLog("SellerTypeViewModel");

        sellerTypeListData = Transformations.switchMap(sellerTypeListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            Utils.psLog("SellerTypeViewModel : transmission");
            return repository.getAllSellerTypeList(obj.limit, obj.offset);
        });

    }

    //endregion

    public void setSellerTypeListObj(String limit, String offset) {
        if (!isLoading) {
            SellerTypeViewModel.TmpDataHolder tmpDataHolder = new SellerTypeViewModel.TmpDataHolder();
            tmpDataHolder.offset = offset;
            tmpDataHolder.limit = limit;
            sellerTypeListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<SellerType>>> getSellerTypeListData() {
        return sellerTypeListData;
    }


    class TmpDataHolder {
        public String limit = "";
        public String offset = "";
    }
}



