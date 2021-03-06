package com.ionic.ekhelp.viewmodel.clearalldata;

import com.ionic.ekhelp.repository.clearpackage.ClearPackageRepository;
import com.ionic.ekhelp.utils.AbsentLiveData;
import com.ionic.ekhelp.viewmodel.common.PSViewModel;
import com.ionic.ekhelp.viewobject.common.Resource;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class ClearAllDataViewModel extends PSViewModel {

    private final LiveData<Resource<Boolean>> deleteAllDataData;
    private MutableLiveData<Boolean> deleteAllDataObj = new MutableLiveData<>();


    @Inject
    public ClearAllDataViewModel(ClearPackageRepository repository) {

        deleteAllDataData = Transformations.switchMap(deleteAllDataObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            return repository.clearAllTheData();
        });
    }

    public void setDeleteAllDataObj() {

        this.deleteAllDataObj.setValue(true);
    }

    public LiveData<Resource<Boolean>> getDeleteAllDataData() {
        return deleteAllDataData;
    }


}
