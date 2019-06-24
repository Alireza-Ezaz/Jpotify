package GUI.Listener;

import Logic.Entities.Library;

import Logic.Entities.PlayList;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class newPlaylistListener implements ActionListener {
    private JFrame newPlaylistFrame;
    private Library library;
    private PlayList playList;
    private ImageIcon artwork;
    private float[] floats;


    public newPlaylistListener(Library library){
        floats = new float[3];
        this.library = library;
        newPlaylistFrame = new JFrame();
        newPlaylistFrame.setMinimumSize(new Dimension(500,300));
        JPanel panel = new JPanel(new BorderLayout());
        JLabel creatPlaylist = new JLabel("Creat Playlist");
        creatPlaylist.setFont(new Font("GothamBold",Font.ROMAN_BASELINE,14));
        creatPlaylist.setPreferredSize(new Dimension(300,50));
        panel.add(creatPlaylist, BorderLayout.NORTH);
        Color.RGBtoHSB(24, 24, 24, floats);

        try {
            BufferedImage img = ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\artwork.png"));
            artwork =new ImageIcon(img);

        } catch (Exception ex) {
            System.out.println("Image not found");
        }

        JButton artworkButton = new JButton();
        artworkButton.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
        artworkButton.setPreferredSize(new Dimension(150,150));
        artworkButton.setIcon(artwork);
        panel.add(artworkButton, BorderLayout.WEST);

        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
        namePanel.setPreferredSize(new Dimension(150,150));

        JLabel nameLabel = new JLabel("New Playlist Name :");
        nameLabel.setFont(new Font("GothamBold",Font.ROMAN_BASELINE,14));
        nameLabel.setPreferredSize(new Dimension(150,50));

        JTextField nameTxtField = new JTextField();
        nameTxtField.setPreferredSize(new Dimension(150, 100));
        namePanel.add(nameLabel, BorderLayout.NORTH);
        nameLabel.add(nameTxtField, BorderLayout.SOUTH);
        panel.add(namePanel, BorderLayout.EAST);
        newPlaylistFrame.add(panel);



    }
    @Override
    public void actionPerformed(ActionEvent e) {
        newPlaylistFrame.setVisible(true);
        //newPlaylistFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
