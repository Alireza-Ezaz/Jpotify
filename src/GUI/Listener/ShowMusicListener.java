package GUI.Listener;

import GUI.CustomPanel.CoverPanel;
import Logic.Entities.Library;
import Logic.Entities.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowMusicListener implements ActionListener {
    private JPanel centerPanel;
    private Library library;
    private ArrayList<Object> centerPanelArray;
    private JFrame frame;
    private JPanel musics;
    private JButton artwork;
    private JLabel artworkMusicName;
    private JLabel artworkArtisiName;


    public ShowMusicListener(JPanel centerPanel, Library library, ArrayList<Object> centerPanelArray, JFrame frame, JPanel musics, JButton artwork, JLabel artworkMusicName, JLabel artworkArtisiName) {
        this.centerPanel = centerPanel;
        this.library = library;
        this.centerPanelArray = centerPanelArray;
        this.frame = frame;
        this.musics = musics;
        this.artwork = artwork;
        this.artworkMusicName = artworkMusicName;
        this.artworkArtisiName = artworkArtisiName;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // centerPanelArray.remove(musics);
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
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(musics);
        scrollPane.setSize(new Dimension(1024,800));
        frame.add(scrollPane);


        library.loadSongs();
        musics.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 300, 730));
        for (Song song : library.getSongs()) {
            CoverPanel musicCoverPanel = new CoverPanel(song);
            musicCoverPanel.setPreferredSize(new Dimension(150, 220));
            musicCoverPanel.add(musicCoverPanel.getButton());
            musicCoverPanel.getButton().addActionListener(new PlayMusicListener(song, library, artwork, artworkMusicName, artworkArtisiName, frame));
            musicCoverPanel.add(musicCoverPanel.getLabel1());
            musicCoverPanel.add(musicCoverPanel.getLabel2());
            musics.add(musicCoverPanel);
        }






        centerPanel.add(musics);
        centerPanelArray.add(musics);
        SwingUtilities.updateComponentTreeUI(frame);
        frame.setVisible(true);
        library.saveLibrarySongs();


    }
}
