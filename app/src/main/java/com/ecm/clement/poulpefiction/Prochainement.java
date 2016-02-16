package com.ecm.clement.poulpefiction;

public class Prochainement {
    private String current;
    private String next;
    private FilmSeance[] films;

    //Constructors
    public Prochainement(String current, String next, FilmSeance[] films) {
        this.current = current;
        this.next = next;
        this.films = films;
    }

    //Get and Set
    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public FilmSeance[] getFilms() {
        return films;
    }

    public void setFilms(FilmSeance[] films) {
        this.films = films;
    }
}
