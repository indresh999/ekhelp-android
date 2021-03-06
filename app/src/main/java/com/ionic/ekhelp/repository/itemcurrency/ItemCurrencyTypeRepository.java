package com.ionic.ekhelp.repository.itemcurrency;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.ionic.ekhelp.AppExecutors;
import com.ionic.ekhelp.Config;
import com.ionic.ekhelp.api.ApiResponse;
import com.ionic.ekhelp.api.PSApiService;
import com.ionic.ekhelp.db.ItemCurrencyDao;
import com.ionic.ekhelp.db.PSCoreDb;
import com.ionic.ekhelp.repository.common.NetworkBoundResource;
import com.ionic.ekhelp.repository.common.PSRepository;
import com.ionic.ekhelp.utils.Utils;
import com.ionic.ekhelp.viewobject.ItemCurrency;
import com.ionic.ekhelp.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ItemCurrencyTypeRepository extends PSRepository {
    private ItemCurrencyDao itemCurrencyDao;

    @Inject
    ItemCurrencyTypeRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, ItemCurrencyDao itemCurrencyDao) {

        super(psApiService, appExecutors, db);
        this.itemCurrencyDao = itemCurrencyDao;
    }

    public LiveData<Resource<List<ItemCurrency>>> getAllItemCurrencyList(String limit, String offset) {

        return new NetworkBoundResource<List<ItemCurrency>, List<ItemCurrency>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<ItemCurrency> itemTypeList) {
                Utils.psLog("SaveCallResult of getAllCategoriesWithUserId");

                try {
                    db.runInTransaction(() -> {
                        itemCurrencyDao.deleteAllItemCurrency();

                        itemCurrencyDao.insertAll(itemTypeList);
                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }
            }


            @Override
            protected boolean shouldFetch(@Nullable List<ItemCurrency> data) {

                return connectivity.isConnected();
            }

            @NonNull
            @Override
            protected LiveData<List<ItemCurrency>> loadFromDb() {

                Utils.psLog("Load From Db (All Categories)");

                return itemCurrencyDao.getAllItemCurrency();

            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<ItemCurrency>>> createCall() {
                Utils.psLog("Call Get All Categories webservice.");

                return psApiService.getItemCurrencyTypeList(Config.API_KEY, limit, offset);
            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed of About Us");
            }

        }.asLiveData();
    }


}