package GUI.Listener;

import GUI.CustomPanel.CoverPanel;
import Logic.Entities.Library;
import Logic.Entities.PlayList;
import Logic.Entities.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static GUI.Listener.ShowMusicListener.getA;
import static GUI.MainGraph.getPn;


public class PlayListMusicList implements ActionListener {
    private JPanel centerPanel;
    private Library library;
    private ArrayList<Object> centerPanelArray;
    private JFrame frame;
    private JScrollPane sp;
    private JButton artwork;
    private JLabel artworkMusicName;
    private JLabel artworkArtisiName;
    private String playListName;


    public PlayListMusicList(JPanel centerPanel, Library library, ArrayList<Object> centerPanelArray, JFrame frame, JScrollPane sp, JButton artwork, JLabel artworkMusicName, JLabel artworkArtisiName, String playListName) {
        this.centerPanel = centerPanel;
        this.library = library;
        this.centerPanelArray = centerPanelArray;
        this.frame = frame;
        this.sp = sp;
        this.artwork = artwork;
        this.artworkMusicName = artworkMusicName;
        this.artworkArtisiName = artworkArtisiName;
        this.playListName = playListName;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        centerPanel.removeAll();
        if (getA() == 0) {
            frame.remove(getPn());
        }
        float[] floats = new float[3];
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
                    centerPanel.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));


                } else {
                    centerPanel.setBackground(Color.gray);

                }
            }

        }


        for (PlayList playList : library.getPlayLists()) {
            if (playListName.equals(playList.getName())) {
                for (Song song : playList.getSongs()) {
                    CoverPanel musicCoverPanel = new CoverPanel(song);
                    musicCoverPanel.setPreferredSize(new Dimension(150, 220));
                    musicCoverPanel.add(musicCoverPanel.getButton());
                    musicCoverPanel.getButton().addActionListener(new RemovePlayListMusic(centerPanel,library,centerPanelArray,frame,sp,artwork,artworkMusicName,artworkArtisiName,playListName,song.getName()));
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

