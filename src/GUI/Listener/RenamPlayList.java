package GUI.Listener;

import Logic.Entities.Library;
import Logic.Entities.PlayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RenamPlayList implements ActionListener {
    private PlayList playList;
    private String name;
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

    public RenamPlayList(JPanel centerPanel, Library library, ArrayList<Object> centerPanelArray, JFrame frame, JScrollPane sp, JButton artwork, JLabel artworkMusicName, JLabel artworkArtisiName, String playListName, JPanel panel,PlayList playList) {
        this.playList = playList;
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
        name = JOptionPane.showInputDialog("Enter new playlist neme:");
        if (!playList.getName().equals( "Favorite Songs") && !playList.getName().equals("Shared Playlist"))
            playList.setName(name);
        library.saveLibrarySongs();
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
        SwingUtilities.updateComponentTreeUI(frame);
    }
}
