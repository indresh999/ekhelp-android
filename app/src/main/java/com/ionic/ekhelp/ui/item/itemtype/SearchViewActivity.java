package com.ionic.ekhelp.ui.item.itemtype;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.databinding.DataBindingUtil;

import com.ionic.ekhelp.Config;
import com.ionic.ekhelp.R;
import com.ionic.ekhelp.databinding.ActivitySearchViewBinding;
import com.ionic.ekhelp.ui.buildtype.BuildTypeFragment;
import com.ionic.ekhelp.ui.common.PSAppCompactActivity;
import com.ionic.ekhelp.ui.fueltype.FuelTypeFragment;
import com.ionic.ekhelp.ui.item.itemcondition.ItemConditionFragment;
import com.ionic.ekhelp.ui.item.itemcurrency.ItemCurrencyTypeFragment;
import com.ionic.ekhelp.ui.item.itemdealoption.ItemDealOptionTypeFragment;
import com.ionic.ekhelp.ui.item.itemlocation.ItemLocationFragment;
import com.ionic.ekhelp.ui.item.itempricetype.ItemPriceTypeFragment;
import com.ionic.ekhelp.ui.itemcolor.ItemColorFragment;
import com.ionic.ekhelp.ui.itemtransmission.ItemTransmissionFragment;
import com.ionic.ekhelp.ui.sellertype.SellerTypeFragment;
import com.ionic.ekhelp.utils.Constants;
import com.ionic.ekhelp.utils.MyContextWrapper;

public class SearchViewActivity extends PSAppCompactActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySearchViewBinding databinding = DataBindingUtil.setContentView(this, R.layout.activity_search_view);

        initUI(databinding);

    }

    @Override
    protected void attachBaseContext(Context newBase) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        String CURRENT_LANG_CODE = preferences.getString(Constants.LANGUAGE_CODE, Config.DEFAULT_LANGUAGE);
        String CURRENT_LANG_COUNTRY_CODE = preferences.getString(Constants.LANGUAGE_COUNTRY_CODE, Config.DEFAULT_LANGUAGE_COUNTRY_CODE);

        super.attachBaseContext(MyContextWrapper.wrap(newBase, CURRENT_LANG_CODE, CURRENT_LANG_COUNTRY_CODE, true));
    }

    protected void initUI(ActivitySearchViewBinding binding) {
        Intent intent = getIntent();

        String fragName = intent.getStringExtra(Constants.ITEM_TYPE_FLAG);

        switch (fragName) {
            case Constants.ITEM_TYPE:
                setupFragment(new ItemTypeFragment());
                initToolbar(binding.toolbar, getResources().getString(R.string.Feature_UI__search_alert_type_title));
                break;
            case Constants.ITEM_PRICE_TYPE:
                setupFragment(new ItemPriceTypeFragment());
                initToolbar(binding.toolbar, getResources().getString(R.string.Feature_UI__search_alert_price_type_title));
                break;
            case Constants.ITEM_CURRENCY_TYPE:
                setupFragment(new ItemCurrencyTypeFragment());
                initToolbar(binding.toolbar, getResources().getString(R.string.Feature_UI__search_alert_currency_title));
                break;
            case Constants.ITEM_DEAL_OPTION_TYPE:
                setupFragment(new ItemDealOptionTypeFragment());
                initToolbar(binding.toolbar, getResources().getString(R.string.Feature_UI__search_alert_deal_option_title));
                break;
            case Constants.ITEM_LOCATION_TYPE:
                setupFragment(new ItemLocationFragment());
                initToolbar(binding.toolbar, getResources().getString(R.string.Feature_UI__search_alert_location_title));
                break;
            case Constants.ITEM_CONDITION_TYPE:
                setupFragment(new ItemConditionFragment());
                initToolbar(binding.toolbar, getResources().getString(R.string.Feature_UI__search_alert_condition_title));
                break;
            case Constants.TRANSMISSION:
                setupFragment(new ItemTransmissionFragment());
                initToolbar(binding.toolbar, getResources().getString(R.string.Feature_UI__search_alert_transmission));
                break;
            case Constants.ITEM_COLOR:
                setupFragment(new ItemColorFragment());
                initToolbar(binding.toolbar, getResources().getString(R.string.Feature_UI__search_alert_color));
                break;
            case Constants.FUEL_TYPE:
                setupFragment(new FuelTypeFragment());
                initToolbar(binding.toolbar, getResources().getString(R.string.Feature_UI__search_fuel_type));
                break;
            case Constants.BUILD_TYPE:
                setupFragment(new BuildTypeFragment());
                initToolbar(binding.toolbar, getResources().getString(R.string.Feature_UI__search_build_type));
                break;
            case Constants.SELLER_TYPE:
                setupFragment(new SellerTypeFragment());
                initToolbar(binding.toolbar, getResources().getString(R.string.Feature_UI__search_seller_type));
                break;
        }

    }
}