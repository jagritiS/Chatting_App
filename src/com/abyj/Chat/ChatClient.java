package com.abyj.Chat;

import com.abyj.login.ConnectUsers;
import com.abyj.steg.SteganoMain;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.System.out;
import java.util.Base64;
import java.util.Random;

public class  ChatClient extends JFrame implements ActionListener{
    String uname;
    PrintWriter pw;
    BufferedReader br;
    JTextArea  taMessages;
    JTextField tfInput;
    JButton btnSend,btnExit,btnEncrypt,btnDecrypt,btnStegn,btnStDec,createGrp,joinGroup;
    Socket client;
    private String encryptedString,decryptedString;
    
    public ChatClient(String uname,String servername) throws Exception {
        super(uname);  // set title for frame
        this.uname = uname;
        client  = new Socket("192.168.137.32",6012);
        br = new BufferedReader( new InputStreamReader( client.getInputStream()) ) ;
        pw = new PrintWriter(client.getOutputStream(),true);
        pw.println(uname);  // send name to server
        int chk = ConnectUsers.checkUsers(uname);
        if(chk == 1){
            buildInterface();
            new MessagesThread().start();  // create thread to listen for messages

        }else{
            //TODO: show signup or error dialog

            String message = "NO USER FOUND PLEASE CHECK AGAIN ";
            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        }
         }

    public void buildInterface() {
        btnSend = new JButton("Send");
        btnExit = new JButton("Exit");
        btnEncrypt = new JButton("Enc");
        btnStegn = new JButton("Img");
        btnStDec = new JButton("ImgD");
        createGrp= new JButton("CGrp");
        joinGroup = new JButton("JGrp");
        btnDecrypt = new JButton("Dec");
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
        //bp.add(btnStegn);
        bp.add(btnDecrypt);
       // bp.add(btnStDec);
        bp.add(createGrp);
        bp.add(joinGroup);
        add(bp,"South");
        btnSend.addActionListener(this);
        btnExit.addActionListener(this);
        btnEncrypt.addActionListener(this);
        btnStegn.addActionListener(this);
        btnStDec.addActionListener(this);
        btnDecrypt.addActionListener(this);
        createGrp.addActionListener(this);
        joinGroup.addActionListener(this);
        setSize(900,300);
        setVisible(true);
        pack();
    }
    
    public void actionPerformed(ActionEvent evt) {
        String key = ""; //= rand_int1+""+rand_int2;
        String name ; //= key+uname;
        String message ; //="Key for this group is --> "+key;

        if ( evt.getSource() == btnStegn )
        {
            //TODO: image code here
            out.println("image entry");
            SteganoMain page = new SteganoMain();
            dispose();
            page.setVisible(true);
        }else if(evt.getSource() == btnStDec){
            //TODO: image decrypt code here
        }
        ///////////////////////////////////
        if ( evt.getSource() == createGrp )
        {
            //TODO: group create
            Random rand = new Random();

            // Generate random integers in range 0 to 999
            int rand_int1 = rand.nextInt(1000);
            int rand_int2 = rand.nextInt(1000);

            key = rand_int1+""+rand_int2;
            name = key+uname;
            message ="Key for this group is --> "+key;
            JOptionPane.showMessageDialog(new JFrame(), message, name,
                    JOptionPane.INFORMATION_MESSAGE);
            pw.println("The key for group "+name+" is "+key);
        }else if(evt.getSource() == joinGroup){
            //TODO: join group
           ChatDialog.main();
            //// buildInterface();
            //new MessagesThread().start();  // create thread to listen for messages


        }
        ////////////////////////////////////

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
     ChatClient.signIn();
    } // end of main
public static void signIn(){
    String name = JOptionPane.showInputDialog(null,"Enter your name :", "Username",
            JOptionPane.PLAIN_MESSAGE);
    String servername = "localhost";
    try {
        new ChatClient( name ,servername);
    } catch(Exception ex) {
        String message = "Technical Error ";
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                JOptionPane.ERROR_MESSAGE);
        out.println( "Error --> " + ex.getMessage());

    }
}
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