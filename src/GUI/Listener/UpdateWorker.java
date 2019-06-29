package GUI.Listener;

import GUI.MainGraph;
import Logic.Player.MP3Player;

import javax.swing.*;
import java.util.List;

import static GUI.Listener.PlayMusicListener.getSlider;

/**
 * @author S.Alireza-Ezaz
 * @version final
 * This class handles slider and syncinc with mp3 file
 */
public class UpdateWorker extends SwingWorker<Void, Integer> {
    private volatile static int i;


    public static int getI() {
        return i;
    }


    public static void setI(int i) {
        UpdateWorker.i = i;
    }

    private volatile static int duration;

    public UpdateWorker(int duration) {
        this.duration = duration;
    }

    public static int getDuration() {
        return duration;
    }

    public static void setDuration(int duration) {
        UpdateWorker.duration = duration;
    }

    @Override
    protected Void doInBackground() throws Exception {
        synchronized ((Integer) duration) {
            for (i = 1; i <= duration; i++) {
                int min = i / 60;
                int second = i % 60;
                MainGraph.getRemainingTime().setText("  " + (min) + "  :  " + (second));

                int timeMin = (duration - i) / 60;
                int timeSecond = (duration - i) % 60;
                MainGraph.getTime().setText(" - " + (timeMin) + "  :  " + (timeSecond));
                publish(i);
                if (!MP3Player.isPaused()) {
                    Thread.sleep(1000);

                }
                if (MP3Player.isPaused()) {
                    //System.out.println(duration);
                    i--;

                }
                if (i == duration)
                    i--;



            }
        }
        return null;
    }

    @Override
    protected void process(List<Integer> chunks) {
        getSlider().setValue(chunks.get(0));
    }

}