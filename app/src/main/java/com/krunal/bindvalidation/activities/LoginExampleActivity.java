package com.krunal.bindvalidation.activities;

import android.support.v7.widget.Toolbar;

import com.krunal.bindvalidation.BaseActivity;
import com.krunal.bindvalidation.R;
import com.krunal.bindvalidation.databinding.ActivityLoginExampleBinding;
import com.krunal.bindvalidation.viewModels.LoginExampleViewModel;

public class LoginExampleActivity extends BaseActivity<ActivityLoginExampleBinding, LoginExampleViewModel> {
    @Override
    protected int getLayout() {
        return R.layout.activity_login_example;
    }

    @Override
    protected LoginExampleViewModel createViewModel() {
        return new LoginExampleViewModel();
    }

    @Override
    protected void setViewModel(ActivityLoginExampleBinding binding, LoginExampleViewModel viewModel) {
        binding.setViewModel(viewModel);
    }

    @Override
    protected Toolbar getToolbar() {
        return binding.toolbar;
    }
}
