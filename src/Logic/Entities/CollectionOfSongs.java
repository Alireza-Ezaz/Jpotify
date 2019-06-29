package Logic.Entities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class CollectionOfSongs implements Serializable {
    private String name = "";
    private ImageIcon imageIcon = null;
    private ArrayList<Song> songs;

    public CollectionOfSongs(String name) {
        songs = new ArrayList<Song>();
        this.name = name;
        try {
            BufferedImage img = ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\artwork.png"));
            imageIcon = new ImageIcon(img);

        } catch (Exception ex) {
            System.out.println("Image not found");

        }
    }

    public void setName(String newName) {
       // if(this.name.equals("Favorite Songs")|| this.name.equals("Shared Playlist"))
          //  return;
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }


    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    /**
     * addSong add a song to Arraylist of songs
     * @param song This function take a song
     */
    public void addSong(Song song){
        if (!songs.contains(song))
        songs.add(song);
    }

    @Override
    public String toString() {
        return "Name:" + name+"\n";
    }
}
