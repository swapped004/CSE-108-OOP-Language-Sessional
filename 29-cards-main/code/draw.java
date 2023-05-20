package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class draw implements Initializable {
    @FXML
    private Label Team1;
    @FXML
    private Label Team2;

    public draw()
    {

    }
    public void labelSetter()
    {
        Team1.setText(player1Controller.players[0]+" & "+player1Controller.players[2]);
        Team2.setText(player1Controller.players[1]+" & "+player1Controller.players[3]);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
