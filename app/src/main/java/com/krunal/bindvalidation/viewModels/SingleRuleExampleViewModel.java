package com.krunal.bindvalidation.viewModels;

import com.kevadiyakrunalk.bindValidation.forJava.Rule;
import com.kevadiyakrunalk.bindValidation.forJava.ValidatedObservableField;
import com.kevadiyakrunalk.bindValidation.forJava.rules.MinimumLengthRule;

public class SingleRuleExampleViewModel {

    public ValidatedObservableField<Integer> age = new ValidatedObservableField<>(null,
            new Rule<Integer>() {
                @Override
                public boolean isValid(Integer integer) {
                    return integer != null && integer >= 18;
                }

                @Override
                public String getErrorMessage() {
                    return "You have to be adult";
                }
            });

    public ValidatedObservableField<String> name = new ValidatedObservableField<>(null, new MinimumLengthRule(1, "Name cannot be empty"));
}
