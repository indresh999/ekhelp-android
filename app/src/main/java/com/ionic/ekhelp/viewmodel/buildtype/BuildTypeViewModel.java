package com.ionic.ekhelp.viewmodel.buildtype;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.ionic.ekhelp.repository.buildtype.BuildTypeRepository;
import com.ionic.ekhelp.utils.AbsentLiveData;
import com.ionic.ekhelp.utils.Utils;
import com.ionic.ekhelp.viewmodel.common.PSViewModel;
import com.ionic.ekhelp.viewobject.BuildType;
import com.ionic.ekhelp.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

public class BuildTypeViewModel extends PSViewModel {


    //region Variables

    private final LiveData<Resource<List<BuildType>>> buildTypeListData;
    private MutableLiveData<BuildTypeViewModel.TmpDataHolder> buildTypeListObj = new MutableLiveData<>();

    //endregion

    //region Constructors

    @Inject
    BuildTypeViewModel(BuildTypeRepository repository) {

        Utils.psLog("BuildTypeViewModel");

        buildTypeListData = Transformations.switchMap(buildTypeListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            Utils.psLog("BuildTypeViewModel : transmission");
            return repository.getAllBuildTypeList(obj.limit, obj.offset);
        });

    }

    //endregion

    public void setBuildTypeListObj(String limit, String offset) {
        if (!isLoading) {
            BuildTypeViewModel.TmpDataHolder tmpDataHolder = new BuildTypeViewModel.TmpDataHolder();
            tmpDataHolder.offset = offset;
            tmpDataHolder.limit = limit;
            buildTypeListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<BuildType>>> getBuildTypeListData() {
        return buildTypeListData;
    }


    class TmpDataHolder {
        public String limit = "";
        public String offset = "";
    }
}


