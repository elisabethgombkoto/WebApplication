
/*
 *
 * Die Klasse SourceObject bekommt vom HTTPWorkThread den gelesenen
 * Resource-Path.
 *
 * Sie �berpr�ft ob der Path existiert, erstellt den passenden Header
 * und gibt beides an den WorkThread zur�ck, der dann Header und Datei verschickt.
 *
 */

import java.io.File;
import java.io.IOException;


public class SourceObject {

    String _header;
    String _path;



    long _len;
    String _mimeType;

    SourceObject( String resource ) {

        if( resource.equals( "/" ) ) {

            _header = HTTPHeader.get_302_Found();
            _path = null;

        }
        else {
            _path = "Server/root" + resource;
        }


        System.out.println( "Suche Path: " + _path + "\n" );



        if( _path != null ){

            try{


                File start = new File( _path);

                System.out.println( "Datei vorhanden: " + start.exists() );


                _len = start.getTotalSpace();
                _mimeType = _path.substring( _path.lastIndexOf( '.' ) + 1 );

                System.out.println( "MimeType: " + _mimeType + "\nL�nge: " + _len );

                _header = HTTPHeader.get_200_OK( _len , _mimeType );


            }catch( NullPointerException e ){


                _header = HTTPHeader.get_404_NotFound();
                _path = null;

            }

        }

    }


    public String get_path() {
        return _path;
    }


    public String get_header() {
        return _header;
    }








    /*----------------Test-----------------*/
    public static void main(String[] args) throws IOException{


        SourceObject so = new SourceObject("/root/pages/page1.html");

        System.out.println( "Path: " + so._path + "\nHeader: " + so._header );

    }

}
