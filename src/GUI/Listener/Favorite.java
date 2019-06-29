package GUI.Listener;

import GUI.MainGraph;
import Logic.Entities.Library;
import Logic.Entities.PlayList;
import Logic.Entities.Song;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Favorite implements ActionListener {

    private Library library;

    public Favorite( Library library) {

        this.library = library;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Song song = PlayMusicListener.getMp3Player().getPlayingSong();
        if (song.isFavorite()) {
            try {
                MainGraph.getFavorite().setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\unFavorite.png"))));
                song.setFavorite(false);
                for (PlayList playList : library.getPlayLists()) {
                    if (playList.getName().equals("Favorite Songs")) {
                        for (Song sg : playList.getSongs()){
                            if (sg.getName().equals(song.getName())){
                                sg.setFavorite(false);
                                playList.removeSong(sg);
                                break;
                            }
                        }
                        break;
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            try {
                MainGraph.getFavorite().setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\favorite.png")).getScaledInstance(150, 30, Image.SCALE_SMOOTH)));
                song.setFavorite(true);
                for (PlayList playList : library.getPlayLists()) {
                    if (playList.getName().equals("Favorite Songs")) {
                        playList.addSong(song);
                        break;
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        library.saveLibrarySongs();
        System.out.println();
    }
}
