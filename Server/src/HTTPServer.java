
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer extends Thread{

    private static HTTPServer _srv;

    private boolean _status;
    private int _port;

    private HTTPServer(){

        this._port = 8081;
        _status = true;
        System.out.println("starte Server auf Port 8080");
        this.start();
    }


    @Override
    public void run(){

        ServerSocket srv = null;
        Socket sock = null;
        HTTPWorkThread client;
        Thread clientThread;



        try {

            srv = new ServerSocket( _port );

            while( _status ){

                sock = srv.accept();

                System.out.println( "Accepted connection from " + sock.getInetAddress().getHostName()  + ", " +
                        sock.getInetAddress().getHostAddress()
                );


                client = new HTTPWorkThread( sock );
                clientThread = new Thread( client );
                clientThread.start();

            }

            System.out.println( "Server wird beendet" );



            srv.close();
//			sock.close();


        }catch( Exception e ){

            try{

                srv.close();

            }catch( Exception e1 ){}

            System.out.println( "HTTPServer1 " + e );
            System.exit( 1 );

        }
    }


    private void stopIt(){

        _status = false;
        _srv = null;
    }

    private void startIt(){

        _status = true;
        if( !this.isAlive() ) this.start();
    }



    public static void stopServer(){

        if( _srv != null )
            _srv.stopIt();
    }

    public static void startServer(){

        if( _srv == null ) _srv = new HTTPServer();

        _srv._status = true;
        _srv.startIt();

    }


}


