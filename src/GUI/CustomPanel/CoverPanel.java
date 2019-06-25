package GUI.CustomPanel;

import Logic.Entities.Album;
import Logic.Entities.PlayList;
import Logic.Entities.Song;

import javax.swing.*;
import java.awt.*;

public class CoverPanel extends JPanel {
    private String name;
    private ImageIcon artwork;
    private String artistName;
    private JButton button;
    private JLabel label1;
    private JLabel label2;

    public JButton getButton() {
        return button;
    }

    public JLabel getLabel1() {
        return label1;
    }

    public JLabel getLabel2() {
        return label2;
    }

    public CoverPanel(Song song){
        super(new FlowLayout());
        this.name = song.getName();
        this.artwork = song.getArtWork();
        this.artistName = song.getArtistName();
        button = new JButton();
        button.setIcon(artwork);
        label1 = new JLabel(name);
        label2 = new JLabel(artistName);
        button.setBackground(Color.lightGray);
        label1.setBackground(Color.lightGray);
        label2.setBackground(Color.lightGray);
        label1.setFont(new Font("GothamBold",Font.ROMAN_BASELINE,12));
        label2.setFont(new Font("GothamBold",Font.ITALIC,12));
        button.setPreferredSize(new Dimension(150,150));
        label1.setPreferredSize(new Dimension(150,20));
        label2.setPreferredSize(new Dimension(150,30));

    }
    public CoverPanel(Album album){
        super(new FlowLayout());
        this.name = album.getName();
        this.artwork = album.getSongs().get(0).getArtWork();
        this.artistName = album.getSongs().get(0).getArtistName();
        button = new JButton();
        button.setIcon(artwork);
        label1 = new JLabel(name);
        label2 = new JLabel(artistName);
        button.setBackground(Color.lightGray);
        label1.setBackground(Color.lightGray);
        label2.setBackground(Color.lightGray);
        label1.setFont(new Font("GothamBold",Font.ROMAN_BASELINE,12));
        label2.setFont(new Font("GothamBold",Font.ROMAN_BASELINE,12));
        button.setPreferredSize(new Dimension(150,150));
        label1.setPreferredSize(new Dimension(150,30));
        label2.setPreferredSize(new Dimension(150,30));

    }
    public CoverPanel(PlayList playList){
        super(new FlowLayout());
        this.name = playList.getName();
        this.artwork = playList.getImageIcon();
        button = new JButton();
        button.setIcon(artwork);
        label1 = new JLabel("Playlist");
        label2 = new JLabel(name);
        button.setBackground(Color.lightGray);
        label1.setBackground(Color.lightGray);
        label2.setBackground(Color.lightGray);
        label1.setFont(new Font("GothamBold",Font.ROMAN_BASELINE,12));
        label2.setFont(new Font("GothamBold",Font.ROMAN_BASELINE,12));
        button.setPreferredSize(new Dimension(150,150));
        label1.setPreferredSize(new Dimension(150,30));
        label2.setPreferredSize(new Dimension(150,30));
    }

}