package com.ecm.clement.poulpefiction.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
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
    public static final String EVENTS_TABLE_NAME = "events";
    public static final String EVENTS_COLUMN_TYPE ="type";
    public static final String EVENTS_COLUMN_EVENTLIST ="event_list";
    public static final String EVENTS_COLUMN_TITLE ="titre";

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
    public static final String PROCHAINEMENT_TABLE_NAME = "prochainement";
    public static final String PROCHAINEMENT_COLUMN_CURRENT = "current";
    public static final String PROCHAINEMENT_COLUMN_NEXT = "next";
    public static final String PROCHAINEMENT_COLUMN_FILMSLIST = "films";

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
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table event" +
                        "(id texte primary key, titre text, soustitre text, affiche text, description text,"+
                        "vad_condition text, partenaires text, date_deb text, date_fin text, heure text," +
                        "contact text, web_label text, evenementtpeid text, film text)"
        );
        db.execSQL(
                "create table events" +
                        "(type text, event_list text, titre text)"
        );
        db.execSQL(
                "create table film" +
                        "id text primary key, titre text, titre_vo text, affiche text,"+
                        "web text, duree text, distributeur text, participants text,"+
                        "realisateur text, Synopsis text, annee text, date_sortie text,"+
                        "info text, is_visible text, is_vente text, genreid text, categorieid text"+
                        "genre text, categorie text, releaseNumber text, pays text, share_url text"+
                        "medias text, videos text, is_avp text, is_alaune text, is_lastWeek text)"
        );
        db.execSQL(
                "create table prochainement" +
                        "(current text, next text, films text)"
        );
        db.execSQL(
                "create table Seance" +
                        "(id text primary key, show_time text, is_troisd text, is_malentendant text,  is_handicape text, "+
                        "nationality text, cinemaid text, filmid text, titre text, categorieid text, "+
                        "performancied text, cinema_salle text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ EVENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ EVENTS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ FILM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ PROCHAINEMENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ SEANCE_TABLE_NAME);

        onCreate(db);
    }

    public boolean insertContact  (String name, String phone, String email, String street,String place)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.insert("contacts", null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, FILM_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllCotacts()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(FILM_TABLE_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}
