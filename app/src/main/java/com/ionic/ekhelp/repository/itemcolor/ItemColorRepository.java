package com.ionic.ekhelp.repository.itemcolor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.ionic.ekhelp.AppExecutors;
import com.ionic.ekhelp.Config;
import com.ionic.ekhelp.api.ApiResponse;
import com.ionic.ekhelp.api.PSApiService;
import com.ionic.ekhelp.db.PSCoreDb;
import com.ionic.ekhelp.db.ItemColorDao;
import com.ionic.ekhelp.repository.common.NetworkBoundResource;
import com.ionic.ekhelp.repository.common.PSRepository;
import com.ionic.ekhelp.utils.Utils;
import com.ionic.ekhelp.viewobject.Color;
import com.ionic.ekhelp.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ItemColorRepository extends PSRepository {
    private ItemColorDao itemColorDao;

    @Inject
    ItemColorRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, ItemColorDao itemColorDao) {

        super(psApiService, appExecutors, db);
        this.itemColorDao = itemColorDao;
    }

    public LiveData<Resource<List<Color>>> getAllItemColorList(String limit, String offset) {

        return new NetworkBoundResource<List<Color>, List<Color>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<Color> transmissionList) {
                Utils.psLog("SaveCallResult of getTransmissionList");

                try {
                    db.runInTransaction(() -> {
                        itemColorDao.deleteAllItemColor();

                        itemColorDao.insertAll(transmissionList);

                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }
            }


            @Override
            protected boolean shouldFetch(@Nullable List<Color> data) {

                return connectivity.isConnected();
            }

            @NonNull
            @Override
            protected LiveData<List<Color>> loadFromDb() {

                Utils.psLog("Load From Db (All Color)");

                return itemColorDao.getAllItemColor();

            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Color>>> createCall() {
                Utils.psLog("Call Get All Color webservice.");

                return psApiService.getColorList(Config.API_KEY, limit, offset);
            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed of Color");
            }

        }.asLiveData();
    }
}
