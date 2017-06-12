package com.krunal.bindvalidation.viewModels;

import android.databinding.ObservableBoolean;
import android.view.View;
import android.widget.Toast;

import com.kevadiyakrunalk.bindValidation.forJava.RuleCommand;
import com.kevadiyakrunalk.bindValidation.forJava.ValidatedObservableField;
import com.kevadiyakrunalk.bindValidation.forJava.rules.MaximumLengthRule;
import com.kevadiyakrunalk.bindValidation.forJava.rules.MinimumLengthRule;
import com.kevadiyakrunalk.bindValidation.forJava.rules.RegexRule;

public class MultiRulesExampleViewModel {

    public final ObservableBoolean passwordVisible = new ObservableBoolean(false);

    public final ValidatedObservableField<String> userName = new ValidatedObservableField<>("",
            new RuleCommand.Builder<String>()
                    .withRule(new RegexRule("[a-z]+", "Only small letters")) // THE ORDER IS IMPORTANT!
                    .withRule(new MinimumLengthRule(3, "Three or more characters"))
                    .withRule(new MaximumLengthRule(12, "No more then twelve characters"))
                    .build());

    public final ValidatedObservableField<String> password = new ValidatedObservableField<>("",
            new RuleCommand.Builder<String>()
                    .withRule(new RegexRule("[\\S]+", "Whitespace characters not allowed")) // THE ORDER IS IMPORTANT!
                    .withRule(new RegexRule(".*[A-Z]+.*", "Must contain capital letters"))
                    .withRule(new RegexRule(".*[0-9]+.*", "Must contain digits"))
                    .withRule(new RegexRule(".*[a-z]+.*", "Must contain small letters"))
                    .withRule(new MinimumLengthRule(8, "Eight or more characters"))
                    .withRule(new MaximumLengthRule(16, "No more then sixteen characters"))
                    .build());

    public void onViewClick(View view) {
        if (userName.isValid() && password.isValid()) {
            Toast.makeText(view.getContext(), "ALL OK !", Toast.LENGTH_LONG).show();
        }
    }
}
