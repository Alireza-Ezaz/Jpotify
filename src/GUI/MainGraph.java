package GUI;

import GUI.Listener.darkModeListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class MainGraph {
    private JFrame myFrame;
    private ArrayList<Object> centerPanelArrayList;
    private ArrayList<Object> eastPanelArrayList;
    private ArrayList<Object> northPanelArrayList;
    private ArrayList<Object> southPanelArrayList;
    private ArrayList<Object> leftPanelArrayList;

    private ImageIcon imageCreator(String path) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            BufferedImage fimg = new BufferedImage(30, 30, img.getType());
            return (new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println("Image not found");
            return null;
        }
    }

    private Dimension dimensionCreator(int width, int height) {
        Dimension dimension = new Dimension(width, height);
        return dimension;
    }

    private void centerPanel() {
        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanelArrayList.add(centerPanel);
        centerPanel.setBackground(Color.GRAY);

        JPanel recentlyPlayed = new JPanel();
        centerPanelArrayList.add(recentlyPlayed);

        recentlyPlayed.setPreferredSize(dimensionCreator(Toolkit.getDefaultToolkit().getScreenSize().width, 150));
        recentlyPlayed.setBackground(Color.lightGray);

        myFrame.add(centerPanel, BorderLayout.CENTER);
    }

    private void eastPanel() {
        JPanel eastPanel = new JPanel(new FlowLayout());
        eastPanelArrayList.add(eastPanel);
        eastPanel.setPreferredSize(dimensionCreator(150, Toolkit.getDefaultToolkit().getScreenSize().height));
        eastPanel.setBackground(Color.lightGray);

        JLabel fActivity = new JLabel(" Friend Activity");
        eastPanelArrayList.add(fActivity);
        fActivity.setPreferredSize(dimensionCreator(150, 20));
        eastPanel.add(fActivity);
        myFrame.add(eastPanel, BorderLayout.EAST);
    }

    private void northPanel() {
        JPanel northPanel = new JPanel(new FlowLayout());
        northPanelArrayList.add(northPanel);

        northPanel.setPreferredSize(dimensionCreator(Toolkit.getDefaultToolkit().getScreenSize().width, 30));
        JLabel add_music_to_libraryLabel = new JLabel("Add Music To Library");
        northPanelArrayList.add(add_music_to_libraryLabel);

        add_music_to_libraryLabel.setPreferredSize(dimensionCreator(150, 30));
        northPanel.add(add_music_to_libraryLabel);
        JButton add = new JButton(" + ");
        northPanelArrayList.add(add);

        add.setPreferredSize(dimensionCreator(60, 30));
        add.setBackground(Color.lightGray);
        northPanel.add(add);
        northPanel.setBackground(Color.lightGray);
        myFrame.add(northPanel, BorderLayout.NORTH);
        JButton signIn = new JButton("Sign in");
        northPanelArrayList.add(signIn);

        signIn.setPreferredSize(dimensionCreator(150, 30));
        signIn.setBackground(Color.lightGray);
        northPanel.add(signIn);
        JLabel profileIcon = new JLabel();
        northPanelArrayList.add(profileIcon);

        profileIcon.setIcon(imageCreator("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\profile.jpg"));
        profileIcon.setPreferredSize(dimensionCreator(60, 30));
        northPanel.add(profileIcon);
        JCheckBox darkMode = new JCheckBox("Dark Mode", false);
        northPanelArrayList.add(darkMode);
        darkMode.addActionListener(new darkModeListener(myFrame, centerPanelArrayList, eastPanelArrayList, northPanelArrayList, southPanelArrayList, leftPanelArrayList));
        darkMode.setPreferredSize(dimensionCreator(150, 30));
        darkMode.setBackground(Color.lightGray);
        northPanel.add(darkMode);
    }

    public void southPanel() {
        //Player Panel in southpanel
        JPanel southPanel = new JPanel(new BorderLayout());
        JPanel playerPanel = new JPanel(new FlowLayout());
        southPanelArrayList.add(southPanel);
        southPanelArrayList.add(playerPanel);

        JButton play = new JButton();
        southPanelArrayList.add(play);
        play.setPreferredSize(dimensionCreator(44, 44));
        play.setBackground(Color.lightGray);
        play.setIcon(imageCreator("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\pause.png"));
        JButton pNext = new JButton();
        southPanelArrayList.add(pNext);
        pNext.setPreferredSize(dimensionCreator(44, 44));
        pNext.setBackground(Color.lightGray);
        pNext.setIcon(imageCreator("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\next.png"));
        JButton privios = new JButton();
        southPanelArrayList.add(privios);
        privios.setPreferredSize(dimensionCreator(44, 44));
        privios.setBackground(Color.lightGray);
        privios.setIcon(imageCreator("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\pr.png"));
        playerPanel.add(privios);
        playerPanel.add(play);
        playerPanel.add(pNext);
        playerPanel.setBackground(Color.lightGray);
        southPanel.add(playerPanel, BorderLayout.WEST);

        //Song slider
        JSlider musicSlider = new JSlider();
        southPanelArrayList.add(musicSlider);
        musicSlider.setBackground(Color.lightGray);
        southPanel.add(musicSlider, BorderLayout.CENTER);
        JSlider voloum = new JSlider();
        southPanelArrayList.add(voloum);
        voloum.setBackground(Color.lightGray);
        voloum.setPreferredSize(dimensionCreator(150, 30));
        southPanel.add(voloum, BorderLayout.EAST);
        southPanel.setBackground(Color.lightGray);
        myFrame.add(southPanel, BorderLayout.SOUTH);
    }

    private void leftPanel() {
        JPanel leftPanel = new JPanel(new FlowLayout());
        leftPanelArrayList.add(leftPanel);
        leftPanel.setPreferredSize(dimensionCreator(150, Toolkit.getDefaultToolkit().getScreenSize().height));

        DefaultListModel listModelLibrary = new DefaultListModel();
        listModelLibrary.addElement(" Musics");
        listModelLibrary.addElement(" Albums");
        listModelLibrary.addElement(" Artists");
        JList libraryList = new JList(listModelLibrary);
        leftPanelArrayList.add(libraryList);
//        JScrollPane scrollPane = new JScrollPane();
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        DefaultListModel listModelplaylist = new DefaultListModel();//list of your all playlist
        JList playlistList = new JList(listModelplaylist);
        leftPanelArrayList.add(playlistList);


        libraryList.setPreferredSize(dimensionCreator(150, 70));
        playlistList.setPreferredSize(dimensionCreator(150, 300));

        JLabel library = new JLabel("Library");
        leftPanelArrayList.add(library);
        library.setPreferredSize(dimensionCreator(150, 20));

        JLabel playlistListLabel = new JLabel("Playlists");
        leftPanelArrayList.add(playlistListLabel);
        playlistListLabel.setPreferredSize(dimensionCreator(150, 20));

        leftPanel.add(library);
        leftPanel.add(libraryList);
        //leftPanel.add(scrollPane);
        leftPanel.add(playlistListLabel);
        leftPanel.add(playlistList);

        JButton artworkP = new JButton();
        leftPanelArrayList.add(artworkP);
        artworkP.setBackground(Color.lightGray);
        artworkP.setIcon(imageCreator("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\artwork.jpg"));
        artworkP.setPreferredSize(dimensionCreator(150, 150));
        leftPanel.add(artworkP);

        JLabel musicName = new JLabel(" Music Name");
        JLabel artist = new JLabel(" Artist Name");
        leftPanelArrayList.add(musicName);
        leftPanelArrayList.add(artist);
        musicName.setPreferredSize(dimensionCreator(150, 20));
        artist.setPreferredSize(dimensionCreator(150, 20));
        leftPanel.add(musicName);
        leftPanel.add(artist);
        JCheckBox favorite = new JCheckBox("Favorite");
        leftPanelArrayList.add(favorite);
        favorite.setPreferredSize(dimensionCreator(150, 30));
        favorite.setBackground(Color.lightGray);
        leftPanel.add(favorite);


        leftPanel.setBackground(Color.LIGHT_GRAY);
        myFrame.add(leftPanel, BorderLayout.WEST);

    }


    public MainGraph() {
        myFrame = new JFrame("Jpotify");
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myFrame.setLayout(new BorderLayout());
        centerPanelArrayList = new ArrayList<>();
        eastPanelArrayList = new ArrayList<>();
        northPanelArrayList = new ArrayList<>();
        southPanelArrayList = new ArrayList<>();
        leftPanelArrayList = new ArrayList<>();

        //Left panel
        leftPanel();

        //Center panel
        centerPanel();

        //East panel
        eastPanel();

        //South panel
        southPanel();

        //North panel
        northPanel();

        ///////////
        //System.out.println("width: " + Toolkit.getDefaultToolkit().getScreenSize().width + "Height :" + Toolkit.getDefaultToolkit().getScreenSize().height);
        myFrame.setMinimumSize(dimensionCreator(1500, 1200));
        myFrame.setMaximumSize(dimensionCreator(1440, 960));
        if (Toolkit.getDefaultToolkit().getScreenSize().width < 1500)
            myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);

    }

    public static void main(String[] args) {
        MainGraph mainGraph = new MainGraph();
    }
}
