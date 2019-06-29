package GUI.CustomPanel;

import Logic.Entities.Album;
import Logic.Entities.Artist;
import Logic.Entities.PlayList;
import Logic.Entities.Song;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
/**
 * @author M.S.Haeri
 * @version final
 * This class music, album, playlist, add, remove or artist sample
 */

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


    public CoverPanel(Song song) {
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
        label1.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 12));
        label2.setFont(new Font("GothamBold", Font.ITALIC, 12));
        button.setPreferredSize(new Dimension(150, 150));
        label1.setPreferredSize(new Dimension(150, 20));
        label2.setPreferredSize(new Dimension(150, 30));
        label1.setForeground(Color.lightGray);
        label2.setForeground(Color.lightGray);
        float[] floats = new float[3];
        Color.RGBtoHSB(40, 40, 40, floats);
        setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));


    }

    public CoverPanel(Album album) {
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
        label1.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 12));
        label2.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 12));
        label1.setForeground(Color.lightGray);
        label2.setForeground(Color.lightGray);
        button.setPreferredSize(new Dimension(150, 150));
        label1.setPreferredSize(new Dimension(150, 30));
        label2.setPreferredSize(new Dimension(150, 30));
        float[] floats = new float[3];
        Color.RGBtoHSB(40, 40, 40, floats);
        setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));


    }

    public CoverPanel(Artist artist) {
        super(new FlowLayout());
        this.name = artist.getName();
        this.artwork = artist.getSongs().get(0).getArtWork();
        this.artistName = artist.getSongs().get(0).getArtistName();
        button = new JButton();
        button.setIcon(artwork);
        label1 = new JLabel("Artist :");
        label2 = new JLabel(artistName);
        button.setBackground(Color.lightGray);
        label1.setBackground(Color.lightGray);
        label2.setBackground(Color.lightGray);
        label1.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 12));
        label2.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 12));
        label1.setForeground(Color.lightGray);
        label2.setForeground(Color.lightGray);
        button.setPreferredSize(new Dimension(150, 150));
        label1.setPreferredSize(new Dimension(150, 30));
        label2.setPreferredSize(new Dimension(150, 30));
        float[] floats = new float[3];
        Color.RGBtoHSB(40, 40, 40, floats);
        setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));


    }

    public CoverPanel(PlayList playList) {
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
        label1.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 12));
        label2.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 12));
        label1.setForeground(Color.lightGray);
        label2.setForeground(Color.lightGray);
        button.setPreferredSize(new Dimension(150, 150));
        label1.setPreferredSize(new Dimension(150, 30));
        label2.setPreferredSize(new Dimension(150, 30));
        float[] floats = new float[3];
        Color.RGBtoHSB(40, 40, 40, floats);
        setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));

    }
    public CoverPanel() {
        super(new FlowLayout());
        this.name = "New Music";
        button = new JButton();
        button.setFont(new Font("GothamBold", Font.ITALIC, 120));
        button.setIcon(artwork);
        label1 = new JLabel("Add");
        label2 = new JLabel(name);
        button.setBackground(Color.lightGray);
        label1.setBackground(Color.lightGray);
        label2.setBackground(Color.lightGray);
        try {
            button.setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\plus.png")).getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        label1.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 12));
        label2.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 12));
        label1.setForeground(Color.lightGray);
        label2.setForeground(Color.lightGray);
        button.setPreferredSize(new Dimension(150, 150));
        label1.setPreferredSize(new Dimension(150, 30));
        label2.setPreferredSize(new Dimension(150, 30));
        float[] floats = new float[3];
        Color.RGBtoHSB(40, 40, 40, floats);
        setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));

    }
    public CoverPanel(String remove) {
        super(new FlowLayout());
        this.name = "";
        button = new JButton();
        button.setFont(new Font("GothamBold", Font.ITALIC, 120));
        button.setIcon(artwork);
        label1 = new JLabel("Remove");
        label2 = new JLabel(name);
        button.setBackground(Color.lightGray);
        label1.setBackground(Color.lightGray);
        label2.setBackground(Color.lightGray);
        try {
            button.setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\remove.png")).getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        label1.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 12));
        label2.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 12));
        label1.setForeground(Color.lightGray);
        label2.setForeground(Color.lightGray);
        button.setPreferredSize(new Dimension(150, 150));
        label1.setPreferredSize(new Dimension(150, 30));
        label2.setPreferredSize(new Dimension(150, 30));
        float[] floats = new float[3];
        Color.RGBtoHSB(40, 40, 40, floats);
        setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));

    }

}
