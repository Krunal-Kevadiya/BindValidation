package com.krunal.bindvalidation.activities;

import android.support.v7.widget.Toolbar;

import com.krunal.bindvalidation.BaseActivity;
import com.krunal.bindvalidation.R;
import com.krunal.bindvalidation.databinding.ActivityXmlExampleBinding;
import com.krunal.bindvalidation.viewModels.XmlExampleViewModel;

public class XmlExampleActivity extends BaseActivity<ActivityXmlExampleBinding, XmlExampleViewModel> {

    @Override
    protected int getLayout() {
        return R.layout.activity_xml_example;
    }

    @Override
    protected XmlExampleViewModel createViewModel() {
        return new XmlExampleViewModel(this);
    }

    @Override
    protected void setViewModel(ActivityXmlExampleBinding binding, XmlExampleViewModel viewModel) {
        binding.setViewModel(viewModel);
    }

    @Override
    protected Toolbar getToolbar() {
        return binding.toolbar;
    }
}
