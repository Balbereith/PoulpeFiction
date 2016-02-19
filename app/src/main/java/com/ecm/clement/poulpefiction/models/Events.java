package com.ecm.clement.poulpefiction.models;

import com.ecm.clement.poulpefiction.models.Event;

public class Events {
    private String type;
    private Event[] events;
    private String titre;

    //Constructors
    public Events(String type, Event[] events, String titre) {
        this.type = type;
        this.events = events;
        this.titre = titre;
    }


    //Get and Set
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
