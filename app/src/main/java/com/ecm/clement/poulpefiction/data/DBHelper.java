package com.ecm.clement.poulpefiction.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ecm.clement.poulpefiction.models.Event;
import com.ecm.clement.poulpefiction.models.Film;
import com.ecm.clement.poulpefiction.models.Seance;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBHelper extends SQLiteOpenHelper {
    private static String TAG = DBHelper.class.getSimpleName();
    private static DBHelper instance = null;

    public static final String DATABASE_NAME = "RottenPotatoes.db";

    public static final String FILM_TABLE_NAME= "films";
    public static final String FILM_COLUMN_ID= "id";
    public static final String FILM_COLUMN_TITRE="titre";
    public static final String FILM_COLUMN_TITRE_ORI="titre_ori";
    public static final String FILM_COLUMN_AFFICHE="affiche";
    public static final String FILM_COLUMN_WEB="web";
    public static final String FILM_COLUMN_DUREE="duree";
    public static final String FILM_COLUMN_DISTRIBUTEUR="distributeur";
    public static final String FILM_COLUMN_PARTICIPANTS="participants";
    public static final String FILM_COLUMN_REALISATEUR="realisateur";
    public static final String FILM_COLUMN_SYNOPSIS="synopsis";
    public static final String FILM_COLUMN_ANNEE="annee";
    public static final String FILM_COLUMN_DATE_SORTIE="date_sortie";
    public static final String FILM_COLUMN_INFO="info";
    public static final String FILM_COLUMN_IS_VISIBLE="is_visible";
    public static final String FILM_COLUMN_IS_VENTE="is_vente";
    public static final String FILM_COLUMN_GENREID="genreid";
    public static final String FILM_COLUMN_CATEGORIEID="categorieid";
    public static final String FILM_COLUMN_GENRE="genre";
    public static final String FILM_COLUMN_CATEGORIE="categorie";
    public static final String FILM_COLUMN_RELEASENUMBER="ReleaseNumber";
    public static final String FILM_COLUMN_PAYS="pays";
    public static final String FILM_COLUMN_SHARE_URL="share_url";
    public static final String FILM_COLUMN_MEDIAS="medias";
    public static final String FILM_COLUMN_VIDEOS="videoes";
    public static final String FILM_COLUMN_IS_AVP="is_avp";
    public static final String FILM_COLUMN_IS_ALAUNE="is_alaune";
    public static final String FILM_COLUMN_IS_LASTWEEK="is_lastWeek";
    public static final String FILM_COLUMN_IS_PROCHAINEMENT="is_prochainement";
    public static final String FILM_COLUMN_IS_ALAFFICHE="is_alafiche";

    public static final String SEANCE_TABLE_NAME="seances";
    public static final String SEANCE_COLUMN_ID="id";
    public static final String SEANCE_COLUMN_ACTUAL_DATE="actual_date";
    public static final String SEANCE_COLUMN_SHOW_TIME="show_time";
    public static final String SEANCE_COLUMN_IS_TROISD="is_troisd";
    public static final String SEANCE_COLUMN_IS_MALENTENDANT="is_malentendant";
    public static final String SEANCE_COLUMN_IS_HANDICAPE="is_handicape";
    public static final String SEANCE_COLUMN_NATIONALITY="nationality";
    public static final String SEANCE_COLUMN_CINEMAID="cinemaid";
    public static final String SEANCE_COLUMN_FILMID="filmid";
    public static final String SEANCE_COLUMN_TITRE="titre";
    public static final String SEANCE_COLUMN_CATEGORIEID="categorieid";
    public static final String SEANCE_COLUMN_PERFORMANCEID="performanceid";
    public static final String SEANCE_COLUMN_CINEMA_SALLE="cinema_salle";

    public static final String EVENT_TABLE_NAME="events";
    public static final String EVENT_COLUMN_ID="id";
    public static final String EVENT_COLUMN_TITRE="titre";
    public static final String EVENT_COLUMN_SOUSTITRE="soustitre";
    public static final String EVENT_COLUMN_AFFICHE="affiche";
    public static final String EVENT_COLUMN_DESCRIPTION="description";
    public static final String EVENT_COLUMN_VAD_CONDITION="vad_condition";
    public static final String EVENT_COLUMN_PARTENAIRE="partenaire";
    public static final String EVENT_COLUMN_DATE_DEB="date_deb";
    public static final String EVENT_COLUMN_DATE_FIN="date_fin";
    public static final String EVENT_COLUMN_HEURE="heure";
    public static final String EVENT_COLUMN_CONTACT="contact";
    public static final String EVENT_COLUMN_WEB_LABEL="web_label";
    public static final String EVENT_COLUMN_EVENEMENTTYPEID="evenementtypeid";
    public static final String EVENT_COLUMN_FILMS="films";
    public static final String EVENT_COLUMN_TYPE_WRAPPED="type";
    public static final String EVENT_COLUMN_TITRE_WRAPPED="titre_wrapped";

    public static DBHelper getInstance(Context ctx) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (instance == null) {
            instance = new DBHelper(ctx.getApplicationContext());
        }
        return instance;
    }

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table "+FILM_TABLE_NAME+" (" +
                        FILM_COLUMN_ID + " integer primary key, "+
                        FILM_COLUMN_TITRE + " text, " +
                        FILM_COLUMN_TITRE_ORI + " text, " +
                        FILM_COLUMN_AFFICHE + " text, "+
                        FILM_COLUMN_WEB + " text, "+
                        FILM_COLUMN_DUREE + " integer, "+
                        FILM_COLUMN_DISTRIBUTEUR + " text, "+
                        FILM_COLUMN_PARTICIPANTS + " text, "+
                        FILM_COLUMN_REALISATEUR + " text, "+
                        FILM_COLUMN_SYNOPSIS + " text, "+
                        FILM_COLUMN_ANNEE+" integer, "+
                        FILM_COLUMN_DATE_SORTIE+" text, "+
                        FILM_COLUMN_INFO+" text, "+
                        FILM_COLUMN_IS_VISIBLE+ " boolean, "+
                        FILM_COLUMN_IS_VENTE+ " boolean, "+
                        FILM_COLUMN_GENREID+ " integer, "+
                        FILM_COLUMN_CATEGORIEID+ " integer, "+
                        FILM_COLUMN_GENRE+ " text, "+
                        FILM_COLUMN_CATEGORIE+ " text, "+
                        FILM_COLUMN_RELEASENUMBER+ " text, "+
                        FILM_COLUMN_PAYS+ " text, "+
                        FILM_COLUMN_SHARE_URL+ " text, "+
                        FILM_COLUMN_MEDIAS+ " text, "+
                        FILM_COLUMN_VIDEOS+ " text, "+
                        FILM_COLUMN_IS_AVP+ " boolean, "+
                        FILM_COLUMN_IS_ALAUNE+ " boolean, "+
                        FILM_COLUMN_IS_LASTWEEK+ " boolean, "+
                        FILM_COLUMN_IS_PROCHAINEMENT+ " boolean, "+
                        FILM_COLUMN_IS_ALAFFICHE + " boolean "+
                        ")"
        );

        db.execSQL(
                "create table "+SEANCE_TABLE_NAME+" (" +
                        SEANCE_COLUMN_ID + " integer primary key, "+
                        SEANCE_COLUMN_ACTUAL_DATE+ " text, "+
                        SEANCE_COLUMN_SHOW_TIME+ " text, "+
                        SEANCE_COLUMN_IS_TROISD+ " boolean, "+
                        SEANCE_COLUMN_IS_MALENTENDANT+ " boolean, "+
                        SEANCE_COLUMN_IS_HANDICAPE+ " boolean, "+
                        SEANCE_COLUMN_NATIONALITY+ " text, "+
                        SEANCE_COLUMN_CINEMAID+ " integer, "+
                        SEANCE_COLUMN_FILMID+ " integer, "+
                        SEANCE_COLUMN_TITRE+ " text, "+
                        SEANCE_COLUMN_CATEGORIEID+ " integer, "+
                        SEANCE_COLUMN_PERFORMANCEID+ " integer, "+
                        SEANCE_COLUMN_CINEMA_SALLE+ " text "+
                        ")"
        );

        db.execSQL(
                "create table " + EVENT_TABLE_NAME + " (" +
                        EVENT_COLUMN_ID + " integer primary key, " +
                        EVENT_COLUMN_TITRE + " text, " +
                        EVENT_COLUMN_SOUSTITRE + " text, " +
                        EVENT_COLUMN_AFFICHE + " text, " +
                        EVENT_COLUMN_DESCRIPTION + " text, " +
                        EVENT_COLUMN_VAD_CONDITION + " text, " +
                        EVENT_COLUMN_PARTENAIRE + " text, " +
                        EVENT_COLUMN_DATE_DEB + " text, " +
                        EVENT_COLUMN_DATE_FIN + " text, " +
                        EVENT_COLUMN_HEURE + " text, " +
                        EVENT_COLUMN_CONTACT + " text, " +
                        EVENT_COLUMN_WEB_LABEL + " text, " +
                        EVENT_COLUMN_EVENEMENTTYPEID + " text, " +
                        EVENT_COLUMN_FILMS + " text, " +
                        EVENT_COLUMN_TYPE_WRAPPED + " text, " +
                        EVENT_COLUMN_TITRE_WRAPPED + " text " +
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ FILM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ SEANCE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ EVENT_TABLE_NAME);
        onCreate(db);
    }

    public void insertFilm (int id, String titre, String titre_ori, String affiche, String web, int duree, String distributeur, String participants, String realisateur, String synopsis, int annee,
                            String date_sortie, String info, boolean is_visible, boolean is_vente, int genreid, int categorieid, String genre, String categorie, String ReleaseNumber, String pays, String share_url,
                            String medias, String videos, boolean is_avp, boolean is_alaune, boolean is_lastWeek, boolean is_prochainement, boolean is_alafiche){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FILM_COLUMN_ID, id);
        contentValues.put(FILM_COLUMN_TITRE, titre);
        contentValues.put(FILM_COLUMN_TITRE_ORI, titre_ori);
        contentValues.put(FILM_COLUMN_AFFICHE, affiche);
        contentValues.put(FILM_COLUMN_WEB, web);
        contentValues.put(FILM_COLUMN_DUREE, duree);
        contentValues.put(FILM_COLUMN_DISTRIBUTEUR, distributeur);
        contentValues.put(FILM_COLUMN_PARTICIPANTS, participants);
        contentValues.put(FILM_COLUMN_REALISATEUR, realisateur);
        contentValues.put(FILM_COLUMN_SYNOPSIS, synopsis);
        contentValues.put(FILM_COLUMN_ANNEE, annee);
        contentValues.put(FILM_COLUMN_DATE_SORTIE, date_sortie);
        contentValues.put(FILM_COLUMN_INFO, info);
        contentValues.put(FILM_COLUMN_IS_VISIBLE, is_visible);
        contentValues.put(FILM_COLUMN_IS_VENTE, is_vente);
        contentValues.put(FILM_COLUMN_GENREID, genreid);
        contentValues.put(FILM_COLUMN_CATEGORIEID, categorieid);
        contentValues.put(FILM_COLUMN_GENRE, genre);
        contentValues.put(FILM_COLUMN_CATEGORIE, categorie);
        contentValues.put(FILM_COLUMN_RELEASENUMBER, ReleaseNumber);
        contentValues.put(FILM_COLUMN_PAYS, pays);
        contentValues.put(FILM_COLUMN_SHARE_URL,share_url);
        contentValues.put(FILM_COLUMN_MEDIAS,medias);
        contentValues.put(FILM_COLUMN_VIDEOS,videos);
        contentValues.put(FILM_COLUMN_IS_AVP,is_avp);
        contentValues.put(FILM_COLUMN_IS_ALAUNE,is_alaune);
        contentValues.put(FILM_COLUMN_IS_LASTWEEK,is_lastWeek);
        contentValues.put(FILM_COLUMN_IS_PROCHAINEMENT,is_prochainement);
        contentValues.put(FILM_COLUMN_IS_ALAFFICHE,is_alafiche);
        db.insert(FILM_TABLE_NAME, null, contentValues);
    }

    public void insertSeance(int id, String actual_date, String show_time, boolean is_troisd, boolean is_malentendant, boolean is_handicape, String nationality, int cinemaid,
                             int filmid, String titre, int categorieid, int performanceid, String cinema_salle){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SEANCE_COLUMN_ID,id);
        contentValues.put(SEANCE_COLUMN_ACTUAL_DATE,actual_date);
        contentValues.put(SEANCE_COLUMN_SHOW_TIME,show_time);
        contentValues.put(SEANCE_COLUMN_IS_TROISD,is_troisd);
        contentValues.put(SEANCE_COLUMN_IS_MALENTENDANT,is_malentendant);
        contentValues.put(SEANCE_COLUMN_IS_HANDICAPE,is_handicape);
        contentValues.put(SEANCE_COLUMN_NATIONALITY,nationality);
        contentValues.put(SEANCE_COLUMN_CINEMAID,cinemaid);
        contentValues.put(SEANCE_COLUMN_FILMID,filmid);
        contentValues.put(SEANCE_COLUMN_TITRE,titre);
        contentValues.put(SEANCE_COLUMN_CATEGORIEID,categorieid);
        contentValues.put(SEANCE_COLUMN_PERFORMANCEID,performanceid);
        contentValues.put(SEANCE_COLUMN_CINEMA_SALLE,cinema_salle);
        db.insert(SEANCE_TABLE_NAME,null,contentValues);
    }

    public void insertEvent(String id, String titre, String soustitre, String affiche, String description, String vad_condition, String partenaire, String date_deb, String date_fin,
                            String heure, String contact, String web_label, String evenementtypeid, String films, String type_wrapped, String titre_wrapped){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EVENT_COLUMN_TITRE,titre);
        contentValues.put(EVENT_COLUMN_SOUSTITRE,soustitre);
        contentValues.put(EVENT_COLUMN_AFFICHE,affiche);
        contentValues.put(EVENT_COLUMN_DESCRIPTION,description);
        contentValues.put(EVENT_COLUMN_VAD_CONDITION,vad_condition);
        contentValues.put(EVENT_COLUMN_PARTENAIRE,partenaire);
        contentValues.put(EVENT_COLUMN_DATE_DEB,date_deb);
        contentValues.put(EVENT_COLUMN_DATE_FIN,date_fin);
        contentValues.put(EVENT_COLUMN_HEURE,heure);
        contentValues.put(EVENT_COLUMN_CONTACT,contact);
        contentValues.put(EVENT_COLUMN_WEB_LABEL,web_label);
        contentValues.put(EVENT_COLUMN_EVENEMENTTYPEID,evenementtypeid);
        contentValues.put(EVENT_COLUMN_FILMS,films);
        contentValues.put(EVENT_COLUMN_TYPE_WRAPPED,type_wrapped);
        contentValues.put(EVENT_COLUMN_TITRE_WRAPPED,titre_wrapped);
        db.insert(EVENT_TABLE_NAME,null,contentValues);
    }

    public void updateProchainement(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FILM_COLUMN_IS_PROCHAINEMENT,true);
        db.update(FILM_TABLE_NAME, contentValues,FILM_COLUMN_ID+"="+id,null);

    }

    public void updateAffiche(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FILM_COLUMN_IS_ALAFFICHE,true);
        db.update(FILM_TABLE_NAME, contentValues, FILM_COLUMN_ID + "=" + id, null);

    }

    public boolean checkFilmInDb(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from " + FILM_TABLE_NAME + " where " + FILM_COLUMN_ID + " = " + id;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public int seancesCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, SEANCE_TABLE_NAME);
        return numRows;
    }

    public int eventsCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, EVENT_TABLE_NAME);
        return numRows;
    }

    public int filmsCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, FILM_TABLE_NAME);
        return numRows;
    }

    public ArrayList<String> getAllSeances()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from seances", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(SEANCE_COLUMN_TITRE)));
            res.moveToNext();
        }
        return array_list;
    }

    public List<Film> cursorToFilm(Cursor res){
        List<Film> listFilm = new ArrayList<Film>();
        res.moveToFirst();
        while(res.isAfterLast() == false){

            String paths = res.getString(res.getColumnIndex(FILM_COLUMN_MEDIAS));
            String videoStringified= res.getString(res.getColumnIndex(FILM_COLUMN_VIDEOS));
            List<Film.Medias> listMedias = stringToMedias(paths);
            List<Film.Videos> listVideos = stringToVideos(videoStringified);

            Film film = new Film(res.getInt(res.getColumnIndex(FILM_COLUMN_ID)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_TITRE)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_TITRE_ORI)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_AFFICHE)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_WEB)),
                    res.getInt(res.getColumnIndex(FILM_COLUMN_DUREE)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_DISTRIBUTEUR)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_PARTICIPANTS)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_REALISATEUR)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_SYNOPSIS)),
                    res.getInt(res.getColumnIndex(FILM_COLUMN_ANNEE)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_DATE_SORTIE)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_INFO)),
                    res.getInt(res.getColumnIndex(FILM_COLUMN_IS_VISIBLE))==1,
                    res.getInt(res.getColumnIndex(FILM_COLUMN_IS_VENTE))==1,
                    res.getInt(res.getColumnIndex(FILM_COLUMN_GENREID)),
                    res.getInt(res.getColumnIndex(FILM_COLUMN_CATEGORIEID)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_GENRE)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_CATEGORIE)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_RELEASENUMBER)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_PAYS)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_SHARE_URL)),
                    listMedias,
                    listVideos,
                    res.getInt(res.getColumnIndex(FILM_COLUMN_IS_AVP))==1,
                    res.getInt(res.getColumnIndex(FILM_COLUMN_IS_ALAUNE))==1,
                    res.getInt(res.getColumnIndex(FILM_COLUMN_IS_LASTWEEK))==1
            );

            listFilm.add(film);
            res.moveToNext();
        }
        return listFilm;
    }

    public Film getFilmById(int Id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from " + FILM_TABLE_NAME + " where " + FILM_COLUMN_ID + "=" + Id, null);
        Film film = cursorToFilm(res).get(0);
        return film;
    }

    public List<Film> getAllFilm(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from " + FILM_TABLE_NAME, null);

        List<Film> listFilm = cursorToFilm(res);
        return(listFilm);
    }

    public List<Film> getAllFilmProchainement(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from " + FILM_TABLE_NAME + " where " + FILM_COLUMN_IS_PROCHAINEMENT + "=" + "1", null);

        List<Film> listFilm = cursorToFilm(res);
        return(listFilm);
    }

    public List<Film> getAllFilmAlAffiche(){
        List<Film> listFilm = new ArrayList<Film>();
        SQLiteDatabase db = this.getReadableDatabase();
        String request= "select * from " + SEANCE_TABLE_NAME;

        Log.d(TAG, request);
        Cursor resSeance = db.rawQuery(request, null);
        List<Seance> listSeance = cursorToSeance(resSeance);
        List<Integer> listFilmId = new ArrayList<Integer>();
        Log.d(TAG, "done requesting");
        for(int i=0; i< listSeance.size();i++){
            Seance seance = listSeance.get(i);
            Film filmzor =getFilmById(seance.getFilmid());
            if(!listFilmId.contains(seance.getFilmid())){
                listFilm.add(filmzor);
                listFilmId.add(seance.getFilmid());
            }
        }
        return(listFilm);
    }

    public List<Seance> cursorToSeance(Cursor res){
        List<Seance> listSeances = new ArrayList<Seance>();
        res.moveToFirst();

        while(res.isAfterLast() == false) {

            Seance seance = new Seance(res.getInt(res.getColumnIndex(SEANCE_COLUMN_ID)),
                    res.getString(res.getColumnIndex(SEANCE_COLUMN_ACTUAL_DATE)),
                    res.getString(res.getColumnIndex(SEANCE_COLUMN_SHOW_TIME)),
                    res.getInt(res.getColumnIndex(SEANCE_COLUMN_IS_TROISD))==1,
                    res.getInt(res.getColumnIndex(SEANCE_COLUMN_IS_MALENTENDANT))==1,
                    res.getInt(res.getColumnIndex(SEANCE_COLUMN_IS_HANDICAPE))==1,
                    res.getString(res.getColumnIndex(SEANCE_COLUMN_NATIONALITY)),
                    res.getInt(res.getColumnIndex(SEANCE_COLUMN_CINEMAID)),
                    res.getInt(res.getColumnIndex(SEANCE_COLUMN_FILMID)),
                    res.getString(res.getColumnIndex(SEANCE_COLUMN_TITRE)),
                    res.getInt(res.getColumnIndex(SEANCE_COLUMN_CATEGORIEID)),
                    res.getInt(res.getColumnIndex(SEANCE_COLUMN_PERFORMANCEID)),
                    res.getString(res.getColumnIndex(SEANCE_COLUMN_CINEMA_SALLE)));
            res.moveToNext();
            listSeances.add(seance);
        }
        return listSeances;
    }

    public List<Event> cursorToEvent(Cursor res){
        List<Event> listEvents = new ArrayList<Event>();
        res.moveToFirst();

        while(res.isAfterLast() == false){

            List<Film> listFilms = stringToFilms(res.getString(res.getColumnIndex(EVENT_COLUMN_FILMS)));

            Event event = new Event(res.getString(res.getColumnIndex(EVENT_COLUMN_ID)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_TITRE)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_SOUSTITRE)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_AFFICHE)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_DESCRIPTION)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_VAD_CONDITION)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_PARTENAIRE)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_DATE_DEB)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_DATE_FIN)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_HEURE)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_CONTACT)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_WEB_LABEL)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_EVENEMENTTYPEID)),
                    listFilms);
            event.setTitre_wrapped(res.getString(res.getColumnIndex(EVENT_COLUMN_TITRE_WRAPPED)));
            event.setType_wrapped(res.getString(res.getColumnIndex(EVENT_COLUMN_TYPE_WRAPPED)));

            listEvents.add(event);
            res.moveToNext();
        }
        return listEvents;
    }

    public List<Event> getAllEvents(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from " + EVENT_TABLE_NAME, null);
        List<Event> listEvents= cursorToEvent(res);
        return(listEvents);
    }

    public List<Film.Medias> stringToMedias(String paths){
        ArrayList<Film.Medias> listMedias = new ArrayList<Film.Medias>();
        if(paths.length()>0) {
            List<String> pathList = Arrays.asList(paths.split(","));

            for (int i = 0; i < pathList.size(); i++) {
                listMedias.add(new Film.Medias(pathList.get(i)));
            }
        }
        return listMedias;
    }

    public List<Film.Videos> stringToVideos(String videoStringified){
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        ArrayList<Film.Videos> listVideos = new ArrayList<Film.Videos>();
        if(videoStringified.length()>10) {
            JsonArray jArray = parser.parse(videoStringified).getAsJsonArray();
            for(JsonElement obj : jArray ) {
                Film.Videos video = gson.fromJson( obj , Film.Videos.class);
                listVideos.add(video);
            }
        }
        return listVideos;
    }

    public List<Film> stringToFilms(String filmStringified){
        List<Film> listFilm = new ArrayList<Film>();

        List<String> listFilmId = Arrays.asList(filmStringified.split(","));
        for (int i = 0; i < listFilmId.size(); i++){
            listFilm.add(getFilmById(Integer.parseInt(listFilmId.get(i))));
        }
        return(listFilm);
    }

    //FOR DEBUGGING
    public Map<String, String> getAllTitle(){
        SQLiteDatabase db = this.getReadableDatabase();
        Map<String,String> listTitle= new HashMap<String, String>();
        Cursor res= db.rawQuery("SELECT DISTINCT " + FILM_COLUMN_TITRE + " FROM " + FILM_TABLE_NAME, null);
        res.moveToFirst();
        while(res.isAfterLast() == false) {
            listTitle.put("film", res.getString(res.getColumnIndex(FILM_COLUMN_TITRE)));
            res.moveToNext();
        }
        Cursor resEvent= db.rawQuery("SELECT DISTINCT " + EVENT_COLUMN_TITRE + " FROM " + EVENT_TABLE_NAME, null);
        res.moveToFirst();
        while(res.isAfterLast() == false) {
            listTitle.put("event",res.getString(res.getColumnIndex(EVENT_COLUMN_TITRE)));
            res.moveToNext();
        }
        return listTitle;
    }


    // FOR DEBUGGING
    public Map<Integer,String> getAllNationality(){
        SQLiteDatabase db = this.getReadableDatabase();
        Map<Integer,String> listNationality = new HashMap<Integer, String>();
        Cursor res= db.rawQuery("SELECT DISTINCT " + SEANCE_COLUMN_NATIONALITY + " FROM " + SEANCE_TABLE_NAME, null);
        res.moveToFirst();
        int i =0;
        while(res.isAfterLast() == false) {

            listNationality.put(i,res.getString(res.getColumnIndex(SEANCE_COLUMN_NATIONALITY)));
            res.moveToNext();
            i++;
        }
        Log.d(TAG,listNationality.toString() );
        return listNationality;
    }

    // FOR DEBUGGING
    public Map<Integer,String> getAllCategorie(){
        SQLiteDatabase db = this.getReadableDatabase();
        Map<Integer,String> listCategorie = new HashMap<Integer,String>();
        Cursor res= db.rawQuery("SELECT DISTINCT " + FILM_COLUMN_CATEGORIE + " FROM " + FILM_TABLE_NAME, null);
        res.moveToFirst();
        int i =0;
        while(res.isAfterLast() == false) {

            listCategorie.put(i,res.getString(res.getColumnIndex(FILM_COLUMN_CATEGORIE)));
            res.moveToNext();
            i++;
        }
        Log.d(TAG,listCategorie.toString() );
        return listCategorie;
    }
}