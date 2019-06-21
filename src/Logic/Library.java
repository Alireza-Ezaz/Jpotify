package Logic;

import com.mpatric.mp3agic.*;


import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Library {
    private final String name = "MYMUSICS";
    private ArrayList<Song> songs;
    private ArrayList<Album> albums;
    private ArrayList<Artist> artists;

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
                tempAlbum.setArtist(song.getArtistName());
                tempAlbum.addSong(song);
                albumsSongs.put(song.getAlbumName(), tempAlbum.getSongs());
                albums.add(tempAlbum);
                tempAlbum = null;

            }

        } else
            System.out.println("Already added");
        song = null;
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


}

