package com.example.ndpsh.prueba_09.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ndpsh.prueba_09.Fragments.AlertsFragment;
import com.example.ndpsh.prueba_09.Fragments.EmailFragment;
import com.example.ndpsh.prueba_09.Fragments.InfoFragment;
import com.example.ndpsh.prueba_09.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navview);

        setFragmentsByDefault();

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                Toast.makeText(MainActivity.this, "Open", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                Toast.makeText(MainActivity.this, "Close", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                boolean fragmentsTransaction = false;
                Fragment fragment = null;

                switch (menuItem.getItemId()) {
                    case R.id.menu_mail:
                        fragment = new EmailFragment();
                        fragmentsTransaction = true;

                        break;
                    case R.id.menu_alert:
                        fragment = new AlertsFragment();
                        fragmentsTransaction = true;

                        break;
                    case R.id.menu_info:
                        fragment = new InfoFragment();
                        fragmentsTransaction = true;

                        break;
                    case R.id.menu_option_1:
                        Toast.makeText(MainActivity.this, "Has clikeado en la opcion 1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_option_2:
                        Toast.makeText(MainActivity.this, "Has clikeado en la opcion 2", Toast.LENGTH_SHORT).show();
                        break;

                }

                if (fragmentsTransaction){
                    changeFragment(fragment,menuItem);
                    drawerLayout.closeDrawers();
                }

                return true;
            }
        });
    }

    private void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Para habilitar en la barra justo al lado del nombre
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void changeFragment (Fragment fragment, MenuItem menuItem){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        menuItem.setChecked(true);
        getSupportActionBar().setTitle(menuItem.getTitle());

    }

    private void setFragmentsByDefault(){
        changeFragment(new EmailFragment(), navigationView.getMenu().getItem(0));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                // abrir el nav_options lateral
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
