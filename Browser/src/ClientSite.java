import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Elisabeth on 28.02.2017.
 */
public class ClientSite {
    private Socket _socket;


    private String _host;

    public ClientSite(String host) {
        _host = host;
    }

    public void tryRequest(String host) {
        PrintWriter s_out = null;
        BufferedReader s_in = null;
        try {

            _socket = new Socket(get_host(),80);
            s_out = new PrintWriter(_socket.getOutputStream(),true);
            s_in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));

            //Host not found
        } catch (IOException e) {
            //TODO pop up fenster
            e.printStackTrace();
        }
        String message = "GET " + getFile() + " HTTP/1.1\r\nHost: " + get_urlAdresse() + "\r\n"+ "Connention: close \r\n\r\n";
        s_out.println(message);
        //Get response from server
        String response;

        try {
            while ((response = s_in.readLine()) != null) {
                System.out.println(response);
            }
            //close the i/o streams
            s_out.close();
            s_in.close();
            //close the socket
            _socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get_urlAdresse() {
        char[] url= _host.toCharArray();
        int i=0;
        StringBuffer sb= new StringBuffer();

        while( url[i]!='/'&& i<url.length-1){
          sb.append(url[i]);
          i++;
        }
        return  sb.toString();
    }


    private String getFile() {
        char[] url = _host.toCharArray();
        int i = 0;
        StringBuffer sb = new StringBuffer();

        while (url[i] != '/' && i < url.length - 1) {
            i++;
            if (url[i] == '/') {
                int j = i;
                while (j < url.length - 1) {
                    sb.append(url[j]);
                }
            }
        }
        return sb.append('/').toString();
    }
/*
Hallo Elisabeth,
Ich hab nicht wirklich nützliche links gefunden für die aufgabe in webbaplikationen.
Dieser link hat etwas geholfen: http://www.binarytides.com/java-socket-programming-tutorial/
Hab diesen teil: String message = "GET / HTTP/1.1\r\n\r\n"; umgeändert damits bei mir funktioniert.
Schaut nun so aus: String message = "GET " + file + " HTTP/1.1\r\nHost: " +domainName +"\r\n";
Für www.fhv.at/studium/wirtschaft/ ist das file /studium/wirtschaft/ und domainName www.fhv.at
 */
public String get_host() {
    return _host;
}

    public static void main(String[] args) {
ClientSite clientSite= new ClientSite("www.fhv.at");
clientSite.tryRequest(clientSite.get_urlAdresse());
    }
}