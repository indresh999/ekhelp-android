package com.ionic.ekhelp.repository.homebanner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.ionic.ekhelp.AppExecutors;
import com.ionic.ekhelp.Config;
import com.ionic.ekhelp.api.ApiResponse;
import com.ionic.ekhelp.api.PSApiService;
import com.ionic.ekhelp.db.HomeBannerDao;
import com.ionic.ekhelp.db.PSCoreDb;
import com.ionic.ekhelp.repository.common.NetworkBoundResource;
import com.ionic.ekhelp.repository.common.PSRepository;
import com.ionic.ekhelp.utils.Utils;
import com.ionic.ekhelp.viewobject.Banner;
import com.ionic.ekhelp.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class HomeBannerRepository extends PSRepository {
    /**
     * Constructor of PSRepository
     *
     * @param psApiService Panacea-Soft API Service Instance
     * @param appExecutors Executors Instance
     * @param db           Panacea-Soft DB
     */

    private final HomeBannerDao homeBannerDao;

    @Inject
    protected HomeBannerRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, HomeBannerDao homeBannerDao) {
        super(psApiService, appExecutors, db);
        this.homeBannerDao = homeBannerDao;
    }

    public LiveData<Resource<List<Banner>>> getHomeBannerList(String limit, String offset) {
        return new NetworkBoundResource<List<Banner>, List<Banner>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<Banner> itemList) {
                Utils.psLog("SaveCallResult of getHomeBannerList.");

                try {
                    db.runInTransaction(() -> {
                        homeBannerDao.deleteAll();
                        homeBannerDao.insertAll(itemList);
                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at getHomeBannerList", ex);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Banner> data) {

                // Recent news always load from server
                return connectivity.isConnected();

            }

            @NonNull
            @Override
            protected LiveData<List<Banner>> loadFromDb() {
                Utils.psLog("Load getHomeBannerList From Db");

                    return homeBannerDao.getAllHomeBanner();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Banner>>> createCall() {
                Utils.psLog("Call API Service to getHomeBannerList.");

                return psApiService.getAllBanner(Config.API_KEY, limit, offset);
            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed (getHomeBannerList) : " + message);
            }
        }.asLiveData();
    }

}
