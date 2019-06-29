package Logic.Entities;

import javax.swing.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Album extends CollectionOfSongs {
    private String artistName;



    public Album(String name, String artistName, ImageIcon imageIcon) {
        super(name);
        this.artistName = artistName;
        super.setImageIcon(imageIcon);
    }

    public void setArtist(String artist) {
        this.artistName = artist;
    }



    @Override
    public String toString() {
        return super.toString() + "Artist: " + artistName;
    }


}
