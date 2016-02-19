package com.ecm.clement.poulpefiction.models;

public class Seance {
    private int id;
    private String show_time;
    private boolean is_troisd;
    private boolean is_malentendant;
    private boolean is_handicape;
    private String nationality;
    private int cinemaid;
    private int filmid;
    private String titre;
    private int categorieid;
    private String performancied;
    private String cinema_salle;

    //Constructors
    public Seance(int id, String show_time, boolean is_troisd, boolean is_malentendant,
                  boolean is_handicape, String nationality, int cinemaid, int filmid,
                  String titre, int categorieid, String performancied, String cinema_salle) {
        this.id = id;
        this.show_time = show_time;
        this.is_troisd = is_troisd;
        this.is_malentendant = is_malentendant;
        this.is_handicape = is_handicape;
        this.nationality = nationality;
        this.cinemaid = cinemaid;
        this.filmid = filmid;
        this.titre = titre;
        this.categorieid = categorieid;
        this.performancied = performancied;
        this.cinema_salle = cinema_salle;
    }

    // Get and Set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShow_time() {
        return show_time;
    }

    public void setShow_time(String show_time) {
        this.show_time = show_time;
    }

    public boolean is_troisd() {
        return is_troisd;
    }

    public void setIs_troisd(boolean is_troisd) {
        this.is_troisd = is_troisd;
    }

    public boolean is_malentendant() {
        return is_malentendant;
    }

    public void setIs_malentendant(boolean is_malentendant) {
        this.is_malentendant = is_malentendant;
    }

    public boolean is_handicape() {
        return is_handicape;
    }

    public void setIs_handicape(boolean is_handicape) {
        this.is_handicape = is_handicape;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getCinemaid() {
        return cinemaid;
    }

    public void setCinemaid(int cinemaid) {
        this.cinemaid = cinemaid;
    }

    public int getFilmid() {
        return filmid;
    }

    public void setFilmid(int filmid) {
        this.filmid = filmid;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getCategorieid() {
        return categorieid;
    }

    public void setCategorieid(int categorieid) {
        this.categorieid = categorieid;
    }

    public String getPerformancied() {
        return performancied;
    }

    public void setPerformancied(String performancied) {
        this.performancied = performancied;
    }

    public String getCinema_salle() {
        return cinema_salle;
    }

    public void setCinema_salle(String cinema_salle) {
        this.cinema_salle = cinema_salle;
    }




}
