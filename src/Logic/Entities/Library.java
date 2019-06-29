package Logic.Entities;

import Logic.Player.MP3Player;
import com.mpatric.mp3agic.*;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author S.Alireza-Ezaz
 * @version final
 * This class handles songs,playlists,albums,...
 */

public class Library {
    private final String name = "MYMUSICS";
    private static String username;
    private static int numberOfSongs = 0;
    private ArrayList<Song> songs;
    private ArrayList<PlayList> playLists;
    private ArrayList<Album> albums;
    private ArrayList<Artist> artists;
    // private HashMap<String, ArrayList<Song>> playListsSongs;
    private HashMap<String, ArrayList<Song>> albumsSongs;
    private HashMap<String, ArrayList<Song>> artistsSongs;


    public Library(String username) {
        this.username = username;
        songs = new ArrayList<Song>();
        playLists = new ArrayList<PlayList>();
        albums = new ArrayList<Album>();
        artists = new ArrayList<Artist>();
        albumsSongs = new HashMap<String, ArrayList<Song>>();
        artistsSongs = new HashMap<String, ArrayList<Song>>();
        PlayList favoritePlayList = new PlayList("Favorite Songs");
        PlayList sharedPlaylist = new PlayList("Shared Playlist");
        playLists.add(favoritePlayList);
        playLists.add(sharedPlaylist);
    }

    public static String getUsername() {
        return username;
    }

    public static void setNumberOfSongs(int numberOfSongs) {
        Library.numberOfSongs = numberOfSongs;
    }

    public static int getNumberOfSongs() {
        return numberOfSongs;
    }

