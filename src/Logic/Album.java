package Logic;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Album extends CollectionOfSongs {
    private Artist artist;


    public Album(String name, ImageIcon imageIcon) {
        super(name, imageIcon);
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return super.toString() + "Artist: " + artist.getName();
    }
}
