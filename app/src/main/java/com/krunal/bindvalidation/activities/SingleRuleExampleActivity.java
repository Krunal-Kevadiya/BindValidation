package com.krunal.bindvalidation.activities;

import android.support.v7.widget.Toolbar;

import com.krunal.bindvalidation.BaseActivity;
import com.krunal.bindvalidation.R;
import com.krunal.bindvalidation.databinding.ActivitySingleRuleExampleBinding;
import com.krunal.bindvalidation.viewModels.SingleRuleExampleViewModel;

public class SingleRuleExampleActivity extends BaseActivity<ActivitySingleRuleExampleBinding, SingleRuleExampleViewModel> {

    @Override
    protected int getLayout() {
        return R.layout.activity_single_rule_example;
    }

    @Override
    protected SingleRuleExampleViewModel createViewModel() {
        return new SingleRuleExampleViewModel();
    }

    @Override
    protected void setViewModel(ActivitySingleRuleExampleBinding binding, SingleRuleExampleViewModel viewModel) {
        binding.setViewModel(viewModel);
    }

    @Override
    protected Toolbar getToolbar() {
        return binding.toolbar;
    }
}