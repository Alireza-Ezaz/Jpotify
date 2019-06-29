package Logic.Entities;

/**
 * @author S.Alireza-Ezaz
 * @version final
 */
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
    //@Override


}
