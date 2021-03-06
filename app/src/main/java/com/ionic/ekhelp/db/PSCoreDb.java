package com.ionic.ekhelp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.ionic.ekhelp.db.common.Converters;
import com.ionic.ekhelp.viewobject.AboutUs;
import com.ionic.ekhelp.viewobject.Banner;
import com.ionic.ekhelp.viewobject.Blog;
import com.ionic.ekhelp.viewobject.BuildType;
import com.ionic.ekhelp.viewobject.ChatHistory;
import com.ionic.ekhelp.viewobject.ChatHistoryMap;
import com.ionic.ekhelp.viewobject.City;
import com.ionic.ekhelp.viewobject.CityMap;
import com.ionic.ekhelp.viewobject.Color;
import com.ionic.ekhelp.viewobject.DeletedObject;
import com.ionic.ekhelp.viewobject.FuelType;
import com.ionic.ekhelp.viewobject.Image;
import com.ionic.ekhelp.viewobject.Item;
import com.ionic.ekhelp.viewobject.ItemCategory;
import com.ionic.ekhelp.viewobject.ItemCollection;
import com.ionic.ekhelp.viewobject.ItemCollectionHeader;
import com.ionic.ekhelp.viewobject.ItemCondition;
import com.ionic.ekhelp.viewobject.ItemCurrency;
import com.ionic.ekhelp.viewobject.ItemDealOption;
import com.ionic.ekhelp.viewobject.ItemFavourite;
import com.ionic.ekhelp.viewobject.ItemFromFollower;
import com.ionic.ekhelp.viewobject.ItemHistory;
import com.ionic.ekhelp.viewobject.ItemLocation;
import com.ionic.ekhelp.viewobject.ItemMap;
import com.ionic.ekhelp.viewobject.ItemPriceType;
import com.ionic.ekhelp.viewobject.ItemSpecs;
import com.ionic.ekhelp.viewobject.ItemSubCategory;
import com.ionic.ekhelp.viewobject.ItemType;
import com.ionic.ekhelp.viewobject.Manufacturer;
import com.ionic.ekhelp.viewobject.Model;
import com.ionic.ekhelp.viewobject.Noti;
import com.ionic.ekhelp.viewobject.PSAppInfo;
import com.ionic.ekhelp.viewobject.PSAppSetting;
import com.ionic.ekhelp.viewobject.PSAppVersion;
import com.ionic.ekhelp.viewobject.PSCount;
import com.ionic.ekhelp.viewobject.Rating;
import com.ionic.ekhelp.viewobject.SellerType;
import com.ionic.ekhelp.viewobject.Transmission;
import com.ionic.ekhelp.viewobject.User;
import com.ionic.ekhelp.viewobject.UserLogin;
import com.ionic.ekhelp.viewobject.UserMap;
import com.ionic.ekhelp.viewobject.messageHolder.Message;


/**
 * Created by Panacea-Soft on 11/20/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Database(entities = {
        Image.class,
        User.class,
        UserLogin.class,
        AboutUs.class,
        ItemFavourite.class,
        Noti.class,
        ItemHistory.class,
        Blog.class,
        Rating.class,
        PSAppInfo.class,
        PSAppVersion.class,
        DeletedObject.class,
        City.class,
        CityMap.class,
        Item.class,
        ItemMap.class,
        ItemCategory.class,
        ItemCollectionHeader.class,
        ItemCollection.class,
        ItemSubCategory.class,
        ItemSpecs.class,
        ItemCurrency.class,
        ItemPriceType.class,
        ItemType.class,
        ItemLocation.class,
        ItemDealOption.class,
        ItemCondition.class,
        ItemFromFollower.class,
        Message.class,
        ChatHistory.class,
        ChatHistoryMap.class,
        PSAppSetting.class,
        UserMap.class,
        PSCount.class,
        Manufacturer.class,
        Model.class,
        Transmission.class,
        Color.class,
        FuelType.class,
        BuildType.class,
        SellerType.class,
        Banner.class
}, version = 1, exportSchema = false)
// app version 1.1 = db version 1
// app version 1.0 = db version 1


@TypeConverters({Converters.class})

public abstract class PSCoreDb extends RoomDatabase {

    abstract public UserDao userDao();

    abstract public UserMapDao userMapDao();

    abstract public HistoryDao historyDao();

    abstract public SpecsDao specsDao();

    abstract public AboutUsDao aboutUsDao();

    abstract public ImageDao imageDao();

    abstract public ItemDealOptionDao itemDealOptionDao();

    abstract public ItemConditionDao itemConditionDao();

    abstract public ItemLocationDao itemLocationDao();

    abstract public ItemCurrencyDao itemCurrencyDao();

    abstract public ItemPriceTypeDao itemPriceTypeDao();

    abstract public ItemTypeDao itemTypeDao();

    abstract public RatingDao ratingDao();

    abstract public NotificationDao notificationDao();

    abstract public BlogDao blogDao();

    abstract public PSAppInfoDao psAppInfoDao();

    abstract public PSAppVersionDao psAppVersionDao();

    abstract public DeletedObjectDao deletedObjectDao();

    abstract public CityDao cityDao();

    abstract public CityMapDao cityMapDao();

    abstract public ItemDao itemDao();

    abstract public ItemMapDao itemMapDao();

    abstract public ItemManufacturerDao itemManufacturerDao();

    abstract public ItemCollectionHeaderDao itemCollectionHeaderDao();

    abstract public ItemModelDao itemModelDao();

    abstract public ChatHistoryDao chatHistoryDao();

    abstract public MessageDao messageDao();

    abstract public PSCountDao psCountDao();

    abstract public TransmissionDao transmissionDao();

    abstract public ItemColorDao itemColorDao();

    abstract public FuelTypeDao fuelTypeDao();

    abstract public BuildTypeDao buildTypeDao();

    abstract public SellerTypeDao sellerTypeDao();

    abstract public HomeBannerDao homeBannerDao();

//    /**
//     * Migrate from:
//     * version 1 - using Room
//     * to
//     * version 2 - using Room where the {@link } has an extra field: addedDateStr
//     */
//    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE news "
//                    + " ADD COLUMN addedDateStr INTEGER NOT NULL DEFAULT 0");
//        }
//    };

    /* More migration write here */
}