package com.example.mydairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mydairy.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //done some changes

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarSidebar.toolbar);
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#5e9c00")));

        binding.appBarSidebar.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_dashboard, R.id.nav_graph, R.id.nav_report, R.id.nav_settings, R.id.nav_share, R.id.nav_help)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_sidebar);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int i = item.getItemId();
                if(i==R.id.nav_dashboard)
                {
                    Toast.makeText(getApplicationContext(),"Dashboard",Toast.LENGTH_LONG).show();
                    drawer.closeDrawer(GravityCompat.START);
                }
                else if(i==R.id.nav_graph)
                {
                    Toast.makeText(getApplicationContext(),"Graph",Toast.LENGTH_SHORT).show();
                    drawer.closeDrawer(GravityCompat.START);
                }
                else if(i==R.id.nav_report)
                {
                    Toast.makeText(getApplicationContext(),"Reports",Toast.LENGTH_SHORT).show();
                    drawer.closeDrawer(GravityCompat.START);
                }
                else if(i==R.id.nav_settings)
                {
                    Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
                    drawer.closeDrawer(GravityCompat.START);
                }
                else if(i==R.id.nav_share)
                {
                    Toast.makeText(getApplicationContext(),"Share",Toast.LENGTH_SHORT).show();
                    drawer.closeDrawer(GravityCompat.START);
                }
                else if(i==R.id.nav_help)
                {
                    Toast.makeText(getApplicationContext(),"Help",Toast.LENGTH_SHORT).show();
                    drawer.closeDrawer(GravityCompat.START);
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sidebar, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_sidebar);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}