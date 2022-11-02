package client;

import javax.swing.*;
import java.net.Socket;

public class Start {
    static String port  = "4907";

    public static void main(String[] args) {
        String ip = JOptionPane.showInputDialog("Please enter server IP");
        new Start().initialize(ip, Integer.parseInt(port));
    }
    public static void initialize(String ip, int port){
        try {
            Socket sc = new Socket(ip, port);
            System.out.println("Connecting to the server");
            Authentication frame1 = new Authentication(sc);
            frame1.setSize(300,80);
            frame1.setLocation(500,300);
            frame1.setVisible(true);

        }
        catch (Exception e ){
            e.printStackTrace();
        }

    }
}
