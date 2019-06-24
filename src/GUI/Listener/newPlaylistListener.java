package GUI.Listener;

import Logic.Library;
import Logic.PlayList;

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


    public newPlaylistListener(Library library){
        this.library = library;
        newPlaylistFrame = new JFrame();
        newPlaylistFrame.setMinimumSize(new Dimension(500,300));
        JPanel panel = new JPanel(new BorderLayout());
        JLabel creatPlaylist = new JLabel("Creat Playlit");
        creatPlaylist.setFont(new Font("GothamBold",Font.ROMAN_BASELINE,14));
        creatPlaylist.setPreferredSize(new Dimension(300,50));
        panel.add(creatPlaylist, BorderLayout.NORTH);

        try {
            BufferedImage img = ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\artwork.png"));
            artwork =new ImageIcon(img);

        } catch (Exception ex) {
            System.out.println("Image not found");
        }

        JButton artworkButton = new JButton();
        artworkButton.setPreferredSize(new Dimension(150,150));
        artworkButton.setIcon(artwork);
        panel.add(artworkButton, BorderLayout.WEST);

        JTextField name = new JTextField("Name");
        name.setPreferredSize(new Dimension(150, 150));
        panel.add(name);
        newPlaylistFrame.add(panel);



    }
    @Override
    public void actionPerformed(ActionEvent e) {
        newPlaylistFrame.setVisible(true);
        //newPlaylistFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
