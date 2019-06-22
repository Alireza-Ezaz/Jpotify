package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainGraph {
    JFrame myFrame;

    public MainGraph() {
        myFrame = new JFrame("Jpotify");
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myFrame.setLayout(new BorderLayout());

        //left panel
        JPanel leftPanel = new JPanel(new FlowLayout());
        Dimension leftPanelDim = new Dimension(150, Toolkit.getDefaultToolkit().getScreenSize().height);
        leftPanel.setPreferredSize(leftPanelDim);

        DefaultListModel listModelLibrary = new DefaultListModel();
        listModelLibrary.addElement("Music");
        listModelLibrary.addElement("Album");
        listModelLibrary.addElement("Artist");
        JList libraryList = new JList(listModelLibrary);
//        JScrollPane scrollPane = new JScrollPane();
//        scrollPane.setViewportView(libraryList);
//        libraryList.setLayoutOrientation(JList.VERTICAL);


        DefaultListModel listModelplaylist = new DefaultListModel();//list of your all playlist
        JList playlistList = new JList(listModelplaylist);

        Dimension libListdim = new Dimension(150, 70);
        Dimension playlisListdim = new Dimension(150, 150);
        libraryList.setPreferredSize(libListdim);
        playlistList.setPreferredSize(playlisListdim);

        JLabel library = new JLabel("Library");
        Dimension LabelSize = new Dimension(150, 20);
        library.setPreferredSize(LabelSize);

        JLabel playlistListLabel = new JLabel("Playlist");
        playlistListLabel.setPreferredSize(LabelSize);

        leftPanel.add(library);
        leftPanel.add(libraryList);
        //leftPanel.add(spane);
        leftPanel.add(playlistListLabel);
        leftPanel.add(playlistList);

        JButton artworkP = new JButton();
        artworkP.setBackground(Color.lightGray);
        try {
            BufferedImage img = ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\artwork.jpg"));
            BufferedImage fimg = new BufferedImage(150, 150, img.getType());
            artworkP.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println("Image not found");
        }
        Dimension artworkMaxDim = new Dimension(150, 150);
        artworkP.setPreferredSize(artworkMaxDim);
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
        JPanel musicinfo = new JPanel(new GridLayout(2, 1));
        JLabel musicName = new JLabel("  Music  ");
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
        JPanel player = new JPanel(new GridLayout(1, 3));
        JButton play = new JButton();
        play.setBackground(Color.lightGray);
        try {
            Image img = ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\pause.png"));//didnt work?!
            play.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println("Image not found");
        }

        JButton next = new JButton();
        next.setBackground(Color.lightGray);
        try {
            Image img = ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\next.png"));//didnt work?!
            next.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println("Image not found");
        }

        JButton privios = new JButton();
        privios.setBackground(Color.lightGray);
        try {
            Image img = ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\pr.png"));//didnt work?!
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
        Dimension max_dimension = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension min_dimension = new Dimension(1500, 1200);
        myFrame.setMinimumSize(min_dimension);
        myFrame.setMaximumSize(max_dimension);
        myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);

    }

    public static void main(String[] args) {
        MainGraph mainGraph = new MainGraph();
    }
}
