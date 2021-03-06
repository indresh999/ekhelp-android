package com.ionic.ekhelp.ui.buildtype;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import com.ionic.ekhelp.R;
import com.ionic.ekhelp.databinding.ItemBuildTypeBinding;
import com.ionic.ekhelp.ui.common.DataBoundListAdapter;
import com.ionic.ekhelp.ui.common.DataBoundViewHolder;
import com.ionic.ekhelp.utils.Objects;
import com.ionic.ekhelp.viewobject.BuildType;

public class BuildTypeAdapter extends DataBoundListAdapter<BuildType, ItemBuildTypeBinding> {

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private final BuildTypeAdapter.NewsClickCallback callback;
    private DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface = null;
    public String itemBuildType = "";

    public BuildTypeAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                           BuildTypeAdapter.NewsClickCallback callback, String itemBuildType) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.itemBuildType = itemBuildType;
    }

    @Override
    protected ItemBuildTypeBinding createBinding(ViewGroup parent) {
        ItemBuildTypeBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_build_type, parent, false,
                        dataBindingComponent);
        binding.getRoot().setOnClickListener(v -> {

            BuildType itemType = binding.getBuildType();

            if (itemType != null && callback != null) {

                binding.groupview.setBackgroundColor(parent.getResources().getColor(R.color.md_green_50));

                callback.onClick(itemType, itemType.id);
            }
        });

        
        return binding;
    }

    @Override
    public void bindView(DataBoundViewHolder<ItemBuildTypeBinding> holder, int position) {
        super.bindView(holder, position);

    }

    @Override
    protected void dispatched() {
        if (diffUtilDispatchedInterface != null) {
            diffUtilDispatchedInterface.onDispatched();
        }
    }

    @Override
    protected void bind(ItemBuildTypeBinding binding, BuildType buildType) {
        binding.setBuildType(buildType);

        if (itemBuildType != null) {
            if (buildType.id.equals(itemBuildType)) {
                binding.groupview.setBackgroundColor(binding.groupview.getResources().getColor((R.color.md_green_50)));
            }
        }

    }

    @Override
    protected boolean areItemsTheSame(BuildType oldItem, BuildType newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    @Override
    protected boolean areContentsTheSame(BuildType oldItem, BuildType newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    public interface NewsClickCallback {
        void onClick(BuildType itemType, String id);
    }

}

