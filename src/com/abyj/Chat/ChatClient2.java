package com.abyj.Chat;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.System.out;
import java.util.Base64;

public class  ChatClient2 extends JFrame implements ActionListener{
    String uname;
    PrintWriter pw;
    BufferedReader br;
    JTextArea  taMessages;
    JTextField tfInput;
    JButton btnSend,btnExit,btnEncrypt,btnDecrypt;
    Socket client;
    private String encryptedString,decryptedString;

    public ChatClient2(String uname,String servername) throws Exception {
        super(uname);  // set title for frame
        this.uname = uname;
        client  = new Socket("192.168.137.32",6012);
        br = new BufferedReader( new InputStreamReader( client.getInputStream()) ) ;
        pw = new PrintWriter(client.getOutputStream(),true);
        pw.println(uname);  // send name to server
        buildInterface();
        new MessagesThread().start();  // create thread to listen for messages
    }

    public void buildInterface() {
        btnSend = new JButton("Send");
        btnExit = new JButton("Exit");
        btnEncrypt = new JButton("Encrypt and Send");
        btnDecrypt = new JButton("Decrypt and Read");
        taMessages = new JTextArea();
        taMessages.setRows(10);
        taMessages.setColumns(50);
        taMessages.setEditable(false);
        tfInput  = new JTextField(50);
        JScrollPane sp = new JScrollPane(taMessages, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(sp,"Center");
        JPanel bp = new JPanel( new FlowLayout());
        bp.add(tfInput);
        bp.add(btnSend);
        bp.add(btnExit);
        bp.add(btnEncrypt);
        bp.add(btnDecrypt);
        add(bp,"South");
        btnSend.addActionListener(this);
        btnExit.addActionListener(this);
        btnEncrypt.addActionListener(this);
        btnDecrypt.addActionListener(this);
        setSize(500,300);
        setVisible(true);
        pack();
    }

    public void actionPerformed(ActionEvent evt) {


        if ( evt.getSource() == btnEncrypt )
        {
            encryptedString = getEncodedString(tfInput.getText());
            pw.println(encryptedString);
        }

        else if ( evt.getSource() == btnDecrypt )
        {
            String[] lines = taMessages.getText().split("\\n");
            int i= lines.length;
            String[] linen = lines[i-1].split(":");
            taMessages.append(linen[0]+":");
            decryptedString = getDecodeString(linen[1]);
            taMessages.append(decryptedString + "\n");


        }
        else if ( evt.getSource() == btnExit ) {
            pw.println("end");  // send end to server so that server knows about the termination
            System.exit(0);
        } else {
            // send message to server
            pw.println(tfInput.getText());
        }

    }

    public static void main(String ... args) {

        // take username from user
        String name = JOptionPane.showInputDialog(null,"Enter your name :", "Username",
                JOptionPane.PLAIN_MESSAGE);
        String servername = "localhost";
        try {
            new ChatClient2( name ,servername);
        } catch(Exception ex) {
            out.println( "Error --> " + ex.getMessage());
        }

    } // end of main

    private static String getEncodedString(String password)
    {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    private static String getDecodeString(String encryptedString)
    {
        return new String (Base64.getMimeDecoder().decode(encryptedString));
    }

    // inner class for Messages Thread
    class  MessagesThread extends Thread {
        public void run() {
            String line;
            try {
                while(true) {
                    line = br.readLine();
                    taMessages.append(line + "\n");
                } // end of while
            } catch(Exception ex) {}
        }
    }
} //  end of client