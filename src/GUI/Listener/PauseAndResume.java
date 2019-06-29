package GUI.Listener;

import Logic.Player.MP3Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * @author M.S.Haeri
 * @version final
 * This class handles Pause and resume button
 */

public class PauseAndResume implements ActionListener {
    private JButton button;
    public PauseAndResume(JButton button){
        this.button = button;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (MP3Player.isPaused()) {
            PlayMusicListener.getMp3Player().setResume();

            try {
                button.setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\pause.png")).getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        } else{
            PlayMusicListener.getMp3Player().setPaused();
            try {
                button.setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\play1.png")).getScaledInstance(45, 45, Image.SCALE_SMOOTH)));


            } catch (Exception ex) {
                System.out.println("Image not found");

            }
        }

    }
}
