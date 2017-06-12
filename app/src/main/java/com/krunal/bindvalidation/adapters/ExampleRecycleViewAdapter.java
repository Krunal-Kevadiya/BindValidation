package com.krunal.bindvalidation.adapters;

import android.support.annotation.LayoutRes;

import com.krunal.bindvalidation.databinding.ItemExampleBinding;
import com.krunal.bindvalidation.models.ItemExample;
import com.krunal.bindvalidation.viewModels.ItemExampleViewModel;

public class ExampleRecycleViewAdapter extends ListRecyclerViewAdapter<ItemExample, ItemExampleBinding> {

    public ExampleRecycleViewAdapter(@LayoutRes int layoutId) {
        super(layoutId);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemExampleBinding> holder, int position) {
        holder.binding.setViewModel(new ItemExampleViewModel(get(position)));
    }

}
