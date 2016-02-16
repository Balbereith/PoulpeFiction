package com.ecm.clement.poulpefiction;

public class Data {
    private Events[] events;
    private FilmSeance[] filmSeances;
    private Prochainement[] prochainements;
    private Seance[] seances;
    
    //Constructor
    public Data(Events[] events, FilmSeance[] filmSeances, Prochainement[] prochainements, Seance[] seances) {
        this.events = events;
        this.filmSeances = filmSeances;
        this.prochainements = prochainements;
        this.seances = seances;
    }
    
    //Get and Set

    public Events[] getEvents() {
        return events;
    }

    public void setEvents(Events[] events) {
        this.events = events;
    }

    public FilmSeance[] getFilmSeances() {
        return filmSeances;
    }

    public void setFilmSeances(FilmSeance[] filmSeances) {
        this.filmSeances = filmSeances;
    }

    public Prochainement[] getProchainements() {
        return prochainements;
    }

    public void setProchainements(Prochainement[] prochainements) {
        this.prochainements = prochainements;
    }

    public Seance[] getSeances() {
        return seances;
    }

    public void setSeances(Seance[] seances) {
        this.seances = seances;
    }
}
