package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class secondPageController implements Initializable {


    @FXML private AnchorPane rootpane;
    private MediaPlayer audioPlayer;
    private boolean paused;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File audioFile = new File("src\\song.mp3");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayer= new MediaPlayer(audio);
        paused=true;

    }


    public void clickButton2ndpage2(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("invite_page.fxml"));
        Parent root=(Parent)loader.load();
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("29 Cards");
        stage.show();
    }
    public void EXIT(ActionEvent event)throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Exit.fxml"));
        Parent root=(Parent)loader.load();
        Scene scene=new Scene(root);
        Stage stage=(Stage)rootpane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("29 Cards");
        stage.show();
        audioPlayer.setMute(true);
    }
    public void GoBack(ActionEvent event)throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root=(Parent)loader.load();
        Scene scene=new Scene(root);
        Stage stage=(Stage)rootpane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("29 Cards");
        stage.show();
        audioPlayer.setMute(true);
    }
    public void GameSoundOn(ActionEvent event){
        if(paused) {
            audioPlayer.play();
            paused=false;
        }

    }
    public void GameSoundOff(ActionEvent event){
        audioPlayer.pause();
        paused=true;
    }

}
