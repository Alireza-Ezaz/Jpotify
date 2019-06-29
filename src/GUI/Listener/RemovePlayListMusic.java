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

public class RemovePlayListMusic implements ActionListener {
    private String playListName;
    private String musicName;
    private Library library;
    private JPanel centerPanel;
    private ArrayList<Object> centerPanelArray;
    private JFrame frame;
    private JScrollPane sp;
    private JButton artwork;
    private JLabel artworkMusicName;
    private JLabel artworkArtisiName;

    /**
     * @author M.S.Haeri
     * @version final
     * This class removes music from playlist
     */


    public RemovePlayListMusic(JPanel centerPanel, Library library, ArrayList<Object> centerPanelArray, JFrame frame, JScrollPane sp, JButton artwork, JLabel artworkMusicName, JLabel artworkArtisiName, String playListName, String musicName) {
        this.playListName = playListName;
        this.library = library;
        this.centerPanel = centerPanel;
        this.centerPanelArray = centerPanelArray;
        this.frame = frame;
        this.sp = sp;
        this.artwork =artwork;
        this.artworkMusicName = artworkMusicName;
        this.artworkArtisiName = artworkArtisiName;
        this.musicName =musicName;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (PlayList playList : library.getPlayLists()) {
            if (playListName.equals(playList.getName())) {
                for (Song song : playList.getSongs()) {
                    if (song.getName().equals(musicName)){
                        playList.removeSong(song);
                        break;
                    }
                }
                break;
            }
        }
        library.saveLibrarySongs();
        centerPanel.removeAll();
        if (getA() == 0) {
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
        for (PlayList playList : library.getPlayLists()) {
            if (playListName.equals(playList.getName())) {
                CoverPanel musicCoverPanel = new CoverPanel(playList);
                musicCoverPanel.setPreferredSize(new Dimension(150, 220));
                musicCoverPanel.add(musicCoverPanel.getButton());
                musicCoverPanel.add(musicCoverPanel.getLabel1());
                musicCoverPanel.add(musicCoverPanel.getLabel2());
                centerPanel.add(musicCoverPanel);
            }
        }

        CoverPanel addMusic = new CoverPanel();
        addMusic.setPreferredSize(new Dimension(150, 220));
        addMusic.add(addMusic.getButton());
        addMusic.getButton().addActionListener(new MusicList(centerPanel, library, centerPanelArray, frame, sp, artwork, artworkMusicName, artworkArtisiName, playListName));
        addMusic.add(addMusic.getLabel1());
        addMusic.add(addMusic.getLabel2());
        centerPanel.add(addMusic);


        CoverPanel removePanel = new CoverPanel("Remove");
        removePanel.setPreferredSize(new Dimension(150, 220));
        removePanel.add(removePanel.getButton());
        removePanel.getButton().addActionListener(new PlayListMusicList(centerPanel, library, centerPanelArray, frame, sp, artwork, artworkMusicName, artworkArtisiName, playListName));
        removePanel.add(removePanel.getLabel1());
        removePanel.add(removePanel.getLabel2());
        centerPanel.add(removePanel);

        for (PlayList playList : library.getPlayLists()) {
            if (playListName.equals(playList.getName())) {
                for (Song song : playList.getSongs()) {
                    CoverPanel musicCoverPanel = new CoverPanel(song);
                    musicCoverPanel.setPreferredSize(new Dimension(150, 220));
                    musicCoverPanel.add(musicCoverPanel.getButton());
                    musicCoverPanel.getButton().addActionListener(new PlayMusicListener(song, library, artwork, artworkMusicName, artworkArtisiName, frame, playList.getSongs()));
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
