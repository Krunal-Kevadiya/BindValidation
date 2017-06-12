package com.krunal.bindvalidation.activities;

import android.support.v7.widget.Toolbar;

import com.krunal.bindvalidation.BaseActivity;
import com.krunal.bindvalidation.R;
import com.krunal.bindvalidation.databinding.ActivityChangePasswordBinding;
import com.krunal.bindvalidation.viewModels.ChangePasswordViewModel;

public class ChangePasswordExampleActivity extends BaseActivity<ActivityChangePasswordBinding, ChangePasswordViewModel> {
    @Override
    protected int getLayout() {
        return R.layout.activity_change_password;
    }

    @Override
    protected ChangePasswordViewModel createViewModel() {
        return new ChangePasswordViewModel();
    }

    @Override
    protected void setViewModel(ActivityChangePasswordBinding binding, ChangePasswordViewModel viewModel) {
        binding.setViewModel(viewModel);
    }

    @Override
    protected Toolbar getToolbar() {
        return binding.toolbar;
    }
}
