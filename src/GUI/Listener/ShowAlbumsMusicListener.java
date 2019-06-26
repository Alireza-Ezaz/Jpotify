package GUI.Listener;

import GUI.CustomPanel.CoverPanel;
import Logic.Entities.Album;
import Logic.Entities.Library;
import Logic.Entities.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowAlbumsMusicListener implements ActionListener {

    private JPanel centerPanel;
    private Library library;
    private ArrayList<Object> centerPanelArray;
    private JFrame frame;
    private JPanel musics;
    private JButton artwork;
    private JLabel artworkMusicName;
    private JLabel artworkArtisiName;
    private String albumName;


    public ShowAlbumsMusicListener(JPanel centerPanel, Library library, ArrayList<Object> centerPanelArray, JFrame frame, JPanel musics, JButton artwork, JLabel artworkMusicName, JLabel artworkArtisiName, String albumName) {
        this.centerPanel = centerPanel;
        this.library = library;
        this.centerPanelArray = centerPanelArray;
        this.frame = frame;
        this.musics = musics;
        this.artwork = artwork;
        this.artworkMusicName = artworkMusicName;
        this.artworkArtisiName = artworkArtisiName;
        this.albumName = albumName;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        float[] floats = new float[3];
        musics.removeAll();
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
                    musics.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
                    centerPanel.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));


                } else {
                    musics.setBackground(Color.gray);
                    centerPanel.setBackground(Color.gray);

                }
            }

        }

        library.loadSongs();
        musics.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 300, 730));
        for (Album album : library.getAlbums()) {
            if (albumName.equals(album.getName())) {
                for (Song song : album.getSongs()) {
                    CoverPanel musicCoverPanel = new CoverPanel(song);
                    musicCoverPanel.setPreferredSize(new Dimension(150, 220));
                    musicCoverPanel.add(musicCoverPanel.getButton());
                    musicCoverPanel.getButton().addActionListener(new PlayMusicListener(song, library, artwork, artworkMusicName, artworkArtisiName, frame,album.getSongs()));
                    musicCoverPanel.add(musicCoverPanel.getLabel1());
                    musicCoverPanel.add(musicCoverPanel.getLabel2());
                    musics.add(musicCoverPanel);
                }
            }
        }


        centerPanel.add(musics);
        centerPanelArray.add(musics);
        SwingUtilities.updateComponentTreeUI(frame);
        frame.setVisible(true);
        library.saveLibrarySongs();
    }
}
