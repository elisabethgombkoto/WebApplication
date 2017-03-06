import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Elisabeth on 06.03.2017.
 */
public class ClientHandler extends Thread {

    private Socket conn;

        ClientHandler(Socket conn)
        {
            this.conn = conn;
        }

        public void run(){
            String line , input = "";

            try {
                //get socket writing and reading streams
                DataInputStream in = new DataInputStream(conn.getInputStream());
                PrintStream out = new PrintStream(conn.getOutputStream());

                //Send welcome message to client
                out.println("Welcome to the Server");

                //Now start reading input from client
                while((line = in.readLine()) != null && !line.equals("."))
                {
                    //reply with the same message, adding some text
                    out.println("I got : " + line);
                }

                //client disconnected, so close socket
                conn.close();
            }
            catch (IOException e)
            {
                System.out.println("IOException on socket : " + e);
                e.printStackTrace();
            }
        }
    }

