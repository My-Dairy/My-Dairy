package com.example.mydairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mydairy.databinding.ActivityMainBinding;
import com.example.mydairy.ui.Dashboard.HomeFragment;
import com.example.mydairy.ui.Graph.GalleryFragment;
import com.example.mydairy.ui.Report.SlideshowFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
        //navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

//            @Override
//            public boolean onNavigationItemSelected(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.nav_dashboard:
//                        startActivity(new Intent(MainActivity.this, HomeFragment.class));
//                        break;
//                    case R.id.nav_graph:
//                        startActivity(new Intent(MainActivity.this, GalleryFragment.class));
//                        break;
//                }
//                drawer.closeDrawer(GravityCompat.START);
//                return true;
//
////                int i = item.getItemId();
////                if(i==R.id.nav_dashboard)
////                {
////                    Intent intent = new Intent(MainActivity.this, HomeFragment.class);
////                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                    MainActivity.this.startActivity(intent);
////                    //startActivity(new Intent(this, HomeFragment.class));
////                    //startActivity(new Intent(this, HomeFragment.class));
////                    //Toast.makeText(getApplicationContext(),"Dashboard",Toast.LENGTH_LONG).show();
////                    //drawer.closeDrawer(GravityCompat.START);
////                    drawer.closeDrawer(GravityCompat.START);
////                    return true;
////
////                }
////                if(i==R.id.nav_graph)
////                {
////                    Intent intent = new Intent(MainActivity.this, GalleryFragment.class);
////                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                    MainActivity.this.startActivity(intent);
////                    //Toast.makeText(getApplicationContext(),"Graph",Toast.LENGTH_SHORT).show();
////                    //drawer.closeDrawer(GravityCompat.START);
////                }
////                if(i==R.id.nav_report)
////                {
////                    Intent intent = new Intent(MainActivity.this, SlideshowFragment.class);
////                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                    MainActivity.this.startActivity(intent);
////
//////                    Intent it = new Intent(MainActivity.this, SlideshowFragment.class);
//////                    startActivity(it);
////                    //Toast.makeText(getApplicationContext(),"Reports",Toast.LENGTH_SHORT).show();
////                    //drawer.closeDrawer(GravityCompat.START);
////                }
////                if(i==R.id.nav_settings)
////                {
////
//////                    Intent it = new Intent(MainActivity.this, HomeFragment.class);
//////                    startActivity(it);
////                    //Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
////                    //drawer.closeDrawer(GravityCompat.START);
////                }
////                if(i==R.id.nav_share)
////                {
////
//////                    Intent it = new Intent(MainActivity.this, HomeFragment.class);
//////                    startActivity(it);
////                    //Toast.makeText(getApplicationContext(),"Share",Toast.LENGTH_SHORT).show();
////                    //drawer.closeDrawer(GravityCompat.START);
////                }
////                if(i==R.id.nav_help)
////                {
////
//////                    Intent it = new Intent(MainActivity.this, HomeFragment.class);
//////                    startActivity(it);
////                    //Toast.makeText(getApplicationContext(),"Help",Toast.LENGTH_SHORT).show();
////                    //drawer.closeDrawer(GravityCompat.START);
////                }
////
////                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
////                drawer.closeDrawer(GravityCompat.START);
////                return true;
//            }
 //       });
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


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = null;
        if(item.getItemId() == R.id.nav_dashboard)
        {
            intent = new Intent(this,HomeFragment.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId() == R.id.nav_graph)
        {
            intent = new Intent(this,GalleryFragment.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId() == R.id.nav_report)
        {
            intent = new Intent(this,SlideshowFragment.class);
            startActivity(intent);
            return true;
        }


        return false;
    }
}