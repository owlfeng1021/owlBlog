package com.owl.owlBlog.bo;

public class Music {
    private String name;
    private String url;
    private String artist;
    private String cover;
    private String lrc;

    public Music() {
    }

    public Music(String name, String url, String artist, String cover, String lrc) {
        this.name = name;
        this.url = url;
        this.artist = artist;
        this.cover = cover;
        this.lrc = lrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getLrc() {
        return lrc;
    }

    public void setLrc(String lrc) {
        this.lrc = lrc;
    }

}
