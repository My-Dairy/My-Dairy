package com.example.mydairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
import android.widget.Toast;

import com.example.mydairy.Fragment.GraphsFragment;
import com.example.mydairy.Fragment.HelpFragment;
import com.example.mydairy.Fragment.HomeFragment;
import com.example.mydairy.Fragment.ReportsFragment;
import com.example.mydairy.Fragment.SettingsFragment;
import com.example.mydairy.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity{

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

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView bottomNav = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_dashboard, R.id.nav_graph, R.id.nav_report, R.id.nav_settings, R.id.nav_share, R.id.nav_help)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_sidebar);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(bottomNav, navController);

        bottomNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.nav_share)
                {
                    drawer.closeDrawer(GravityCompat.START);
                    Intent int_share = new Intent(Intent.ACTION_SEND);
                    int_share.setType("text/plain");
                    int_share.putExtra(Intent.EXTRA_SUBJECT, "My-Dairy App");
                    String message = "https://play.google.com/store/apps/details?="+BuildConfig.APPLICATION_ID+"\n\n";
                    int_share.putExtra(Intent.EXTRA_TEXT, message);
                    startActivity(Intent.createChooser(int_share,"Share via"));
                    return true;
                }

                return false;
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



    void openFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flframelayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}