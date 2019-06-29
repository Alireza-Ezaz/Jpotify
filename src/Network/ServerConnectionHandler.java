package Network; /** In The Name of Allah **/

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class starts a server and waits until a client connect to that then accept that client and make a new 'Server_ClientsHandler'
 * @author S.Alireza-Ezaz
 */
public class ServerConnectionHandler extends JFrame {
    public static JButton setupBut;
    public ServerConnectionHandler() {
        this.setLayout(null);
        this.setSize(120,70);
        setupBut = new JButton("setup");
        setupBut.setLocation(25, 10);
        setupBut.setSize(70, 30);
        this.add(setupBut);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        setupBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.out.println("server started.");
                ExecutorService pool = Executors.newCachedThreadPool();
                try (ServerSocket serverSocket = new ServerSocket(5050)) {
                    System.out.println("waiting for client...");
                    Socket socket = serverSocket.accept();
                    System.out.println("client detected successfully.");
                    pool.execute(new Server_ClientsHandler(socket));
                } catch (IOException e1) {
                }
            }
        });
    }

    public static void main(String[] args) {
        ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler();
    }
}

