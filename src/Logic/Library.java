package Logic;

import java.util.ArrayList;
import java.util.HashMap;

public class Library {
    private final String name = "MYMUSICS";
    private ArrayList<Song> songs;
    private ArrayList<Album> albums;
    private ArrayList<Artist>artists;

    private HashMap<String, ArrayList<Song>> albumsSongs;
    private HashMap<String, ArrayList<Song>> playListsSongs;
    private HashMap<String, ArrayList<Song>> artistsSongs;

    /**
     * @param songDirectory This function find music with songDirectory and add it to library.
     */
    public void addSong(String songDirectory) {
        Song song = findSong(songDirectory);
        if (!songs.contains(song)) {
            songs.add(song);
            if (albumsSongs.containsKey(song.getAlbumName())) {
                albumsSongs.get(song.getAlbumName()).add(song);
            } else {
                Album tempAlbum = new Album(song.getAlbumName(), song.getArtWork());
                tempAlbum.setArtist(song.getArtist());
                tempAlbum.addSong(song);
                albumsSongs.put(song.getAlbumName(), tempAlbum.getSongs());
                albums.add(tempAlbum);
                tempAlbum = null;

            }

        } else
            System.out.println("Already added");
        song = null;
    }
    /*
    private Song findSong(String directory){
        //new artist
    }*/

}

