package Logic.Player;

import Logic.Song;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.*;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MP3Player implements Runnable {
    private AdvancedPlayer player = null;
    private Song song;
    private boolean isPaused = false;
    private FileInputStream inputStream;

    /* public MP3Player(Song song) {
         super("MP3Player");
         this.song = song;
     }*/
    @Override
    public void run() {
        try {
            //need chenge
            inputStream = new FileInputStream("F:\\Jpotify\\src\\Logic\\1.mp3");
            player = new AdvancedPlayer(inputStream);
            while (player.play(1)) {
                if (isPaused) {
                    synchronized (player) {
                        player.wait();
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
                inputStream = new FileInputStream("F:\\Jpotify\\src\\Logic\\1.mp3");
                player = new AdvancedPlayer(inputStream);
                player.play(frame, frame + 1);

            } catch (JavaLayerException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        MP3Player mp3Player = new MP3Player();
        Thread t = new Thread(mp3Player);
        t.start();
        Thread.sleep(5000);
        //mp3Player.setPaused();
        Thread.sleep(3000);
        mp3Player.seekTo(2000);
        //mp3Player.setResume();
        System.out.println(t.isAlive());



        // mp3Player.setIsPaused();

    }
}
