package GUI.Listener;

import Logic.Entities.Library;
import Logic.Entities.Song;
import Logic.Player.MP3Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayMusicListener implements ActionListener {
    private ArrayList<Song> playingSongs;
    private Song song;
    private Library library;
    private static MP3Player mp3Player;
    private static Thread tr;

    public PlayMusicListener(Song song, Library library) {
        this.song = song;
        this.library = library;
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
        if (tr != null)
            mp3Player.setFalseisRunning();
        mp3Player = new MP3Player(playingSongs, library);


        tr = new Thread(mp3Player);
        tr.start();

    }

}
