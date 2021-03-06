package com.ionic.ekhelp.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.databinding.DataBindingUtil;

import com.ionic.ekhelp.Config;
import com.ionic.ekhelp.R;
import com.ionic.ekhelp.databinding.ActivityDashboardSearchBinding;
import com.ionic.ekhelp.ui.common.PSAppCompactActivity;
import com.ionic.ekhelp.utils.Constants;
import com.ionic.ekhelp.utils.MyContextWrapper;

public class DashboardSearchActivity extends PSAppCompactActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDashboardSearchBinding databinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard_search);

        initUI(databinding);
        
    }

    @Override
    protected void attachBaseContext(Context newBase) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        String CURRENT_LANG_CODE = preferences.getString(Constants.LANGUAGE_CODE, Config.DEFAULT_LANGUAGE);
        String CURRENT_LANG_COUNTRY_CODE = preferences.getString(Constants.LANGUAGE_COUNTRY_CODE, Config.DEFAULT_LANGUAGE_COUNTRY_CODE);

        super.attachBaseContext(MyContextWrapper.wrap(newBase, CURRENT_LANG_CODE, CURRENT_LANG_COUNTRY_CODE, true));
    }

    protected void initUI(ActivityDashboardSearchBinding binding) {
        Intent intent = getIntent();

        String fragName = intent.getStringExtra(Constants.MANUFACTURER_FLAG);

        if (fragName.equals(Constants.MANUFACTURER)) {
            setupFragment(new DashBoardSearchManufacturerFragment());

            initToolbar(binding.toolbar, getResources().getString(R.string.Feature_UI__search_alert_manufacturer_title));
        } else if (fragName.equals(Constants.MODEL)) {
            setupFragment(new DashBoardSearchModelFragment());

            initToolbar(binding.toolbar, getResources().getString(R.string.Feature_UI__search_alert_model_title));
        }


    }
}
