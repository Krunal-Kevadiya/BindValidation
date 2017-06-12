package com.krunal.bindvalidation.viewModels;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.krunal.bindvalidation.R;
import com.krunal.bindvalidation.activities.ChangePasswordExampleActivity;
import com.krunal.bindvalidation.activities.LambdaRulesExampleActivity;
import com.krunal.bindvalidation.activities.LoginExampleActivity;
import com.krunal.bindvalidation.activities.MultiRulesExampleActivity;
import com.krunal.bindvalidation.activities.SingleRuleExampleActivity;
import com.krunal.bindvalidation.activities.XmlExampleActivity;
import com.krunal.bindvalidation.adapters.ExampleRecycleViewAdapter;
import com.krunal.bindvalidation.models.ItemExample;

import java.util.ArrayList;
import java.util.List;

public class MainListViewModel {

    private final Context context;
    private final ExampleRecycleViewAdapter adapter = new ExampleRecycleViewAdapter(R.layout.item_example);

    public MainListViewModel(Context context) {
        this.context = context;
        adapter.setList(getList());
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    private List<ItemExample> getList() {
        List<ItemExample> list = new ArrayList<>();

        list.add(new ItemExample("Multi Rules",
                "[MultiRulesExampleActivity] Show multi rules example",
                new Intent(context, MultiRulesExampleActivity.class)));

        list.add(new ItemExample("Single Rules",
                "[SingleRuleExampleActivity] Show single rules example",
                new Intent(context, SingleRuleExampleActivity.class)));

        list.add(new ItemExample("Other fields validate",
                "[ChangePasswordExampleActivity] Show example to validate other fields",
                new Intent(context, ChangePasswordExampleActivity.class)));

        list.add(new ItemExample("OR rules validate",
                "[LoginExampleActivity] Show example with OR rules",
                new Intent(context, LoginExampleActivity.class)));

        list.add(new ItemExample("Show how use lambda",
                "[LambdaRulesExampleActivity] Show creating rules with lambda",
                new Intent(context, LambdaRulesExampleActivity.class)));

        list.add(new ItemExample("Show how use in xml",
                "[XMLExampleActivity] Show creating rules with xml file",
                new Intent(context, XmlExampleActivity.class)));
        return list;
    }
}
