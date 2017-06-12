package com.krunal.bindvalidation.viewModels;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.kevadiyakrunalk.bindValidation.forXml.Validator;
import com.krunal.bindvalidation.databinding.ActivityMultiRulesExampleBinding;
import com.krunal.bindvalidation.databinding.ActivityXmlExampleBinding;

import java.util.Arrays;

public class XmlExampleViewModel {
    private Context context;
    private ActivityXmlExampleBinding binding;
    private Validator validator;

    public XmlExampleViewModel(Context context) {
        this.context = context;

    }

    public void setBinding(ActivityXmlExampleBinding binding1) {
        binding = binding1;
        validator = Validator.getInstance(binding);
        validator.setKeyPress(EditorInfo.IME_ACTION_DONE, EditorInfo.IME_ACTION_NEXT);
        validator.enableFormValidationMode();
    }

    public void onAllViewClick(View view) {
        if (validator.validate()) {
            saveToDatabase();
        } else {
            Toast.makeText(context, "Dados inválidos!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onNameViewClick(View view) {
        validator.validate(binding.name);
    }

    public void onMultipleViewClick(View view) {
        validator.validate(Arrays.asList(binding.username, binding.email));
    }

    private void saveToDatabase() {
        Log.i("XML EXAMPLE", "Salvar os dados no banco de dados");
    }
}
