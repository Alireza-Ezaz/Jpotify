package Network; /**
 * In The Name of Allah
 **/

import GUI.Listener.PlayMusicListener;
import Logic.Entities.Library;
import Logic.Entities.Song;
import Logic.Player.MP3Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * This class handles a client to connect to the game server
 * @author S.Alireza Ezaz
 */
public class ClientConnectionHandler extends JFrame implements Runnable {
    public static String newSongData = "";
    public static boolean sentSong = false;
    public static JTextField ipText;
    public static JButton connectBut;
    public static String friendIP = "";

    /**
     * Constructor: use an IP address and a port to create a socket.
     */
    public ClientConnectionHandler() {
        this.setLayout(null);
        this.setSize(120, 85);
        /**
         * the device IP that get across via 'ifconfig' in terminal
         */
        ipText = new JTextField("127.0.0.1");
        ipText.setSize(100, 20);
        ipText.setLocation(10, 10);
        connectBut = new JButton("connect");
        connectBut.setLocation(15, 30);
        connectBut.setSize(90, 30);
        this.add(ipText);
        this.add(connectBut);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        connectBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("client started.");
                dispose();
            }
        });
    }

    @Override
    public void run() {
        try (Socket server = new Socket(friendIP, 5050)) {
            System.out.println("connected to server successfully.");
            OutputStream outputStream = server.getOutputStream();
            InputStream inputStream = server.getInputStream();
            byte[] buffer = new byte[2048];
            String message = "";
            while (true) {
                Thread.sleep(2000 * 60);
                /**
                 * send song to friend
                 */
                try {
                    Song sendingSong = null;
                    if (PlayMusicListener.getMp3Player() != null)
                        sendingSong = PlayMusicListener.getMp3Player().getPlayingSong();
                    // else
                    // sendingSong = null;

                    if (!sentSong) {
                        message = sendingSong.toString();
                        newSongData = "";
                        sentSong = true;
                    } else {
                        newSongData = "";
                    }
                } catch (Exception e) {
                    message = "ex from client";
                }
                outputStream.write(message.getBytes());
                if (message.equals("over")) {
                    break;
                }
                int read = inputStream.read(buffer);
                String receivedMessage = new String(buffer, 0, read);
                /**
                 * update friend activity
                 */
                try {
                    String songData = receivedMessage;
                //right panel

                } catch (Exception e) {
                }
            }
        } catch (IOException ex) {
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClientConnectionHandler clientConnectionHandler = new ClientConnectionHandler();
    }
}
