package com.ionic.ekhelp.ui.item.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.ionic.ekhelp.R;
import com.ionic.ekhelp.databinding.ItemItemSpecsAdapterBinding;
import com.ionic.ekhelp.ui.common.DataBoundListAdapter;
import com.ionic.ekhelp.utils.Objects;
import com.ionic.ekhelp.viewobject.ItemSpecs;

public class ItemSpecsAdapter extends DataBoundListAdapter<ItemSpecs, ItemItemSpecsAdapterBinding> {
    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private SpecsClickCallBack callback;
    private DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface = null;


    public ItemSpecsAdapter(androidx.databinding.DataBindingComponent dataBindingComponent, SpecsClickCallBack specsClickCallBack) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = specsClickCallBack;
    }

    @Override
    protected ItemItemSpecsAdapterBinding createBinding(ViewGroup parent) {
        ItemItemSpecsAdapterBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_item_specs_adapter, parent, false,
                        dataBindingComponent);
        binding.getRoot().setOnClickListener(v -> {
            ItemSpecs ItemSpecs = binding.getItemSpecs();
            if (ItemSpecs != null && callback != null) {
                callback.onClick(ItemSpecs);
            }
        });
        return binding;
    }

    @Override
    protected void dispatched() {
        if (diffUtilDispatchedInterface != null) {
            diffUtilDispatchedInterface.onDispatched();
        }
    }

    @Override
    protected void bind(ItemItemSpecsAdapterBinding binding, ItemSpecs item) {
        binding.setItemSpecs(item);
    }

    @Override
    protected boolean areItemsTheSame(ItemSpecs oldItem, ItemSpecs newItem) {
        return Objects.equals(oldItem.id, newItem.id)
                && oldItem.itemId.equals(newItem.itemId);
    }

    @Override
    protected boolean areContentsTheSame(ItemSpecs oldItem, ItemSpecs newItem) {
        return Objects.equals(oldItem.id, newItem.id)
                && oldItem.itemId.equals(newItem.itemId);
    }

    public interface SpecsClickCallBack {
        void onClick(ItemSpecs ItemSpecs);
    }
}
