package com.ecm.clement.poulpefiction;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        JsonObjectRequest requestEvents = new JsonObjectRequest("http://centrale.corellis.eu/events.json", null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                       // mTextView.setText(response.toString());
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Pas de connexion !",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );
        JsonObjectRequest requestFilms = new JsonObjectRequest("http://centrale.corellis.eu/filmseances.json", null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Pas de connexion !",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );
        JsonObjectRequest requestProchainnements = new JsonObjectRequest("http://centrale.corellis.eu/prochainement.json", null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Pas de connexion !",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );

        JsonObjectRequest requestSeances = new JsonObjectRequest("http://centrale.corellis.eu/seances.json", null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Pas de connexion !",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );

        VolleyApplication.getInstance().getRequestQueue().add(requestEvents);
        VolleyApplication.getInstance().getRequestQueue().add(requestFilms);
        VolleyApplication.getInstance().getRequestQueue().add(requestProchainnements);
        VolleyApplication.getInstance().getRequestQueue().add(requestSeances);

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
            Intent monIntent = new Intent(MainActivity.this,AlafficheActivity.class);
            startActivity(monIntent);

        } else if (id == R.id.nav_prochainement) {
            Intent monIntent = new Intent(MainActivity.this,ProchainnementActivity.class);
            startActivity(monIntent);

        } else if (id == R.id.nav_evennements) {
            Intent monIntent = new Intent(MainActivity.this,EventActivity.class);
            startActivity(monIntent);

        } else if (id == R.id.nav_preferences) {
            Intent monIntent = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(monIntent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
