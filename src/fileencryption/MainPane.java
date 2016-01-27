package fileencryption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainPane extends JPanel {

    private static JFrame frame;
    MainRSAActivity rsaActivity = new MainRSAActivity();
    public MainPane() {
        super(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();
        JTextField textField;
        JTextField textField1;
        JTextField textField2;
        JTextField textField3;


        //RSA Panel
        JComponent panel1 = makeTextPanel("RSA Encryption");
        tabbedPane.addTab("RSA", panel1);

        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        textField = new JTextField("Input file path");
        textField1 = new JTextField("Output file path");
        textField2 = new JTextField("Public key path");
        textField3 = new JTextField("Private key path");





        JButton rsaEncryptButton = new JButton("Encrypt");

        rsaEncryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rsaActivity.setInputdir(textField.getText());
                rsaActivity.setOutputdir(textField1.getText());
                rsaActivity.setPubkeydir(textField2.getText());
                rsaActivity.doEncryptRSA();
            }
        });


        JButton rsaDecryptButton = new JButton("Decrypt");

        rsaDecryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rsaActivity.setInputdir(textField.getText());
                rsaActivity.setOutputdir(textField1.getText());
                rsaActivity.setPrivkeydir(textField3.getText());
                rsaActivity.doDecryptRSA();
            }
        });


        JButton rsaGenerateKeyButton = new JButton("Generate new key");
        rsaGenerateKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rsaActivity.setPubkeydir(textField2.getText());
                rsaActivity.setPrivkeydir(textField3.getText());
                rsaActivity.doGenerateKey();
            }
        });

        panel1.add(textField);
        panel1.add(textField1);
        panel1.add(textField2);
        panel1.add(textField3);
        panel1.add(rsaEncryptButton);
        panel1.add(rsaDecryptButton);
        panel1.add(rsaGenerateKeyButton);








        //AES Panel
        JComponent panel2 = makeTextPanel("AES Encryption");
        tabbedPane.addTab("AES", panel2);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);


        //Blowfish Panel
        JComponent panel3 = makeTextPanel("Blowfish Encryption");
//        panel3.setPreferredSize(new Dimension(410, 410));
        tabbedPane.addTab("Blowfish", panel3);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Encryption Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new MainPane(), BorderLayout.CENTER);
        frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });


    }
}