package com.ionic.ekhelp.ui.user.verifyemail;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.ionic.ekhelp.MainActivity;
import com.ionic.ekhelp.R;
import com.ionic.ekhelp.binding.FragmentDataBindingComponent;
import com.ionic.ekhelp.databinding.FragmentVerifyEmailBinding;
import com.ionic.ekhelp.ui.common.DataBoundListAdapter;
import com.ionic.ekhelp.ui.common.PSFragment;
import com.ionic.ekhelp.utils.AutoClearedValue;
import com.ionic.ekhelp.utils.PSDialogMsg;
import com.ionic.ekhelp.utils.Utils;
import com.ionic.ekhelp.viewmodel.user.UserViewModel;
import com.ionic.ekhelp.viewobject.UserLogin;
import com.ionic.ekhelp.viewobject.common.Resource;

public class VerifyEmailFragment extends PSFragment implements DataBoundListAdapter.DiffUtilDispatchedInterface {

    //region Variables
    private UserViewModel userViewModel;
    private PSDialogMsg psDialogMsg;
    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);


    @VisibleForTesting
    private AutoClearedValue<FragmentVerifyEmailBinding> binding;

//endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentVerifyEmailBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_verify_email, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        return binding.get().getRoot();
    }

    @Override
    public void onDispatched() {

    }

    @Override
    protected void initUIAndActions() {

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setToolbarText(((MainActivity) getActivity()).binding.toolbar, getString(R.string.verify_email__title));
            ((MainActivity) this.getActivity()).binding.toolbar.setBackgroundColor(getResources().getColor(R.color.global__primary));
            ((MainActivity) getActivity()).updateMenuIconWhite();
            ((MainActivity) getActivity()).updateToolbarIconColor(Color.WHITE);

        }

        psDialogMsg = new PSDialogMsg(getActivity(), false);

        binding.get().emailTextView.setText(userEmailToVerify);

        binding.get().submitButton.setOnClickListener(v -> {
            if (validateInput()) {
                binding.get().submitButton.setEnabled(false);
                binding.get().submitButton.setText(getResources().getString(R.string.message__loading));
                userViewModel.setEmailVerificationUser(Utils.checkUserId(userIdToVerify), binding.get().enterCodeEditText.getText().toString());
            } else {
                Toast.makeText(getContext(), getString(R.string.verify_email__enter_code), Toast.LENGTH_SHORT).show();
            }
        });

        binding.get().resentCodeButton.setOnClickListener(v -> userViewModel.setResentVerifyCodeObj(userEmailToVerify));

        binding.get().changeEmailButton.setOnClickListener(v -> {
            if(getActivity() instanceof MainActivity) {
                navigationController.navigateToUserRegister((MainActivity) VerifyEmailFragment.this.getActivity());
            }else {
                navigationController.navigateToUserRegisterActivity(getActivity());
                if(getActivity() != null){
                    getActivity().finish();
                }
            }
        });
    }

    private boolean validateInput() {
        return !binding.get().enterCodeEditText.getText().toString().isEmpty();
    }

    @Override
    protected void initViewModels() {
        userViewModel = new ViewModelProvider(this, viewModelFactory).get(UserViewModel.class);
    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

        LiveData<Resource<UserLogin>> itemList = userViewModel.getEmailVerificationUser();

        if (itemList != null) {

            itemList.observe(this, listResource -> {
                if (listResource != null) {

                    binding.get().submitButton.setEnabled(true);
                    binding.get().submitButton.setText(getResources().getString(R.string.verify_email__submit));
                    switch (listResource.status) {
                        case LOADING:
                            // Loading State
                            // Data are from Local DB

//                            if (listResource.data != null) {
//                                //fadeIn Animation
//                                fadeIn(binding.get().getRoot());
//
//                                // Update the data
//                                Toast.makeText(getContext(), "Loading Loading", Toast.LENGTH_SHORT).show();
//
//                            }

                            break;

                        case SUCCESS:
                            // Success State
                            // Data are from Server

                            if (listResource.data != null) {

                                try {
                                    if (getActivity() != null) {

                                        Utils.updateUserLoginData(pref, listResource.data.user);
                                        Utils.navigateAfterUserLogin(getActivity(),navigationController);

                                    }

                                } catch (NullPointerException ne) {
                                    Utils.psErrorLog("Null Pointer Exception.", ne);
                                } catch (Exception e) {
                                    Utils.psErrorLog("Error in getting notification flag data.", e);
                                }

                            }

                            break;

                        case ERROR:
                            // Error State
                            psDialogMsg.showErrorDialog(listResource.message, getString(R.string.app__ok));
//                            psDialogMsg.showErrorDialog(getString(R.string.error_message__code_not_verify), getString(R.string.app__ok));
                            psDialogMsg.show();

                            break;
                        default:
                            // Default

                            break;
                    }

                }

            });
        }


        //For resent code
        userViewModel.getResentVerifyCodeData().observe(this, result -> {

            if (result != null) {
                switch (result.status) {
                    case SUCCESS:

                        //add offer text
                        Toast.makeText(getContext(), "Success Success", Toast.LENGTH_SHORT).show();

                        break;

                    case ERROR:
                        Toast.makeText(getContext(), "Fail resent code again", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }


}
