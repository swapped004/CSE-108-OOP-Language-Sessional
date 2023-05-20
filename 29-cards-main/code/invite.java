package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class invite implements Initializable {
    @FXML
    private AnchorPane rootpane;
    @FXML
    private TextField textField;

    public static String username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void Join(ActionEvent event)throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("thirdpage2.fxml"));
        //thirdpage2Controller tp=loader.getController();
        //tp.setUsername(textField.getText());
        username=textField.getText();
        Parent root=(Parent)loader.load();
        Scene scene=new Scene(root);
        Stage stage=(Stage)rootpane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("29 Cards");
        stage.show();
    }
    public void GoBack(ActionEvent event)throws IOException {
        /*FXMLLoader loader=new FXMLLoader(getClass().getResource("secondPage.fxml"));
        Parent root=(Parent)loader.load();
        Scene scene=new Scene(root);
        Stage stage=(Stage)rootpane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("29 Cards");
        stage.show();
        */
        Stage stage=(Stage)rootpane.getScene().getWindow();
        stage.close();

    }

}
