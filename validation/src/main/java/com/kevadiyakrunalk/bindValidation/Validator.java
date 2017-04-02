package com.kevadiyakrunalk.bindValidation;

import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;

import com.kevadiyakrunalk.bindValidation.rule.Rule;
import com.kevadiyakrunalk.bindValidation.util.ValidatorListener;
import com.kevadiyakrunalk.bindValidation.util.ViewTagHelper;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator {
    public static Validator sSingleton;

    private static final int FIELD_VALIDATION_MODE = 0;
    private static final int FORM_VALIDATION_MODE = 1;

    private ViewDataBinding target;
    public static ValidatorListener listener;

    private int mode = FIELD_VALIDATION_MODE;
    private final Set<View> disabledViews;
    private int[] keyPress;

    public static Validator getInstance(ViewDataBinding target) {
        if (sSingleton == null) {
            synchronized (Validator.class) {
                if (sSingleton == null) {
                    sSingleton = new Validator(target);
                }
            }
        }
        return sSingleton;
    }

    public static Validator getInstance(ViewDataBinding target, ValidatorListener validatorListener) {
        if (sSingleton == null) {
            synchronized (Validator.class) {
                if (sSingleton == null) {
                    sSingleton = new Validator(target, validatorListener);
                }
            }
        }
        return sSingleton;
    }

    private Validator(ViewDataBinding target) {
        this.target = target;
        listener = null;
        this.disabledViews = new HashSet<>();
    }

    private Validator(ViewDataBinding target, ValidatorListener validatorListener) {
        this.target = target;
        listener = validatorListener;
        this.disabledViews = new HashSet<>();
    }

    public void setKeyPress(int... keys) {
        keyPress = keys;
    }

    public int[] getKeyPress() { return keyPress; }

    public boolean validate() {
        List<View> viewWithValidations = getViewsWithValidation();
        return isAllViewsValid(viewWithValidations);
    }

    public boolean validate(View view) {
        List<View> viewWithValidations = getViewsWithValidation(view);
        return isAllViewsValid(viewWithValidations);
    }

    public <ViewType extends View> boolean validate(List<ViewType> views) {
        List<View> viewWithValidations = getViewsWithValidation(views);
        return isAllViewsValid(viewWithValidations);
    }

    public boolean isViewValid(View viewWithValidations) {
        boolean allViewsValid = true;
        boolean viewValid = true;
        List<Rule> rules = (List) viewWithValidations.getTag(R.id.validator_rule);
        for (Rule rule : rules) {
            viewValid = viewValid && isRuleValid(rule);
            allViewsValid = allViewsValid && viewValid;

            if(listener != null && !allViewsValid)
                break;
        }

        return allViewsValid;
    }

    private boolean isAllViewsValid(List<View> viewWithValidations) {
        boolean allViewsValid = true;
        for (View viewWithValidation : viewWithValidations) {
            boolean viewValid = true;
            List<Rule> rules = (List) viewWithValidation.getTag(R.id.validator_rule);
            for (Rule rule : rules) {
                viewValid = viewValid && isRuleValid(rule);
                allViewsValid = allViewsValid && viewValid;

                if(listener != null && !allViewsValid)
                    break;
            }

            if(mode == FIELD_VALIDATION_MODE && !viewValid)
                break;

            if(listener != null && !allViewsValid)
                break;
        }
        return allViewsValid;
    }

    private boolean isRuleValid(Rule rule) {
        return disabledViews.contains(rule.getView()) || rule.validate();
    }

    public void disableValidation(View view) {
        disabledViews.add(view);
    }

    public void enableValidation(View view) {
        disabledViews.remove(view);
    }

    public void enableFormValidationMode() {
        this.mode = FORM_VALIDATION_MODE;
    }

    public void enableFieldValidationMode() {
        this.mode = FIELD_VALIDATION_MODE;
    }

    private List<View> getViewsWithValidation() {
        if(target.getRoot() instanceof ViewGroup) {
            return ViewTagHelper.getViewsByTag((ViewGroup) target.getRoot(), R.id.validator_rule);
        }
        return Collections.singletonList(target.getRoot());
    }

    private <ViewType extends View> List<View> getViewsWithValidation(List<ViewType> views) {
        return ViewTagHelper.filterViewsWithTag(R.id.validator_rule, views);
    }

    private List<View> getViewsWithValidation(View view) {
        return ViewTagHelper.filterViewWithTag(R.id.validator_rule, view);
    }
}
