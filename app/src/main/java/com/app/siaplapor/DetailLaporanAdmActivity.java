package com.app.siaplapor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailLaporanAdmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Detail Laporan");
        setContentView(R.layout.activity_detail_laporan_adm);
    }
}