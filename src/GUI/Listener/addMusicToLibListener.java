package GUI.Listener;

import Logic.Entities.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class addMusicToLibListener implements ActionListener {
    private JFileChooser chooser;
    private Library library;
    private JFrame fileChooserFrame;

    public addMusicToLibListener(Library library) {
        chooser = new JFileChooser();
        fileChooserFrame = new JFrame();
        this.library = library;


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        fileChooserFrame.add(chooser);
        File file = null;
        int returnValue = chooser.showOpenDialog(fileChooserFrame);
        if (returnValue == JFileChooser.APPROVE_OPTION)
            file = chooser.getSelectedFile();
        library.addSong(file.getPath());
        System.out.println(library);
        library.saveLibrarySongs();



        fileChooserFrame.setMinimumSize(new Dimension(600, 400));


        fileChooserFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
