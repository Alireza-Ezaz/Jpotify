import javax.imageio.ImageIO;

import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainGraph {
    JFrame myFrame;

    public MainGraph() {
        myFrame = new JFrame("Jpotify");
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myFrame.setLayout(new BorderLayout());

        //left panel
        DefaultListModel listModelLibrary = new DefaultListModel();
        listModelLibrary.addElement("Music");
        listModelLibrary.addElement("Album");
        listModelLibrary.addElement("Artist");
        JList libraryList = new JList(listModelLibrary);

        DefaultListModel listModelplaylist= new DefaultListModel();//list of your all playlist
        JList playlistList = new JList(listModelplaylist);

        JPanel leftPanel = new JPanel(new GridLayout(5, 1));
        leftPanel.add(new JLabel("Library"));
        leftPanel.add(libraryList);
        leftPanel.add(new JLabel("Playlist    "));
        leftPanel.add(playlistList);
        JButton artworkP = new JButton();
        try {
//            Image img = ImageIO.read(getClass().getResource("artwork.jpg"));//didnt work?!
//            artworkP.setIcon(new ImageIcon(img));
            BufferedImage img  =ImageIO.read(getClass().getResource("artwork.jpg"));
            BufferedImage fimg = new BufferedImage(150, 150,img.getType());
            artworkP.setIcon(new ImageIcon(fimg));

        } catch (Exception ex) {
            System.out.println("Image not found");
        }
        leftPanel.add(artworkP);
        leftPanel.setBackground(Color.LIGHT_GRAY);
        myFrame.add(leftPanel, BorderLayout.WEST);

        //Center panel
        JPanel centerPanel = new JPanel(new GridLayout(1, 1));
        centerPanel.setBackground(Color.BLACK);
        myFrame.add(centerPanel, BorderLayout.CENTER);

        //East panel
        JPanel eastPanel = new JPanel(new GridLayout(2, 1));
        eastPanel.setBackground(Color.GREEN);
        eastPanel.add(new JLabel("Friend Activity"));
        myFrame.add(eastPanel, BorderLayout.EAST);

        //South panel
        JPanel southPanel = new JPanel(new BorderLayout());
          // west southpanel
        JPanel musicinfo = new JPanel(new GridLayout(2,1));
        JLabel musicName =new JLabel(" Music ");
        JLabel artist = new JLabel("  Artist  ");
        musicinfo.add(musicName);
        musicinfo.add(artist);
        musicinfo.setBackground(Color.lightGray);
        southPanel.add(musicinfo, BorderLayout.WEST);
          //east sothpanel
        JSlider voloum = new JSlider();
        southPanel.add(voloum, BorderLayout.EAST);
          //south southpanel
        JSlider musicSlider = new JSlider();
        southPanel.add(musicSlider, BorderLayout.SOUTH);
          // Center soutpanel
        JPanel player =new JPanel(new GridLayout(1,3));
        JButton play= new JButton();
        play.setBackground(Color.lightGray);
        try {
            Image img = ImageIO.read(getClass().getResource("pause.png"));//didnt work?!
            play.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println("Image not found");
        }

        JButton next = new JButton();
        next.setBackground(Color.lightGray);
        try {
            Image img = ImageIO.read(getClass().getResource("next.png"));//didnt work?!
            next.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println("Image not found");
        }

        JButton privios = new JButton();
        privios.setBackground(Color.lightGray);
        try {
            Image img = ImageIO.read(getClass().getResource("pr.png"));//didnt work?!
            privios.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println("Image not found");
        }

        player.add(privios);
        player.add(play);
        player.add(next);
        southPanel.add(player, BorderLayout.CENTER);
        southPanel.setBackground(Color.lightGray);
        myFrame.add(southPanel, BorderLayout.SOUTH);


        // north
        JPanel northPanel = new JPanel(new GridLayout(1, 2));
        northPanel.add(new JLabel("Add Music To Library"));
        JButton add = new JButton(" + ");
        add.setBackground(Color.lightGray);
        northPanel.add(add);
        northPanel.setBackground(Color.lightGray);
        myFrame.add(northPanel, BorderLayout.NORTH);





        ///////////
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        myFrame.setMaximumSize(dimension);
        myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);

    }

    public static void main(String[] args) {
        MainGraph mainGraph = new MainGraph();
    }
}
