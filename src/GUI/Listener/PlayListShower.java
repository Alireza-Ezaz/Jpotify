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
 * This class shows  all playlists with remove panel
 */

public class PlayListShower implements ActionListener {
    private JPanel centerPanel;
    private Library library;
    private ArrayList<Object> centerPanelArray;
    private JFrame frame;
    private JScrollPane sp;
    private JButton artwork;
    private JLabel artworkMusicName;
    private JLabel artworkArtisiName;
    private JPanel plPanel;


    public PlayListShower(JPanel centerPanel, Library library, ArrayList<Object> centerPanelArray, JFrame frame, JScrollPane sp, JButton artwork, JLabel artworkMusicName, JLabel artworkArtisiName, JPanel plPanel) {
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


        CoverPanel removePanel = new CoverPanel("Remove");
        removePanel.setPreferredSize(new Dimension(150, 220));
        removePanel.add(removePanel.getButton());
        removePanel.getButton().addActionListener(new PlayListList(centerPanel, library, centerPanelArray, frame, sp, artwork, artworkMusicName, artworkArtisiName,plPanel));
        removePanel.add(removePanel.getLabel1());
        removePanel.add(removePanel.getLabel2());
        centerPanel.add(removePanel);

        for (PlayList playList : library.getPlayLists()) {
            CoverPanel musicCoverPanel = new CoverPanel(playList);
            musicCoverPanel.setPreferredSize(new Dimension(150, 220));
            musicCoverPanel.getButton().addActionListener(new ShowPlayListMusic(centerPanel, library, centerPanelArray, frame, sp, artwork, artworkMusicName, artworkArtisiName, playList.getName(),plPanel));
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
