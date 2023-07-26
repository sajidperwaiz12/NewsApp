package com.sajidperwaiz.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.sajidperwaiz.news.Fragments.BBCragment;
import com.sajidperwaiz.news.Fragments.CNNFragment;
import com.sajidperwaiz.news.Fragments.ESPNragment;
import com.sajidperwaiz.news.Fragments.FoxNewsFragment;
import com.sajidperwaiz.news.Fragments.NBCFragment;
import com.sajidperwaiz.news.Fragments.NewYorkTimesFragment;

public class MainActivity extends AppCompatActivity {
    private static final String ROOT_FRAGMENT_TAG = "root fragment";
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.openDrawer, R.string.clodeDrawer);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_bbc) {
                    loadFrag(new BBCragment(), 0);
                } else if (id == R.id.nav_nbc) {
                    loadFrag(new NBCFragment(), 1);
                } else if (id == R.id.nav_cnn) {
                    loadFrag(new CNNFragment(), 1);
                } else if (id == R.id.nav_fox) {
                    loadFrag(new FoxNewsFragment(), 1);
                } else if (id == R.id.nav_espn) {
                    loadFrag(new ESPNragment(), 1);
                } else {
                    Toast.makeText(MainActivity.this, "New York Times is coming soon!!", Toast.LENGTH_LONG).show();
                }

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            new AlertDialog.Builder(MainActivity.this)
                    .setIcon(R.drawable.baseline_exit_to_app_24)
                    .setTitle("Exit?")
                    .setMessage("Are you sure you want to exit this app?")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
        }
    }

    private void loadFrag(Fragment fragment, int flag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (flag == 0) {
            ft.add(R.id.f, fragment);
            fm.popBackStack(ROOT_FRAGMENT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ft.addToBackStack(ROOT_FRAGMENT_TAG);
        } else {
            ft.replace(R.id.f, fragment);
            ft.addToBackStack(null);
        }

        ft.commit();
    }

}