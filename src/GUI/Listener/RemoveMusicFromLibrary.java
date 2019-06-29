package GUI.Listener;

import GUI.CustomPanel.CoverPanel;
import Logic.Entities.Library;
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

public class RemoveMusicFromLibrary implements ActionListener {
    private JPanel centerPanel;
    private Library library;
    private ArrayList<Object> centerPanelArray;
    private JFrame frame;
    private JScrollPane sp;
    private JButton artwork;
    private JLabel artworkMusicName;
    private JLabel artworkArtisiName;
    private Song removedMusic;


    public RemoveMusicFromLibrary(JPanel centerPanel, Library library, ArrayList<Object> centerPanelArray, JFrame frame, JScrollPane sp, JButton artwork, JLabel artworkMusicName, JLabel artworkArtisiName,Song removedMusic) {
        this.centerPanel = centerPanel;
        this.library = library;
        this.centerPanelArray = centerPanelArray;
        this.frame = frame;
        this.artwork = artwork;
        this.sp = sp;
        this.artworkMusicName = artworkMusicName;
        this.artworkArtisiName = artworkArtisiName;
        this.removedMusic = removedMusic;

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        library.removeSong(removedMusic.getDirectory());
        library.saveLibrarySongs();
        ////////////////////////
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
//                    centerPanel.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));


                } else {
                    centerPanel.setBackground(Color.gray);
//                    centerPanel.setBackground(Color.gray);

                }
            }

        }


        library.loadSongs();
        CoverPanel removePanel = new CoverPanel("Remove");
        removePanel.setPreferredSize(new Dimension(150, 220));
        removePanel.add(removePanel.getButton());
        removePanel.getButton().addActionListener(new AllMusic(centerPanel, library, centerPanelArray,frame,sp,artwork, artworkMusicName, artworkArtisiName));
        removePanel.add(removePanel.getLabel1());
        removePanel.add(removePanel.getLabel2());
        centerPanel.add(removePanel);
        for (Song song : library.getSongs()) {
            CoverPanel musicCoverPanel = new CoverPanel(song);
            musicCoverPanel.setPreferredSize(new Dimension(150, 220));
            musicCoverPanel.add(musicCoverPanel.getButton());
            musicCoverPanel.getButton().addActionListener(new PlayMusicListener(song, library, artwork, artworkMusicName, artworkArtisiName, frame, library.getSongs()));
            musicCoverPanel.add(musicCoverPanel.getLabel1());
            musicCoverPanel.add(musicCoverPanel.getLabel2());
            centerPanel.add(musicCoverPanel);
        }

        centerPanelArray.add(centerPanel);
        SwingUtilities.updateComponentTreeUI(frame);
        frame.add(sp, BorderLayout.CENTER);
        frame.setVisible(true);
        library.saveLibrarySongs();


    }
}
