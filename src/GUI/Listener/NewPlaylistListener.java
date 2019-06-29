package GUI.Listener;

import Logic.Entities.Library;

import Logic.Entities.PlayList;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class NewPlaylistListener implements ActionListener {
    private JFrame newPlaylistFrame;
    private Library library;
    private PlayList playList;
    private float[] floats;
    private String playlistName = null;
    private JPanel centerPanel;
    private JButton artwork;
    private JLabel artworkMusicName;
    private JLabel artworkArtisiName;
    private ArrayList<Object> centerPanelArray;
    private JScrollPane sp;
    private ImageIcon imageIcon;
    private JPanel plPanel;
    private JFrame frame;
    private JButton picture = new JButton();
    private JPanel panel = new JPanel(new GridLayout(1, 2));
    private JLabel setPicture = new JLabel();
    private JFileChooser chooser;
    private JFrame fileChooserFrame;


    public NewPlaylistListener(JPanel centerPanel, Library library, JPanel plPanel, JFrame frame, JScrollPane sp, JButton artwork, JLabel artworkMusicName, JLabel artworkArtisiName, ArrayList<Object> centerPanelArray) {
        this.library = library;
        this.plPanel = plPanel;
        this.frame = frame;
        this.centerPanel = centerPanel;
        this.centerPanelArray = centerPanelArray;
        this.artwork = artwork;
        this.sp = sp;
        this.artworkMusicName = artworkMusicName;
        this.artworkArtisiName = artworkArtisiName;
        chooser = new JFileChooser();
        fileChooserFrame = new JFrame();
        floats = new float[3];
        Color.RGBtoHSB(40, 40, 40, floats);


        newPlaylistFrame = new JFrame("Create Playlist");
        newPlaylistFrame.setMinimumSize(new Dimension(300, 200));
        setPicture.setForeground(Color.getHSBColor(floats[0], floats[1], floats[2]));

        setPicture.setText("PlayList Artwork");

        try {
            BufferedImage img = ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\artwork.png"));
            picture.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println("Image not found");
        }
        picture.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
        setPicture.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            BufferedImage img = ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\artwork.png"));
            picture.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println("Image not found");
        }
        playlistName = JOptionPane.showInputDialog("Playlist Name");

        if (playlistName != null) {
            fileChooserFrame.add(chooser);
            File file = null;
            int returnValue = chooser.showOpenDialog(fileChooserFrame);
            if (returnValue == JFileChooser.APPROVE_OPTION)
                file = chooser.getSelectedFile();

            if (file != null) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    byte[] bytes = new byte[(int) file.length()];
                    fileInputStream.read(bytes);
                    imageIcon = (new ImageIcon(ImageIO.read(new ByteArrayInputStream(bytes)).getScaledInstance(150, 150, Image.SCALE_SMOOTH)));

                    picture.setIcon(imageIcon);
                    library.createPlayList(playlistName,imageIcon);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        } else {
                try {
                    BufferedImage img = (BufferedImage) ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\artwork.png")).getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(img);
                    library.createPlayList(playlistName, imageIcon);
                } catch (Exception ex) {
                    System.out.println("Image not found");
                }
            }
            fileChooserFrame.setMinimumSize(new Dimension(600, 400));
            fileChooserFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);





            ///////////////////////////////////////////
            picture.setPreferredSize(new Dimension(150, 150));
            panel.add(setPicture);
            panel.add(picture);
            newPlaylistFrame.add(panel);
            newPlaylistFrame.setLocationRelativeTo(null);
            JButton button = new JButton(playlistName);
            button.setPreferredSize(new Dimension(150, 30));
            button.addActionListener(new ShowPlayListMusic(centerPanel, library, centerPanelArray, frame, sp, artwork, artworkMusicName, artworkArtisiName, playlistName,plPanel));
            button.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
            button.setForeground(Color.lightGray);
            plPanel.add(button);
            SwingUtilities.updateComponentTreeUI(frame);
            newPlaylistFrame.setVisible(true);
            library.saveLibrarySongs();
        }


    }
}
