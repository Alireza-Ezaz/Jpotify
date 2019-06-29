package Logic.Player;

import GUI.Listener.PlayMusicListener;
import GUI.Listener.UpdateWorker;
import GUI.MainGraph;
import Logic.Entities.Library;
import Logic.Entities.PlayList;
import Logic.Entities.Song;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class handles Playing,pausing,seeking,... for mp3 files
 * @author S.Alireza-Ezaz
 * @version final
 */

public class MP3Player implements Runnable {
    private volatile AdvancedPlayer player = null;
    private Library library = null;
    private Song playingSong = null;
    private ArrayList<Song> songs = null;
    private static boolean isPaused = false;
    private static boolean isRunning = false;
    private static boolean isFirstTimePlayingAnArray = true;
    private FileInputStream inputStream;
    private JButton artworkButton;
    private JLabel nameLabel;
    private JLabel artistLabel;
    private int index;
    private static int numberOfFrames = 0;


    public MP3Player(Song song) {
        songs = new ArrayList<Song>();
        songs.add(song);
    }


    /**
     * @param songs list of songs
     * @param library all songs in the app
     * @param index playing start from this index
     */
    public MP3Player(ArrayList<Song> songs, Library library, JButton artworkButton, JLabel nameLabel, JLabel artistLabel, int index) {
        this.songs = songs;
        this.library = library;
        this.artworkButton = artworkButton;
        this.nameLabel = nameLabel;
        this.artistLabel = artistLabel;
        this.index = index;
    }
    public Song getPlayingSong() {
        return playingSong;
    }

    public void setFalseisRunning() {
        this.isRunning = false;
    }

    /**
     *shows that sth is playing right now or not
     */
    public static boolean isIsRunning() {
        return isRunning;
    }

    public static boolean isPaused() {
        return isPaused;
    }

    /**
     *this is for order of playing songs in arraylist
     */
    public static void setIsFirstTimePlayingAnArray(boolean isFirstTimePlayingAnArray) {
        MP3Player.isFirstTimePlayingAnArray = isFirstTimePlayingAnArray;
    }

    public static void setIsPaused(boolean isPaused) {
        MP3Player.isPaused = isPaused;
    }

    @Override
    public void run() {
        try {
            isRunning = true;
            //need chenge
            int i = 0;
            for (Song song : songs) {
                if (isFirstTimePlayingAnArray) {
                    if (i != index) {
                        i++;
                        continue;
                    }
                }
                if (i == index) {
                    i = 0;
                    isFirstTimePlayingAnArray = false;
                }
                song.setLastPlay(new Date());
                playingSong = song;
                artworkButton.setIcon(song.getArtWork());
                nameLabel.setText(song.getName());
                artistLabel.setText(song.getArtistName());
                try {
                    int a = 0;
                    for (PlayList playList : library.getPlayLists()) {
                        if (playList.getName().equals("Favorite Songs")) {
                            for (Song sg : playList.getSongs()) {
                                if (sg.getName().equals(song.getName())) {
                                    MainGraph.getFavorite().setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\favorite.png")).getScaledInstance(150, 30, Image.SCALE_SMOOTH)));
                                    a = 1;
                                    break;
                                }
                            }
                            if (a == 0) {
                                MainGraph.getFavorite().setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\unFavorite.png"))));
                            }
                            break;
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                inputStream = new FileInputStream(song.getDirectory());
                player = new AdvancedPlayer(inputStream);
                if (PlayMusicListener.getUpdateWorker() != null) {
                    synchronized ((Integer) UpdateWorker.getDuration()) {
                        UpdateWorker.setI(1);
                        UpdateWorker.setDuration(findDuration(playingSong));
                        PlayMusicListener.getSlider().setMaximum(MP3Player.findDuration(playingSong));
                        PlayMusicListener.getUpdateWorker().execute();
                    }
                }
                //playing

                while (player.play(1)) {
                    if (!isRunning)
                        return;
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
        } catch (IOException e) {
            e.printStackTrace();
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
                player.close();
                inputStream = new FileInputStream(playingSong.getDirectory());
                player = new AdvancedPlayer(inputStream);
                Thread.sleep(200);
                player.play(frame, frame + 1);

            } catch (JavaLayerException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * next song
     *
     * @param nextOrPrevious song is true & previous song is false
     */
    public void nextOrPrevious(boolean nextOrPrevious) {
        synchronized (player) {
            try {
                player.close();
                int i = 0;
                for (Song song : songs) {
                    if (song.equals(playingSong))
                        break;
                    i++;
                }


                if (nextOrPrevious) {
                    inputStream = new FileInputStream(songs.get((i + 1) % songs.size()).getDirectory());
                    player = new AdvancedPlayer(inputStream);
                    playingSong = songs.get((i + 1) % songs.size());
                } else {
                    if (i == 0) {
                        inputStream = new FileInputStream(songs.get((songs.size() - 1)).getDirectory());
                        player = new AdvancedPlayer(inputStream);
                        playingSong = songs.get(songs.size() - 1);
                    } else {
                        inputStream = new FileInputStream(songs.get((i - 1)).getDirectory());
                        player = new AdvancedPlayer(inputStream);
                        playingSong = songs.get((i - 1));
                    }
                }


                playingSong.setLastPlay(new Date());
                player.play(0, 1);
                artworkButton.setIcon(playingSong.getArtWork());
                nameLabel.setText(playingSong.getName());
                artistLabel.setText(playingSong.getArtistName());

            } catch (JavaLayerException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     *This method finds a duration of a given song
     */
    public static int findDuration(Song song) {
        Header h = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(song.getDirectory());
            Bitstream bitstream = new Bitstream(fileInputStream);
            h = bitstream.readFrame();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (BitstreamException e) {
            e.printStackTrace();
        }

        int size = h.calculate_framesize();
        float ms_per_frame = h.ms_per_frame();
        int maxSize = h.max_number_of_frames(10000);
        float t = h.total_ms(size);
        long tn = 0;
        try {
            tn = fileInputStream.getChannel().size();
        } catch (IOException ex) {

        }
        int min = h.min_number_of_frames(500);
        int durationAllSeconds = (int) (h.total_ms((int) tn) / 1000);

        numberOfFrames = (int) (durationAllSeconds / (h.ms_per_frame() / 1000));
        return durationAllSeconds;

    }

    public static int getNumberOfFrames() {
        return numberOfFrames;
    }

}
