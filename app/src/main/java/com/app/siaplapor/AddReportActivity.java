package com.app.siaplapor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddReportActivity extends AppCompatActivity {
    private EditText nikField;
    private EditText nameField;
    private EditText addressField;
    private EditText phoneField;
    private EditText reportField;
    private Button btn_add;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);

        nikField = findViewById(R.id.nik);
        nameField = findViewById(R.id.nama);
        addressField = findViewById(R.id.alamat);
        phoneField = findViewById(R.id.telepon);
        reportField = findViewById(R.id.isi_laporan);
        btn_add = findViewById(R.id.btn_add);

        validateForm();
    }

    public void onAdd(View view) {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void validateForm() {
        boolean isNikNull = nikField.getText().toString().isEmpty();
        boolean isNameNull = nameField.getText().toString().isEmpty();
        boolean isAddressNull = addressField.getText().toString().isEmpty();
        boolean isPhoneNull = phoneField.getText().toString().isEmpty();
        boolean isReportNull = reportField.getText().toString().isEmpty();

        if (!(isNikNull && isNameNull && isAddressNull && isPhoneNull && isReportNull)) {
            btn_add.setBackground(getDrawable(R.drawable.blue_button));
            btn_add.setEnabled(true);
            btn_add.setClickable(true);
        } else {
            btn_add.setBackground(getDrawable(R.drawable.disabled_button));
            btn_add.setEnabled(false);
            btn_add.setClickable(false);
        }
    }
}