package com.krunal.bindvalidation.activities;

import android.support.v7.widget.Toolbar;

import com.krunal.bindvalidation.BaseActivity;
import com.krunal.bindvalidation.R;
import com.krunal.bindvalidation.databinding.ActivityLambdaExampleBinding;
import com.krunal.bindvalidation.viewModels.LambdaRulesExampleViewModel;

public class LambdaRulesExampleActivity extends BaseActivity<ActivityLambdaExampleBinding, LambdaRulesExampleViewModel> {
    @Override
    protected int getLayout() {
        return R.layout.activity_lambda_example;
    }

    @Override
    protected LambdaRulesExampleViewModel createViewModel() {
        return new LambdaRulesExampleViewModel();
    }

    @Override
    protected void setViewModel(ActivityLambdaExampleBinding binding, LambdaRulesExampleViewModel viewModel) {
        binding.setViewModel(viewModel);
    }

    @Override
    protected Toolbar getToolbar() {
        return binding.toolbar;
    }
}
