package com.josmartinez.topmoviesapp;

public class Poster {

    private long id;
    private String originalTitle;
    private String posterPath;
    private String plotSynopsis;
    private double userRating;
    private String releaseDate;

    public Poster(long id, String originalTitle, String posterPath, String synopsis, double rating, String releaseDate) {
        this.id = id;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.plotSynopsis = synopsis;
        this.userRating = rating;
        this.releaseDate = releaseDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getSynopsis() {
        return plotSynopsis;
    }

    public void setSynopsis(String synopsis) {
        this.plotSynopsis = synopsis;
    }

    public double getRating() {
        return userRating;
    }

    public void setRating(double rating) {
        this.userRating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }



}
