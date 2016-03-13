package com.ecm.clement.poulpefiction.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.ecm.clement.poulpefiction.R;
import com.ecm.clement.poulpefiction.data.DBHelper;
import com.ecm.clement.poulpefiction.data.VolleyApplication;
import com.ecm.clement.poulpefiction.models.Event;
import com.ecm.clement.poulpefiction.models.EventList;
import com.ecm.clement.poulpefiction.models.Film;
import com.ecm.clement.poulpefiction.models.FilmList;
import com.ecm.clement.poulpefiction.models.Seance;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

public class MainActivity extends Activity {

    private static String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;

    private String urlEvent = "http://centrale.corellis.eu/events.json";
    private String urlFilmSeances = "http://centrale.corellis.eu/filmseances.json";
    private String urlProchainement = "http://centrale.corellis.eu/prochainement.json";
    private String urlSeances = "http://centrale.corellis.eu/seances.json";

    private boolean prochainementsRequestIsDone = false;
    private boolean eventsRequestIsDone = false;
    private boolean filmSeancesRequestIsDone = false;
    private boolean seancesRequestIsDone = false;

    private DBHelper poulpePowerBDD;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(true);
        showpDialog();
        poulpePowerBDD = new DBHelper(this);

        if (!isUTD()) {
            if (isOnline()) {
                makeProchainementRequest();
                makeSeancesRequest();
                makeFilmSeancesRequest();
                makeEventRequest();
            } else noConnectionMessage();
        } else showMainActivity();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //showMainActivity();
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private boolean isUTD() {
        boolean dbUpToDate = false;
        String destPath = "/data/data/" + getPackageName()
                + "/databases/" + DBHelper.DATABASE_NAME;
        File f = new File(destPath);
        if (f.exists()) {
            dbUpToDate = poulpePowerBDD.filmsCount() > 1;
        }
        return dbUpToDate;
    }

