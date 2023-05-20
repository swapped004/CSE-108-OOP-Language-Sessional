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

public class Controller implements Initializable {
    @FXML
    private AnchorPane rootpane;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void clickButtoncoverpage(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("secondPage.fxml"));
        Parent root=(Parent)loader.load();
        Scene scene=new Scene(root);
        Stage stage=(Stage)rootpane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("29 Cards");
        stage.show();
    }
    public void help(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("instructions.fxml"));
        Parent root=(Parent)loader.load();
        Scene scene=new Scene(root);
        Stage stage=(Stage)rootpane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("29 Cards");
        stage.show();
    }
    public void EXIT(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Exit2.fxml"));
        Parent root=(Parent)loader.load();
        Scene scene=new Scene(root);
        Stage stage=(Stage)rootpane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("29 Cards");
        stage.show();
    }
}
