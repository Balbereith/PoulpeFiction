package com.ecm.clement.poulpefiction.models;

/**
 * Created by Clement on 07/03/2016.
 */
public class Media {
    private String path;

    public Media(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String paths) {
        this.path = paths;
    }

    @Override
    public String toString() {
        return "Medias{" +
                "paths=" + path +
                '}';
    }
}
