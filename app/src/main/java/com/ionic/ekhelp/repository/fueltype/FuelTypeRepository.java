package com.ionic.ekhelp.repository.fueltype;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.ionic.ekhelp.AppExecutors;
import com.ionic.ekhelp.Config;
import com.ionic.ekhelp.api.ApiResponse;
import com.ionic.ekhelp.api.PSApiService;
import com.ionic.ekhelp.db.FuelTypeDao;
import com.ionic.ekhelp.db.PSCoreDb;
import com.ionic.ekhelp.repository.common.NetworkBoundResource;
import com.ionic.ekhelp.repository.common.PSRepository;
import com.ionic.ekhelp.utils.Utils;
import com.ionic.ekhelp.viewobject.FuelType;
import com.ionic.ekhelp.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FuelTypeRepository extends PSRepository {
    private FuelTypeDao fuelTypeDao;

    @Inject
    FuelTypeRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, FuelTypeDao fuelTypeDao) {

        super(psApiService, appExecutors, db);
        this.fuelTypeDao = fuelTypeDao;
    }

    public LiveData<Resource<List<FuelType>>> getAllFuelTypeList(String limit, String offset) {

        return new NetworkBoundResource<List<FuelType>, List<FuelType>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<FuelType> transmissionList) {
                Utils.psLog("SaveCallResult of getTransmissionList");

                try {
                    db.runInTransaction(() -> {
                        fuelTypeDao.deleteAllItemFuel();

                        fuelTypeDao.insertAll(transmissionList);

                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }
            }


            @Override
            protected boolean shouldFetch(@Nullable List<FuelType> data) {

                return connectivity.isConnected();
            }

            @NonNull
            @Override
            protected LiveData<List<FuelType>> loadFromDb() {

                Utils.psLog("Load From Db (All FuelType)");

                return fuelTypeDao.getAllItemFuel();

            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<FuelType>>> createCall() {
                Utils.psLog("Call Get All FuelType webservice.");

                return psApiService.getFuelTypeList(Config.API_KEY, limit, offset);
            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed of FuelType");
            }

        }.asLiveData();
    }
}
