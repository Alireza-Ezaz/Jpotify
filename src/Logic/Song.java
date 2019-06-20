package Logic;

import javax.swing.*;


public class Song {
    private String name;
    private String directory;
    private String artist;
    private String album;
    private ImageIcon artWork;

    public Song(String directory) {
        this.directory = directory;
    }

    public String getName() {
        return name;
    }

    public String getDirectory() {
        return directory;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public ImageIcon getArtWork() {
        return artWork;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nDirectory: " + directory + "\nArtist: " + artist + "\nAlbum: " + album;
    }
}
