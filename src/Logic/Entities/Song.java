package Logic.Entities;

import javax.swing.*;
import java.io.Serializable;


public class Song implements Serializable {
    private String name;
    private String directory;
    private String artistName;
    private String albumName;
    private String lyric = "not downloaded";
    private boolean isFavorite = false;
    private ImageIcon artWork = null;

    public Song(String directory) {
        this.directory = directory;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtWork(ImageIcon artWork) {
        this.artWork = artWork;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getName() {
        return name;
    }

    public String getDirectory() {
        return directory;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public ImageIcon getArtWork() {
        return artWork;
    }

    public String getLyric() {
        return lyric;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nDirectory: " + directory + "\nArtist: " + artistName + "\nAlbum: " + albumName + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Song))
            return false;
        Song other = (Song) obj;
        return name.equals(other.name) && directory.equals(other.directory);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + name.hashCode();
        result = 37 * result + directory.hashCode();
        return result;
    }
}
