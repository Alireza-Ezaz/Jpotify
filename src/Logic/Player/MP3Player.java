package Logic.Player;

import Logic.Song;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MP3Player {
    private AdvancedPlayer player = null;
    private Song song;

   /* public MP3Player(Song song) {
        this.song = song;
    }*/
    public void playSong(){
        try {
            FileInputStream inputStream = new FileInputStream("F:\\Jpotify\\src\\Logic\\1.mp3");
            player = new AdvancedPlayer(inputStream);
            player.play();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        MP3Player mp3Player = new MP3Player();
        mp3Player.playSong();
    }
}
