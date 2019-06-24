package GUI.Listener;

import Logic.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addMusicToLibListener implements ActionListener {
    private JFileChooser fileChooser;
    private Library library;
    private JFrame fileChooserFrame;

    public addMusicToLibListener(Library library) {
        fileChooser = new JFileChooser();
        fileChooserFrame = new JFrame();
        this.library = library;


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        fileChooserFrame.add(fileChooser);
        fileChooserFrame.setVisible(true);
        fileChooserFrame.setMinimumSize(new Dimension(600,400));
            System.out.println(fileChooser.getSelectedFile().getPath());
        library.addSong(fileChooser.getSelectedFile().getPath());
        fileChooserFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
