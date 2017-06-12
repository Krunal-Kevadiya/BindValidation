package com.krunal.bindvalidation.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.krunal.bindvalidation.R;
import com.krunal.bindvalidation.databinding.ActivityMainListBinding;
import com.krunal.bindvalidation.viewModels.MainListViewModel;

public class MainListActivity extends AppCompatActivity {

    private ActivityMainListBinding binding;
    private MainListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_list);
        viewModel = new MainListViewModel(this);
        binding.setViewModel(viewModel);

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
    }
}
