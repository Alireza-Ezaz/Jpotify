package Logic.Entities;

import javax.swing.*;

public class Album extends CollectionOfSongs {
    private String artistName;


    public Album(String artistName, ImageIcon imageIcon) {
        super(artistName);
    }

    public void setArtist(String artist) {
        this.artistName = artist;
    }

    @Override
    public String toString() {
        return super.toString() + "Artist: " + artistName;
    }
}
