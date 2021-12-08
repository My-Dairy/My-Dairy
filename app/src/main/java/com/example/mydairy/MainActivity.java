package com.example.mydairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mydairy.Fragment.GraphsFragment;
import com.example.mydairy.Fragment.HelpFragment;
import com.example.mydairy.Fragment.HomeFragment;
import com.example.mydairy.Fragment.ReportsFragment;
import com.example.mydairy.Fragment.SettingsFragment;
import com.example.mydairy.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private DrawerLayout drawer;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //done some changes

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary_color, this.getTheme()));
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView bottomNav = findViewById(R.id.nav_view);
        bottomNav.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        TextView MailPhone = bottomNav.getHeaderView(0).findViewById(R.id.mail_phn_txt);
        TextView FullName = bottomNav.getHeaderView(0).findViewById(R.id.txt_name);


        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();

        if(!user.getProviderId().equals("firebase"))
        {
            if(!user.getPhoneNumber().equals(""))
            {
                MailPhone.setText(user.getPhoneNumber());
            }
            else if(!user.getEmail().equals(""))
            {
                MailPhone.setText(user.getEmail());
            }
        }



        databaseReference = databaseReference.child("users").child(keyid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String name = snapshot.child("name").getValue().toString();
                String surname = snapshot.child("surname").getValue().toString();
                String fullname = name+" "+surname;

                FullName.setText(fullname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (savedInstanceState == null) {
            openFragment(new HomeFragment());
            bottomNav.setCheckedItem(R.id.nav_dashboard);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.nav_share:
                drawer.closeDrawer(GravityCompat.START);
                Intent int_share = new Intent(Intent.ACTION_SEND);
                int_share.setType("text/plain");
                int_share.putExtra(Intent.EXTRA_SUBJECT, "My-Dairy App");
                String message = "https://play.google.com/store/apps/details?="+BuildConfig.APPLICATION_ID+"\n\n";
                int_share.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(int_share,"Share via"));
                break;

            case R.id.nav_dashboard:
                openFragment(new HomeFragment());
                break;

            case R.id.nav_graph:
                openFragment(new GraphsFragment());
                break;

            case R.id.nav_report:
                openFragment(new ReportsFragment());
                break;

            case R.id.nav_help:
                openFragment(new HelpFragment());
                break;

            case R.id.nav_settings:
                openFragment(new SettingsFragment());
                break;

            default:
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            NavigationView bottomNav = findViewById(R.id.nav_view);
            openFragment(new HomeFragment());
            bottomNav.setCheckedItem(R.id.nav_dashboard);
        }
    }
}