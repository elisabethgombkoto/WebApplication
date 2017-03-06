import java.io.*;
import java.net.*;


public class ServerSite {
    public static void main(String args[])
    {
        ServerSocket s = null;
        Socket conn = null;

        try
        {
            //1. creating a server socket - 1st parameter is port number and 2nd is the backlog
            s = new ServerSocket(8080 , 10);

            //2. Wait for an incoming connection
            echo("Server socket created.Waiting for connection...");

            while(true)
            {
                //get the connection socket
                conn = s.accept();

                //print the hostname and port number of the connection
                echo("Connection received from " + conn.getInetAddress().getHostName() + " : " + conn.getPort());

                //create new thread to handle client
                new ClientHandler(conn).start();
            }
        }

        catch(IOException e)
        {
            System.err.println("IOException");
        }

        //5. close the connections and stream
        try
        {
            s.close();
        }

        catch(IOException ioException)
        {
            System.err.println("Unable to close. IOexception");
        }
    }

    public static void echo(String msg)
    {
        System.out.println(msg);
    }
}
