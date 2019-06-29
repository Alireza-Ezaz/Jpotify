package GUI;

import GUI.Layout.ModifiedFlowLayout;
import GUI.Listener.*;
import GUI.Listener.AddMusicToLibListener;
import GUI.Listener.DarkModeListener;
import GUI.Listener.NewPlaylistListener;
import Logic.Entities.Library;
import Logic.Entities.PlayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static GUI.Listener.PlayMusicListener.getSlider;

/**
 * @author M.S.Haeri
 * @version final
 * This class handles GUI
 */
public class MainGraph {
    private Library library;
    private JFrame myFrame;
    private ArrayList<Object> centerPanelArrayList;
    private ArrayList<Object> eastPanelArrayList;
    private ArrayList<Object> northPanelArrayList;
    private ArrayList<Object> southPanelArrayList;
    private ArrayList<Object> leftPanelArrayList;
    private static JPanel pn;
    private static JButton play = new JButton();
    private static JButton favorite = new JButton();
    private JButton search = new JButton();
    private static JLabel remainingTime = new JLabel();
    private static JLabel time = new JLabel();

    public static JLabel getTime() {
        return time;
    }

    public static JLabel getRemainingTime() {
        return remainingTime;
    }

    public static JButton getPlay() {
        return play;
    }

    public static JPanel getPn() {
        return pn;
    }

    public static JButton getFavorite() {
        return favorite;
    }