    public ArrayList<PlayList> getPlayLists() {
        return playLists;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public HashMap<String, ArrayList<Song>> getAlbumsSongs() {
        return albumsSongs;
    }

    public HashMap<String, ArrayList<Song>> getArtistsSongs() {
        return artistsSongs;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    /**
     * @param songDirectory This function find music with songDirectory and add it to library.
     */
    public void addSong(String songDirectory) {
        Song song = findSong(songDirectory);
        if (!songs.contains(song)) {
            songs.add(song);
            classifySongs();
        } else
            System.out.println("Already added");

        //when a new music is added to a library numberOfSongs increases by 1
        numberOfSongs++;
    }

    /**
     * This method will remove a song from library
     */
    public void removeSong(String songDirectory) {
        Song tempSong = null;
        for (Song song : songs)
            if (song.getDirectory().equals(songDirectory))
                tempSong = song;

        if (tempSong != null) {
            songs.remove(tempSong);
            for (PlayList playList : playLists) {
                for (Song song : playList.getSongs()) {
                    if (song.getName().equals(tempSong.getName()))
                        playList.getSongs().remove(song);
                    saveLibrarySongs();
                    break;
                }
            }
        }

    }

    /**
     * @param directory This functoin take a directory to create a song object
     * @return a song object that has its metadata extracted
     */
    private Song findSong(String directory) {
        Song song = new Song(directory);
        try {
            File songFile = new File(directory);
            byte[] mp3tag = new byte[3];
            byte[] mp3title = new byte[30];
            byte[] mp3artist = new byte[30];
            byte[] mp3album = new byte[30];
            FileInputStream inputStream = new FileInputStream(directory);
            RandomAccessFile randomAccessFile = new RandomAccessFile(songFile, "r");
            randomAccessFile.seek(songFile.length() - 128);
            randomAccessFile.read(mp3tag, 0, 3);
            randomAccessFile.read(mp3title, 0, 30);
            randomAccessFile.read(mp3artist, 0, 30);
            randomAccessFile.read(mp3album, 0, 30);
            song.setName(new String(mp3title, "US-ASCII"));
            song.setArtistName(new String(mp3artist, "US-ASCII"));
            song.setAlbumName(new String(mp3album, "US-ASCII"));
            inputStream.close();
            Mp3File mp3file = new Mp3File(directory);
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            byte[] albumImageData = id3v2Tag.getAlbumImage();
            //All files do not have image icon;
            if (albumImageData != null)
                song.setArtWork(new ImageIcon(ImageIO.read(new ByteArrayInputStream(albumImageData)).getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
            //                song.setArtWork(new ImageIcon(albumImageData));


        } catch (FileNotFoundException e) {
            System.err.println("Couldnt find file");
        } catch (IOException e) {
            System.err.println("IOEExeption");
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            System.err.println("UnsupportedTagException");
        }

        return song;
    }

    /**
     * This method classifies the songs and add them to hashMaps and arraylists in the fields
     */
    public void classifySongs() {
        //classifying into albums and artists


        for (Song song : songs) {
            if (albumsSongs.containsKey(song.getAlbumName())) {
                if (!albumsSongs.get(song.getAlbumName()).contains(song)) {
                    albumsSongs.get(song.getAlbumName()).add(song);
                    for (Album album : albums)
                        if (album.getName().equals(song.getAlbumName())) {
                            if (!album.getSongs().contains(song))
                                album.getSongs().add(song);
                            break;
                        }
                }
            }


            if (!albumsSongs.containsKey(song.getAlbumName()) && song.getAlbumName().trim().length() != 0) {
                Album album = new Album(song.getAlbumName(), song.getArtistName(), song.getArtWork());
                album.addSong(song);
                albums.add(album);
                albumsSongs.put(song.getAlbumName(), album.getSongs());
            }

            if (artistsSongs.containsKey(song.getArtistName())) {
                if (!artistsSongs.get(song.getArtistName()).contains(song)) {
                    artistsSongs.get(song.getArtistName()).add(song);
                    for (Artist artist : artists)
                        if (artist.getName().equals(song.getArtistName())) {
                            if (!artist.getSongs().contains(song))
                                artist.getSongs().add(song);
                            break;
                        }
                }
            }
            if (!artistsSongs.containsKey(song.getArtistName())) {
                Artist artist = new Artist(song.getArtistName());
                artists.add(artist);
                artist.addSong(song);
                artistsSongs.put(song.getArtistName(), artist.getSongs());
            }
        }
    }

    /**
     * This method will sort songs by their lastPlay
     */
    private void sortSongs() {
        Collections.sort(songs, new Comparator<Song>() {
            public int compare(Song s1, Song s2) {
                if (s1.getLastPlay().equals(s2.getLastPlay()))
                    return 0;
                return s1.getLastPlay().after(s2.getLastPlay()) ? -1 : 1;
            }
        });
    }

    /**
     * This method create a playlist
     * @param playListName name of the playlist
     */
    public void createPlayList(String playListName, ImageIcon imageIcon) {
        if (playListName.trim().length() != 0) {
            boolean flag = true;
            for (PlayList playList : playLists)
                if (playList.getName().equals(playListName))
                    flag = false;

            if (flag) {
                PlayList playList = new PlayList(playListName);
                playList.setImageIcon(imageIcon);
                playLists.add(playList);
                //playListsSongs.put(playListName, playList.getSongs());}
            }
        }
    }

    /**
     * @param playlistName is given and a song will be added to it
     */
    public void addSongToSpecificPlayList(String playlistName, Song song) {
        for (PlayList playList : playLists) {
            if (playList.getName().equals(playlistName) && !playList.getSongs().contains(song)) {
                playList.addSong(song);
            }
        }
    }

    /**
     * This method will remove a playList
     * This method does not let the user to delete static playLists
     */
    public void removePlaylist(String playlistName) {
        if (playlistName.equals("Favorite Songs") && playlistName.equals("Shared Playlist"))
            return;
        PlayList tempPlayList = null;
        for (PlayList playList : playLists)
            if (playList.getName().equals(playlistName))
                tempPlayList = playList;

        if (tempPlayList != null)
            playLists.remove(tempPlayList);


    }

    /**
     * This method save the songs and playlists in a bin file in order to load them later
     */
    public void saveLibrarySongs() {
        try {

            FileOutputStream fileOutputStream = new FileOutputStream(username + "_lib.bin", false);
            FileOutputStream fileOutputStream1 = new FileOutputStream(username + "_Playlists.bin", false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);


            //The order of writing in file is important
            objectOutputStream.writeObject(numberOfSongs);
            objectOutputStream.writeObject(songs);
            objectOutputStream1.writeObject(playLists);

            objectOutputStream.close();
            objectOutputStream1.close();
            fileOutputStream.close();
            //System.out.println("saved");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method loads the songs
     */
    public void loadSongs() {
        try {
            FileInputStream fileInputStream = new FileInputStream(username + "_lib.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            FileInputStream fileInputStream1 = new FileInputStream(username + "_Playlists.bin");
            ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1);
            numberOfSongs = (int) objectInputStream.readObject();
            songs = (ArrayList<Song>) objectInputStream.readObject();
            playLists = (ArrayList<PlayList>) objectInputStream1.readObject();
            sortSongs();
            fileInputStream.close();
            fileInputStream1.close();
            classifySongs();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        String str = "<< All Songs >>\n\n";
        for (Song song : songs) {
            str = str.concat(song.toString()) + "\n\n";

        }
        return str;
    }
}

