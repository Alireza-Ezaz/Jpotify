package GUI.Listener;

import GUI.CustomPanel.CoverPanel;
import Logic.Entities.Library;
import Logic.Entities.PlayList;
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
 * This class shows all playlist`s list
 */


public class PlayListList implements ActionListener {
    private JPanel centerPanel;
    private Library library;
    private ArrayList<Object> centerPanelArray;
    private JFrame frame;
    private JScrollPane sp;
    private JButton artwork;
    private JLabel artworkMusicName;
    private JLabel artworkArtisiName;
    private JPanel plPanel;


    public PlayListList(JPanel centerPanel, Library library, ArrayList<Object> centerPanelArray, JFrame frame, JScrollPane sp, JButton artwork, JLabel artworkMusicName, JLabel artworkArtisiName, JPanel plPanel) {
        this.centerPanel = centerPanel;
        this.library = library;
        this.centerPanelArray = centerPanelArray;
        this.frame = frame;
        this.sp = sp;
        this.artwork = artwork;
        this.artworkMusicName = artworkMusicName;
        this.artworkArtisiName = artworkArtisiName;
        this.plPanel = plPanel;

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
            if (!(playList.getName().equals("Favorite Songs") || playList.getName().equals("Shared Playlist"))) {
                CoverPanel musicCoverPanel = new CoverPanel(playList);
                musicCoverPanel.setPreferredSize(new Dimension(150, 220));
                musicCoverPanel.getButton().addActionListener(new RemovePlayList(centerPanel, library, centerPanelArray, frame, sp, artwork, artworkMusicName, artworkArtisiName, playList.getName(), plPanel));
                musicCoverPanel.add(musicCoverPanel.getButton());
                musicCoverPanel.add(musicCoverPanel.getLabel1());
                musicCoverPanel.add(musicCoverPanel.getLabel2());
                centerPanel.add(musicCoverPanel);
            }
        }


        centerPanelArray.add(centerPanel);
        SwingUtilities.updateComponentTreeUI(frame);
        frame.add(sp, BorderLayout.CENTER);
        frame.setVisible(true);
        library.saveLibrarySongs();


    }
}

