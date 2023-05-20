package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class exit implements Initializable {
    @FXML
    private  Button closeButton;
    @FXML
    private AnchorPane rootpane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void Yes(ActionEvent event)throws IOException{
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    public void No(ActionEvent event)throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("secondPage.fxml"));
        Parent root=(Parent)loader.load();
        Scene scene=new Scene(root);
        Stage stage=(Stage)rootpane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("29 Cards");
        stage.show();
    }
}
