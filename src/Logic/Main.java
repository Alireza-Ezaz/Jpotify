package Logic;

import GUI.MainGraph;
import Logic.Entities.Library;
import Logic.LoginPage.Login;

/**
 * This class controls the App
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Login login = new Login();
        Library lib = new Library(login.getUserName());
        if (Library.getUsername().equals("Guest"))
            lib.saveLibrarySongs();
        else
            lib.loadSongs();
        MainGraph mainGraph = new MainGraph(lib);
    }
}
