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
import com.ecm.clement.poulpefiction.models.Media;
import com.ecm.clement.poulpefiction.models.Video;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static String TAG = DBHelper.class.getSimpleName();

    public static final String DATABASE_NAME = "PoulpeFictionBDD.db";
    // Event Table
    public static final String EVENT_TABLE_NAME = "event";
    public static final String EVENT_COLUMN_ID ="id";
    public static final String EVENT_COLUMN_TITLE ="titre";
    public static final String EVENT_COLUMN_SUBTITLE ="soustitre";
    public static final String EVENT_COLUMN_POSTER="affiche";
    public static final String EVENT_COLUMN_DESCRIPTION = "description";
    public static final String EVENT_COLUMN_VADCONDITION = "vad_condition";
    public static final String EVENT_COLUMN_PARTENAIRES = "partenaires";
    public static final String EVENT_COLUMN_BEGINNINGDATE = "date_deb";
    public static final String EVENT_COLUMN_ENDDATE = "date_fin";
    public static final String EVENT_COLUMN_HOUR ="heure";
    public static final String EVENT_COLUMN_CONTACT ="contact";
    public static final String EVENT_COLUMN_WEBLABEL = "web_label";
    public static final String EVENT_COLUMN_EVENNEMENTTPEID ="evenementtpeid";
    public static final String EVENT_COLUMN_FILMSLIST ="films";
    // Events Table
    //public static final String EVENTS_TABLE_NAME = "events";
    //public static final String EVENTS_COLUMN_TYPE ="type";
    public static final String EVENT_COLUMN_TYPE = "type";
    //public static final String EVENTS_COLUMN_TITLE ="titre";
    public static final String EVENT_COLUMN_TITREEVENT = "titre_event";

    //Film Seance
    public static final String FILM_TABLE_NAME = "film";
    public static final String FILM_COLUMN_ID = "id";
    public static final String FILM_COLUMN_TITLE="titre";
    public static final String FILM_COLUMN_TITLEVO="titre_vo";
    public static final String FILM_COLUMN_POSTER="affiche";
    public static final String FILM_COLUMN_WEB="web";
    public static final String FILM_COLUMN_LENGTH="duree";
    public static final String FILM_COLUMN_DISTRIBUTOR="distributeur";
    public static final String FILM_COLUMN_PARTICIPANT="participants";
    public static final String FILM_COLUMN_DIRECTOR="realisateur";
    public static final String FILM_COLUMN_SYNOPSIS="Synopsis";
    public static final String FILM_COLUMN_YEAR="annee";
    public static final String FILM_COLUMN_DATE="date_sortie";
    public static final String FILM_COLUMN_INFO="info";
    public static final String FILM_COLUMN_VISIBLE="is_visible";
    public static final String FILM_COLUMN_VENTE="is_vente";
    public static final String FILM_COLUMN_GENREID="genreid";
    public static final String FILM_COLUMN_CATEGORIEID="categorieid";
    public static final String FILM_COLUMN_GENRE="genre";
    public static final String FILM_COLUMN_CATEGORIE="categorie";
    public static final String FILM_COLUMN_RELEASENUMBER="releaseNumber";
    public static final String FILM_COLUMN_COUNTRY="pays";
    public static final String FILM_COLUMN_SHAREURL="share_url";
    public static final String FILM_COLUMN_MEDIASLIST="medias";
    public static final String FILM_COLUMN_VIDEOSLIST="videos";
    public static final String FILM_COLUMN_AVP="is_avp";
    public static final String FILM_COLUMN_ALAUNE="is_alaune";
    public static final String FILM_COLUMN_LASTWEEK="is_lastWeek";
    //Prochainement
    //public static final String PROCHAINEMENT_TABLE_NAME = "prochainement";
    public static final String FILM_COLUMN_INCOMMING = "is_prochainement";
    //public static final String PROCHAINEMENT_COLUMN_CURRENT = "current";
    //public static final String PROCHAINEMENT_COLUMN_NEXT = "next";
    public static final String FILM_COLUMN_CURRENT = "is_affiche";

    //Seance
    public static final String SEANCE_TABLE_NAME = "Seance";
    public static final String SEANCE_COLUMN_ID = "id";
    public static final String SEANCE_COLUMN_SHOWTIME="show_time";
    public static final String SEANCE_COLUMN_3D="is_troisd";
    public static final String SEANCE_COLUMN_MALENTENDANT="is_malentendant";
    public static final String SEANCE_COLUMN_HANDICAPE="is_handicape";
    public static final String SEANCE_COLUMN_NATIONALITY="nationality";
    public static final String SEANCE_COLUMN_CINEMAID="cinemaid";
    public static final String SEANCE_COLUMN_FILMID="filmid";
    public static final String SEANCE_COLUMN_TITLE="titre";
    public static final String SEANCE_COLUMN_CATEGORIEID="categorieid";
    public static final String SEANCE_COLUMN_PERFORMANCIED="performancied";
    public static final String SEANCE_COLUMN_CINEMASALLE="cinema_salle";


    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table event" +
                        "(id texte primary key, titre text, soustitre text, affiche text, description text,"+
                        "vad_condition text, partenaires text, date_deb text, date_fin text, heure text," +
                        "contact text, web_label text, evenementtpeid text, films text, type text, titre_event text )"
        );
        db.execSQL(
                "create table film" +
                        "id text primary key, titre text, titre_vo text, affiche text,"+
                        "web text, duree text, distributeur text, participants text,"+
                        "realisateur text, Synopsis text, annee text, date_sortie text,"+
                        "info text, is_visible text, is_vente text, genreid text, categorieid text"+
                        "genre text, categorie text, releaseNumber text, pays text, share_url text"+
                        "medias text, videos text, is_avp text, is_alaune text, is_lastWeek text)"+
                        "is_prochainement text, is_affiche text"
        );
        db.execSQL(
                "create table Seance" +
                        "(id text primary key, show_time text, is_troisd text, is_malentendant text,  is_handicape text, " +
                        "nationality text, cinemaid text, filmid text, titre text, categorieid text, " +
                        "performancied text, cinema_salle text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ EVENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ FILM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ SEANCE_TABLE_NAME);

        onCreate(db);
    }

    public void insertEvent(int id, String titre, String soustitre, String affiche, String description, String vad_condition, String partenaire, String date_deb, String date_fin,
                            String heure, String contact, String web_label, String evenementtypeid, String films, String type_wrapped, String titre_wrapped){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EVENT_COLUMN_ID, id);
        contentValues.put(EVENT_COLUMN_TITLE,titre);
        contentValues.put(EVENT_COLUMN_SUBTITLE,soustitre);
        contentValues.put(EVENT_COLUMN_POSTER,affiche);
        contentValues.put(EVENT_COLUMN_DESCRIPTION,description);
        contentValues.put(EVENT_COLUMN_VADCONDITION,vad_condition);
        contentValues.put(EVENT_COLUMN_PARTENAIRES,partenaire);
        contentValues.put(EVENT_COLUMN_BEGINNINGDATE,date_deb);
        contentValues.put(EVENT_COLUMN_ENDDATE,date_fin);
        contentValues.put(EVENT_COLUMN_HOUR,heure);
        contentValues.put(EVENT_COLUMN_CONTACT,contact);
        contentValues.put(EVENT_COLUMN_WEBLABEL,web_label);
        contentValues.put(EVENT_COLUMN_EVENNEMENTTPEID,evenementtypeid);
        contentValues.put(EVENT_COLUMN_FILMSLIST,films);
        contentValues.put(EVENT_COLUMN_TYPE,type_wrapped);
        contentValues.put(EVENT_COLUMN_TITREEVENT,titre_wrapped);
        db.insert(EVENT_TABLE_NAME,null,contentValues);
    }

    public void insertFilm (int id, String titre, String titre_ori, String affiche, String web, int duree, String distributeur, String participants, String realisateur, String synopsis, int annee,
                            String date_sortie, String info, boolean is_visible, boolean is_vente, int genreid, int categorieid, String genre, String categorie, String ReleaseNumber, String pays, String share_url,
                            String medias, String videos, boolean is_avp, boolean is_alaune, boolean is_lastWeek, boolean is_prochainement, boolean is_alafiche){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FILM_COLUMN_ID, id);
        contentValues.put(FILM_COLUMN_TITLE, titre);
        contentValues.put(FILM_COLUMN_TITLEVO, titre_ori);
        contentValues.put(FILM_COLUMN_POSTER, affiche);
        contentValues.put(FILM_COLUMN_WEB, web);
        contentValues.put(FILM_COLUMN_LENGTH, duree);
        contentValues.put(FILM_COLUMN_DISTRIBUTOR, distributeur);
        contentValues.put(FILM_COLUMN_PARTICIPANT, participants);
        contentValues.put(FILM_COLUMN_DIRECTOR, realisateur);
        contentValues.put(FILM_COLUMN_SYNOPSIS, synopsis);
        contentValues.put(FILM_COLUMN_YEAR, annee);
        contentValues.put(FILM_COLUMN_DATE, date_sortie);
        contentValues.put(FILM_COLUMN_INFO, info);
        contentValues.put(FILM_COLUMN_VISIBLE, is_visible);
        contentValues.put(FILM_COLUMN_VENTE, is_vente);
        contentValues.put(FILM_COLUMN_GENREID, genreid);
        contentValues.put(FILM_COLUMN_CATEGORIEID, categorieid);
        contentValues.put(FILM_COLUMN_GENRE, genre);
        contentValues.put(FILM_COLUMN_CATEGORIE, categorie);
        contentValues.put(FILM_COLUMN_RELEASENUMBER, ReleaseNumber);
        contentValues.put(FILM_COLUMN_COUNTRY, pays);
        contentValues.put(FILM_COLUMN_SHAREURL,share_url);
        contentValues.put(FILM_COLUMN_MEDIASLIST,medias);
        contentValues.put(FILM_COLUMN_VIDEOSLIST,videos);
        contentValues.put(FILM_COLUMN_AVP,is_avp);
        contentValues.put(FILM_COLUMN_ALAUNE,is_alaune);
        contentValues.put(FILM_COLUMN_LASTWEEK,is_lastWeek);
        contentValues.put(FILM_COLUMN_INCOMMING,is_prochainement);
        contentValues.put(FILM_COLUMN_CURRENT,is_alafiche);
        db.insert(FILM_TABLE_NAME, null, contentValues);
    }

    public void insertSeance(int id, String actual_date, String show_time, boolean is_troisd, boolean is_malentendant, boolean is_handicape, String nationality, int cinemaid,
                             int filmid, String titre, int categorieid, String performanceid, String cinema_salle){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SEANCE_COLUMN_ID,id);
        contentValues.put(SEANCE_COLUMN_SHOWTIME,actual_date);
        contentValues.put(SEANCE_COLUMN_3D,is_troisd);
        contentValues.put(SEANCE_COLUMN_MALENTENDANT,is_malentendant);
        contentValues.put(SEANCE_COLUMN_HANDICAPE,is_handicape);
        contentValues.put(SEANCE_COLUMN_NATIONALITY,nationality);
        contentValues.put(SEANCE_COLUMN_CINEMAID,cinemaid);
        contentValues.put(SEANCE_COLUMN_FILMID,filmid);
        contentValues.put(SEANCE_COLUMN_TITLE,titre);
        contentValues.put(SEANCE_COLUMN_CATEGORIEID,categorieid);
        contentValues.put(SEANCE_COLUMN_PERFORMANCIED,performanceid);
        contentValues.put(SEANCE_COLUMN_CINEMASALLE,cinema_salle);
        db.insert(SEANCE_TABLE_NAME, null, contentValues);
    }

    public void updateProchainement(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FILM_COLUMN_INCOMMING, true);
        db.update(FILM_TABLE_NAME, contentValues, FILM_COLUMN_ID + "=" + id, null);

    }

    public void updateAlaffiche(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FILM_COLUMN_CURRENT, true);
        db.update(FILM_TABLE_NAME, contentValues, FILM_COLUMN_ID + "=" + id, null);

    }

    public Cursor getData(int id, String tableName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+tableName+" where id="+id+"", null );
        return res;
    }

    public boolean checkIfFilmIsAlreadyInDb(Film film) {
        int search = film.getId();
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from " + FILM_TABLE_NAME + " where " + FILM_COLUMN_ID + " = " + Integer.toString(search);
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }


    public int numberOfRowsInSeances(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, SEANCE_TABLE_NAME);
        return numRows;
    }

    public int numberOfRowsInEvents(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, EVENT_TABLE_NAME);
        return numRows;
    }

    public int numberOfRowsInFIlms(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, FILM_TABLE_NAME);
        return numRows;
    }

    public ArrayList<String> getAllSeances()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from seances", null);
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(SEANCE_COLUMN_TITLE)));
            res.moveToNext();
        }
        return array_list;
    }

    public List<Event> cursorToEvent(Cursor res){
        List<Event> listEvents = new ArrayList<Event>();
        res.moveToFirst();

        while(res.isAfterLast() == false){

            Event event = new Event(res.getInt(res.getColumnIndex(EVENT_COLUMN_ID)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_TITLE)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_SUBTITLE)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_POSTER)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_DESCRIPTION)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_VADCONDITION)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_PARTENAIRES)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_BEGINNINGDATE)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_ENDDATE)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_HOUR)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_CONTACT)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_WEBLABEL)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_EVENNEMENTTPEID)),
                    stringToFilms(res.getString(res.getColumnIndex(EVENT_COLUMN_FILMSLIST))),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_TITREEVENT)),
                    res.getString(res.getColumnIndex(EVENT_COLUMN_TYPE))
            );

            listEvents.add(event);
            res.moveToNext();
        }
        return listEvents;
    }


    public List<Film> cursorToFilm(Cursor res){
        List<Film> listFilm = new ArrayList<Film>();
        res.moveToFirst();
        while(res.isAfterLast() == false){

            Film film = new Film(res.getInt(res.getColumnIndex(FILM_COLUMN_ID)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_TITLE)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_TITLEVO)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_POSTER)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_WEB)),
                    res.getInt(res.getColumnIndex(FILM_COLUMN_LENGTH)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_DISTRIBUTOR)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_PARTICIPANT)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_DIRECTOR)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_SYNOPSIS)),
                    res.getInt(res.getColumnIndex(FILM_COLUMN_YEAR)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_DATE)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_INFO)),
                    res.getInt(res.getColumnIndex(FILM_COLUMN_VISIBLE))==1,
                    res.getInt(res.getColumnIndex(FILM_COLUMN_VENTE))==1,
                    res.getInt(res.getColumnIndex(FILM_COLUMN_GENREID)),
                    res.getInt(res.getColumnIndex(FILM_COLUMN_CATEGORIEID)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_GENRE)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_CATEGORIE)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_RELEASENUMBER)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_COUNTRY)),
                    res.getString(res.getColumnIndex(FILM_COLUMN_SHAREURL)),
                    stringToMedia(res.getString(res.getColumnIndex(FILM_COLUMN_MEDIASLIST))),
                    stringToVideos(res.getString(res.getColumnIndex(EVENT_COLUMN_FILMSLIST))),
                    res.getInt(res.getColumnIndex(FILM_COLUMN_AVP))==1,
                    res.getInt(res.getColumnIndex(FILM_COLUMN_ALAUNE))==1,
                    res.getInt(res.getColumnIndex(FILM_COLUMN_LASTWEEK))==1,
                    res.getInt(res.getColumnIndex(FILM_COLUMN_INCOMMING))==1,
                    res.getInt(res.getColumnIndex(FILM_COLUMN_CURRENT))==1
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

    public List<Film> getAllFilmAlAffiche(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from " + FILM_TABLE_NAME + " where " + FILM_COLUMN_CURRENT + "=" + "1", null);

        List<Film> listFilm = cursorToFilm(res);
        return(listFilm);
    }

    public List<Film> getAllFilmProchainement(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from " + FILM_TABLE_NAME+ " where " + FILM_COLUMN_INCOMMING + "=" + "1", null);

        List<Film> listFilm = cursorToFilm(res);
        return(listFilm);
    }

    public List<Event> getAllEvents(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from " + EVENT_TABLE_NAME, null);
        List<Event> listEvents= cursorToEvent(res);
        return(listEvents);
    }

    public List<Film> stringToFilms(String filmStringified){
        List<Film> listFilm = new ArrayList<Film>();

        List<String> listFilmId = Arrays.asList(filmStringified.split(","));
        for (int i = 0; i < listFilmId.size(); i++){
            listFilm.add(getFilmById(Integer.parseInt(listFilmId.get(i))));
        }
        return(listFilm);
    }

    public List<Video> stringToVideos(String inputString) {
        String inputString1 = inputString.replace("{", "");
        inputString1 = inputString1.replace("}","");
        String[] listString = inputString.split("'");
       List<Video> listVideo = new ArrayList<Video>();
        int length = listString.length;
        for (int i = 0; i<=length; i=i+3){

        listString[i].replace("\"titre\":", "");
        listString[i+1].replace("\"type\":", "");
        listString[i+2].replace("\"url\":", "");
        listVideo.add(new Video(listString[i], listString[i+1], listString[i+2]));}


        return listVideo;
    }

    public List<String> getAllNationality(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> listNationality = new ArrayList<String>();
        Cursor res= db.rawQuery("SELECT DISTINCT " + SEANCE_COLUMN_NATIONALITY + " FROM " + SEANCE_TABLE_NAME, null);
        res.moveToFirst();
        while(res.isAfterLast() == false) {

            listNationality.add(res.getString(res.getColumnIndex(SEANCE_COLUMN_NATIONALITY)));
            res.moveToNext();
        }
        Log.d(TAG, listNationality.toString());
        return listNationality;
    }

    public List<String> getAllCategorie(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> listCategorie = new ArrayList<String>();
        Cursor res= db.rawQuery("SELECT DISTINCT " + FILM_COLUMN_CATEGORIE + " FROM " + FILM_TABLE_NAME, null);
        res.moveToFirst();
        while(res.isAfterLast() == false) {

            listCategorie.add(res.getString(res.getColumnIndex(FILM_COLUMN_CATEGORIE)));
            res.moveToNext();
        }
        Log.d(TAG,listCategorie.toString() );
        return listCategorie;
    }

    public List<Media> stringToMedia(String paths){
        ArrayList<Media> listMedias = new ArrayList<Media>();
        if(paths.length()>0) {
            List<String> pathList = Arrays.asList(paths.split(","));

            for (int i = 0; i < pathList.size(); i++) {
                listMedias.add(new Media(pathList.get(i)));
            }
        }
        return listMedias;
    }
}
