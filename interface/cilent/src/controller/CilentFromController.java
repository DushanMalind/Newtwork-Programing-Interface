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

public class CilentFromController {

    public TextField txtclient;
    @FXML
    private Button btnSend;

    @FXML
    private TextArea cilenttxtArea;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtType;


    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String message="";
    String replay="";
    public void  initialize(){
        new Thread(() -> {
            try {
                Socket socket=new Socket("localhost",3000);

                 dataInputStream=new DataInputStream(socket.getInputStream());
                 dataOutputStream=new DataOutputStream(socket.getOutputStream());


                while (!message.equalsIgnoreCase("Finish")){
                    message=dataInputStream.readUTF();
                    cilenttxtArea.appendText("\nserver:"+message);


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
        dataOutputStream.writeUTF(txtclient.getText().trim());
        dataOutputStream.flush();
    }

}
