package com.ecm.clement.poulpefiction.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ecm.clement.poulpefiction.R;
import com.ecm.clement.poulpefiction.data.DBHelper;
import com.ecm.clement.poulpefiction.data.VolleyApplication;
import com.ecm.clement.poulpefiction.models.Event;
import com.ecm.clement.poulpefiction.models.EventList;
import com.ecm.clement.poulpefiction.models.Film;
import com.ecm.clement.poulpefiction.models.FilmList;
import com.ecm.clement.poulpefiction.models.Seance;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    private DBHelper poulpePowerBDD;

    private static String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog progress;
    private boolean prochainementsRequestDone = false;
    private boolean eventsRequestDone = false;
    private boolean filmSeancesRequestDone = false;
    private boolean seancesRequestDone = false;

    private String eventURL = "http://centrale.corellis.eu/events.json";
    private String filmSeanceURL = "http://centrale.corellis.eu/filmseances.json";
    private String prochainnementURL = "http://centrale.corellis.eu/prochainement.json";
    private String seancesURL = "http://centrale.corellis.eu/seances.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //if(savedInstanceState!=null){
        //    Log.d("STATE", savedInstanceState.toString());
        //}

        progress = new ProgressDialog(this);
        progress.setMessage("En chargement.");
        progress.setCancelable(true);
        showProgress();

        final Gson gson = new Gson();


        requestQueue = Volley.newRequestQueue(this);

        if (is_UTD()==false) {
            if (isOnline()==true) {
                prochainementRequest();
                seancesRequest();
                filmSeancesRequest();
                eventRequest();
            } else noConnectionMessage();
        } else showNavigationActivity();
    }

    //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
    //        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    //drawer.setDrawerListener(toggle);
    //toggle.syncState();

    //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    //navigationView.setNavigationItemSelectedListener(this);
    //}

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

    private void addFilmToSQL(Film film, boolean is_Prochainement, boolean is_alafiche) {
        Gson gson = new Gson();
        try {
            if (!poulpePowerBDD.checkIfFilmIsAlreadyInDb(film)) {
                String media = gson.toJson(film.getMedias());
                String video = gson.toJson(film.getVideos());
                poulpePowerBDD.insertFilm(film.getId(), film.getTitre(), film.getTitre_vo(), film.getAffiche(), film.getWeb(), film.getDuree(), film.getDistributeur(), film.getParticipants(),
                        film.getRealisateur(), film.getSynopsis(), film.getAnnee(), film.getDate_sortie(), film.getInfo(), film.getIs_visible(), film.getIs_vente(), film.getGenreid(),
                        film.getCategorieid(), film.getGenre(), film.getCategorie(), film.getReleaseNumber(), film.getPays(), film.getShare_url(), media, video,
                        film.is_avp(), film.is_alaune(), film.is_lastWeek(), is_Prochainement, is_alafiche);
            } else {
                if (is_Prochainement) {

                    poulpePowerBDD.updateProchainement(film.getId());
                } else if (is_alafiche) poulpePowerBDD.updateAlaffiche(film.getId());
            }
        }catch(NullPointerException e){
            Log.d(TAG, "NullPointer Fucking Exception de ta mère que je sais pas d'où ça vient.");}
    }

    private void addEventToSQL(Event event, String titre_wrapped, String type_wrapped) {
        String films = event.getStringFilms();
        for (int i = 0; i < event.getFilms().size(); i++) {
            addFilmToSQL(event.getFilms().get(i), false, false);
        }
        poulpePowerBDD.insertEvent(event.getId(), event.getTitre(), event.getSoustitre(),
                event.getAffiche(), event.getDescription(), event.getVad_condition(),
                event.getPartenaires(), event.getDate_deb(), event.getDate_fin(), event.getHeure(),
                event.getContact(), event.getWeb_label(), event.getEvenementtpeid(),
                films, type_wrapped, titre_wrapped);
    }

    private void addSeanceToSQL(Seance seance) {
        poulpePowerBDD.insertSeance(seance.getId(), seance.getShow_time(), seance.getShow_time(),
                seance.is_troisd(), seance.is_malentendant(), seance.is_handicape(),
                seance.getNationality(), seance.getCinemaid(), seance.getFilmid(),
                seance.getTitre(), seance.getCategorieid(), seance.getPerformancied(), seance.getCinema_salle());
    }


    private void showNavigationActivity() {
        hideProgress();
        Intent intent = new Intent(this, MainNavigationActivity.class);
        startActivity(intent);
    }

    private void noConnectionMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("No internet connection.")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        showNavigationActivity();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void showProgress() {
        if (!progress.isShowing())
            progress.show();
    }

    private void hideProgress() {
        if (progress.isShowing())
            progress.dismiss();
    }

    private Boolean RequestsDone() {
        return (prochainementsRequestDone && eventsRequestDone && filmSeancesRequestDone && seancesRequestDone);
    }

    private void prochainementRequest() {
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                prochainnementURL, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(final JSONArray response) {
                        try {
                            JSONObject responseSingle = response.getJSONObject(0);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Gson gson = new Gson();
                        FilmList listFilmProchainement = gson.fromJson(response.toString(), FilmList.class);
                        if (listFilmProchainement != null) {
                            for (int i = 0; i < listFilmProchainement.getFilmsSeanceList().size(); i++) {
                                Film film = listFilmProchainement.getFilmsSeanceList().get(i);
                                addFilmToSQL(film, true, false); //true correspond à is_prochainement
                            }
                        }
                        prochainementsRequestDone = true;
                        if (RequestsDone()) showNavigationActivity();
                    }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "failed to retrieve seances", Toast.LENGTH_SHORT).show();
                showNavigationActivity();
            }
        });
        // Adding request to request queue
        VolleyApplication.getInstance().addToRequestQueue(jsonArrReq);
    }


    private void seancesRequest() {
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                seancesURL, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(final JSONArray response) {
                        Gson gson = new Gson();
                        JsonParser parser = new JsonParser();
                        JsonArray jArray = parser.parse(response.toString()).getAsJsonArray();
                        for (JsonElement obj : jArray) {
                            Seance seance = gson.fromJson(obj, Seance.class);
                            addSeanceToSQL(seance);

                        }
                        seancesRequestDone = true;
                        if (RequestsDone()) showNavigationActivity();
                    }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "failed to retrieve seances", Toast.LENGTH_SHORT).show();
                showNavigationActivity();
            }
        });
        // Adding request to request queue
        VolleyApplication.getInstance().addToRequestQueue(jsonArrReq);
    }

    private void filmSeancesRequest() {
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                filmSeanceURL, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(final JSONArray response) {
                        Gson gson = new Gson();
                        JsonParser parser = new JsonParser();
                        JsonArray jArray = parser.parse(response.toString()).getAsJsonArray();
                        for (JsonElement obj : jArray) {
                            Film film = gson.fromJson(obj, Film.class);
                            addFilmToSQL(film, false, true); // false car le film n'est pas "prochainement"
                        }
                        filmSeancesRequestDone = true;
                        if (RequestsDone()) showNavigationActivity();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "failed to retrieve Films", Toast.LENGTH_SHORT).show();
                showNavigationActivity();
            }
        });
        // Adding request to request queue
        VolleyApplication.getInstance().addToRequestQueue(jsonArrReq);
    }

    private void eventRequest() {
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                eventURL, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(final JSONArray response) {
                        Gson gson = new Gson();
                        JsonParser parser = new JsonParser();
                        JsonArray jArray = parser.parse(response.toString()).getAsJsonArray();
                        for (JsonElement obj : jArray) {
                            EventList eventWrapped = gson.fromJson(obj, EventList.class);
                            if (eventWrapped.getEvents() != null) {
                                for (int i = 0; i < eventWrapped.getEvents().size(); i++) {
                                    Event event = eventWrapped.getEvents().get(i);
                                    addEventToSQL(event, eventWrapped.getTitre(), eventWrapped.getType());
                                }
                            }
                        }
                        eventsRequestDone = true;
                        if (RequestsDone()) showNavigationActivity();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "failed to retrieve Events", Toast.LENGTH_SHORT).show();
                showNavigationActivity();
                // hide the progress dialog
            }
        });
        // Adding request to request queue
        VolleyApplication.getInstance().addToRequestQueue(jsonArrReq);
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private boolean is_UTD() {
        boolean UTD = false;
        String destPath = "/data/data/" + getPackageName()
                + "/databases/" + DBHelper.DATABASE_NAME;
        File f = new File(destPath);
        if (f.exists()) {
            UTD = poulpePowerBDD.numberOfRowsInFIlms() > 1;
        }
        return UTD;
    }
}