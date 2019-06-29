package GUI.Listener;

import Logic.Entities.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AddMusicToLibListener implements ActionListener {
    private JFileChooser chooser;
    private JFrame fileChooserFrame;
    private Library library;


    public AddMusicToLibListener(Library library) {
        chooser = new JFileChooser();
        fileChooserFrame = new JFrame();
        this.library = library;

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        fileChooserFrame.add(chooser);
        chooser.setBackground(Color.gray);
        File file = null;
        int returnValue = chooser.showOpenDialog(fileChooserFrame);
        if (returnValue == JFileChooser.APPROVE_OPTION)
            file = chooser.getSelectedFile();
        if (file != null) {
            library.addSong(file.getPath());
            System.out.println(library);
            library.saveLibrarySongs();
            library.loadSongs();
        }


        fileChooserFrame.setMinimumSize(new Dimension(600, 400));


        fileChooserFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        

    }
}
