package GUI.Listener;

import Logic.Entities.Library;
import Logic.Entities.Song;
import Logic.Player.MP3Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    public PlayMusicListener(Song song, Library library,JButton artwork, JLabel artworkMusicName, JLabel artworkArtistiName, JFrame frame) {
        this.song = song;
        this.library = library;
        this.artwork = artwork;
        this.artworkMusicName = artworkMusicName;
        this.artworkArtistiName = artworkArtistiName;
        this.frame = frame;
        playingSongs = new ArrayList<>();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //System.out.println(tr.isAlive());
        System.out.println(song);
        int index = 0;
        for (Song sg : library.getSongs()) {
            if (sg.equals(song))
                break;
            index++;
        }
        System.out.println(index);
        for (int i = index; i < library.getSongs().size(); i++)
            playingSongs.add(library.getSongs().get(i));
        if (tr != null){
            mp3Player.setFalseisRunning();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        }

        mp3Player = new MP3Player(playingSongs, library, artwork,artworkMusicName,artworkArtistiName);


        tr = new Thread(mp3Player);
        tr.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println(mp3Player.getPlayingSong());
//        artworkMusicName.setText(mp3Player.getPlayingSong().getName());
//        artworkArtistiName.setText(mp3Player.getPlayingSong().getArtistName());
//        artwork.setIcon(mp3Player.getPlayingSong().getArtWork());


    }

}
