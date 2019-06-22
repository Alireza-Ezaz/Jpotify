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
        listModelLibrary.addElement(" Music");
        listModelLibrary.addElement(" Album");
        listModelLibrary.addElement(" Artist");
        JList libraryList = new JList(listModelLibrary);
//        JScrollPane scrollPane = new JScrollPane();
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        DefaultListModel listModelplaylist = new DefaultListModel();//list of your all playlist
        JList playlistList = new JList(listModelplaylist);

        Dimension libListdim = new Dimension(150, 70);
        Dimension playlisListdim = new Dimension(150, 300);
        libraryList.setPreferredSize(libListdim);
        playlistList.setPreferredSize(playlisListdim);

        JLabel library = new JLabel("Library");
        Dimension LabelSize = new Dimension(150, 20);
        library.setPreferredSize(LabelSize);

        JLabel playlistListLabel = new JLabel("Playlist");
        playlistListLabel.setPreferredSize(LabelSize);

        leftPanel.add(library);
        leftPanel.add(libraryList);
        //leftPanel.add(scrollPane);
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

        JLabel musicName = new JLabel(" Music Name");
        JLabel artist = new JLabel(" Artist Name");
        musicName.setPreferredSize(LabelSize);
        artist.setPreferredSize(LabelSize);
        leftPanel.add(musicName);
        leftPanel.add(artist);


        leftPanel.setBackground(Color.LIGHT_GRAY);
        myFrame.add(leftPanel, BorderLayout.WEST);


        //Center panel
        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.setBackground(Color.BLACK);
        myFrame.add(centerPanel, BorderLayout.CENTER);

        //East panel
        JPanel eastPanel = new JPanel(new FlowLayout());
        eastPanel.setPreferredSize(leftPanelDim);
        eastPanel.setBackground(Color.GREEN);
        JLabel fActivity = new JLabel(" Friend Activity");
        fActivity.setPreferredSize(LabelSize);
        fActivity.setForeground(Color.WHITE);
        eastPanel.add(fActivity);
        myFrame.add(eastPanel, BorderLayout.EAST);

        /////////South panel
        JPanel southPanel = new JPanel(new BorderLayout());
        JPanel playerPanel = new JPanel(new FlowLayout());
        Dimension playerPanelSize = new Dimension(150, 100);

        JButton play = new JButton();
        Dimension playerButtonSize = new Dimension(44, 44);
        play.setPreferredSize(playerButtonSize);
        play.setBackground(Color.lightGray);
        try {
            Image img = ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\pause.png"));//didnt work?!
            play.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println("Image not found");
        }

        JButton pNext = new JButton();
        pNext.setPreferredSize(playerButtonSize);
        pNext.setBackground(Color.lightGray);
        try {
            Image img = ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\next.png"));//didnt work?!
            pNext.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println("Image not found");
        }

        JButton privios = new JButton();
        privios.setPreferredSize(playerButtonSize);
        privios.setBackground(Color.lightGray);
        try {
            Image img = ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\pr.png"));//didnt work?!
            privios.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println("Image not found");
        }

        playerPanel.add(privios);
        playerPanel.add(play);
        playerPanel.add(pNext);
        playerPanel.setBackground(Color.lightGray);
        southPanel.add(playerPanel, BorderLayout.WEST);


        Dimension southPanelDim = new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 100);
        southPanel.setBackground(Color.lightGray);
        myFrame.add(southPanel, BorderLayout.SOUTH);


        // north
        JPanel northPanel = new JPanel(new FlowLayout());
        Dimension northPanelDim = new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 30);
        northPanel.setPreferredSize(northPanelDim);
        JLabel add_music_to_libraryLabel = new JLabel("Add Music To Library");
        Dimension addMtoL = new Dimension(150, 30);
        add_music_to_libraryLabel.setPreferredSize(addMtoL);
        northPanel.add(add_music_to_libraryLabel);
        JButton add = new JButton(" + ");
        Dimension addLabel = new Dimension(60, 30);
        add.setPreferredSize(addLabel);
        add.setBackground(Color.lightGray);
        northPanel.add(add);
        northPanel.setBackground(Color.lightGray);
        myFrame.add(northPanel, BorderLayout.NORTH);
        JButton signIn = new JButton("Sign in");
        signIn.setPreferredSize(addMtoL);
        signIn.setBackground(Color.lightGray);
        northPanel.add(signIn);
        JLabel profileIcon = new JLabel();
        try {
            BufferedImage img = ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\profile.jpg"));
            BufferedImage fimg = new BufferedImage(30, 30, img.getType());
            profileIcon.setIcon(new ImageIcon(fimg));

        } catch (Exception ex) {
            System.out.println("Image not found");
        }
        profileIcon.setPreferredSize(addLabel);
        northPanel.add(profileIcon);
        JCheckBox darkMode = new JCheckBox("Dark Mode");
        darkMode.setPreferredSize(addMtoL);
        darkMode.setBackground(Color.lightGray);
        northPanel.add(darkMode);


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
