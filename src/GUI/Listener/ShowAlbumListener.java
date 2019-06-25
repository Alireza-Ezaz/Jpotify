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
import java.util.Map;

public class ShowAlbumListener implements ActionListener {
    private JPanel centerPanel;
    private Library library;
    private ArrayList<Object> centerPanelArray;
    private JFrame frame;
    private JPanel albums;


    public ShowAlbumListener(JPanel centerPanel, Library library, ArrayList<Object> centerPanelArray, JFrame frame, JPanel albums) {
        this.centerPanel = centerPanel;
        this.library = library;
        this.centerPanelArray = centerPanelArray;
        this.frame = frame;
        this.albums = albums;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        float[] floats = new float[3];
        albums.removeAll();
        SwingUtilities.updateComponentTreeUI(frame);
        frame.setVisible(true);
        library.loadSongs();

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
                    albums.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
                    centerPanel.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));


                } else {
                    albums.setBackground(Color.gray);
                    centerPanel.setBackground(Color.gray);

                }
            }

        }



        library.loadSongs();
        albums.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 300, 730));
        for (Album album : library.getAlbums()) {
            CoverPanel musicCoverPanel = new CoverPanel(album);
            musicCoverPanel.setPreferredSize(new Dimension(150, 220));
            musicCoverPanel.add(musicCoverPanel.getButton());
            musicCoverPanel.add(musicCoverPanel.getLabel1());
            musicCoverPanel.add(musicCoverPanel.getLabel2());
            albums.add(musicCoverPanel);
        }


        centerPanel.add(albums);
        centerPanelArray.add(albums);
        SwingUtilities.updateComponentTreeUI(frame);
        frame.setVisible(true);


    }
}
