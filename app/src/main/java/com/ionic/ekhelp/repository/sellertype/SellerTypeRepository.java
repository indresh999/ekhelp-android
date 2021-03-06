package com.ionic.ekhelp.repository.sellertype;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.ionic.ekhelp.AppExecutors;
import com.ionic.ekhelp.Config;
import com.ionic.ekhelp.api.ApiResponse;
import com.ionic.ekhelp.api.PSApiService;
import com.ionic.ekhelp.db.SellerTypeDao;
import com.ionic.ekhelp.db.PSCoreDb;
import com.ionic.ekhelp.repository.common.NetworkBoundResource;
import com.ionic.ekhelp.repository.common.PSRepository;
import com.ionic.ekhelp.utils.Utils;
import com.ionic.ekhelp.viewobject.SellerType;
import com.ionic.ekhelp.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SellerTypeRepository extends PSRepository {
    private SellerTypeDao sellerTypeDao;

    @Inject
    SellerTypeRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, SellerTypeDao sellerTypeDao) {

        super(psApiService, appExecutors, db);
        this.sellerTypeDao = sellerTypeDao;
    }

    public LiveData<Resource<List<SellerType>>> getAllSellerTypeList(String limit, String offset) {

        return new NetworkBoundResource<List<SellerType>, List<SellerType>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<SellerType> transmissionList) {
                Utils.psLog("SaveCallResult of getTransmissionList");

                try {
                    db.runInTransaction(() -> {
                        sellerTypeDao.deleteAllSellerType();

                        sellerTypeDao.insertAll(transmissionList);

                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }
            }


            @Override
            protected boolean shouldFetch(@Nullable List<SellerType> data) {

                return connectivity.isConnected();
            }

            @NonNull
            @Override
            protected LiveData<List<SellerType>> loadFromDb() {

                Utils.psLog("Load From Db (All SellerType)");

                return sellerTypeDao.getAllSellerType();

            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<SellerType>>> createCall() {
                Utils.psLog("Call Get All SellerType webservice.");

                return psApiService.getSellerTypeList(Config.API_KEY, limit, offset);
            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed of SellerType");
            }

        }.asLiveData();
    }
}


