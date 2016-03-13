package com.ecm.clement.poulpefiction.activity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ecm.clement.poulpefiction.R;
import com.ecm.clement.poulpefiction.data.DBHelper;
import com.ecm.clement.poulpefiction.models.Event;
import com.ecm.clement.poulpefiction.models.Film;
import com.ecm.clement.poulpefiction.models.Seance;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import Fragments.EventFragment;
import Fragments.FilmFragment;
import Fragments.FilmItemFragment;
import Fragments.ParametersFragment;


public class MainNavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FilmFragment.OnFilmSelectedListener, FilmItemFragment.OnFilmSelectedFragmentInteractionListener {

    private static String TAG = MainActivity.class.getSimpleName();
    private DBHelper poulpePowerBDD;
    private boolean isFilmFragmentVisible;
    private boolean isEventFragmentVisible;
    private boolean isProchainementFragmentVisible;
    private ProgressDialog pDialog;

    public static List<Film> listFilmToShow; // contient la list de tous les films à l'affiche
    public static List<Event> listEventToShow; // contient la list de tous les events
    public static List<Film> listFilmProchainement; // contient la liste de tous les films prochainement
    public static ArrayList<String> listSeances; // contient toutes les seances de la DB pour accélerer la recherche.
    public static List<Seance> listSeancesFilmSelected; // contient les seances projettant le film selectionné par l'utilisateur
    public static Film filmSelected; // film choisi par l'user
    public static Event eventSelected; // event choisi par l'user

    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        poulpePowerBDD = new DBHelper(this);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(true);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            showAlAfficheFragment();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setIcon(android.R.drawable.ic_menu_search);
        SearchView sv = new SearchView((this).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, sv);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {
        // Handle navigation view item clicks here.
        showpDialog();
        new Thread(new Runnable() {
            public void run() {
                int id = item.getItemId();
                if (id == R.id.nav_alAffiche) {
                    showAlAfficheFragment();
                    hidepDialog();

                } else if (id == R.id.nav_prochainement) {
                    if (listFilmToShow.size() != listFilmProchainement.size())
                        listFilmToShow = copyListFilm(listFilmProchainement);
                    showProchainementFragment();
                    hidepDialog();

                } else if (id == R.id.nav_evennements) {
                    listEventToShow = poulpePowerBDD.getAllEvents();
                    showEventFragment();
                    hidepDialog();

                } else if (id == R.id.nav_preferences) {
                    showPreferenceFragment();
                    hidepDialog();
                }
            }
        }).start();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void showAlAfficheFragment() {
        Fragment filmFragment = new FilmFragment();

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, filmFragment)
                .commit();
        isFilmFragmentVisible = true;
        isEventFragmentVisible = false;
        isProchainementFragmentVisible = false;
    }

    public void showProchainementFragment() {
        Fragment filmFragment = new FilmFragment();

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, filmFragment)
                .commit();
        isFilmFragmentVisible = false;
        isEventFragmentVisible = false;
        isProchainementFragmentVisible = true;
    }

    public void showEventFragment() {
        Fragment eventFragment = new EventFragment();

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, eventFragment)
                .commit();
        isFilmFragmentVisible = false;
        isEventFragmentVisible = true;
        isProchainementFragmentVisible = false;
    }

    public void showPreferenceFragment() {
        Fragment parametersFragment = new ParametersFragment();

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, parametersFragment)
                .commit();
        isFilmFragmentVisible = false;
        isEventFragmentVisible = false;
        isProchainementFragmentVisible = false;
    }

    public void showFilmSelectedFragment() {
        Fragment filmSelectedFragment = new FilmItemFragment();

        //Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, filmSelectedFragment)
                .commit();
    }

    @Override
    public void onFilmSelected(Film film) {
        filmSelected = film;
        listSeancesFilmSelected = getFilmSeances(filmSelected);
        showFilmSelectedFragment();
    }


    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        showpDialog();
        new Thread(new Runnable() {
            public void run() {
                if (listFilmToShow == null) listFilmToShow = poulpePowerBDD.getAllFilmAlAffiche();
                if (listEventToShow == null) listEventToShow = poulpePowerBDD.getAllEvents();
                if (listFilmProchainement == null)
                    listFilmProchainement = poulpePowerBDD.getAllFilmProchainement();
                if (listSeances == null) listSeances = poulpePowerBDD.getAllSeances();
                showAlAfficheFragment();
                hidepDialog();
            }
        }).start();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainNavigation Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.ecm.clement.poulpefiction.activity/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainNavigation Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.ecm.clement.poulpefiction.activity/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public List<Film> copyListFilm(List<Film> listToCopy) {
        List<Film> newList = new ArrayList<Film>();
        if (listToCopy.size() > 0) {
            for (int i = 0; i < listToCopy.size(); i++) {
                Film film = listToCopy.get(i);
                newList.add(film);
            }
        }
        return newList;
    }

    public List<Seance> getFilmSeances(Film film) {
        List<Seance> listSeance = new ArrayList<>();
        for (int i = 0; i < listSeance.size(); i++) {
            if (listSeance.get(i).getFilmid() == film.getId()) {listSeance.add(listSeance.get(i));}
        }
        return listSeance;
    }

    public void onEventSelected(Event event) {

    }

    @Override
    public void OnFilmSelectedFragmentInteraction(Uri uri) {

    }

}