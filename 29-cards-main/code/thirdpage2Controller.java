package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class thirdpage2Controller implements Initializable {
        @FXML
        private AnchorPane rootpane;

        private String username;

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        @Override
        public void initialize(URL location, ResourceBundle resources) {

        }



        public void newdealbutton(ActionEvent event)throws IOException
        {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("player1.fxml"));
                //player1Controller p=loader.getController();
                //p.setUsername(username);
                Parent root=(Parent)loader.load();
                Scene scene=new Scene(root);
                Stage stage= (Stage) rootpane.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("29 Cards");
                stage.show();
        }
    }
