package GUI.Listener;

import Logic.Player.MP3Player;

import javax.swing.*;
import java.util.List;

import static GUI.Listener.PlayMusicListener.getSlider;

public class UpdateWorker extends SwingWorker<Void, Integer> {
    private volatile static int i;

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

                if (!MP3Player.isPaused()) {
                    Thread.sleep(1000);
                    publish(i);
                }
                if (MP3Player.isPaused()) {
                    publish(i);
                    //System.out.println(duration);
                    i--;

                }
                if (i == duration)
                    i--;


            }
        }
        //PlayMusicListener.setUpdateWorker(null);
        //i = 1;
        return null;
    }

    @Override
    protected void process(List<Integer> chunks) {
        getSlider().setValue(chunks.get(0));
    }

}