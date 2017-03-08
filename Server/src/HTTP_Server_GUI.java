
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class HTTP_Server_GUI extends JFrame {

    private JPanel contentPane;
    private JButton btnStatus;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HTTP_Server_GUI frame = new HTTP_Server_GUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public HTTP_Server_GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblHttpServer = new JLabel("HTTP Server");
        panel.add(lblHttpServer);

        btnStatus = new JButton("Connect");
        btnStatus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {

                if( btnStatus.getText().equals( "Connect" ) ){

                    HTTPServer.startServer();
                    btnStatus.setText( "DisConnect" );
                } else if ( btnStatus.getText().equals( "DisConnect" ) ){

                    HTTPServer.stopServer();
                    btnStatus.setText( "Connect" );
                }
            }
        });
        panel.add(btnStatus);
    }

}
