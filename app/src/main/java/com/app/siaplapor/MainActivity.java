package com.app.siaplapor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.app.siaplapor.data.session.SessionRepository;
import com.app.siaplapor.data.session.UserSessionRepository;
import com.app.siaplapor.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private SessionRepository userRepository;
    User userLogin;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_logout:
                logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        userRepository.destroy();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    //    BottomNavigationView bottom_nav;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        bottom_nav = (BottomNavigationView) findViewById(R.id.bottom_nav);
//        bottom_nav.setOnNavigationItemSelectedListener(navListener);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                new DashboardAdminFragment()).commit();
//    }
//
//    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    Fragment selected = null;
//                    switch (item.getItemId()) {
//                        case R.id.dashboard:
//                            selected = new DashboardAdminFragment();
//                            break;
//                        case R.id.tambah_laporan:
//                            selected = new AddLaporanFragment();
//                            break;
//                    }
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                            selected).commit();
//                    return true;
//                }
//            };

    BottomNavigationView bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userRepository =  new UserSessionRepository(getApplicationContext());
        userLogin = (User) userRepository.getSessionData();
        bottom_nav = (BottomNavigationView)findViewById(R.id.bottom_nav);
        bottom_nav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new DashboardUserFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selected = null;
                    switch (item.getItemId()){
                        case  R.id.dashboard:
                            selected = new DashboardUserFragment();
                            break;
                        case  R.id.tambah_laporan:
                            selected = new AddLaporanFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selected).commit();
                    return true;
                }
            };
}