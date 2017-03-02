import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Elisabeth on 01.03.2017.
 */
public class GuiBrowser extends JFrame {
    JTextArea textArea;
    GuiBrowser(){

        BorderLayout borderLayout= new BorderLayout();
        JButton buttonSearch=new JButton("Search");
        final JTextField textField=new JTextField();
        this.setLayout(borderLayout);

        textField.setColumns(35);
        JPanel panel = new JPanel();
        panel.add(new Label("URL: http://"));
        panel.add(textField);
        panel.add(buttonSearch);
        add(panel, BorderLayout.NORTH);

        JPanel panelTextfield= new JPanel();
        textArea= new JTextArea();
        textArea.setEditable(true);
        textArea.setColumns(40);
        textArea.setRows(25);
        panelTextfield.add(new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        add(panelTextfield,borderLayout.CENTER);

        buttonSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientSite clientSite=new ClientSite();
                System.out.println(textField.getText());
                String text= null;
                try {
                    text = clientSite.tryRequest(textField.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                    textArea.setText("There was a problem:\n" +
                            "Errors can occur for the following reasons:\n"+
                            "\t - if the IP address of the host could not be determined.\n" +
                            "\t- if an I/O error occurs when creating the socket.\n" +
                            "\t- if a security manager exists and its checkConnect method doesn't allow the operation.\n" +
                            "\t- if the port parameter is outside the specified range of valid port values, which is between 0 and 65535, inclusive.\n"+
                            "Please check your input parameters and your internet connection.\n");
                }catch (BadFormatException e2){
                    e2.printStackTrace();
                    textArea.setText("This website can not be displayed.\n" +
                            "This website does not contain an empty line after the head.\n"+
                            " Can not be shown in a coherent manner. Please contact your administrator.");
                }
                textArea.append(text);
            }
        });

        this.setSize(800,500);
        setVisible(true);
    }

}