package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EndPage implements Initializable {

    @FXML
    private AnchorPane rootpane;
    @FXML
    private Label winner;
    @FXML
    private Label loser;
    public EndPage()
    {

    }
    public void LabelSetter()
    {
        if(player1Controller.winnerId==0)
        {
            winner.setText(player1Controller.players[0]+" & "+player1Controller.players[2]);
            loser.setText(player1Controller.players[1]+" & "+player1Controller.players[3]);
        }
        else
        {
            winner.setText(player1Controller.players[1]+" & "+player1Controller.players[3]);
            loser.setText(player1Controller.players[0]+" & "+player1Controller.players[2]);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void clickButton(ActionEvent event)
    {
        Stage stage=(Stage)rootpane.getScene().getWindow();
        stage.close();
    }
}
