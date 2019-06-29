package Logic.Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Artist {
    private String name;
    private ArrayList<Song> songs;

    public Artist(String name) {
        this.name = name;
        songs = new ArrayList<Song>();
    }

    /**
     * addSong add a song to Arraylist of songs
     *
     * @param song This function take a song
     */
    public void addSong(Song song) {
        songs.add(song);
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public String getName() {
        return name;
    }

}
