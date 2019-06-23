package Logic.Entities;

import java.util.ArrayList;
import java.util.HashMap;

public class PlaylistLibrary {
    private ArrayList<PlayList> playLists;
    private HashMap<String, ArrayList<Song>> playListsSongs;

    public PlaylistLibrary() {
        playLists = new ArrayList<PlayList>();
        playListsSongs = new HashMap<String, ArrayList<Song>>();
    }

    public void createPlayList(String playListName) {
        if (!playListsSongs.containsKey(playListName)) {
            PlayList playList = new PlayList(playListName);
            playLists.add(playList);
            playListsSongs.put(playListName, playList.getSongs());
        }

    }

    public HashMap<String, ArrayList<Song>> getPlayListsSongs() {
        return playListsSongs;
    }

    /**
     * @param playlistName is given and a song will be added to it
     */
    public void addSongToSpecificPlayList(String playlistName, Song song) {
        if (playListsSongs.containsKey(playlistName))
            playListsSongs.get(playlistName).add(song);
    }
}
