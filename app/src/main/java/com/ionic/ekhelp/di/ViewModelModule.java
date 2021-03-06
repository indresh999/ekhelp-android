package com.ionic.ekhelp.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ionic.ekhelp.viewmodel.aboutus.AboutUsViewModel;
import com.ionic.ekhelp.viewmodel.apploading.PSAPPLoadingViewModel;
import com.ionic.ekhelp.viewmodel.blog.BlogViewModel;
import com.ionic.ekhelp.viewmodel.buildtype.BuildTypeViewModel;
import com.ionic.ekhelp.viewmodel.chat.ChatViewModel;
import com.ionic.ekhelp.viewmodel.chathistory.ChatHistoryViewModel;
import com.ionic.ekhelp.viewmodel.city.CityViewModel;
import com.ionic.ekhelp.viewmodel.city.FeaturedCitiesViewModel;
import com.ionic.ekhelp.viewmodel.city.PopularCitiesViewModel;
import com.ionic.ekhelp.viewmodel.city.RecentCitiesViewModel;
import com.ionic.ekhelp.viewmodel.clearalldata.ClearAllDataViewModel;
import com.ionic.ekhelp.viewmodel.common.NotificationViewModel;
import com.ionic.ekhelp.viewmodel.common.PSNewsViewModelFactory;
import com.ionic.ekhelp.viewmodel.contactus.ContactUsViewModel;
import com.ionic.ekhelp.viewmodel.fueltype.FuelTypeViewModel;
import com.ionic.ekhelp.viewmodel.homebanner.HomeBannerViewModel;
import com.ionic.ekhelp.viewmodel.homelist.HomeTrendingCategoryListViewModel;
import com.ionic.ekhelp.viewmodel.image.ImageViewModel;
import com.ionic.ekhelp.viewmodel.item.FavouriteViewModel;
import com.ionic.ekhelp.viewmodel.item.HistoryViewModel;
import com.ionic.ekhelp.viewmodel.item.PopularItemViewModel;
import com.ionic.ekhelp.viewmodel.item.RecentItemViewModel;
import com.ionic.ekhelp.viewmodel.item.SpecsViewModel;
import com.ionic.ekhelp.viewmodel.item.TouchCountViewModel;
import com.ionic.ekhelp.viewmodel.itemmanufacturer.ItemManufacturerViewModel;
import com.ionic.ekhelp.viewmodel.itemcolor.ItemColorViewModel;
import com.ionic.ekhelp.viewmodel.itemcondition.ItemConditionViewModel;
import com.ionic.ekhelp.viewmodel.itemcurrency.ItemCurrencyViewModel;
import com.ionic.ekhelp.viewmodel.itemdealoption.ItemDealOptionViewModel;
import com.ionic.ekhelp.viewmodel.itemfromfollower.ItemFromFollowerViewModel;
import com.ionic.ekhelp.viewmodel.itemlocation.ItemLocationViewModel;
import com.ionic.ekhelp.viewmodel.itempricetype.ItemPriceTypeViewModel;
import com.ionic.ekhelp.viewmodel.itemmodel.ItemModelViewModel;
import com.ionic.ekhelp.viewmodel.itemtransmission.ItemTransmissionViewModel;
import com.ionic.ekhelp.viewmodel.itemtype.ItemTypeViewModel;
import com.ionic.ekhelp.viewmodel.notification.NotificationsViewModel;
import com.ionic.ekhelp.viewmodel.pscount.PSCountViewModel;
import com.ionic.ekhelp.viewmodel.rating.RatingViewModel;
import com.ionic.ekhelp.viewmodel.sellertype.SellerTypeViewModel;
import com.ionic.ekhelp.viewmodel.user.UserViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Panacea-Soft on 11/16/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Module
abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(PSNewsViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel.class)
    abstract ViewModel bindUserViewModel(UserViewModel userViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AboutUsViewModel.class)
    abstract ViewModel bindAboutUsViewModel(AboutUsViewModel aboutUsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemLocationViewModel.class)
    abstract ViewModel bindItemLocationViewModel(ItemLocationViewModel itemLocationViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemDealOptionViewModel.class)
    abstract ViewModel bindItemDealOptionViewModel(ItemDealOptionViewModel itemDealOptionViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemConditionViewModel.class)
    abstract ViewModel bindItemConditionViewModel(ItemConditionViewModel itemConditionViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ImageViewModel.class)
    abstract ViewModel bindImageViewModel(ImageViewModel imageViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemTypeViewModel.class)
    abstract ViewModel bindItemTypeViewModel(ItemTypeViewModel itemTypeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RatingViewModel.class)
    abstract ViewModel bindRatingViewModel(RatingViewModel ratingViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NotificationViewModel.class)
    abstract ViewModel bindNotificationViewModel(NotificationViewModel notificationViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemFromFollowerViewModel.class)
    abstract ViewModel bindItemFromFollowerViewModel(ItemFromFollowerViewModel itemFromFollowerViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemPriceTypeViewModel.class)
    abstract ViewModel bindItemPriceTypeViewModel(ItemPriceTypeViewModel itemPriceTypeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemCurrencyViewModel.class)
    abstract ViewModel bindItemCurrencyViewModel(ItemCurrencyViewModel itemCurrencyViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(ContactUsViewModel.class)
    abstract ViewModel bindContactUsViewModel(ContactUsViewModel contactUsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteViewModel.class)
    abstract ViewModel bindFavouriteViewModel(FavouriteViewModel favouriteViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TouchCountViewModel.class)
    abstract ViewModel bindTouchCountViewModel(TouchCountViewModel touchCountViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SpecsViewModel.class)
    abstract ViewModel bindProductSpecsViewModel(SpecsViewModel specsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel.class)
    abstract ViewModel bindHistoryProductViewModel(HistoryViewModel historyViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemManufacturerViewModel.class)
    abstract ViewModel bindCityCategoryViewModel(ItemManufacturerViewModel itemManufacturerViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NotificationsViewModel.class)
    abstract ViewModel bindNotificationListViewModel(NotificationsViewModel notificationListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeTrendingCategoryListViewModel.class)
    abstract ViewModel bindHomeTrendingCategoryListViewModel(HomeTrendingCategoryListViewModel transactionListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(BlogViewModel.class)
    abstract ViewModel bindNewsFeedViewModel(BlogViewModel blogViewModel);

//    @Binds
//    @IntoMap
//    @ViewModelKey(PSAppInfoViewModel.class)
//    abstract ViewModel bindPSAppInfoViewModel(PSAppInfoViewModel psAppInfoViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ClearAllDataViewModel.class)
    abstract ViewModel bindClearAllDataViewModel(ClearAllDataViewModel clearAllDataViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CityViewModel.class)
    abstract ViewModel bindCityViewModel(CityViewModel cityViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(com.ionic.ekhelp.viewmodel.item.ItemViewModel.class)
    abstract ViewModel bindItemViewModel(com.ionic.ekhelp.viewmodel.item.ItemViewModel itemViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PopularItemViewModel.class)
    abstract ViewModel bindPopularItemViewModel(PopularItemViewModel popularItemViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RecentItemViewModel.class)
    abstract ViewModel bindRecentItemViewModel(RecentItemViewModel recentItemViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PSAPPLoadingViewModel.class)
    abstract ViewModel bindPSAPPLoadingViewModel(PSAPPLoadingViewModel psappLoadingViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PopularCitiesViewModel.class)
    abstract ViewModel bindPopularCitiesViewModel(PopularCitiesViewModel popularCitiesViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FeaturedCitiesViewModel.class)
    abstract ViewModel bindFeaturedCitiesViewModel(FeaturedCitiesViewModel featuredCitiesViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RecentCitiesViewModel.class)
    abstract ViewModel bindRecentCitiesViewModel(RecentCitiesViewModel recentCitiesViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemModelViewModel.class)
    abstract ViewModel bindItemSubCategoryViewModel(ItemModelViewModel itemModelViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ChatViewModel.class)
    abstract ViewModel bindChatViewModel(ChatViewModel chatViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ChatHistoryViewModel.class)
    abstract ViewModel bindSellerViewModel(ChatHistoryViewModel chatHistoryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PSCountViewModel.class)
    abstract ViewModel bindPSCountViewModel(PSCountViewModel psCountViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemTransmissionViewModel.class)
    abstract ViewModel bindTransmissionViewModel(ItemTransmissionViewModel transmissionViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemColorViewModel.class)
    abstract ViewModel bindItemColorViewModel(ItemColorViewModel itemColorViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FuelTypeViewModel.class)
    abstract ViewModel bindFueltypeViewModel(FuelTypeViewModel fuelTypeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(BuildTypeViewModel.class)
    abstract ViewModel bindBuildtypeViewModel(BuildTypeViewModel buildTypeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SellerTypeViewModel.class)
    abstract ViewModel bindSellertypeViewModel(SellerTypeViewModel sellerTypeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeBannerViewModel.class)
    abstract ViewModel bindHomeBannerViewModel(HomeBannerViewModel homeBannerViewModel);
}


