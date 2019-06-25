package GUI.Listener;

import GUI.CustomPanel.CoverPanel;
import Logic.Entities.Library;
import Logic.Entities.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowMusicListener implements ActionListener {
    private JPanel centerPanel;
    private Library library;
    private ArrayList<Object> centerPanelArray;
    private JFrame frame;
    private JPanel musics;



    public ShowMusicListener(JPanel centerPanel, Library library, ArrayList<Object> centerPanelArray, JFrame frame, JPanel musics) {
        this.centerPanel = centerPanel;
        this.library = library;
        this.centerPanelArray = centerPanelArray;
        this.frame = frame;
        this.musics = musics;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        musics.removeAll();
        frame.setVisible(true);

        for (Object ob : centerPanelArray) {
            if (ob instanceof JCheckBox) {
                if (((JCheckBox) ob).isSelected()) {
                    System.out.println("hi");
                    float[] floats = new float[3];
                    Color.RGBtoHSB(24, 24, 24, floats);
                    musics.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));

                } else
                    musics.setBackground(Color.gray);
            }

        }


        musics.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 300, 630));
        for (Song song : library.getSongs()) {
            CoverPanel musicCoverPanel = new CoverPanel(song);
            musicCoverPanel.setPreferredSize(new Dimension(150, 220));
            musicCoverPanel.add(musicCoverPanel.getButton());
            musicCoverPanel.add(musicCoverPanel.getLabel1());
            musicCoverPanel.add(musicCoverPanel.getLabel2());
            musics.add(musicCoverPanel);
        }
        centerPanel.add(musics);
        centerPanelArray.add(musics);

        frame.setVisible(true);


    }
}
