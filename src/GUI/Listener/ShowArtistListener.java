package GUI.Listener;

import GUI.CustomPanel.CoverPanel;
import Logic.Entities.Artist;
import Logic.Entities.Library;
import Logic.Entities.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowArtistListener implements ActionListener {
    private JPanel centerPanel;
    private Library library;
    private ArrayList<Object> centerPanelArray;
    private JFrame frame;
    private JPanel artists;


    public ShowArtistListener(JPanel centerPanel, Library library, ArrayList<Object> centerPanelArray, JFrame frame, JPanel artists) {
        this.centerPanel = centerPanel;
        this.library = library;
        this.centerPanelArray = centerPanelArray;
        this.frame = frame;
        this.artists = artists;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //centerPanelArray.remove(artists);
        artists.removeAll();
        SwingUtilities.updateComponentTreeUI(frame);
//        frame.invalidate();
//        frame.validate();
//        frame.repaint();
        frame.setVisible(true);
        float[] floats = new float[3];

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
                    artists.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
                    centerPanel.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));


                } else {
                    artists.setBackground(Color.gray);
                    centerPanel.setBackground(Color.gray);

                }
            }

        }


        artists.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 300, 730));
        for (Artist artist : library.getArtists()) {
            CoverPanel musicCoverPanel = new CoverPanel(artist);
            musicCoverPanel.setPreferredSize(new Dimension(150, 220));
            musicCoverPanel.add(musicCoverPanel.getButton());
            musicCoverPanel.add(musicCoverPanel.getLabel1());
            musicCoverPanel.add(musicCoverPanel.getLabel2());
            artists.add(musicCoverPanel);
        }

        centerPanel.add(artists);
        centerPanelArray.add(artists);
        SwingUtilities.updateComponentTreeUI(frame);
        frame.setVisible(true);


    }
}
