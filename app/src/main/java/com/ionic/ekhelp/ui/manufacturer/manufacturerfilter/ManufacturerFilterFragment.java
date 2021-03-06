package com.ionic.ekhelp.ui.manufacturer.manufacturerfilter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.ionic.ekhelp.Config;
import com.ionic.ekhelp.R;
import com.ionic.ekhelp.binding.FragmentDataBindingComponent;
import com.ionic.ekhelp.databinding.TypeFilterBinding;
import com.ionic.ekhelp.ui.manufacturer.adapter.ManufacturerFilterAdapter;
import com.ionic.ekhelp.ui.common.DataBoundListAdapter;
import com.ionic.ekhelp.ui.common.PSFragment;
import com.ionic.ekhelp.utils.AutoClearedValue;
import com.ionic.ekhelp.utils.Constants;
import com.ionic.ekhelp.utils.Utils;
import com.ionic.ekhelp.viewmodel.itemmanufacturer.ItemManufacturerViewModel;
import com.ionic.ekhelp.viewmodel.itemmodel.ItemModelViewModel;
import com.ionic.ekhelp.viewobject.Manufacturer;
import com.ionic.ekhelp.viewobject.Model;
import com.ionic.ekhelp.viewobject.common.Resource;

import java.util.List;

public class ManufacturerFilterFragment extends PSFragment implements DataBoundListAdapter.DiffUtilDispatchedInterface {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
    private ItemManufacturerViewModel itemManufacturerViewModel;
    private ItemModelViewModel itemModelViewModel;
    private String manufacturerId, modelId;
    public Intent intent = new Intent();

    @VisibleForTesting
    private AutoClearedValue<TypeFilterBinding> binding;
    private AutoClearedValue<ManufacturerFilterAdapter> adapter;
    private AutoClearedValue<List<Manufacturer>> lastCategoryData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        TypeFilterBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.type_filter, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        binding.get().setLoadingMore(connectivity.isConnected());
        setHasOptionsMenu(true);

        return binding.get().getRoot();
    }

    @Override
    protected void initUIAndActions() {

    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.clear_button, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.clear) {
            this.manufacturerId = "";
            this.modelId = "";

            initializeAdapter();

            initData();

            if(getActivity() != null){
            navigationController.navigateBackToHomeFeaturedFragment(ManufacturerFilterFragment.this.getActivity(), this.manufacturerId, this.modelId);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initViewModels() {
        itemManufacturerViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemManufacturerViewModel.class);
        itemModelViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemModelViewModel.class);

    }

    @Override
    protected void initAdapters() {

        try {
            if (getActivity() != null) {

                intent = getActivity().getIntent();

                this.manufacturerId = intent.getStringExtra(Constants.MANUFACTURER_ID);
                this.modelId = intent.getStringExtra(Constants.MODEL_ID);

            }
        } catch (Exception e) {
            Utils.psErrorLog("", e);
        }

        initializeAdapter();
    }

    private void initializeAdapter() {
        ManufacturerFilterAdapter nvAdapter = new ManufacturerFilterAdapter(dataBindingComponent, (manufacturerId, modelId) -> {

            ManufacturerFilterFragment.this.assignManufacturerId(manufacturerId, modelId);

            if (getActivity() != null) {
                navigationController.navigateBackToHomeFeaturedFragment(ManufacturerFilterFragment.this.getActivity(), manufacturerId, modelId);
                ManufacturerFilterFragment.this.getActivity().finish();
            }

        }, this.manufacturerId, this.modelId);

        this.adapter = new AutoClearedValue<>(this, nvAdapter);
        binding.get().CategoryList.setAdapter(nvAdapter);
    }

    private void assignManufacturerId(String manufacturerId, String modelId) {
        this.manufacturerId = manufacturerId;
        this.modelId = modelId;

    }

    @Override
    protected void initData() {

        itemManufacturerViewModel.manufacturerParameterHolder.cityId = selectedCityId;

        itemManufacturerViewModel.setManufacturerListObj(String.valueOf(Config.LIST_MANUFACTURER_COUNT), String.valueOf(itemManufacturerViewModel.offset));
        itemModelViewModel.setAllModelListObj();

        LiveData<Resource<List<Manufacturer>>> manufacturer = itemManufacturerViewModel.getManufacturerListData();
        LiveData<Resource<List<Model>>> model = itemModelViewModel.getAllModelListData();


        if (manufacturer != null) {

            manufacturer.observe(this, listResource -> {
                if (listResource != null) {

                    if (listResource.data != null && listResource.data.size() > 0) {

                        lastCategoryData = new AutoClearedValue<>(this, listResource.data);
                        replaceManufacturer(lastCategoryData.get());

                    }

                } else {

                    // Init Object or Empty Data

                    if (itemManufacturerViewModel.offset > 1) {
                        // No more data for this list
                        // So, Block all future loading
                        itemManufacturerViewModel.forceEndLoading = true;
                    }

                }

            });
        }

        if (model != null) {

            model.observe(this, listResource -> {
                if (listResource != null) {


                    if (listResource.data != null && listResource.data.size() > 0) {

                        replaceModel(listResource.data);
                    }


                } else {

                    // Init Object or Empty Data

                    if (itemModelViewModel.offset > 1) {
                        // No more data for this list
                        // So, Block all future loading
                        itemModelViewModel.forceEndLoading = true;
                    }

                }

            });
        }

    }


    private void replaceManufacturer(List<Manufacturer> ManufacturerList) {

        adapter.get().replaceManufacturer(ManufacturerList);
        adapter.get().notifyDataSetChanged();
        binding.get().executePendingBindings();

    }

    private void replaceModel(List<Model> modelList) {

        adapter.get().replaceModel(modelList);
        adapter.get().notifyDataSetChanged();
        binding.get().executePendingBindings();

    }

    @Override
    public void onDispatched() {

//      if  (itemManufacturerViewModel.loadingDirection == Utils.LoadingDirection.top) {
//
////            if (binding.get().CategoryList != null) {
////
////                LinearLayoutManager layoutManager = (LinearLayoutManager)
////                        binding.get().CategoryList.getLayoutManager();
////
////
////                if (layoutManager != null) {
////                    layoutManager.scrollToPosition(0);
////                }
////            }
//        }
//
    }
}
