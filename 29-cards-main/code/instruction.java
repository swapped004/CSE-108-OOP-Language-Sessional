package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class instruction implements Initializable {
    @FXML
    AnchorPane rootpane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void goBack(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root=(Parent)loader.load();
        Scene scene=new Scene(root);
        Stage stage=(Stage)rootpane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("29 Cards");
        stage.show();
    }

}