    private void makeProchainementRequest() {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(JsonRequest.Method.POST, urlProchainement, (String)null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(final JSONObject response) {
                new Thread(new Runnable() {
                    public void run() {
                        Gson gson = new Gson();
                        FilmList listFilmProchainement = gson.fromJson(response.toString(), FilmList.class);
                        if (listFilmProchainement != null) {
                            for (int i = 0; i < listFilmProchainement.getFilmsSeanceList().size(); i++) {
                                Film film = listFilmProchainement.getFilmsSeanceList().get(i);
                                addFilmToDB(film, true, false); //true correspond à is_prochainement
                            }
                        }
                        prochainementsRequestIsDone = true;
                        if (checkRequestsAreDone()) showMainActivity();
                    }
                }).start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "failed to retrieve Upcomming Films", Toast.LENGTH_SHORT).show();
            }
        });
        // Adding request to request queue
        VolleyApplication.getInstance().addToRequestQueue(jsonObjReq);
    }

    private void makeSeancesRequest() {
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                urlSeances, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(final JSONArray response) {
                new Thread(new Runnable() {
                    public void run() {
                        Gson gson = new Gson();
                        JsonParser parser = new JsonParser();
                        JsonArray jArray = parser.parse(response.toString()).getAsJsonArray();
                        for (JsonElement obj : jArray) {
                            Seance seance = gson.fromJson(obj, Seance.class);
                            addSeanceToDB(seance);

                        }
                        seancesRequestIsDone = true;
                        if (checkRequestsAreDone()) showMainActivity();
                    }
                }).start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "failed to retrieve seances", Toast.LENGTH_SHORT).show();
            }
        });
        // Adding request to request queue
        VolleyApplication.getInstance().addToRequestQueue(jsonArrReq);
    }

    private void makeFilmSeancesRequest() {
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                urlFilmSeances, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(final JSONArray response) {
                new Thread(new Runnable() {
                    public void run() {
                        Gson gson = new Gson();
                        JsonParser parser = new JsonParser();
                        JsonArray jArray = parser.parse(response.toString()).getAsJsonArray();
                        for (JsonElement obj : jArray) {
                            Film film = gson.fromJson(obj, Film.class);
                            addFilmToDB(film, false, true); // false car le film n'est pas "prochainement"
                        }
                        filmSeancesRequestIsDone = true;
                        if (checkRequestsAreDone()) showMainActivity();
                    }
                }).start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "failed to retrieve Films", Toast.LENGTH_SHORT).show();
            }
        });
        // Adding request to request queue
        VolleyApplication.getInstance().addToRequestQueue(jsonArrReq);
    }

    private void makeEventRequest() {
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                urlEvent, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(final JSONArray response) {
                new Thread(new Runnable() {
                    public void run() {
                        Gson gson = new Gson();
                        JsonParser parser = new JsonParser();
                        JsonArray jArray = parser.parse(response.toString()).getAsJsonArray();
                        for (JsonElement obj : jArray) {
                            EventList eventWrapped = gson.fromJson(obj, EventList.class);
                            if (eventWrapped.getEvents() != null) {
                                for (int i = 0; i < eventWrapped.getEvents().size(); i++) {
                                    Event event = eventWrapped.getEvents().get(i);
                                    addEventToDB(event, eventWrapped.getTitre(), eventWrapped.getType());
                                }
                            }
                        }
                        eventsRequestIsDone = true;
                        if (checkRequestsAreDone()) showMainActivity();
                    }
                }).start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "failed to retrieve Events", Toast.LENGTH_SHORT).show();
            }
        });
        // Adding request to request queue
        VolleyApplication.getInstance().addToRequestQueue(jsonArrReq);
    }

    private void addFilmToDB(Film film, boolean is_Prochainement, boolean is_alafiche) {
        Gson gson = new Gson();
        if (!poulpePowerBDD.checkFilmInDb(film.getId())) {
            String media = gson.toJson(film.getMedias());
            String video = gson.toJson(film.getVideos());
            poulpePowerBDD.insertFilm(film.getId(), film.getTitre(), film.getTitre_ori(), film.getAffiche(), film.getWeb(), film.getDuree(), film.getDistributeur(), film.getParticipants(),
                    film.getRealisateur(), film.getSynopsis(), film.getAnnee(), film.getDate_sortie(), film.getInfo(), film.getIs_visible(), film.getIs_vente(), film.getGenreid(),
                    film.getCategorieid(), film.getGenre(), film.getCategorie(), film.getReleaseNumber(), film.getPays(), film.getShare_url(), media, video,
                    film.getIs_avp(), film.getIs_alaune(), film.getIs_lastWeek(), is_Prochainement, is_alafiche);
        } else {
            if (is_Prochainement) {
                poulpePowerBDD.updateProchainement(film.getId());
            } else if (is_alafiche) poulpePowerBDD.updateAffiche(film.getId());
        }
    }

    private void addEventToDB(Event event, String titre_wrapped, String type_wrapped) {
        String films = event.getFilmsIdAsString();
        //Rajoute les films qui ne sont que dans des événements à la DB
        for (int i = 0; i < event.getFilms().size(); i++) {
            addFilmToDB(event.getFilms().get(i), false, false);
        }
        poulpePowerBDD.insertEvent(event.getId(), event.getTitre(), event.getSoustitre(), event.getAffiche(), event.getDescription(), event.getVad_condition(),
                event.getPartenaire(), event.getDate_deb(), event.getDate_fin(), event.getHeure(), event.getContact(), event.getWeb_label(), event.getEvenementtypeid(),
                films, type_wrapped, titre_wrapped);
    }

    private void addSeanceToDB(Seance seance) {
        poulpePowerBDD.insertSeance(seance.getId(), seance.getActual_date(), seance.getShow_time(), seance.getIs_troisd(), seance.getIs_malentendant(), seance.getIs_handicape(),
                seance.getNationality(), seance.getCinemaid(), seance.getFilmid(), seance.getTitre(), seance.getCategorieid(), seance.getPerformanceid(), seance.getCinema_salle());
    }

    private Boolean checkRequestsAreDone() {
        return (prochainementsRequestIsDone && eventsRequestIsDone && filmSeancesRequestIsDone && seancesRequestIsDone);
    }

    private void showMainActivity() {
        hidepDialog();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void noConnectionMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("No internet connection. You can edit your preferences, but you need internet to load the movies!")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        showMainActivity();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
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
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.ecm.clement.poulpefiction.activity/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}