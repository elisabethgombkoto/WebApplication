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
    //private String _text;

    public ClientSite() {

    }

    public String tryRequest(String host) {
        PrintWriter s_out = null;
        BufferedReader s_in = null;
        String text="";
        StringBuffer stringBuffer= new StringBuffer();
        try {

            _socket = new Socket(host,80);
            s_out = new PrintWriter(_socket.getOutputStream(),true);
            s_in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));

            //Host not found
        } catch (IOException e) {
            //TODO pop up fenster
            e.printStackTrace();
        }
        String message = "GET " + getFile(host) + " HTTP/1.1\r\nHost: " + get_urlAdresse(host) + "\r\n"+ "Connention: close \r\n\r\n";
        s_out.println(message);

        //Get response from server
        String response;
        try {
            boolean print=false;
        while ((response = s_in.readLine()) != null) {
            if(print==true){
                stringBuffer.append(response+"\n");
            }
            if(response.equals("")){
                print=true;
            }

            System.out.println(response);
        }

        text=stringBuffer.toString();

            //close the i/o streams
            s_out.close();
            s_in.close();
            //close the socket
            _socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public String get_urlAdresse( String host) {
        char[] url= host.toCharArray();
        int i=0;
        StringBuffer sb= new StringBuffer();

        while( url[i]!='/'&& i<url.length-1){
          sb.append(url[i]);
          i++;
        }
        return  sb.toString();
    }


    private String getFile( String host) {
        char[] url = host.toCharArray();
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


    public static void main(String[] args) {
GuiBrowser guiBrowser=new GuiBrowser();


    }
}