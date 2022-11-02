package server;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class InitConnection {
    ServerSocket socket = null;
    DataInputStream password = null;
    String width = "";
    String height = "";
    InitConnection(int port,String value1){
        Robot robot = null;
        Rectangle rectangle =null;
        try {
            System.out.println("Waiting for client to get connected");
            socket = new ServerSocket(port);
            GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gDev = gEnv.getDefaultScreenDevice();
            Dimension dim  = Toolkit.getDefaultToolkit().getScreenSize();
            String width = ""+dim.getWidth();
            String height = ""+dim.getHeight();
            rectangle = new Rectangle(dim);
            robot = new Robot(gDev);
            drawGUI();
            while (true){
                Socket sc = socket.accept();
                password = new DataInputStream(sc.getInputStream());
                DataOutputStream verify = new DataOutputStream(sc.getOutputStream());
                String psword = password.readUTF();
                if(psword.equals(value1)){
                    verify.writeUTF("Valid");
                    verify.writeUTF("width");
                    verify.writeUTF("height");
                    new SendScreen(sc,robot,rectangle);
                    new ReceiveEvents(sc,robot);
                }
                else{
                    verify.writeUTF("Invalid");
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void drawGUI() {
    }


}