    /**
     * This method create image icon without manual size
     *
     * @param path file directory
     */
    private ImageIcon imageCreator(String path) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            return (new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println("Image not found");
            return null;
        }
    }

    /**
     * @return Dimension
     */
    private Dimension dimensionCreator(int width, int height) {
        Dimension dimension = new Dimension(width, height);
        return dimension;
    }

    /**
     * This method create east panel
     */
    private void eastPanel() {
        //Friend Activity column
        JPanel eastPanel = new JPanel(new FlowLayout());
        eastPanelArrayList.add(eastPanel);
        eastPanel.setPreferredSize(dimensionCreator(150, Toolkit.getDefaultToolkit().getScreenSize().height));
        eastPanel.setBackground(Color.lightGray);
        JLabel fActivity = new JLabel(" Friend Activity");
        fActivity.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 15));
        eastPanelArrayList.add(fActivity);
        fActivity.setPreferredSize(dimensionCreator(150, 20));
        eastPanel.add(fActivity);
        myFrame.add(eastPanel, BorderLayout.EAST);
    }

    /**
     * This method create north panel
     *
     * @param lib Library
     */
    public void northPanel(Library lib) {
        try {
            myFrame.setIconImage(ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\logo4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JPanel mainPanel = new JPanel(new BorderLayout());
        northPanelArrayList.add(mainPanel);
        float[] floats = new float[3];
        JPanel menuBar = new JPanel(new FlowLayout());
        northPanelArrayList.add(menuBar);

        Color.RGBtoHSB(200, 200, 201, floats);
        search.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
        Color.RGBtoHSB(40, 40, 41, floats);
        search.setForeground(Color.getHSBColor(floats[0], floats[1], floats[2]));
        northPanelArrayList.add(search);

        search.setPreferredSize(dimensionCreator(30, 30));
        try {
            search.setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\search1.png")).getScaledInstance(30, 30, Image.SCALE_SMOOTH)));

        } catch (
                IOException e1) {
            e1.printStackTrace();
        }

        menuBar.add(search);


        JButton add = new JButton(" +  Add Music To Library");
        add.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 13));

        add.addActionListener(new AddMusicToLibListener(lib));

        add.setPreferredSize(dimensionCreator(200, 30));
        Color.RGBtoHSB(200, 200, 200, floats);
        add.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
        Color.RGBtoHSB(40, 40, 40, floats);
        add.setForeground(Color.getHSBColor(floats[0], floats[1], floats[2]));

        northPanelArrayList.add(add);
        menuBar.add(add);
        Color.RGBtoHSB(200, 200, 199, floats);
        menuBar.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
        Color.RGBtoHSB(40, 40, 39, floats);
        menuBar.setForeground(Color.getHSBColor(floats[0], floats[1], floats[2]));
        JButton friendIp = new JButton();
        //friendIp.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 15));
        northPanelArrayList.add(friendIp);


        friendIp.setPreferredSize(dimensionCreator(30, 30));
        try {
            friendIp.setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\ip.png")).getScaledInstance(30, 30, Image.SCALE_SMOOTH)));

        } catch (
                IOException e1) {
            e1.printStackTrace();
        }
        DefaultListModel ipList = new DefaultListModel();
        friendIp.addActionListener(new IpShower(ipList));
        Color.RGBtoHSB(200, 201, 200, floats);
        friendIp.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
        Color.RGBtoHSB(41, 40, 40, floats);
        friendIp.setForeground(Color.getHSBColor(floats[0], floats[1], floats[2]));
        menuBar.add(friendIp);
        JLabel profileIcon = new JLabel();
        northPanelArrayList.add(profileIcon);
        try {
            profileIcon.setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\2.png")).getScaledInstance(30, 30, Image.SCALE_SMOOTH)));

        } catch (
                IOException e1) {
            e1.printStackTrace();
        }
        profileIcon.setPreferredSize(dimensionCreator(30, 30));
        JPanel user = new JPanel(new FlowLayout());
        northPanelArrayList.add(user);
        user.add(profileIcon);
        JLabel userName = new JLabel(Library.getUsername());
        userName.setPreferredSize(dimensionCreator(150, 30));
        Color.RGBtoHSB(199, 200, 200, floats);
        userName.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
        Color.RGBtoHSB(39, 40, 40, floats);
        userName.setForeground(Color.getHSBColor(floats[0], floats[1], floats[2]));
        userName.setFont(new Font("GothamBold", Font.ITALIC, 15));
        northPanelArrayList.add(userName);
        user.add(userName);
        Color.RGBtoHSB(200, 200, 200, floats);
        user.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
        Color.RGBtoHSB(40, 41, 40, floats);
        user.setForeground(Color.getHSBColor(floats[0], floats[1], floats[2]));
        mainPanel.add(user, BorderLayout.WEST);

        JCheckBox darkMode = new JCheckBox("Dark Mode", false);
        darkMode.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 14));
        northPanelArrayList.add(darkMode);
        darkMode.addActionListener(new DarkModeListener(myFrame, centerPanelArrayList, eastPanelArrayList, northPanelArrayList, southPanelArrayList, leftPanelArrayList));
        darkMode.setPreferredSize(dimensionCreator(150, 30));
        Color.RGBtoHSB(200, 199, 200, floats);
        darkMode.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
        Color.RGBtoHSB(41, 41, 40, floats);
        darkMode.setForeground(Color.getHSBColor(floats[0], floats[1], floats[2]));
        mainPanel.add(darkMode, BorderLayout.EAST);
        mainPanel.add(menuBar, BorderLayout.CENTER);
        myFrame.add(mainPanel, BorderLayout.NORTH);
    }

    /**
     * This method create south panel
     * @throws InterruptedException
     */


    public void southPanel() throws InterruptedException {
        float[] floats = new float[3];

        //Player Panel in southpanel
        JPanel southPanel = new JPanel(new BorderLayout());
        JPanel playerPanel = new JPanel(new FlowLayout());
        southPanelArrayList.add(southPanel);
        southPanelArrayList.add(playerPanel);

        southPanelArrayList.add(play);
        play.addActionListener(new PauseAndResume(play));
        play.setPreferredSize(dimensionCreator(44, 44));
        play.setBackground(Color.lightGray);

        try {
            play.setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\play1.png")).getScaledInstance(44, 44, Image.SCALE_SMOOTH)));

        } catch (
                IOException e1) {
            e1.printStackTrace();
        }
        JButton pNext = new JButton();
        pNext.addActionListener(new Next(play));
        southPanelArrayList.add(pNext);
        pNext.setPreferredSize(dimensionCreator(44, 44));
        pNext.setBackground(Color.lightGray);
        pNext.setIcon(imageCreator("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\next1.png"));
        JButton privios = new JButton();
        privios.addActionListener(new Privious(play));
        southPanelArrayList.add(privios);
        privios.setPreferredSize(dimensionCreator(44, 44));
        privios.setBackground(Color.lightGray);
        privios.setIcon(imageCreator("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\pri1.png"));
        playerPanel.add(privios);
        playerPanel.add(play);
        playerPanel.add(pNext);
        playerPanel.setBackground(Color.lightGray);
        southPanel.add(playerPanel, BorderLayout.WEST);
        Color.RGBtoHSB(40, 40, 40, floats);

        JPanel sliderPanel = new JPanel(new BorderLayout());
        time.setPreferredSize(dimensionCreator(50,30));
        remainingTime.setPreferredSize(dimensionCreator(50,30));
        sliderPanel.add(time, BorderLayout.EAST);
        time.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
        southPanelArrayList.add(time);
        sliderPanel.add(getSlider(), BorderLayout.CENTER);

        southPanelArrayList.add(getSlider());
        getSlider().setBackground(Color.lightGray);


        time.setBackground(Color.lightGray);
        remainingTime.setBackground(Color.lightGray);
        time.setFont(new Font("GothamBold", Font.ITALIC, 12));
        remainingTime.setFont(new Font("GothamBold", Font.ITALIC, 12));
        sliderPanel.add(remainingTime, BorderLayout.WEST);
        sliderPanel.setBackground(Color.lightGray);
        southPanelArrayList.add(remainingTime);
        southPanelArrayList.add(sliderPanel);

        southPanel.add(sliderPanel, BorderLayout.CENTER);

        //southPanel.add(getSlider(), BorderLayout.CENTER);
        JSlider voloum = new JSlider();
        voloum.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //
                double endVolume = 655.35 * ((JSlider) (e.getSource())).getValue();
                Runtime rt = Runtime.getRuntime();
                Process pr;
                try {
                    pr = rt.exec("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\nircmdc.exe" + " setsysvolume " + endVolume);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        southPanelArrayList.add(voloum);
        voloum.setBackground(Color.lightGray);
        voloum.setPreferredSize(dimensionCreator(150, 30));
        southPanel.add(voloum, BorderLayout.EAST);
        southPanel.setBackground(Color.lightGray);
        myFrame.add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * This method create left panel
     */
    private void leftPanel(Library lib, JFrame frame) {
        float[] floats = new float[3];
        JPanel cp = new JPanel(new ModifiedFlowLayout());
        JScrollPane sp = new JScrollPane(cp, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JButton artworkP = new JButton();
        JLabel musicName = new JLabel(" Music Name");
        JLabel artist = new JLabel(" Artist Name");
        JPanel leftPanel = new JPanel(new FlowLayout());
        leftPanelArrayList.add(leftPanel);
        leftPanel.setPreferredSize(dimensionCreator(150, Toolkit.getDefaultToolkit().getScreenSize().height));


        JButton music = new JButton("Musics");
        JButton album = new JButton("Albums");
        JButton artists = new JButton("Artists");
        JButton playLists = new JButton("Playlists");
        music.setBackground(Color.LIGHT_GRAY);
        album.setBackground(Color.LIGHT_GRAY);
        artists.setBackground(Color.LIGHT_GRAY);
        playLists.setBackground(Color.LIGHT_GRAY);


        leftPanelArrayList.add(music);
        leftPanelArrayList.add(album);
        leftPanelArrayList.add(artists);
        leftPanelArrayList.add(playLists);
        music.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 14));
        album.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 14));
        artists.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 14));
        playLists.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 14));
        music.setPreferredSize(dimensionCreator(150, 30));
        album.setPreferredSize(dimensionCreator(150, 30));
        artists.setPreferredSize(dimensionCreator(150, 30));
        playLists.setPreferredSize(dimensionCreator(150, 30));

        JPanel plPanel = new JPanel(new ModifiedFlowLayout());
        JScrollPane plPane = new JScrollPane(plPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        leftPanelArrayList.add(plPane);
        plPane.setPreferredSize(dimensionCreator(150, 200));

        JLabel library = new JLabel("LIBRARY");
        library.setFont(new Font("GothamBold", Font.ITALIC, 16));
        leftPanelArrayList.add(library);
        library.setPreferredSize(dimensionCreator(150, 20));

        JLabel playlistListLabel = new JLabel("PLAYLISTS");
        playlistListLabel.setFont(new Font("GothamBold", Font.ITALIC, 16));
        leftPanelArrayList.add(playlistListLabel);
        playlistListLabel.setPreferredSize(dimensionCreator(150, 20));
        Color.RGBtoHSB(40, 40, 40, floats);
        plPanel.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
        plPanel.setForeground(Color.lightGray);

        for (PlayList playList : lib.getPlayLists()) {
            JButton button = new JButton(playList.getName());
            button.addActionListener(new ShowPlayListMusic(cp, lib, centerPanelArrayList, frame, sp, artworkP, musicName, artist, playList.getName(), plPanel));
            button.setPreferredSize(new Dimension(150, 30));
            plPanel.add(button);
            Color.RGBtoHSB(40, 40, 40, floats);
            button.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
            button.setForeground(Color.lightGray);
        }

        leftPanel.add(library);
        leftPanel.add(music);
        leftPanel.add(album);
        leftPanel.add(artists);
        leftPanel.add(playLists);
        leftPanel.add(playlistListLabel);
        leftPanel.add(plPane);


        leftPanelArrayList.add(artworkP);
        artworkP.setBackground(Color.lightGray);
        try {
            artworkP.setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\artwork.png")).getScaledInstance(150, 150, Image.SCALE_SMOOTH)));

        } catch (
                IOException e1) {
            e1.printStackTrace();
        }

        artworkP.setPreferredSize(dimensionCreator(150, 150));
        leftPanel.add(artworkP);

        musicName.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 14));
        artist.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 14));
        leftPanelArrayList.add(musicName);
        leftPanelArrayList.add(artist);

        musicName.setPreferredSize(dimensionCreator(150, 20));
        artist.setPreferredSize(dimensionCreator(150, 20));
        leftPanel.add(musicName);
        leftPanel.add(artist);
        favorite.setPreferredSize(dimensionCreator(150, 50));
        favorite.setIcon(imageCreator("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\unFavorite.png"));
        leftPanelArrayList.add(favorite);
        favorite.setBackground(Color.lightGray);
        leftPanel.add(favorite);

        cp.setBackground(Color.gray);
        centerPanelArrayList.add(cp);

        sp.getViewport().setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 310, 730));

        try {

            JLabel label = new JLabel();
            label.setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\main.png")).getScaledInstance(960, 753, Image.SCALE_SMOOTH)));
            centerPanelArrayList.add(label);
            pn = new JPanel();
            pn.setBackground(Color.gray);
            centerPanelArrayList.add(pn);
            pn.add(label);
            myFrame.add(pn, BorderLayout.CENTER);

        } catch (Exception ex) {
            System.out.println("Image not found");

        }

        music.addActionListener(new ShowMusicListener(cp, lib, centerPanelArrayList, frame, sp, artworkP, musicName, artist));
        album.addActionListener(new ShowAlbumListener(cp, lib, centerPanelArrayList, frame, sp, artworkP, musicName, artist));
        artists.addActionListener(new ShowArtistListener(cp, lib, centerPanelArrayList, frame, sp, artworkP, musicName, artist));
        playLists.addActionListener(new PlayListShower(cp, lib, centerPanelArrayList, frame, sp, artworkP, musicName, artist, plPanel));
        favorite.addActionListener(new Favorite(lib));
        search.addActionListener(new Search(cp, lib, centerPanelArrayList, frame, sp, artworkP, musicName, artist));

        leftPanel.setBackground(Color.LIGHT_GRAY);
        myFrame.add(leftPanel, BorderLayout.WEST);
        JButton newPlaylist = new JButton("+   New Playlist");
        newPlaylist.addActionListener(new NewPlaylistListener(cp, lib, plPanel, frame, sp, artworkP, musicName, artist, centerPanelArrayList));
        newPlaylist.setFont(new Font("GothamBold", Font.ROMAN_BASELINE, 15));
        leftPanelArrayList.add(newPlaylist);
        newPlaylist.setBackground(Color.lightGray);
        newPlaylist.setPreferredSize(dimensionCreator(150, 50));
        leftPanel.add(newPlaylist);


    }


    public MainGraph(Library library) throws InterruptedException {
        play = new JButton();
        this.library = library;
        myFrame = new JFrame("Jpotify");
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myFrame.setLayout(new BorderLayout());
        centerPanelArrayList = new ArrayList<>();
        eastPanelArrayList = new ArrayList<>();
        northPanelArrayList = new ArrayList<>();
        southPanelArrayList = new ArrayList<>();
        leftPanelArrayList = new ArrayList<>();

        leftPanel(library, myFrame);

        eastPanel();

        southPanel();

        northPanel(library);

        ///////////
        myFrame.setMinimumSize(dimensionCreator(1600, 1255));
        myFrame.setMaximumSize(dimensionCreator(1440, 1100));
        myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);

    }

}
