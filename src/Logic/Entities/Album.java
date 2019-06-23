package Logic;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Album extends CollectionOfSongs {
    private String artistName;


    public Album(String artistName, ImageIcon imageIcon) {
        super(artistName, imageIcon);
    }

    public void setArtist(String artist) {
        this.artistName = artist;
    }

    @Override
    public String toString() {
        return super.toString() + "Artist: " + artistName;
    }
}
