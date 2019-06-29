package Logic.LoginPage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Login {
    private String userName;
    private ImageIcon imageIcon;
    private JOptionPane optionPane;

    public Login() {

        try {
            imageIcon = new ImageIcon(ImageIO.read(new File("C:\\Users\\Mohammad Sadra\\IdeaProjects\\FP AP\\src\\logo4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object[] obj = new Object[1];
        Object ob = new Object();
        optionPane = new JOptionPane();
        optionPane.setIcon(imageIcon);

        userName = (String) optionPane.showInputDialog(null, "Welcom,\nEnter your username", "Jpotify", JOptionPane.INFORMATION_MESSAGE, imageIcon, null, "");
        if (userName == null || userName.equals("") || userName.equals(" ")) {
            userName = "Guest";
        }
    }

    public String getUserName() {
        return userName;
    }
}
