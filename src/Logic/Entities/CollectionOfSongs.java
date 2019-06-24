package Logic.Entities;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class CollectionOfSongs implements Serializable {
    private String name;
    private ImageIcon imageIcon = null;
    private ArrayList<Song> songs;

    public CollectionOfSongs(String name) {
        songs = new ArrayList<Song>();
        this.name = name;
        //this.imageIcon  = imageIcon;
    }

    public void setName(String name) {
        if(this.name.equals("Favorite Songs")&& this.name.equals("Shared Playlist"))
            return;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    /**
     * addSong add a song to Arraylist of songs
     * @param song This function take a song
     */
    public void addSong(Song song){
        songs.add(song);
    }

    @Override
    public String toString() {
        return "Name:" + name+"\n";
    }
}
