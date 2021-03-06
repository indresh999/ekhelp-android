package com.ionic.ekhelp.viewmodel.fueltype;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.ionic.ekhelp.repository.fueltype.FuelTypeRepository;
import com.ionic.ekhelp.utils.AbsentLiveData;
import com.ionic.ekhelp.utils.Utils;
import com.ionic.ekhelp.viewmodel.common.PSViewModel;
import com.ionic.ekhelp.viewobject.FuelType;
import com.ionic.ekhelp.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

public class FuelTypeViewModel extends PSViewModel {


    //region Variables

    private final LiveData<Resource<List<FuelType>>> fuelTypeListData;
    private MutableLiveData<FuelTypeViewModel.TmpDataHolder> fuelTypeListObj = new MutableLiveData<>();

    //endregion

    //region Constructors

    @Inject
    FuelTypeViewModel(FuelTypeRepository repository) {

        Utils.psLog("FuelTypeViewModel");

        fuelTypeListData = Transformations.switchMap(fuelTypeListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            Utils.psLog("FuelTypeViewModel : transmission");
            return repository.getAllFuelTypeList(obj.limit, obj.offset);
        });

    }

    //endregion

    public void setFuelTypeListObj(String limit, String offset) {
        if (!isLoading) {
            FuelTypeViewModel.TmpDataHolder tmpDataHolder = new FuelTypeViewModel.TmpDataHolder();
            tmpDataHolder.offset = offset;
            tmpDataHolder.limit = limit;
            fuelTypeListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<FuelType>>> getFuelTypeListData() {
        return fuelTypeListData;
    }


    class TmpDataHolder {
        public String limit = "";
        public String offset = "";
    }
}


