package fileencryption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainPane extends JPanel {

    private static JFrame frame;
    MainRSAActivity rsaActivity = new MainRSAActivity();
//    FlowLayout flowLayout = new FlowLayout();
    public MainPane() {
        super(new GridLayout(1, 1));
//        final boolean pubkey = false;
//        final boolean privkey = false;
//        final boolean inputfile = false;
//        boolean outputfile = false;
        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon icon = createImageIcon("images/middle.gif");


        //RSA Panel
        JComponent panel1 = makeTextPanel("RSA Encryption");
        tabbedPane.addTab("RSA", icon, panel1,
                "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JLabel inputLabel = new JLabel("");
        JLabel outputLabel = new JLabel("");
        JLabel pubkeyLabel = new JLabel("");
        JLabel privkeyLabel = new JLabel("");


        //BROWSE INPUT FILE
        final JFileChooser inputFileDialog = new JFileChooser();
        JButton browseInputButton = new JButton("Browse Input File Directory");
        browseInputButton.setPreferredSize(new Dimension(20,20));
        browseInputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = inputFileDialog.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = inputFileDialog.getSelectedFile();
                    System.out.println(file.getPath());

                    inputLabel.setText("Input File Directory :"
                            + file.getPath());
                    rsaActivity.setInputdir(file.getPath());
//                    outputfile = true;
                }
                else{
                    System.out.println("Cancel");
                    inputLabel.setText("Open command cancelled by user." );
                }
            }
        });

        //BROWSE OUTPUT FILE
        final JFileChooser outputFileDialog = new JFileChooser();
//        outputFileDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        JButton browseOutputButton = new JButton("Browse Output File Directory");
        browseOutputButton.setPreferredSize(new Dimension(20,20));
        browseOutputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = outputFileDialog.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = outputFileDialog.getSelectedFile();
                    System.out.println(file.getPath());

                    outputLabel.setText("Output File Directory :"
                            + file.getPath());
                    rsaActivity.setOutputdir(file.getPath());
                }
                else{
                    System.out.println("Cancel");
                    outputLabel.setText("Open command cancelled by user." );
                }
            }
        });

        //BROWSE PUBKEY FILE
        final JFileChooser pubkeyDialog = new JFileChooser();
        JButton browsepubkeyButton = new JButton("Browse Public key Directory");
        browsepubkeyButton.setPreferredSize(new Dimension(20,20));
        browsepubkeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = pubkeyDialog.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = pubkeyDialog.getSelectedFile();
                    System.out.println(file.getPath());

                    pubkeyLabel.setText("Public key Directory :"
                            + file.getPath());
                    rsaActivity.setPubkeydir(file.getPath());
                }
                else{
                    System.out.println("Cancel");
                    pubkeyLabel.setText("Open command cancelled by user." );
                }
            }
        });

        //BROWSE Private FILE
        final JFileChooser privateDialog = new JFileChooser();
        JButton browseprivkeyButton = new JButton("Browse Private key Directory");
        browseprivkeyButton.setPreferredSize(new Dimension(20,20));
        browseprivkeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = privateDialog.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = privateDialog.getSelectedFile();
                    System.out.println(file.getPath());

                    privkeyLabel.setText("Private key Directory :"
                            + file.getPath());
                    rsaActivity.setPrivkeydir(file.getPath());
                }
                else{
                    System.out.println("Cancel");
                    privkeyLabel.setText("Open command cancelled by user." );
                }
            }
        });

        final JFileChooser newKeyDialog = new JFileChooser();
        JButton browseNewKeyButton = new JButton("Browse new key Directory");
        browseNewKeyButton.setPreferredSize(new Dimension(20,20));
        browseNewKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = newKeyDialog.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = newKeyDialog.getSelectedFile();
                    System.out.println(file.getPath());

                    privkeyLabel.setText("New Key Directory :"
                            + file.getPath());
                    rsaActivity.setPrivkeydir(file.getPath());
                }
                else{
                    System.out.println("Cancel");
                    privkeyLabel.setText("Open command cancelled by user." );
                }
            }
        });

        JButton rsaEncryptButton = new JButton("Encrypt");

        rsaEncryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rsaActivity.doEncryptRSA();
            }
        });


        JButton rsaDecryptButton = new JButton("Decrypt");

        rsaDecryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rsaActivity.doDecryptRSA();
            }
        });
        JButton rsaGenerateKeyButton = new JButton("Generate new key");
        rsaGenerateKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rsaActivity.doGenerateKey();
            }
        });

        panel1.add(browseInputButton);
        panel1.add(inputLabel);
        panel1.add(browseOutputButton);
        panel1.add(outputLabel);
        panel1.add(browsepubkeyButton);
        panel1.add(pubkeyLabel);
        panel1.add(browseprivkeyButton);
        panel1.add(privkeyLabel);
        panel1.add(rsaEncryptButton);
        panel1.add(rsaDecryptButton);
        panel1.add(rsaGenerateKeyButton);








        //AES Panel
        JComponent panel2 = makeTextPanel("AES Encryption");
        tabbedPane.addTab("AES", icon, panel2,
                "Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);


        //Blowfish Panel
        JComponent panel3 = makeTextPanel("Blowfish Encryption");
//        panel3.setPreferredSize(new Dimension(410, 410));
        tabbedPane.addTab("Blowfish", icon, panel3,
                "Still does nothing");
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

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MainPane.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
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