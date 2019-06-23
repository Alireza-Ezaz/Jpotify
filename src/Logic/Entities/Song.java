package Logic;

import javax.swing.*;


public class Song {
    private String name;
    private String directory;
    private String artistName;
    private String albumName;
    private String lyric ="not downloaded";
    private ImageIcon artWork;

    public Song(String directory) {
        this.directory = directory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtWork(ImageIcon artWork) {
        this.artWork = artWork;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getName() {
        return name;
    }

    public String getDirectory() {
        return directory;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public ImageIcon getArtWork() {
        return artWork;
    }

    public String getLyric() {
        return lyric;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nDirectory: " + directory + "\nArtist: " + artistName + "\nAlbum: " + albumName;
    }
}
