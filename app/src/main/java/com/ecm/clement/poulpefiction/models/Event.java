package com.ecm.clement.poulpefiction.models;

import java.util.List;

public class Event {
    private int id;
    private String titre;
    private String soustitre;
    private String affiche;
    private String description;
    private String vad_condition;
    private String partenaires;
    private String date_deb;
    private String date_fin;
    private String heure;
    private String contact;
    private String web_label;
    private String evenementtpeid;
    private List<Film> films;
    private String type;
    private String titre_event;

    //Constructors

    public Event(int id, String titre, String soustitre, String affiche,
                 String description, String vad_condition, String partenaires,
                 String date_deb, String date_fin, String heure, String contact,
                 String web_label, String evenementtpeid, List<Film> films, String type, String titre_event) {
        this.id = id;
        this.titre = titre;
        this.soustitre = soustitre;
        this.affiche = affiche;
        this.description = description;
        this.vad_condition = vad_condition;
        this.partenaires = partenaires;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.heure = heure;
        this.contact = contact;
        this.web_label = web_label;
        this.evenementtpeid = evenementtpeid;
        this.films = films;
        this.type = type;
        this.titre_event = titre_event;
    }

    //Get and Set

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSoustitre() {
        return soustitre;
    }

    public void setSoustitre(String soustitre) {
        this.soustitre = soustitre;
    }

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVad_condition() {
        return vad_condition;
    }

    public void setVad_condition(String vad_condition) {
        this.vad_condition = vad_condition;
    }

    public String getPartenaires() {
        return partenaires;
    }

    public void setPartenaires(String partenaires) {
        this.partenaires = partenaires;
    }

    public String getDate_deb() {
        return date_deb;
    }

    public void setDate_deb(String date_deb) {
        this.date_deb = date_deb;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getWeb_label() {
        return web_label;
    }

    public void setWeb_label(String web_label) {
        this.web_label = web_label;
    }

    public String getEvenementtpeid() {
        return evenementtpeid;
    }

    public void setEvenementtpeid(String evenementtpeid) {
        this.evenementtpeid = evenementtpeid;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public String getTitre_event() {
        return titre_event;
    }

    public void setTitre_event(String titre_event) {
        this.titre_event = titre_event;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
