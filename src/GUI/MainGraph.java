package GUI;

import GUI.Listener.*;
import GUI.Listener.AddMusicToLibListener;
import GUI.Listener.DarkModeListener;
import GUI.Listener.NewPlaylistListener;
import Logic.Entities.Library;
import Logic.Player.MP3Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class MainGraph {
    private Library library;
    private JFrame myFrame;
    private ArrayList<Object> centerPanelArrayList;
    private ArrayList<Object> eastPanelArrayList;
    private ArrayList<Object> northPanelArrayList;
    private ArrayList<Object> southPanelArrayList;
    private ArrayList<Object> leftPanelArrayList;
    private JPanel centerPanel;

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


    private void eastPanel() {
        JPanel eastPanel = new JPanel(new FlowLayout());
        eastPanelArrayList.add(eastPanel);
        eastPanel.setPreferredSize(dimensionCreator(150, Toolkit.getDefaultToolkit().getScreenSize().height));
        eastPanel.setBackground(Color.lightGray);

        JLabel fActivity = new JLabel(" Friend Activity");
        fActivity.setFont(new Font("GothamBold",Font.ROMAN_BASELINE,15));
        eastPanelArrayList.add(fActivity);
        fActivity.setPreferredSize(dimensionCreator(150, 20));
        eastPanel.add(fActivity);
        myFrame.add(eastPanel, BorderLayout.EAST);
    }

    private void centerPanel(Library lib, JPanel centerPanel ) {

        centerPanelArrayList.add(centerPanel);
        centerPanel.setBackground(Color.GRAY);

        JPanel menuBar = new JPanel(new FlowLayout());
        centerPanelArrayList.add(menuBar);
        menuBar.setPreferredSize(dimensionCreator(Toolkit.getDefaultToolkit().getScreenSize().width - 300, 50));


        JButton add = new JButton(" +  Add Music To Library");
        add.setFont(new Font("GothamBold",Font.ROMAN_BASELINE,13));
        centerPanelArrayList.add(add);
        add.addActionListener(new AddMusicToLibListener(lib));

        add.setPreferredSize(dimensionCreator(200, 30));
        add.setBackground(Color.gray);
        menuBar.add(add);
        menuBar.setBackground(Color.gray);
        JButton signIn = new JButton("Sign in");
        signIn.setFont(new Font("GothamBold",Font.ROMAN_BASELINE,15));
        centerPanelArrayList.add(signIn);

        signIn.setPreferredSize(dimensionCreator(150, 30));
        signIn.setBackground(Color.gray);
        menuBar.add(signIn);
        JLabel profileIcon = new JLabel();
        centerPanelArrayList.add(profileIcon);

        profileIcon.setIcon(imageCreator("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\profile.png"));
        profileIcon.setPreferredSize(dimensionCreator(30, 30));
        menuBar.add(profileIcon);
        JCheckBox darkMode = new JCheckBox("Dark Mode", false);
        darkMode.setFont(new Font("GothamBold",Font.ROMAN_BASELINE,14));
        centerPanelArrayList.add(darkMode);
        darkMode.addActionListener(new DarkModeListener(myFrame, centerPanelArrayList, eastPanelArrayList, northPanelArrayList, southPanelArrayList, leftPanelArrayList));
        darkMode.setPreferredSize(dimensionCreator(150, 30));
        darkMode.setBackground(Color.gray);
        menuBar.add(darkMode);
        centerPanel.add(menuBar);


        /////////////////////////////
       /* JPanel recentlyPlayed = new JPanel();
        centerPanelArrayList.add(recentlyPlayed);

        recentlyPlayed.setPreferredSize(dimensionCreator(Toolkit.getDefaultToolkit().getScreenSize().width, 150));
        recentlyPlayed.setBackground(Color.lightGray);*/

        myFrame.add(centerPanel, BorderLayout.CENTER);
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
        play.setIcon(imageCreator("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\play.png"));
        JButton pNext = new JButton();
        southPanelArrayList.add(pNext);
        pNext.setPreferredSize(dimensionCreator(44, 44));
        pNext.setBackground(Color.lightGray);
        pNext.setIcon(imageCreator("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\next.png"));
        JButton privios = new JButton();
        southPanelArrayList.add(privios);
        privios.setPreferredSize(dimensionCreator(44, 44));
        privios.setBackground(Color.lightGray);
        privios.setIcon(imageCreator("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\pri.png"));
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

    private void leftPanel(Library lib, JPanel centerPanel, ArrayList<Object> cpArrayList, JFrame frame) {
        JPanel leftPanel = new JPanel(new FlowLayout());
        leftPanelArrayList.add(leftPanel);
        leftPanel.setPreferredSize(dimensionCreator(150, Toolkit.getDefaultToolkit().getScreenSize().height));



        JButton music = new JButton("Musics");
        JButton album = new JButton("Albums");
        JButton artists = new JButton("Artists");
        music.setBackground(Color.LIGHT_GRAY);
        album.setBackground(Color.LIGHT_GRAY);
        artists.setBackground(Color.LIGHT_GRAY);
        JPanel musics = new JPanel(new FlowLayout());

        leftPanelArrayList.add(music);
        leftPanelArrayList.add(album);
        leftPanelArrayList.add(artists);
        music.setFont(new Font("GothamBold",Font.ROMAN_BASELINE,14));
        album.setFont(new Font("GothamBold",Font.ROMAN_BASELINE,14));
        artists.setFont(new Font("GothamBold",Font.ROMAN_BASELINE,14));
        music.setPreferredSize(dimensionCreator(150,30));
        album.setPreferredSize(dimensionCreator(150,30));
        artists.setPreferredSize(dimensionCreator(150,30));

//        JScrollPane scrollPane = new JScrollPane();
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        DefaultListModel listModelplaylist = new DefaultListModel();//list of your all playlist
        JList playlistList = new JList(listModelplaylist);
        leftPanelArrayList.add(playlistList);


        //libraryList.setPreferredSize(dimensionCreator(150, 70));
        playlistList.setPreferredSize(dimensionCreator(150, 300));

        JLabel library = new JLabel("LIBRARY");
        library.setFont(new Font("GothamBold",Font.ITALIC,16));
        leftPanelArrayList.add(library);
        library.setPreferredSize(dimensionCreator(150, 20));

        JLabel playlistListLabel = new JLabel("PLAYLISTS");
        playlistListLabel.setFont(new Font("GothamBold",Font.ITALIC,16));
        leftPanelArrayList.add(playlistListLabel);
        playlistListLabel.setPreferredSize(dimensionCreator(150, 20));

        leftPanel.add(library);
        leftPanel.add(music);
        leftPanel.add(album);
        leftPanel.add(artists);
        //leftPanel.add(scrollPane);
        leftPanel.add(playlistListLabel);
        leftPanel.add(playlistList);

        JButton artworkP = new JButton();
        leftPanelArrayList.add(artworkP);
        artworkP.setBackground(Color.lightGray);
        artworkP.setIcon(imageCreator("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\artwork.png"));
        artworkP.setPreferredSize(dimensionCreator(150, 150));
        leftPanel.add(artworkP);



        JLabel musicName = new JLabel(" Music Name");
        musicName.setFont(new Font("GothamBold",Font.ROMAN_BASELINE,14));
        JLabel artist = new JLabel(" Artist Name");
        artist.setFont(new Font("GothamBold",Font.ROMAN_BASELINE,14));
        music.addActionListener(new ShowMusicListener(centerPanel, lib,cpArrayList, frame, musics, artworkP,musicName,artist));
        album.addActionListener(new ShowAlbumListener(centerPanel, lib,cpArrayList, frame, musics));
        artists.addActionListener(new ShowArtistListener(centerPanel, lib,cpArrayList, frame, musics));
        leftPanelArrayList.add(musicName);
        leftPanelArrayList.add(artist);
        musicName.setPreferredSize(dimensionCreator(150, 20));
        artist.setPreferredSize(dimensionCreator(150, 20));
        leftPanel.add(musicName);
        leftPanel.add(artist);
//        JCheckBox favorite = new JCheckBox("Favorite");
//        leftPanelArrayList.add(favorite);
//        favorite.setPreferredSize(dimensionCreator(150, 30));
//        favorite.setBackground(Color.lightGray);
//        leftPanel.add(favorite);
        JButton favorite = new JButton();
        favorite.setPreferredSize(dimensionCreator(150, 50));
        favorite.setIcon(imageCreator("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\unFavorite.png"));
        leftPanelArrayList.add(favorite);
        favorite.setBackground(Color.lightGray);
        leftPanel.add(favorite);


        leftPanel.setBackground(Color.LIGHT_GRAY);
        myFrame.add(leftPanel, BorderLayout.WEST);
        JButton newPlaylist = new JButton("+   New Playlist");
        newPlaylist.addActionListener(new NewPlaylistListener(lib));
        newPlaylist.setFont(new Font("GothamBold",Font.ROMAN_BASELINE,15));
        leftPanelArrayList.add(newPlaylist);
        newPlaylist.setBackground(Color.lightGray);
        newPlaylist.setPreferredSize(dimensionCreator(150, 50));
        leftPanel.add(newPlaylist);


    }


    public MainGraph(Library library) {
        this.library = library;
        myFrame = new JFrame("Jpotify");
        centerPanel = new JPanel(new FlowLayout());
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myFrame.setLayout(new BorderLayout());
        centerPanelArrayList = new ArrayList<>();
        eastPanelArrayList = new ArrayList<>();
        northPanelArrayList = new ArrayList<>();
        southPanelArrayList = new ArrayList<>();
        leftPanelArrayList = new ArrayList<>();


        //Left panel
        leftPanel(library, centerPanel, centerPanelArrayList, myFrame);

        //Center panel
        centerPanel(library, centerPanel);

        //East panel
        eastPanel();

        //South panel
        southPanel();

        //North panel
        //northPanel();

        ///////////
        //System.out.println("width: " + Toolkit.getDefaultToolkit().getScreenSize().width + "Height :" + Toolkit.getDefaultToolkit().getScreenSize().height);
        myFrame.setMinimumSize(dimensionCreator(1600, 1255));
        myFrame.setMaximumSize(dimensionCreator(1440, 960));
        //if (Toolkit.getDefaultToolkit().getScreenSize().width < 1500)
        myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);


    }

    public static void main(String[] args) {
        Library lib = new Library();
        lib.loadSongs();
        MainGraph mainGraph = new MainGraph(lib);

//        MP3Player mp3Player = new MP3Player(lib.getSongs(),lib);
//        Thread t = new Thread(mp3Player);
//        t.start();
        //System.out.println(lib);
    }
}
