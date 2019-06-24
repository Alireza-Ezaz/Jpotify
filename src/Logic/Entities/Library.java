package Logic.Entities;

import Logic.Player.MP3Player;
import com.mpatric.mp3agic.*;


import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Library {
    private final String name = "MYMUSICS";
    private static int numberOfSongs = 0;
    private ArrayList<Song> songs;
    private ArrayList<PlayList> playLists;
    private HashMap<String, ArrayList<Song>> playListsSongs;
    private HashMap<String, ArrayList<Song>> albumsSongs;
    private HashMap<String, ArrayList<Song>> artistsSongs;


    public Library() {
        songs = new ArrayList<Song>();
        playLists = new ArrayList<PlayList>();
        playListsSongs = new HashMap<String, ArrayList<Song>>();

        albumsSongs = new HashMap<String, ArrayList<Song>>();
        artistsSongs = new HashMap<String, ArrayList<Song>>();
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

    public HashMap<String, ArrayList<Song>> getPlayListsSongs() {
        return playListsSongs;
    }

    public HashMap<String, ArrayList<Song>> getAlbumsSongs() {
        return albumsSongs;
    }

    public HashMap<String, ArrayList<Song>> getArtistsSongs() {
        return artistsSongs;
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
                song.setArtWork(new ImageIcon(albumImageData));


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
     * This method classifies the songs and add them to hashMaps in the fields
     */
    private void classifySongs() {
        //classifying into albums and artists
        for (Song song : songs) {
            if (albumsSongs.containsKey(song.getAlbumName())) {
                albumsSongs.get(song.getAlbumName()).add(song);
            }
            if (!albumsSongs.containsKey(song.getAlbumName()) && song.getAlbumName().trim().length() != 0) {
                Album album = new Album(song.getArtistName(), song.getArtWork());
                album.addSong(song);
                albumsSongs.put(song.getAlbumName(), album.getSongs());
            }

            if (artistsSongs.containsKey(song.getArtistName())) {
                artistsSongs.get(song.getArtistName()).add(song);
            }
            if (!artistsSongs.containsKey(song.getArtistName())) {
                Artist artist = new Artist(song.getArtistName());
                artist.addSong(song);
                artistsSongs.put(song.getArtistName(), artist.getSongs());
            }
        }
        //classifying playLists
        for (PlayList playList : playLists) {
            playListsSongs.put(playList.getName(), playList.getSongs());
        }

    }

    /**
     * This method create a playlist
     *
     * @param playListName name of the playlist
     */
    public void createPlayList(String playListName) {
        if (!playListsSongs.containsKey(playListName) && playListName.trim().length() != 0) {
            PlayList playList = new PlayList(playListName);
            playLists.add(playList);
            playListsSongs.put(playListName, playList.getSongs());
        }

    }

    /**
     * @param playlistName is given and a song will be added to it
     */
    public void addSongToSpecificPlayList(String playlistName, Song song) {
        for(PlayList playList:playLists){
            if(playList.getName().equals(playlistName)){
                playList.addSong(song);
                }
        }
    }

    /**
     * This method save the songs and playlists in a bin file in order to load them later
     */
    public void saveLibrarySongs() {
        try {

            FileOutputStream fileOutputStream = new FileOutputStream("AllSongs.bin", false);
            FileOutputStream fileOutputStream1 = new FileOutputStream("Playlists.bin", false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);


            //The order of writing in file is important
            objectOutputStream.writeObject(numberOfSongs);
            objectOutputStream.writeObject(songs);
            objectOutputStream1.writeObject(playLists);

            objectOutputStream.close();
            objectOutputStream1.close();
            fileOutputStream.close();
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
            FileInputStream fileInputStream = new FileInputStream("AllSongs.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            FileInputStream fileInputStream1 = new FileInputStream("Playlists.bin");
            ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1);
            numberOfSongs = (int) objectInputStream.readObject();
            songs = (ArrayList<Song>) objectInputStream.readObject();
            playLists = (ArrayList<PlayList>) objectInputStream1.readObject();
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
  /*  public void printPlayListSongs(){
        for(Map.Entry<String,ArrayList<Song>> entry:playListsSongs.entrySet()){
            System.out.println(entry.getKey());
            ArrayList<Song> songs = entry.getValue();
            for(Song song: songs)
                System.out.println(song);
        }
    }*/

   /* public static void main(String[] args) {
        Library library = new Library();
        library.loadSongs();
        library.classifySongs();
        library.addSong("F:\\Jpotify\\src\\Logic\\Entities\\1.mp3");
        library.addSong("F:\\Jpotify\\src\\Logic\\Entities\\2.mp3");
        library.addSong("F:\\Jpotify\\src\\Logic\\Entities\\3.mp3");
        library.addSong("F:\\Jpotify\\src\\Logic\\Entities\\4.mp3");
        library.addSong("F:\\Jpotify\\src\\Logic\\Entities\\5.mp3");
        library.addSong("F:\\Jpotify\\src\\Logic\\Entities\\6.mp3");
        library.addSong("F:\\Jpotify\\src\\Logic\\Entities\\7.mp3");
        library.addSong("F:\\Jpotify\\src\\Logic\\Entities\\8.mp3");
        library.addSong("F:\\Jpotify\\src\\Logic\\Entities\\9.mp3");
        library.addSong("F:\\Jpotify\\src\\Logic\\Entities\\10.mp3");
        library.addSong("F:\\Jpotify\\src\\Logic\\Entities\\11.mp3");
        library.addSong("F:\\Jpotify\\src\\Logic\\Entities\\12.mp3");
        library.addSong("F:\\Jpotify\\src\\Logic\\Entities\\13.mp3");
        library.addSong("F:\\Jpotify\\src\\Logic\\Entities\\14.mp3");
        System.out.println("Number of songs: " + numberOfSongs);

        library.createPlayList("PlayList 1");
        library.addSongToSpecificPlayList("PlayList 1",library.getSongs().get(0));
        library.addSongToSpecificPlayList("PlayList 1",library.getSongs().get(1));
        library.addSongToSpecificPlayList("PlayList 1",library.getSongs().get(2));
        //library.printPlayListSongs();

        //library.saveLibrarySongs();




        System.out.println(library);
        MP3Player mp3Player = new MP3Player(library.songs.get(0));
        Thread t = new Thread(mp3Player);
        t.start();


    }*/


}

