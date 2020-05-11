package com.sih.sihsignupmodule;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

        DrawerLayout drawerLayout;
        NavigationView navigationView;
        // Toolbar toolbar;
        Menu menu;
        TextView textView;

        CardView shop,wallet;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        //   toolbar=findViewById(R.id.toolbar);

        //  setSupportActionBar(toolbar);

        navigationView.bringToFront();


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
        (
        this,
        drawerLayout,
        R.string.navigation_drawer_open,
        R.string.navigation_drawer_close
        )
        {
        };
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();



        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        menu = navigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(false);
        menu.findItem(R.id.nav_profile).setVisible(false);

        shop=findViewById(R.id.shop);
        shop.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Intent intent=new Intent(DashboardActivity.this,ShopListActivity.class);
        startActivity(intent);

        }
        });

        wallet=findViewById(R.id.wallet);
        wallet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent=new Intent(DashboardActivity.this,WalletActivity.class);
                        startActivity(intent);

                }
        });

        }

@Override
public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
        drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {super.onBackPressed();
        }
        }


public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

        case R.id.nav_home:
        Toast.makeText(DashboardActivity.this, "Home Click", Toast.LENGTH_SHORT).show();

        break; case R.id.profile:
        Toast.makeText(DashboardActivity.this, "profile Click", Toast.LENGTH_SHORT).show();

        break;
        case R.id.nav_buy:
        Toast.makeText(DashboardActivity.this, "BUY Click", Toast.LENGTH_SHORT).show();
        break;
        case R.id.nav_faq:
        Toast.makeText(DashboardActivity.this, "FAQ Click", Toast.LENGTH_SHORT).show();
        break;
        case R.id.nav_login:
        menu.findItem(R.id.nav_logout).setVisible(true);
        menu.findItem(R.id.nav_profile).setVisible(true);
        menu.findItem(R.id.nav_login).setVisible(false);
        break;
        case R.id.nav_logout: menu.findItem(R.id.nav_logout).setVisible(false);
        menu.findItem(R.id.nav_profile).setVisible(false);
        menu.findItem(R.id.nav_login).setVisible(true);
        break;
        case R.id.nav_share: Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show(); break;
        }
        drawerLayout.closeDrawer(GravityCompat.START); return true;
        }


        }