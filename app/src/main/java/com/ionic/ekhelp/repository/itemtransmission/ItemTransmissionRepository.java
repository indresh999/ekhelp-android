package com.ionic.ekhelp.repository.itemtransmission;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.ionic.ekhelp.AppExecutors;
import com.ionic.ekhelp.Config;
import com.ionic.ekhelp.api.ApiResponse;
import com.ionic.ekhelp.api.PSApiService;
import com.ionic.ekhelp.db.PSCoreDb;
import com.ionic.ekhelp.db.TransmissionDao;
import com.ionic.ekhelp.repository.common.NetworkBoundResource;
import com.ionic.ekhelp.repository.common.PSRepository;
import com.ionic.ekhelp.utils.Utils;
import com.ionic.ekhelp.viewobject.Transmission;
import com.ionic.ekhelp.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ItemTransmissionRepository extends PSRepository {
    private TransmissionDao transmissionDao;

    @Inject
    ItemTransmissionRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, TransmissionDao transmissionDao) {

        super(psApiService, appExecutors, db);
        this.transmissionDao = transmissionDao;
    }

    public LiveData<Resource<List<Transmission>>> getAllTransmissionList(String limit, String offset) {

        return new NetworkBoundResource<List<Transmission>, List<Transmission>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<Transmission> transmissionList) {
                Utils.psLog("SaveCallResult of getTransmissionList");

                try {
                    db.runInTransaction(() -> {
                        transmissionDao.deleteAllItemPriceType();

                        transmissionDao.insertAll(transmissionList);

                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }
            }


            @Override
            protected boolean shouldFetch(@Nullable List<Transmission> data) {

                return connectivity.isConnected();
            }

            @NonNull
            @Override
            protected LiveData<List<Transmission>> loadFromDb() {

                Utils.psLog("Load From Db (All Transmission)");

                return transmissionDao.getAllItemPriceType();

            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Transmission>>> createCall() {
                Utils.psLog("Call Get All Transmission webservice.");

                return psApiService.getTransmissionList(Config.API_KEY, limit, offset);
            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed of Transmission");
            }

        }.asLiveData();
    }
}