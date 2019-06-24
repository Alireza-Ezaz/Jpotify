package Logic.Player;

import Logic.Entities.Song;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MP3Player implements Runnable {
    private AdvancedPlayer player = null;
    private Song playingSong = null;
    private ArrayList<Song> songs = null;
    private boolean isPaused = false;
    private FileInputStream inputStream;

    public MP3Player(Song song) {
        songs = new ArrayList<Song>();
        songs.add(song);
    }

    public MP3Player(ArrayList<Song> songs) {
        this.songs = songs;
    }
/*
    public void setSong(Song song) {
        this.song = song;
    }*/

    @Override
    public void run() {
        try {
            //need chenge
            for (Song song:songs) {
                inputStream = new FileInputStream(song.getDirectory());
                player = new AdvancedPlayer(inputStream);
                playingSong = song;
                while (player.play(1)) {
                    if (isPaused) {
                        synchronized (player) {
                            player.wait();
                        }

                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("wait has problem");
        }

    }

    /**
     * This method will pause the music
     */
    public void setPaused() {
        this.isPaused = true;
    }

    /**
     * This method will resume the music
     */
    public void setResume() {
        this.isPaused = false;
        synchronized (player) {
            player.notifyAll();
        }
    }

    /**
     * This method seek to any frame passed as its parameter
     */
    public void seekTo(int frame) {
        synchronized (player) {
            try {
                // player.close();
                inputStream = new FileInputStream(playingSong.getDirectory());
                player = new AdvancedPlayer(inputStream);
                player.play(frame, frame + 1);

            } catch (JavaLayerException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}
