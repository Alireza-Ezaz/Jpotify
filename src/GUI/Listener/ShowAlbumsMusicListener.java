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
import static GUI.Listener.ShowMusicListener.getA;
import static GUI.MainGraph.getPn;

/**
 * @author M.S.Haeri
 * @version final
 * This class
 */

public class ShowAlbumsMusicListener implements ActionListener {

    private JPanel centerPanel;
    private Library library;
    private ArrayList<Object> centerPanelArray;
    private JFrame frame;
    private JScrollPane sp;
    private JButton artwork;
    private JLabel artworkMusicName;
    private JLabel artworkArtisiName;
    private String albumName;


    public ShowAlbumsMusicListener(JPanel centerPanel, Library library, ArrayList<Object> centerPanelArray, JFrame frame, JScrollPane sp, JButton artwork, JLabel artworkMusicName, JLabel artworkArtisiName, String albumName) {
        this.centerPanel = centerPanel;
        this.library = library;
        this.centerPanelArray = centerPanelArray;
        this.frame = frame;
        this.sp =sp;
        this.artwork = artwork;
        this.artworkMusicName = artworkMusicName;
        this.artworkArtisiName = artworkArtisiName;
        this.albumName = albumName;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        centerPanel.removeAll();
        if (getA() == 0) {
//            System.out.println("meeeeeeeeeeeeeeeeeeeeee");
//            sp = new JScrollPane(centerPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//            sp.getViewport().setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 310, 730));
//            SwingUtilities.updateComponentTreeUI(frame);
//            setA(getA()+1);
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


                } else {
                    centerPanel.setBackground(Color.gray);

                }
            }

        }

        library.loadSongs();
        for (Album album : library.getAlbums()) {
            if (albumName.equals(album.getName())) {
                for (Song song : album.getSongs()) {
                    CoverPanel musicCoverPanel = new CoverPanel(song);
                    musicCoverPanel.setPreferredSize(new Dimension(150, 220));
                    musicCoverPanel.add(musicCoverPanel.getButton());
                    musicCoverPanel.getButton().addActionListener(new PlayMusicListener(song, library, artwork, artworkMusicName, artworkArtisiName, frame,album.getSongs()));
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
