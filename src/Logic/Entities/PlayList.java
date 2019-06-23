package Logic.Entities;

import Logic.Entities.CollectionOfSongs;

import javax.swing.*;

public class PlayList extends CollectionOfSongs {


    public PlayList(String name) {
        super(name);
    }


    /**
     * Removing a song from playlist.
     * @param song
     */
    public void removeSong(Song song){
        getSongs().remove(song);
    }

}
