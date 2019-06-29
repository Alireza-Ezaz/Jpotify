package GUI.Listener;

import GUI.CustomPanel.CoverPanel;
import Logic.Entities.Album;
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
import java.util.ArrayList;

import static GUI.Listener.ShowMusicListener.getA;
import static GUI.MainGraph.getPn;

public class AddMusicToPlaylist implements ActionListener {
    private JPanel centerPanel;
    private Library library;
    private ArrayList<Object> centerPanelArray;
    private JFrame frame;
    private JScrollPane sp;
    private JButton artwork;
    private JLabel artworkMusicName;
    private JLabel artworkArtisiName;
    private String playListName;
    private Song newSong;


    public AddMusicToPlaylist(JPanel centerPanel, Library library, ArrayList<Object> centerPanelArray, JFrame frame, JScrollPane sp, JButton artwork, JLabel artworkMusicName, JLabel artworkArtisiName, String playListName, Song newSong) {
        this.centerPanel = centerPanel;
        this.library = library;
        this.centerPanelArray = centerPanelArray;
        this.frame = frame;
        this.artwork = artwork;
        this.sp = sp;
        this.artworkMusicName = artworkMusicName;
        this.artworkArtisiName = artworkArtisiName;
        this.playListName = playListName;
        this.newSong = newSong;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        library.addSongToSpecificPlayList(playListName, newSong);
        centerPanel.removeAll();
        if (getA() == 0) {
            frame.remove(getPn());
        }

        float[] floats = new float[3];
        SwingUtilities.updateComponentTreeUI(frame);
        frame.setVisible(true);

        for (Object ob : centerPanelArray) {
            if (ob instanceof JCheckBox) {

                if (((JCheckBox) ob).isSelected()) {
                    for (Object object : centerPanelArray) {
                        if (object instanceof JLabel) {

                        } else {
                            Color.RGBtoHSB(24, 24, 24, floats);
                            ((JComponent) object).setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
                        }
                        ((JComponent) object).setForeground(Color.lightGray);
                    }
                    Color.RGBtoHSB(24, 24, 24, floats);
                    centerPanel.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
//                    centerPanel.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));


                } else {
                    centerPanel.setBackground(Color.gray);
//                    centerPanel.setBackground(Color.gray);

                }
            }

        }


        CoverPanel addMusic = new CoverPanel();
        addMusic.setPreferredSize(new Dimension(150, 220));
        addMusic.add(addMusic.getButton());
        addMusic.getButton().addActionListener(new MusicList(centerPanel, library, centerPanelArray, frame, sp, artwork, artworkMusicName, artworkArtisiName, playListName));
        addMusic.add(addMusic.getLabel1());
        addMusic.add(addMusic.getLabel2());
        centerPanel.add(addMusic);

        for (PlayList playList : library.getPlayLists()) {
            if (playListName.equals(playList.getName())) {
                for (Song song : playList.getSongs()) {
                    if (playList.getName().equals("Favorite Songs")) {
                        song.setFavorite(true);
                    }
                    CoverPanel musicCoverPanel = new CoverPanel(song);
                    musicCoverPanel.setPreferredSize(new Dimension(150, 220));
                    musicCoverPanel.add(musicCoverPanel.getButton());
                    musicCoverPanel.getButton().addActionListener(new PlayMusicListener(song, library, artwork, artworkMusicName, artworkArtisiName, frame, playList.getSongs()));
                    musicCoverPanel.add(musicCoverPanel.getLabel1());
                    musicCoverPanel.add(musicCoverPanel.getLabel2());
                    centerPanel.add(musicCoverPanel);
                }
            }
        }
        centerPanelArray.add(centerPanel);
        SwingUtilities.updateComponentTreeUI(frame);
        frame.add(sp, BorderLayout.CENTER);
        frame.setVisible(true);
        library.saveLibrarySongs();


    }
}
