package Logic.Entities;

import javax.swing.*;
import java.util.ArrayList;

public class CollectionOfSongs {
    private String name;
    private ImageIcon imageIcon = null;
    private ArrayList<Song> songs;

    public CollectionOfSongs(String name) {
        songs = new ArrayList<Song>();
        this.name = name;
        //this.imageIcon  = imageIcon;
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