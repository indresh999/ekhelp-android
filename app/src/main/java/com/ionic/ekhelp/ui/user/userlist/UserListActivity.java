package com.ionic.ekhelp.ui.user.userlist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.databinding.DataBindingUtil;

import com.ionic.ekhelp.Config;
import com.ionic.ekhelp.R;
import com.ionic.ekhelp.databinding.ActivityUserListBinding;
import com.ionic.ekhelp.ui.common.PSAppCompactActivity;
import com.ionic.ekhelp.utils.Constants;
import com.ionic.ekhelp.utils.MyContextWrapper;
import com.ionic.ekhelp.viewobject.holder.UserParameterHolder;

public class UserListActivity extends PSAppCompactActivity {


    UserParameterHolder userHolder = new UserParameterHolder();

    //region Override Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityUserListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_user_list);
        
        // Init all UI
        initUI(binding);

    }

    @Override
    protected void attachBaseContext(Context newBase) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        String CURRENT_LANG_CODE = preferences.getString(Constants.LANGUAGE_CODE, Config.DEFAULT_LANGUAGE);
        String CURRENT_LANG_COUNTRY_CODE = preferences.getString(Constants.LANGUAGE_COUNTRY_CODE, Config.DEFAULT_LANGUAGE_COUNTRY_CODE);

        super.attachBaseContext(MyContextWrapper.wrap(newBase, CURRENT_LANG_CODE, CURRENT_LANG_COUNTRY_CODE, true));
    }

    //endregion


    //region Private Methods

    private void initUI(ActivityUserListBinding binding) {

        userHolder = (UserParameterHolder) getIntent().getSerializableExtra(Constants.USER_PARAM_HOLDER_KEY);

        // Toolbar
        if(userHolder.return_types.equals("follower")) {
            initToolbar(binding.toolbar, getString(R.string.user_follower_list_toolbar_name));
        }else {
            initToolbar(binding.toolbar, getString(R.string.user_following_list_toolbar_name));
        }

        // setup Fragment
        setupFragment(new UserListFragment());

    }

}
