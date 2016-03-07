package com.ecm.clement.poulpefiction.models;

import java.util.List;

public class Film {

    private int id = 0;
    private String titre = null;
    private String titre_vo = null;
    private String affiche = null;
    private String web = null;
    private int duree = 0;
    private String distributeur = null;
    private String participants = null;
    private String realisateur = null;
    private String synopsis = null;
    private int annee = 0;
    private String date_sortie = null;
    private String info = null;
    private Boolean is_visible = false;
    private Boolean is_vente = false;
    private int genreid = 0;
    private int categorieid = 0;
    private String genre = null;
    private String categorie = null;
    private String releaseNumber = null;
    private String pays = null;
    private String share_url = null;
    private List<Media> medias = null;
    private List<Video> videos = null;
    private Boolean is_avp = false;
    private Boolean is_alaune = false;
    private Boolean is_lastWeek = false;
    private Boolean is_prochainement=false;
    private Boolean is_affiche;

    //Constructors
    public Film(int id, String titre, String titre_vo, String affiche, String web,
                int duree, String distributeur, String participants,
                String realisateur, String synopsis, int annee,
                String date_sortie, String info, boolean is_visible,
                boolean is_vente, int genreid, int categorieid, String genre,
                String categorie, String releaseNumber, String pays, String share_url,
                List<Media> medias, List<Video> videos, boolean is_avp, boolean is_alaune,
                boolean is_lastWeek, boolean is_prochainement, boolean is_affiche) {
        this.id = id;
        this.titre = titre;
        this.titre_vo = titre_vo;
        this.affiche = affiche;
        this.web = web;
        this.duree = duree;
        this.distributeur = distributeur;
        this.participants = participants;
        this.realisateur = realisateur;
        this.synopsis = synopsis;
        this.annee = annee;
        this.date_sortie = date_sortie;
        this.info = info;
        this.is_visible = is_visible;
        this.is_vente = is_vente;
        this.genreid = genreid;
        this.categorieid = categorieid;
        this.genre = genre;
        this.categorie = categorie;
        this.releaseNumber = releaseNumber;
        this.pays = pays;
        this.share_url = share_url;
        this.medias = medias;
        this.videos = videos;
        this.is_avp = is_avp;
        this.is_alaune = is_alaune;
        this.is_lastWeek = is_lastWeek;
        this.is_prochainement = is_prochainement;
        this.is_affiche = is_affiche;
    }
    
    //Get and set

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

    public String getTitre_vo() {
        return titre_vo;
    }

    public void setTitre_vo(String titre_vo) {
        this.titre_vo = titre_vo;
    }

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getDistributeur() {
        return distributeur;
    }

    public void setDistributeur(String distributeur) {
        this.distributeur = distributeur;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        synopsis = synopsis;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getDate_sortie() {
        return date_sortie;
    }

    public void setDate_sortie(String date_sortie) {
        this.date_sortie = date_sortie;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean getIs_visible() {
        return is_visible;
    }

    public void setIs_visible(boolean is_visible) {
        this.is_visible = is_visible;
    }

    public boolean getIs_vente() {
        return is_vente;
    }

    public void setIs_vente(boolean is_vente) {
        this.is_vente = is_vente;
    }

    public int getGenreid() {
        return genreid;
    }

    public void setGenreid(int genreid) {
        this.genreid = genreid;
    }

    public int getCategorieid() {
        return categorieid;
    }

    public void setCategorieid(int categorieid) {
        this.categorieid = categorieid;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getReleaseNumber() {
        return releaseNumber;
    }

    public void setReleaseNumber(String releaseNumber) {
        this.releaseNumber = releaseNumber;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public List<Media> getMedias() {
        return medias;
    }

    public void setMedias(List<Media> medias) {
        this.medias = medias;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public boolean is_avp() {
        return is_avp;
    }

    public void setIs_avp(boolean is_avp) {
        this.is_avp = is_avp;
    }

    public boolean is_alaune() {
        return is_alaune;
    }

    public void setIs_alaune(boolean is_alaune) {
        this.is_alaune = is_alaune;
    }

    public boolean is_lastWeek() {
        return is_lastWeek;
    }

    public void setIs_lastWeek(boolean is_lastWeek) {
        this.is_lastWeek = is_lastWeek;
    }

    public boolean is_prochainement() {
        return is_prochainement;
    }

    public void setIs_prochainement(boolean is_prochainement) {
        this.is_prochainement = is_prochainement;
    }

    public boolean is_affiche() {
        return is_affiche;
    }

    public void setIs_affiche(boolean is_affiche) {
        this.is_affiche = is_affiche;
    }
}

