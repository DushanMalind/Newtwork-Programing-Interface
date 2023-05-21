package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFromController {

    @FXML
    private Button btnSend;

    @FXML
    private AnchorPane root;

    @FXML
    private TextArea servertxtArea;

    @FXML
    private TextField txtType;

    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    public void  initialize(){
        new Thread(() -> {
            try {
                ServerSocket serverSocket=new ServerSocket(3000);
                servertxtArea.appendText("Service start");
                Socket socket=serverSocket.accept();
                servertxtArea.appendText("\nService start");
                dataInputStream=new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                String message="";
                String replay="";

                while (!message.equalsIgnoreCase("Finish")){
                    message=dataInputStream.readUTF();
                    servertxtArea.appendText("\nCilent:"+message);
                    dataOutputStream.writeUTF(replay);
                }
                dataInputStream.close();
                dataOutputStream.close();

            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }


    @FXML
    void btnSendOnAction(ActionEvent event) throws IOException {
        dataOutputStream.writeUTF(txtType.getText().trim());
        dataOutputStream.flush();
    }


}
