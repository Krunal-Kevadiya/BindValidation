package com.krunal.bindvalidation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.security.keystore.KeyInfo;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.kevadiyakrunalk.bindValidation.Validator;
import com.kevadiyakrunalk.bindValidation.util.ValidatorListener;
import com.krunal.bindvalidation.databinding.ActivityMainBinding;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.validateName.setOnClickListener(onValidateNameClickListener);
        binding.validateMultiple.setOnClickListener(onValidateMultipleClickListener);
        binding.validate.setOnClickListener(onValidateAllClickListener);

        /*validator = new Validator(binding, new ValidatorListener() {
            @Override
            public void onValidatorError(TextView textView, String errorMessage) {
                Toast.makeText(textView.getContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });*/
        validator = Validator.getInstance(binding);
        validator.setKeyPress(EditorInfo.IME_ACTION_DONE, EditorInfo.IME_ACTION_NEXT);
        validator.enableFormValidationMode();
    }

    private View.OnClickListener onValidateNameClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            validator.validate(binding.name);
        }
    };

    private View.OnClickListener onValidateMultipleClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            validator.validate(Arrays.asList(binding.username, binding.email));
        }
    };

    private View.OnClickListener onValidateAllClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (validator.validate()) {
                saveToDatabase();
            } else {
                Toast.makeText(MainActivity.this, "Dados inválidos!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void saveToDatabase() {
        Log.i(TAG, "Salvar os dados no banco de dados");
    }
}
