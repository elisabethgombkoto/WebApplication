

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Beschreibungen der Header-Felder:
 * https://de.wikipedia.org/wiki/Liste_der_HTTP-Headerfelder#Antwort-Headerfelder
 *
 * Bsp. mein Apache bei HEAD-Methode
HTTP/1.1 200 OK
Date: Fri, 03 Mar 2017 08:43:44 GMT
Server: Apache/2.4.25 (Win32) PHP/7.1.1
Last-Modified: Sat, 28 Jan 2017 11:31:17 GMT
ETag: "4dcd-54725ebbd4c98"
Accept-Ranges: bytes
Content-Length: 19917
Connection: close
Content-Type: text/html
 *
 * Bsp. mein Apache bei GET-Methode - wenn kein Pfad angegeben wurde
 * (sondern nur 'apache2uebung.com' ohne Resource(Path).
 * Er gibt im Feld 'Location' an, wo die Startseite zu erfragen ist:
 *
HTTP/1.1 302 Found
Date: Sun, 05 Mar 2017 20:51:21 GMT
Server: Apache/2.4.25 (Win32) PHP/7.1.1
X-Powered-By: PHP/7.1.1
Location: http://apache2uebung.com/dashboard/
Content-Length: 0
Connection: close
Content-Type: text/html; charset=UTF-8
 *
 * Bsp. mein Apache bei GET-Methode - wenn der Pfad vollst�ndig angegeben wurde:
HTTP/1.1 200 OK
Date: Fri, 03 Mar 2017 08:53:00 GMT
Server: Apache/2.4.25 (Win32) PHP/7.1.1
Last-Modified: Sat, 28 Jan 2017 11:31:17 GMT
ETag: "4dcd-54725ebbd4c98"
Accept-Ranges: bytes
Content-Length: 19917
Connection: close
Content-Type: text/html
 */

public class HTTPHeader {

    static final String[][] mimetypes = {
            {"html", "text/html"},
            {"htm",  "text/html"},
            {"txt",  "text/plain"},
            {"gif",  "image/gif"},
            {"jpg",  "image/jpeg"},
            {"jpeg", "image/jpeg"},
            {"jnlp", "application/x-java-jnlp-file"}
    };


    static String get_200_OK( long contentlenght , String mime ){

        String mimetyp = null;
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
        String srvdate = format.format( date );

        for( int i = 0 ; i < mimetypes.length ; i++ ){

            if( mimetypes[ i ][ 0 ].equals( mime.toLowerCase() ) ) mimetyp = mimetypes[ i ][ 1 ];
        }


        return new String( "HTTP/1.1 200 OK\r\n" +
                "Date: " + srvdate + "\r\n" +
                "Content-Length: " + contentlenght + "\r\n" +
                "Connection: close\r\n" +
                "Content-type: " + mimetyp + "\r\n" +
                "\r\n" );
    }


    static String get_302_Found() {

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
        String srvdate = format.format( date );

        return new String( "HTTP/1.1 302 Found\r\n" +
                "Date: " + srvdate + "\r\n" +
                "Server: My Server 0.1 " + "\r\n" +
                "Location: http://localhost:8080/root/" + "\r\n" +
                "Content-Length: 0\r\n" +
                "Connection: close\r\n" +
                "Content-Type: text/html; charset=UTF-8\r\n\r\n" );
    }

    static String get_404_NotFound(){

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
        String srvdate = format.format( date );


        return new String( "HTTP/1.1 404 Not Found\r\n" +
                "Date: " + srvdate + "\r\n" +
                "Server: My Server 0.1 " + "\r\n" +
                "Content-Length: 228\r\n" +
                "Content-Type: text/html; charset=iso-8859-1\r\n" +
                "Connection: close\r\n\r\n" +

                "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\" " +
                "<html><head>" +
                "<title>400 Bad Request</title>" +
                "</head><body>" +
                "</head><body>" +
                "</head><body>" +
                "<h1>Bad Request</h1>" +
                "<p>Your browser sent a request that this server could not understand.<br />" +
                "</p>" +
                "</body></html>"

        );

    }



    //Test-Content - wird nicht benutzt
    static String get_Content(){


        return "<HTML><TITLE>Welcome to the stupidest site ever</TITLE>\n" +
                "<H1>............................SERVUS.....................................</H1>" +
                "<p>Heute haben wir heute, den heutigen Tag</p>" +
                "</HTML>";

    }

}
