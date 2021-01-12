package com.app.siaplapor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;

public class AddReportActivity extends AppCompatActivity {
    private EditText nikField;
    private EditText nameField;
    private EditText addressField;
    private EditText phoneField;
    private EditText reportField;
    private Button btn_add;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);

        nikField = findViewById(R.id.nik);
        nikField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateForm();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        nameField = findViewById(R.id.nama);
        phoneField = findViewById(R.id.telepon);
        reportField = findViewById(R.id.isi_laporan);
        btn_add = findViewById(R.id.btn_add);
    }

    public void onAdd(View view) {

    }

    public void validateForm() {
        boolean isNikNull = nikField.getText().toString().isEmpty();
        boolean isNameNull = nameField.getText().toString().isEmpty();
        boolean isPhoneNull = phoneField.getText().toString().isEmpty();
        boolean isReportNull = reportField.getText().toString().isEmpty();

        if (!(isNikNull || isNameNull || isPhoneNull || isReportNull)) {
            btn_add.setBackground(ContextCompat.getDrawable(context, R.drawable.blue_button));
            btn_add.setEnabled(true);
            btn_add.setClickable(true);
        } else {
            btn_add.setBackground(ContextCompat.getDrawable(context, R.drawable.disabled_button));
            btn_add.setEnabled(false);
            btn_add.setClickable(false);
        }
    }
}