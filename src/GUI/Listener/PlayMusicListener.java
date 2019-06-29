package GUI.Listener;

import Logic.Entities.Library;
import Logic.Entities.Song;
import Logic.Player.MP3Player;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static GUI.MainGraph.getPlay;

/**
 * @author M.S.Haeri
 * @version final
 * This class plays music
 */

public class PlayMusicListener implements ActionListener {
    private ArrayList<Song> playingSongs;
    private Song song;
    private Library library;
    private static MP3Player mp3Player;
    private static Thread tr;
    private JButton artwork;
    private JLabel artworkMusicName;
    private JLabel artworkArtistiName;
    private JFrame frame;
    private ArrayList<Song> songArrayList;
    private static JSlider slider = new JSlider();
    private static UpdateWorker updateWorker = null;


    public PlayMusicListener(Song song, Library library, JButton artwork, JLabel artworkMusicName, JLabel artworkArtistiName, JFrame frame, ArrayList<Song> songArrayList) {
        this.song = song;
        this.library = library;
        this.artwork = artwork;
        this.artworkMusicName = artworkMusicName;
        this.artworkArtistiName = artworkArtistiName;
        this.frame = frame;
        this.songArrayList = songArrayList;
        playingSongs = new ArrayList<>();


    }

    public static void setUpdateWorker(UpdateWorker updateWorker) {
        PlayMusicListener.updateWorker = updateWorker;
    }

    public static UpdateWorker getUpdateWorker() {
        return updateWorker;
    }

    public static JSlider getSlider() {
        return slider;
    }

    public static MP3Player getMp3Player() {
        return mp3Player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(tr!= null)
            tr.stop();

        MP3Player.setIsFirstTimePlayingAnArray(true);
        int index = 0;
        for (Song sg : songArrayList) {
            if (sg.equals(song))
                break;
            index++;
        }

        if (tr != null) {
            mp3Player.setFalseisRunning();

            try {
                Thread.sleep(300);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        }

        mp3Player = new MP3Player(songArrayList, library, artwork, artworkMusicName, artworkArtistiName, index);
        tr = new Thread(mp3Player);
        tr.start();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        if (MP3Player.isPaused()) {
            PlayMusicListener.getMp3Player().setResume();
        }
        try {
            getPlay().setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\pause.png")).getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
        } catch (IOException e1) {
            e1.printStackTrace();
        }


        slider.setMinimum(0);
        slider.setMaximum(MP3Player.findDuration(mp3Player.getPlayingSong()));

        if (updateWorker == null) {
            updateWorker = new UpdateWorker(MP3Player.findDuration(mp3Player.getPlayingSong()));
            updateWorker.execute();
        }
        if (updateWorker != null) {
            UpdateWorker.setI(1);
            updateWorker.execute();
        }

        slider.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

                UpdateWorker.setI(((JSlider) e.getSource()).getValue());

                slider.setValue(((JSlider) e.getSource()).getValue());

                mp3Player.seekTo((((JSlider) e.getSource()).getValue() * MP3Player.getNumberOfFrames() / MP3Player.findDuration(song)));
                //updateWorker.execute();


            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        library.saveLibrarySongs();


    }

}

