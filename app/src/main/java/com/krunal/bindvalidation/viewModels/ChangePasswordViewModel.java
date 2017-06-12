package com.krunal.bindvalidation.viewModels;

import android.databinding.ObservableBoolean;
import android.view.View;
import android.widget.Toast;

import com.kevadiyakrunalk.bindValidation.forJava.FormValidatedObservableBoolean;
import com.kevadiyakrunalk.bindValidation.forJava.Rule;
import com.kevadiyakrunalk.bindValidation.forJava.RuleCommand;
import com.kevadiyakrunalk.bindValidation.forJava.ValidatedObservableField;
import com.kevadiyakrunalk.bindValidation.forJava.rules.MaximumLengthRule;
import com.kevadiyakrunalk.bindValidation.forJava.rules.MinimumLengthRule;
import com.kevadiyakrunalk.bindValidation.forJava.rules.RegexRule;

public class ChangePasswordViewModel {

    private RuleCommand.Builder<String> passwordRule = new RuleCommand.Builder<String>()
            .withRule(new RegexRule("[\\S]+", "Whitespace characters not allowed")) // THE ORDER IS IMPORTANT!
            .withRule(new RegexRule(".*[A-Z]+.*", "Must contain capital letters"))
            .withRule(new RegexRule(".*[0-9]+.*", "Must contain digits"))
            .withRule(new RegexRule(".*[a-z]+.*", "Must contain small letters"))
            .withRule(new MinimumLengthRule(8, "Eight or more characters"))
            .withRule(new MaximumLengthRule(16, "No more then sixteen characters"));
    //.build();

    public final ObservableBoolean passwordVisible = new ObservableBoolean(false);

    public final ValidatedObservableField<String> oldPassword = new ValidatedObservableField<>(
            "ThiIsMyOldPass09",
            passwordRule.build(),
            true);

    public final ValidatedObservableField<String> newPassword = new ValidatedObservableField<>("",
            passwordRule
                    .withRule(new Rule<String>() {
                        @Override
                        public boolean isValid(String s) {
                            return s != null && !s.equals(oldPassword.getValue());
                        }

                        @Override
                        public String getErrorMessage() {
                            return "Cannot be the same as old password";
                        }
                    })
                    .build());

    public final ValidatedObservableField<String> newPasswordRepeated = new ValidatedObservableField<>("",
            passwordRule
                    .withRule(new Rule<String>() {
                        @Override
                        public boolean isValid(String s) {
                            return s != null && s.equals(newPassword.getValue());
                        }

                        @Override
                        public String getErrorMessage() {
                            return "Have to be the same as new password";
                        }
                    })
                    .build());

    public final FormValidatedObservableBoolean validForm = new FormValidatedObservableBoolean(oldPassword, newPassword, newPasswordRepeated);

    public void onViewClick(View view) {

        oldPassword.setValue(newPassword.getValue());
        newPassword.setValue("");
        newPasswordRepeated.setValue("");

        newPassword.hideErrorMessage();
        newPasswordRepeated.hideErrorMessage();

        Toast.makeText(view.getContext(), "Password changed!", Toast.LENGTH_LONG).show();
    }
}
