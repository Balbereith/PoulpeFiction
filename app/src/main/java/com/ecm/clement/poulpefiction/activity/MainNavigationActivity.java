package com.ecm.clement.poulpefiction.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ecm.clement.poulpefiction.R;

public class MainNavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnAff = (Button)findViewById(R.id.btnAff);
        Button btnPro = (Button)findViewById(R.id.btnPro);
        Button btnEve = (Button)findViewById(R.id.btnEve);
        Button btnPref = (Button)findViewById(R.id.btnPref);
        btnAff.setOnClickListener(new  OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent monIntent = new Intent(MainNavigationActivity.this, AlafficheActivity.class);
                startActivity(monIntent);
            }
        });
        btnPro.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent monIntent = new Intent(MainNavigationActivity.this, ProchainnementActivity.class);
                startActivity(monIntent);
            }
        });
        btnEve.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent monIntent = new Intent(MainNavigationActivity.this, EventActivity.class);
                startActivity(monIntent);
            }
        });
        btnPref.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent monIntent = new Intent(MainNavigationActivity.this, SettingsActivity.class);
                startActivity(monIntent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_alAffiche) {
            Intent monIntent = new Intent(MainNavigationActivity.this,AlafficheActivity.class);
            startActivity(monIntent);

        } else if (id == R.id.nav_prochainement) {
            Intent monIntent = new Intent(MainNavigationActivity.this,ProchainnementActivity.class);
            startActivity(monIntent);

        } else if (id == R.id.nav_evennements) {
            Intent monIntent = new Intent(MainNavigationActivity.this,EventActivity.class);
            startActivity(monIntent);

        } else if (id == R.id.nav_preferences) {
            Intent monIntent = new Intent(MainNavigationActivity.this,SettingsActivity.class);
            startActivity(monIntent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

