import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                String text=clientSite.tryRequest(textField.getText());
                textArea.append(text);
            }
        });

        this.setSize(800,500);
        setVisible(true);
    }

}