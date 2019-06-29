package Logic.Entities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Song implements Serializable {
    private String name;
    private String directory;
    private String artistName;
    private String albumName;
    private String lyric = "not downloaded";
    private boolean isFavorite = false;
    private ImageIcon artWork = null;
    private Date lastPlay;

    public Song(String directory) {
        this.directory = directory;
        lastPlay = new Date();
        try {
//            BufferedImage img = (BufferedImage) ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\artwork.png")).getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            artWork = new ImageIcon(ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\artwork.png")).getScaledInstance(150, 150, Image.SCALE_SMOOTH));

        } catch (Exception ex) {
            System.out.println("Image not found");

        }
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

    public void setLastPlay(Date lastPlay) {
        this.lastPlay = lastPlay;
    }

    public Date getLastPlay() {
        return lastPlay;
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
        return "Name: " + name + "\nDirectory: " + directory + "\nArtist: " + artistName + "\nAlbum: " + albumName + "\n"+"Last play: "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lastPlay)+"\n";
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
