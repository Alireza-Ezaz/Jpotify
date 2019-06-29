package Network; /**
 * In The Name of Allah
 **/

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * This class handles connection of clients that connected to server via thread
 * @author S.Alireza-Ezaz
 */
public class Server_ClientsHandler implements Runnable {
    public static boolean sentSong = false;
    private Socket client;

    /**
     * @param client
     */
    public Server_ClientsHandler(Socket client) {
        this.client = client;
    }
    @Override
    public void run() {
        try {
            OutputStream outputStream = client.getOutputStream();
            InputStream inputStream = client.getInputStream();
            byte[] buffer = new byte[2048];
            String receivedMessage = "";
            String message = "";

            while (true) {
                int read = inputStream.read(buffer);
                receivedMessage = new String(buffer, 0, read);
                /**
                 * update friends Activity
                 */
                try {
                    String songData = receivedMessage;
                } catch (Exception e) {
                }
                if (receivedMessage.equals("over")) {
                    break;
                }
                /**
                 * send Activity to friend
                 */
                try {

                    if (!sentSong) {
                        sentSong = true;
                    }
                } catch (Exception e) {
                    message = "ex from server";
                }
                outputStream.write(message.getBytes());
            }

        } catch (IOException e) {
        } finally {
            try {
                client.close();
            } catch (IOException ex) {
            }
        }
    }
}
