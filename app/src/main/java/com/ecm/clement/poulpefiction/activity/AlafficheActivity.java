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

import com.ecm.clement.poulpefiction.R;

public class AlafficheActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_alaffiche);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            //List<Film> dataList =
           /** //Création de l'adapter
            FilmViewAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrings);

//Récupération du ListView présent dans notre IHM
            ListView list = (ListView)findViewById(R.id.ListView01);

//On passe nos données au composant ListView
            list.setAdapter(adapter);
**/

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
                Intent monIntent = new Intent(AlafficheActivity.this,AlafficheActivity.class);
                startActivity(monIntent);

            } else if (id == R.id.nav_prochainement) {
                Intent monIntent = new Intent(AlafficheActivity.this,ProchainnementActivity.class);
                startActivity(monIntent);

            } else if (id == R.id.nav_evennements) {
                Intent monIntent = new Intent(AlafficheActivity.this,EventActivity.class);
                startActivity(monIntent);

            } else if (id == R.id.nav_preferences) {
                Intent monIntent = new Intent(AlafficheActivity.this,SettingsActivity.class);
                startActivity(monIntent);

            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }