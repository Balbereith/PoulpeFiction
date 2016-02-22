package com.ecm.clement.poulpefiction.models;

public class Video {
    private String titre;
    private String type;
    private String url;

    public Video(String titre, String type, String url) {
        this.titre = titre;
        this.type = type;
        this.url = url;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Videos{" +
                "titre='" + titre + '\'' +
                ", type=" + type +
                ", url='" + url + '\'' +
                '}';
    }
}
