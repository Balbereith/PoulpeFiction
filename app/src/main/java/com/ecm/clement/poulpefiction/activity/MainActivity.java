package com.ecm.clement.poulpefiction.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ecm.clement.poulpefiction.R;
import com.ecm.clement.poulpefiction.data.DBHelper;
import com.ecm.clement.poulpefiction.models.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RequestQueue requestQueue;
    private DBHelper poulpePowerBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(savedInstanceState!=null){
            Log.d("STATE", savedInstanceState.toString());
        }

        String eventURL="http://centrale.corellis.eu/events.json";
        String filmSeancesURL = "http://centrale.corellis.eu/filmseances.json";
        String prochainementURL = "http://centrale.corellis.eu/prochainement.json";
        String seancesURL = "http://centrale.corellis.eu/seances.json";

        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest eventRequest = new JsonObjectRequest(Request.Method.GET, eventURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{
                            Gson gson = new Gson();
                            JsonParser parser = new JsonParser();
                            JsonArray jArray = parser.parse(response.toString()).getAsJsonArray();
                            for(JsonElement obj : jArray )
                            {
                                EventList eventList = gson.fromJson( obj , EventList.class);
                                if(eventList.getEvents() != null){
                                    for(int i = 0; i< eventList.getEvents().size(); i++)
                                    {
                                        Event event= eventList.getEvents().get(i);
                                        addEventToSQL(event, eventList.getTitre(), eventList.getType());
                                    }
                                }
                            }
                        }catch(JSONException e1){e1.printStackTrace();}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley","Error");
                    }
                }
        );
        JsonObjectRequest filmSeancesRequest = new JsonObjectRequest(Request.Method.GET, filmSeancesURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{
                            Gson gson = new Gson();
                            JsonParser parser = new JsonParser();
                            JsonArray jArray = parser.parse(response.toString()).getAsJsonArray();
                            for (JsonElement obj : jArray) {
                                Film film = gson.fromJson(obj, Film.class);
                                addFilmToSQL(film, false, true);
                            }
                        }catch(JSONException e){e.printStackTrace();}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley","Error");
                    }
                }
        );
        JsonObjectRequest prochainnementRequest = new JsonObjectRequest(Request.Method.GET, prochainementURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            Gson gson = new Gson();
                            FilmList listFilmProchainement = gson.fromJson(response.toString(), FilmList.class);
                            if (listFilmProchainement != null) {
                                for (int i = 0; i < listFilmProchainement.getFilmsSeanceList().size(); i++) {
                                    Film film = listFilmProchainement.getFilmsSeanceList().get(i);
                                    addFilmToSQL(film, true, false); //true correspond à is_prochainement
                                }
                            }
                        }catch(JSONException e){e.printStackTrace();}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley","Error");
                    }
                }
        );
        JsonObjectRequest seancesRequest = new JsonObjectRequest(Request.Method.GET, seancesURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            Gson gson = new Gson();
                            JsonParser parser = new JsonParser();
                            JsonArray jArray = parser.parse(response.toString()).getAsJsonArray();
                            for (JsonElement obj : jArray) {
                                Seance seance = gson.fromJson(obj, Seance.class);
                                addSeanceToSQL(seance);

                            }
                        }catch(JSONException e){e.printStackTrace();}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley","Error");
                    }
                }
        );
        requestQueue.add(eventRequest);
        requestQueue.add(filmSeancesRequest);
        requestQueue.add(prochainnementRequest);
        requestQueue.add(seancesRequest);

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

    private void addFilmToSQL(Film film, boolean is_Prochainement, boolean is_alafiche){
        Gson gson = new Gson();
        String media = gson.toJson(film.getMedias());
        String video = gson.toJson(film.getVideos());
        poulpePowerBDD.insertFilm(film.getId(), film.getTitre(), film.getTitre_vo(), film.getAffiche(),
                film.getWeb(), film.getDuree(), film.getDistributeur(), film.getParticipants(),
                    film.getRealisateur(), film.getSynopsis(), film.getAnnee(), film.getDate_sortie(),
                film.getInfo(), film.getIs_visible(), film.getIs_vente(), film.getGenreid(),
                    film.getCategorieid(), film.getGenre(), film.getCategorie(), film.getReleaseNumber(),
                film.getPays(), film.getShare_url(), media, video,
                    film.is_avp(), film.is_alaune(), film.is_lastWeek(), is_Prochainement, is_alafiche);}

    private void addEventToSQL(Event event, String titre_wrapped, String type_wrapped){
        String films= event.getStringFilms();
        for(int i = 0;i<event.getFilms().size();i++){
            addFilmToSQL(event.getFilms().get(i),false,false);
        }
        poulpePowerBDD.insertEvent(event.getId(),event.getTitre(),event.getSoustitre(),
                event.getAffiche(),event.getDescription(),event.getVad_condition(),
                event.getPartenaires(),event.getDate_deb(),event.getDate_fin(),event.getHeure(),
                event.getContact(),event.getWeb_label(),event.getEvenementtpeid(),
                films, type_wrapped,titre_wrapped);
    }

    private void addSeanceToSQL(Seance seance){
        poulpePowerBDD.insertSeance(seance.getId(), seance.getShow_time(), seance.getShow_time(),
                seance.is_troisd(), seance.is_malentendant(), seance.is_handicape(),
                seance.getNationality(), seance.getCinemaid(), seance.getFilmid(),
                seance.getTitre(), seance.getCategorieid(), seance.getPerformancied(), seance.getCinema_salle());
    }

}