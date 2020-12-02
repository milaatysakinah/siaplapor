package com.app.siaplapor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class DashboardAdminActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReportAdapter adapter;
    private ArrayList<Report> reportArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);

        addData();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new ReportAdapter(reportArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DashboardAdminActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.profile) {

        }
        if (item.getItemId() == R.id.logout) {

        }
        return true;
    }

    private void addData() {
        reportArrayList = new ArrayList<>();
        reportArrayList.add(new Report("2110181008", "Akbar", "Lamongan", "085706363468", "Lamongan Aman", "1/12/2020"));
        reportArrayList.add(new Report("2110181005", "Intan", "Jombang", "085748371929", "Jombang Aman", "3/12/2020"));
        reportArrayList.add(new Report("2110181013", "Nisar", "Surabaya", "081331758391", "Surabaya Aman", "5/12/2020"));
    }
}