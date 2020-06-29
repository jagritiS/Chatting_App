package com.abyj.Chat;

import java.awt.*;
        import java.awt.event.*;

public class ChatDialog
{
    ChatDialog(){
        Frame f= new Frame();
        TextArea area=new TextArea("Welcome to group chat ");
        area.setBounds(10,30, 300,300);
        f.add(area);
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);
    }
    public static void main(String...args)
    {
        new ChatDialog();
    }
}