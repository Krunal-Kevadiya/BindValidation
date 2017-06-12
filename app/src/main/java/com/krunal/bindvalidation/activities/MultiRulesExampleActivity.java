package com.krunal.bindvalidation.activities;

import android.support.v7.widget.Toolbar;

import com.krunal.bindvalidation.BaseActivity;
import com.krunal.bindvalidation.R;
import com.krunal.bindvalidation.databinding.ActivityMultiRulesExampleBinding;
import com.krunal.bindvalidation.viewModels.MultiRulesExampleViewModel;

public class MultiRulesExampleActivity extends BaseActivity<ActivityMultiRulesExampleBinding, MultiRulesExampleViewModel> {

    @Override
    protected int getLayout() {
        return R.layout.activity_multi_rules_example;
    }

    @Override
    protected MultiRulesExampleViewModel createViewModel() {
        return new MultiRulesExampleViewModel();
    }

    @Override
    protected void setViewModel(ActivityMultiRulesExampleBinding binding, MultiRulesExampleViewModel viewModel) {
        binding.setViewModel(viewModel);
    }

    @Override
    protected Toolbar getToolbar() {
        return binding.toolbar;
    }
}