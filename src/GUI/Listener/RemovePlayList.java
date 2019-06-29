package GUI.Listener;

import GUI.CustomPanel.CoverPanel;
import Logic.Entities.Library;
import Logic.Entities.PlayList;

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

public class RemovePlayList implements ActionListener {
    private String playListName;
    private Library library;
    private JPanel panel;
    private JPanel centerPanel;
    private ArrayList<Object> centerPanelArray;
    private JFrame frame;
    private JScrollPane sp;
    private JButton artwork;
    private JLabel artworkMusicName;
    private JLabel artworkArtisiName;

    public RemovePlayList(JPanel centerPanel, Library library, ArrayList<Object> centerPanelArray, JFrame frame, JScrollPane sp, JButton artwork, JLabel artworkMusicName, JLabel artworkArtisiName, String playListName, JPanel panel) {
        this.playListName = playListName;
        this.library = library;
        this.panel = panel;
        this.centerPanel = centerPanel;
        this.centerPanelArray = centerPanelArray;
        this.frame = frame;
        this.sp = sp;
        this.artwork =artwork;
        this.artworkMusicName = artworkMusicName;
        this.artworkArtisiName = artworkArtisiName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        float[] floats = new float[3];
        library.removePlaylist(playListName);
        panel.removeAll();
        for (PlayList playList : library.getPlayLists()) {
            JButton button = new JButton(playList.getName());
            button.addActionListener(new ShowPlayListMusic(centerPanel, library, centerPanelArray, frame, sp, artwork, artworkMusicName, artworkArtisiName, playList.getName(),panel));
            button.setPreferredSize(new Dimension(150, 30));
            panel.add(button);
            Color.RGBtoHSB(40, 40, 40, floats);
            button.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
            button.setForeground(Color.lightGray);

        }
        library.saveLibrarySongs();
        centerPanel.removeAll();
        if (getA() == 0) {
//            System.out.println("meeeeeeeeeeeeeeeeeeeeee");
//            sp = new JScrollPane(centerPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//            sp.getViewport().setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 310, 730));
//            SwingUtilities.updateComponentTreeUI(frame);
//            setA(getA()+1);
            frame.remove(getPn());
        }
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


        CoverPanel removePanel = new CoverPanel("Remove");
        removePanel.setPreferredSize(new Dimension(150, 220));
        removePanel.add(removePanel.getButton());
        removePanel.getButton().addActionListener(new PlayListList(centerPanel, library, centerPanelArray, frame, sp, artwork, artworkMusicName, artworkArtisiName,panel));
        removePanel.add(removePanel.getLabel1());
        removePanel.add(removePanel.getLabel2());
        centerPanel.add(removePanel);

        for (PlayList playList : library.getPlayLists()) {
            CoverPanel musicCoverPanel = new CoverPanel(playList);
            musicCoverPanel.setPreferredSize(new Dimension(150, 220));
            musicCoverPanel.getButton().addActionListener(new ShowPlayListMusic(centerPanel, library, centerPanelArray, frame, sp, artwork, artworkMusicName, artworkArtisiName, playList.getName(),panel));
            musicCoverPanel.add(musicCoverPanel.getButton());
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
