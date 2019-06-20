package Logic;

import javax.swing.*;


public class Song {
    private String name;
    private String directory;
    private Artist artist;
    private String albumName;
    private ImageIcon artWork;

    public Song(String directory) {
        this.directory = directory;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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

    public Artist getArtist() {
        return artist;
    }

    public ImageIcon getArtWork() {
        return artWork;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nDirectory: " + directory + "\nArtist: " + artist.getName() + "\nAlbum: " + albumName;
    }
}
