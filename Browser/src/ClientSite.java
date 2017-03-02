import javax.swing.text.BadLocationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Elisabeth on 28.02.2017.
 *
 * Zum startendiese Cla sse ausführen. Es enthält ein main.
 *In der eingabe Feld kann die Adresse
 * mit www
 * ohne www
 * aber immer nur ohne http:// erfolgen.
 *
 * Es können Adressen behandelt sein, wo head eine leerzeile folgt,
 * wenn diese konvencion nicht erfüllt wird bleibt das Fenster leer, wird BadFormatExeption geworfen
 *
 */
public class ClientSite {

    private Socket _socket;
    //private String _text;

    public ClientSite() {

    }

    public String tryRequest(String host) throws IOException, BadFormatException {
        PrintWriter s_out = null;
        BufferedReader s_in = null;
        String text="";
        StringBuffer stringBuffer= new StringBuffer();


            _socket = new Socket(host,80);
            s_out = new PrintWriter(_socket.getOutputStream(),true);
            s_in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));

            //Host not found

        String message = "GET " + getFile(host) + " HTTP/1.1\r\nHost: " + get_urlAdresse(host) + "\r\n"+ "Connention: close \r\n\r\n";
        s_out.println(message);

        //Get response from server
        String response;

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
        if((response=s_in.readLine())==null&&(stringBuffer.toString().equals(null))){
            throw new BadFormatException();
        }

        text=stringBuffer.toString();

            //close the i/o streams
            s_out.close();
            s_in.close();
            //close the socket
            _socket.close();

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


    public static void main(String[] args) {
        GuiBrowser guiBrowser=new GuiBrowser();
    }
}