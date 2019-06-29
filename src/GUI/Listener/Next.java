package GUI.Listener;

import Logic.Player.MP3Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Next implements ActionListener {
    private JButton button;
    public Next(JButton button){
        this.button = button;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {

            Thread.sleep(200);
            if(MP3Player.isPaused())
                PlayMusicListener.getMp3Player().setResume();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        PlayMusicListener.getMp3Player().nextOrPrevious(true);
        if(!MP3Player.isIsRunning()){
            PlayMusicListener.getMp3Player().setFalseisRunning();
        }

        UpdateWorker.setI(1);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        UpdateWorker.setI(1);
        UpdateWorker.setDuration(MP3Player.findDuration(PlayMusicListener.getMp3Player().getPlayingSong()));
        PlayMusicListener.getSlider().setMaximum(MP3Player.findDuration(PlayMusicListener.getMp3Player().getPlayingSong()));
        PlayMusicListener.getUpdateWorker().execute();
        try {
            button.setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\pause.png")).getScaledInstance(44, 44, Image.SCALE_SMOOTH)));

        } catch (
                IOException e1) {
            e1.printStackTrace();
        }


    }
}
