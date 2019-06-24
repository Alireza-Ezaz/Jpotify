package Logic.Entities;

import Logic.Entities.CollectionOfSongs;

import javax.swing.*;
import java.io.Serializable;

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
