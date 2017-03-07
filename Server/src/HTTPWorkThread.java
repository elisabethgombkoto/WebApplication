
/*
 * Laut W3C k�nnen HTTP-Anfragen mit folgenden Methoden kommen:
 *
    GET
    POST
    HEAD
    PUT
    OPTIONS
    DELETE
    TRACE
    CONNECT

 *
 *
 * Bsp. Request-Header:
 * GET / HTTP/1.1
 * Host: www.elektronik-kompendium.de
 * User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; de; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)
 * Accept: text/html,application/xhtml+xml,application/xml;q=0.9,...
 * Accept-Language: de-de,de;q=0.8,en-us;q=0.5,en;q=0.3
 * Accept-Encoding: gzip,deflate
 * Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.7
 * Keep-Alive: 300
 * Connection: keep-alive
 *
 * Der Server behandelt nur GET Requests
 */

import java.io.*;
import java.net.Socket;



public class HTTPWorkThread implements Runnable {

    private Socket _socket;
    private String _method;
    private String _resource;
    private String _version;
    @SuppressWarnings("unused")
    private String[] _fields;


    HTTPWorkThread( Socket sock ){

        _socket = sock;
    }


    @Override
    public void run() {

        OutputStream _out = null;
        BufferedReader _in = null;

        try{

            _out = _socket.getOutputStream();
            _in = new BufferedReader ( new InputStreamReader ( _socket.getInputStream() ) );

            // Request-Line lesen zB: GET /page1.html HTTP/1.1
            String line = _in.readLine();
            parseRequestLine( line );

            StringBuffer sb = new StringBuffer();


            while( !( line = _in.readLine() ).equals("") ){

                sb.append( line + "\n" );
            }



            parseHeaderFields( sb.toString() );

            SourceObject response =  new SourceObject( _resource );

            byte[] buf = new byte[4096];

            int size;

            byte[] headout = response.get_header().getBytes();


            _out.write( headout );
            _out.flush();


            if( response.get_path() != null ){

                System.out.println( "Sende File: " + response.get_path() );
                try (FileInputStream in  = new FileInputStream(new File(response.get_path()))) {
                    int content;
                    while ((content = in.read()) != -1) {
                        _out.write(content);
                    }
                }

                try{



                    _out.flush();



                } catch( IOException e ) {

                    System.out.println( "File konnte nicht gelesen werden" );
                }
            }


            System.out.println( "\nSende Server-Header und Seite an Client:\n" + response.get_header() +
                    "\r\n\r\n" + "Datei: " + response.get_path() );

            _in.close();
            _out.close();
            _socket.close();

            System.out.println( "Workthread - Socket closed\n");


        } catch( Exception e ){

            e.printStackTrace();
            System.out.println( "HTTPWorkThread " + e );
        }

    }


    //Derzeit ungenutzt
    private void parseHeaderFields(String sb) {

        String[] lines = sb.split( "\n" );
        String[] fields = new String[ lines.length * 2 ];


        for( int i = 0 ; i < lines.length ; i++ ) {

            String line = lines[ i ];
            int cut = line.indexOf( ":" );
            int put = i+i;
            fields[ put ] = line.substring( 0 , cut );
            fields[ put+1 ] = line.substring( cut + 2 );

            _fields = fields;

        }


        for( int i = 1 ; i < fields.length ; i=i+2 ) {

            System.out.println( fields[ i-1 ] + ": " + fields[ i ]);

        }
    }



    private void parseRequestLine( String line ){

        System.out.println( "Parse Request Line: " + line );
        if( line == null )return;

        String[] tokens = line.split( " " );

        _method = tokens[ 0 ];
        _resource = tokens[ 1 ];
        _version = tokens[ 2 ];

        System.out.println( "\nRequest-Line gelesen: \n" + "Methode: " + _method +
                "\nRessource: " + _resource + "\nVersion: " + _version + "\n");

    }

}
