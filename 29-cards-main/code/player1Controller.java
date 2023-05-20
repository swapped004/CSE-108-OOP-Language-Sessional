package sample;

import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.rmi.UnknownHostException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import static java.lang.Thread.sleep;

public class player1Controller implements Initializable {

    ReadThread r;
    @FXML
    private AnchorPane rootpane;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label waitingLabel;

    @FXML
    public ImageView image1;
    @FXML
    public ImageView image2;
    @FXML
    public ImageView image3;
    @FXML
    public Label playerturnlabel;
    @FXML
    private ImageView imagecard1;
    @FXML
    private ImageView imagecard2;
    @FXML
    private ImageView imagecard3;
    @FXML
    private ImageView imagecard4;
    @FXML
    private ImageView imagecard5;
    @FXML
    private ImageView imagecard6;
    @FXML
    private ImageView imagecard7;
    @FXML
    private ImageView imagecard8;
    @FXML
    public ImageView cardplayer1imageview;
    @FXML
    public ImageView cardplayer2imageview;
    @FXML
    public ImageView cardplayer3imageview;
    @FXML
    public ImageView cardplayer4imageview;
    @FXML
    public Label winlabel;
    @FXML
    public Label scoreLabel1;
    @FXML
    public Label scoreLabel2;
    @FXML
    public Label user1;
    @FXML
    public Label user2;
    @FXML
    public Label user3;
    @FXML
    public Label score;

    private MediaPlayer audioPlayer;
    private MediaPlayer victory;
    private MediaPlayer lose;
    private MediaPlayer nonono;
    private boolean paused;
    public static int winnerId;
    public static int winnerScore;
    public static int loserscore;
    private String allusername;
    public player[] gameplayer;
    public cards card;
    public int playerturn;
    public cards givencard;
    private int givenplayer;
    private String valid_card;
    public static String[] players=new String[4];
    private Socket clientSocket;
    private BufferedReader inFromseverid;
    private ObjectInputStream inFromserver;
    private ObjectOutputStream outToserver;
    private String username;
    private int id;
    private String available_suit;
    private String[] available_suitAll;
    private boolean im1,im2,im3,im4,im5,im6,im7,im8,animation_on;

    public String getAllusername() {
        return allusername;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPlayerturn() {
        return playerturn;
    }

    public void setPlayerturn(int playerturn) {
        this.playerturn = playerturn;
    }

    public String getValid_card() {
        return valid_card;
    }

    public void setValid_card(String valid_card) {
        this.valid_card = valid_card;
    }

    public void setWinnerID(int a)
    {
        winnerId=a;
    }

    public boolean isValid(int n) {
        int flag = 0;
        cards card = new cards(gameplayer[id].hand[n]);
        String suit = String.valueOf(card.suit);
        System.out.println("current suit: "+suit);
        String[] valid_suit;
        int length=0;
        int count=0;
        String s=new String();
        int []ara=new int[4];


        if(valid_card.equals("")||valid_card==null||valid_card.equals(" "))
            return true;

        else {
            valid_suit = valid_card.split(" ");
            System.out.println("From isValid:");
            System.out.println("valid_card" + valid_card);
            for (int i = 0; i < valid_suit.length; i++) {
                System.out.println("valid card[" + i + "]= " + valid_suit[i]);
                length = valid_suit.length;
                System.out.println("length: "+length);
            }
            for(int i=0;i<available_suitAll.length;i++)
            {
                System.out.println("available suit["+i+"]= "+available_suitAll[i]);
            }
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < 8; j++) {
                    if (available_suitAll[j].equals(valid_suit[0])) {
                        flag=1;
                        //count++;
                        s+=available_suitAll[j];
                        s+=" ";
                        if(available_suitAll[j].equals("clubs"))
                            ara[0]++;
                        else if(available_suitAll[j].equals("spades"))
                            ara[1]++;
                        else if(available_suitAll[j].equals("diamonds"))
                            ara[2]++;
                        else if(available_suitAll[j].equals("hearts"))
                            ara[3]++;
                    }
                }
            }
            String[] match=s.split(" ");
            System.out.println("all="+s);
            for (int i=0;i<match.length;i++)
            {
                System.out.println("match["+i+"]= "+match[i]);
            }
            for(int i=0;i<4;i++)
            {
                if(ara[i]!=0)
                {
                    count++;
                }
            }

            /*if (flag == 1) {
                if(count>1) {
                    for (int i = 0; i < length; i++) {
                        if(suit.equals(valid_suit[i])&&suit.equals(match[0]))
                        {
                            return true;
                        }
                        else
                            return false;
                    }
                }

                else {
                    for (int i = 0; i < length; i++) {

                        if (suit.equals(valid_suit[i])) {
                            return true;
                        }
                    }
                }
                return false;
            }*/
            if(flag==1)
            {
                if(suit.equals(valid_suit[0]))
                {
                    return true;
                }
                else
                    return false;
            }
            else
                return true;
        }
    }

    public void removebar()
    {
        rootpane.getChildren().removeAll(progressBar,waitingLabel);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            animation_on=false;
            gameplayer = new player[4];
            for(int i=0;i<4;i++)
            {
                gameplayer[i]=new player();
            }
            clientSocket = new Socket("localhost", 6789);
            r=new ReadThread(clientSocket,this);
            Thread t=new Thread(r);
            t.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public player1Controller()
    {

    }

    public void setplayer1Controller(game g)
    {

        playerturn=0;//0-3
        givenplayer=-1;
        this.gameplayer=g.gameplayer;
        imageshower(id);
    }

    public void distributecards(int[] cardsondeck) {

        for(int i=0;i<4;i++)
        {
            for(int j=0;j<8;j++) {
                gameplayer[i].hand[j] = cardsondeck[8*i +j];
            }
        }
    }
    public void coordinates(MouseEvent event)
    {
        System.out.println("(x,y)=("+event.getX()+","+event.getY()+")");
    }

    public void truer()
    {
        im1=true;
        im2=true;
        im3=true;
        im4=true;
        im5=true;
        im6=true;
        im7=true;
        im8=true;
    }

    public void dragcard1(MouseEvent event)throws IOException
    {
        if(playerturn==id&&isValid(0)&&!im1&&!animation_on) {
            animation_on=true;
            File audioFile = new File("src\\soundOfCard.mp3");
            Media audio = new Media(audioFile.toURI().toString());
            audioPlayer= new MediaPlayer(audio);
            CardGivingSound();
            //truer();
            card = new cards(gameplayer[id].hand[0]);
            cardplayer1imageview.setImage(card.getImage());
            imagecard1.setImage(null);
            Path path = new Path();
            path.getElements().add(new MoveTo(-155,286));
            path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 57, 84));
            //path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 0, 0));

            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.millis(2000));
            pathTransition.setPath(path);
            pathTransition.setNode(cardplayer1imageview);
            //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition.setCycleCount(1);
            pathTransition.setAutoReverse(true);
            pathTransition.play();

            pathTransition.setOnFinished(e->{
                animation_on=false;
            });
            available_suitAll[0]="";
            String Id_hand = new String(String.valueOf(id));
            Id_hand = Id_hand + " " + (gameplayer[id].hand[0]);
            PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            outToServer.println(Id_hand);
        }
        else
        {
            File audioFileNo = new File("src\\nonono.mp3");
            Media audioNo = new Media(audioFileNo.toURI().toString());
            nonono= new MediaPlayer(audioNo);
            nonono.play();
        }
    }


    public void dragcard2(MouseEvent event) throws IOException
    {
        if(playerturn==id&&isValid(1)&&!im2&&!animation_on) {
            animation_on=true;
            File audioFile = new File("src\\soundOfCard.mp3");
            Media audio = new Media(audioFile.toURI().toString());
            audioPlayer= new MediaPlayer(audio);
            CardGivingSound();
            im2=true;
            card = new cards(gameplayer[id].hand[1]);
            cardplayer1imageview.setImage(card.getImage());
            imagecard2.setImage(null);
            Path path = new Path();
            path.getElements().add(new MoveTo(-68,200));
            path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 57, 84));

            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.millis(2000));
            pathTransition.setPath(path);
            pathTransition.setNode(cardplayer1imageview);
            //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition.setCycleCount(1);
            pathTransition.setAutoReverse(true);
            pathTransition.play();

            pathTransition.setOnFinished(e->{
                animation_on=false;
            });

            available_suitAll[1]="";
            String Id_hand = new String(String.valueOf(id));
            Id_hand = Id_hand + " " + (gameplayer[id].hand[1]);
            PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            outToServer.println(Id_hand);
        }
        else
        {
            File audioFileNo = new File("src\\nonono.mp3");
            Media audioNo = new Media(audioFileNo.toURI().toString());
            nonono= new MediaPlayer(audioNo);
            nonono.play();
        }
    }

    public void dragcard3(MouseEvent event)throws IOException
    {
        if(playerturn==id&&isValid(2)&&!im3&&!animation_on) {
            animation_on=true;
            File audioFile = new File("src\\soundOfCard.mp3");
            Media audio = new Media(audioFile.toURI().toString());
            audioPlayer= new MediaPlayer(audio);
            CardGivingSound();
            im3=true;
            card = new cards(gameplayer[id].hand[2]);
            cardplayer1imageview.setImage(card.getImage());
            imagecard3.setImage(null);
            Path path = new Path();
            path.getElements().add(new MoveTo(147,200));
            path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 57, 84));

            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.millis(2000));
            pathTransition.setPath(path);
            pathTransition.setNode(cardplayer1imageview);
            //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition.setCycleCount(1);
            pathTransition.setAutoReverse(true);
            pathTransition.play();

            pathTransition.setOnFinished(e->{
                animation_on=false;
            });

            available_suitAll[2]="";
            String Id_hand = new String(String.valueOf(id));
            Id_hand = Id_hand + " " + (gameplayer[id].hand[2]);
            PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            outToServer.println(Id_hand);
        }
        else
        {
            File audioFileNo = new File("src\\nonono.mp3");
            Media audioNo = new Media(audioFileNo.toURI().toString());
            nonono= new MediaPlayer(audioNo);
            nonono.play();
        }
    }

    public void dragcard4(MouseEvent event)throws IOException
    {
        if(playerturn==id&&isValid(3)&&!im4&&!animation_on) {
            animation_on=true;
            File audioFile = new File("src\\soundOfCard.mp3");
            Media audio = new Media(audioFile.toURI().toString());
            audioPlayer= new MediaPlayer(audio);
            CardGivingSound();
            im4=true;
            card = new cards(gameplayer[id].hand[3]);
            cardplayer1imageview.setImage(card.getImage());
            imagecard4.setImage(null);
            Path path = new Path();
            path.getElements().add(new MoveTo(216,200));
            path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 57, 84));

            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.millis(2000));
            pathTransition.setPath(path);
            pathTransition.setNode(cardplayer1imageview);
            //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition.setCycleCount(1);
            pathTransition.setAutoReverse(true);
            pathTransition.play();

            pathTransition.setOnFinished(e->{
                animation_on=false;
            });

            available_suitAll[3]="";
            String Id_hand = new String(String.valueOf(id));
            Id_hand = Id_hand + " " + (gameplayer[id].hand[3]);
            PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            outToServer.println(Id_hand);
        }
        else
        {
            File audioFileNo = new File("src\\nonono.mp3");
            Media audioNo = new Media(audioFileNo.toURI().toString());
            nonono= new MediaPlayer(audioNo);
            nonono.play();
        }
    }

    public void dragcard5(MouseEvent event)throws IOException
    {
        if(playerturn==id&&isValid(4)&&!im5&&!animation_on) {
            animation_on=true;
            File audioFile = new File("src\\soundOfCard.mp3");
            Media audio = new Media(audioFile.toURI().toString());
            audioPlayer= new MediaPlayer(audio);
            CardGivingSound();
            im5=true;
            card = new cards(gameplayer[id].hand[4]);
            cardplayer1imageview.setImage(card.getImage());
            imagecard5.setImage(null);
            Path path = new Path();
            path.getElements().add(new MoveTo(-496,200));
            path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 57, 84));

            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.millis(2000));
            pathTransition.setPath(path);
            pathTransition.setNode(cardplayer1imageview);
            //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition.setCycleCount(1);
            pathTransition.setAutoReverse(true);
            pathTransition.play();

            pathTransition.setOnFinished(e->{
                animation_on=false;
            });

            available_suitAll[4]="";
            String Id_hand = new String(String.valueOf(id));
            Id_hand = Id_hand + " " + (gameplayer[id].hand[4]);
            PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            outToServer.println(Id_hand);
        }
        else
        {
            File audioFileNo = new File("src\\nonono.mp3");
            Media audioNo = new Media(audioFileNo.toURI().toString());
            nonono= new MediaPlayer(audioNo);
            nonono.play();
        }
    }

    public void dragcard6(MouseEvent event)throws IOException
    {
        if(playerturn==id&&isValid(5)&&!im6&&!animation_on) {
            animation_on=true;
            File audioFile = new File("src\\soundOfCard.mp3");
            Media audio = new Media(audioFile.toURI().toString());
            audioPlayer= new MediaPlayer(audio);
            CardGivingSound();
            im6=true;
            card = new cards(gameplayer[id].hand[5]);
            cardplayer1imageview.setImage(card.getImage());
            imagecard6.setImage(null);
            Path path = new Path();
            path.getElements().add(new MoveTo(-354,200));
            path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 57, 84));

            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.millis(2000));
            pathTransition.setPath(path);
            pathTransition.setNode(cardplayer1imageview);
            //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition.setCycleCount(1);
            pathTransition.setAutoReverse(true);
            pathTransition.play();

            pathTransition.setOnFinished(e->{
                animation_on=false;
            });

            available_suitAll[5]="";
            String Id_hand = new String(String.valueOf(id));
            Id_hand = Id_hand + " " + (gameplayer[id].hand[5]);
            PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            outToServer.println(Id_hand);
        }
        else
        {
            File audioFileNo = new File("src\\nonono.mp3");
            Media audioNo = new Media(audioFileNo.toURI().toString());
            nonono= new MediaPlayer(audioNo);
            nonono.play();
        }
    }

    public void dragcard7(MouseEvent event)throws IOException
    {
        if(playerturn==id&&isValid(6)&&!im7&&!animation_on) {
            animation_on=true;
            File audioFile = new File("src\\soundOfCard.mp3");
            Media audio = new Media(audioFile.toURI().toString());
            audioPlayer= new MediaPlayer(audio);
            CardGivingSound();
            im7=true;
            card = new cards(gameplayer[id].hand[6]);
            cardplayer1imageview.setImage(card.getImage());
            imagecard7.setImage(null);
            Path path = new Path();
            path.getElements().add(new MoveTo(357,200));
            path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 57, 84));

            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.millis(2000));
            pathTransition.setPath(path);
            pathTransition.setNode(cardplayer1imageview);
            //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition.setCycleCount(1);
            pathTransition.setAutoReverse(true);
            pathTransition.play();

            pathTransition.setOnFinished(e->{
                animation_on=false;
            });


            available_suitAll[6]="";
            String Id_hand = new String(String.valueOf(id));
            Id_hand = Id_hand + " " + (gameplayer[id].hand[6]);
            PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            outToServer.println(Id_hand);
        }
        else
        {
            File audioFileNo = new File("src\\nonono.mp3");
            Media audioNo = new Media(audioFileNo.toURI().toString());
            nonono= new MediaPlayer(audioNo);
            nonono.play();
        }
    }

    public void dragcard8(MouseEvent event)throws IOException
    {
        if(playerturn==id&&isValid(7)&&!im8&&!animation_on) {
            animation_on=true;
            File audioFile = new File("src\\soundOfCard.mp3");
            Media audio = new Media(audioFile.toURI().toString());
            audioPlayer= new MediaPlayer(audio);
            CardGivingSound();
            im8=true;
            card = new cards(gameplayer[id].hand[7]);
            cardplayer1imageview.setImage(card.getImage());
            imagecard8.setImage(null);
            Path path = new Path();
            path.getElements().add(new MoveTo(492,200));
            path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 57, 84));

            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.millis(2000));
            pathTransition.setPath(path);
            pathTransition.setNode(cardplayer1imageview);
            //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition.setCycleCount(1);
            pathTransition.setAutoReverse(true);
            pathTransition.play();

            pathTransition.setOnFinished(e->{
                animation_on=false;
            });

            available_suitAll[7]="";
            String Id_hand = new String(String.valueOf(id));
            Id_hand = Id_hand + " " + (gameplayer[id].hand[7]);
            PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            outToServer.println(Id_hand);
        }
        else
        {
            File audioFileNo = new File("src\\nonono.mp3");
            Media audioNo = new Media(audioFileNo.toURI().toString());
            nonono= new MediaPlayer(audioNo);
            nonono.play();
        }
    }

    public void setgivencard(cards c,int givenplayer)
    {
        givencard=c;
        this.givenplayer=givenplayer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setopponentcard()
    {
        if(givenplayer==1)
        {
            cardplayer2imageview.setImage(givencard.getImage());
        }
        else if(givenplayer==2)
        {
            cardplayer3imageview.setImage(givencard.getImage());
        }
        else if(givenplayer==3)
        {
            cardplayer4imageview.setImage(givencard.getImage());
        }
    }


    public void removeallgivencards()
    {

        this.cardplayer1imageview.setImage(null);
        this.cardplayer2imageview.setImage(null);
        this.cardplayer3imageview.setImage(null);
        this.cardplayer4imageview.setImage(null);
    }


    public void imageshower(int n)
    {
        card = new cards(gameplayer[n].hand[0]);
        imagecard1.setImage(card.getImage());

        System.out.println(card.cardname + " of " + card.suit + ".jpg");


        card = new cards(gameplayer[n].hand[1]);
        imagecard2.setImage(card.getImage());

        System.out.println(card.cardname + " of " + card.suit + ".jpg");

        card = new cards(gameplayer[n].hand[2]);
        imagecard3.setImage(card.getImage());

        System.out.println(card.cardname + " of " + card.suit + ".png");

        card = new cards(gameplayer[n].hand[3]);
        imagecard4.setImage(card.getImage());

        System.out.println(card.cardname + " of " + card.suit + ".png");

        card = new cards(gameplayer[n].hand[4]);
        imagecard5.setImage(card.getImage());

        System.out.println(card.cardname + " of " + card.suit + ".jpg");


        card = new cards(gameplayer[n].hand[5]);
        imagecard6.setImage(card.getImage());

        System.out.println(card.cardname + " of " + card.suit + ".jpg");

        card = new cards(gameplayer[n].hand[6]);
        imagecard7.setImage(card.getImage());

        System.out.println(card.cardname + " of " + card.suit + ".png");

        card = new cards(gameplayer[n].hand[7]);
        imagecard8.setImage(card.getImage());

        System.out.println(card.cardname + " of " + card.suit + ".png");

        String temp=new String();

        System.out.println("From imageshower: ");
        for(int i=0;i<8;i++)
        {
            temp+=(new cards(gameplayer[id].hand[i])).suit;
            temp+=" ";
        }

        available_suit=temp;

        available_suitAll=temp.split(" ");
        System.out.println("available_suit: "+available_suit);
        for(int i=0;i<available_suitAll.length;i++)
        {
            System.out.println("available_suitAll["+i+"]= "+available_suitAll[i]);
        }

    }
    public void opponentcardsetter(int idop,int cardhand)
    {
        cards card=new cards(cardhand);
        if(id==0) {
            if (idop == 1) {
                cardplayer2imageview.setImage(card.getImage());
                Path path = new Path();
                path.getElements().add(new MoveTo(396,127));
                path.getElements().add(new LineTo(57,84));
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.millis(2000));
                pathTransition.setPath(path);
                pathTransition.setNode(cardplayer2imageview);
                //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition.setCycleCount(1);
                pathTransition.setAutoReverse(true);
                pathTransition.play();
            } else if (idop == 2) {
                cardplayer3imageview.setImage(card.getImage());
                Path path = new Path();
                path.getElements().add(new MoveTo(6,-184));
                path.getElements().add(new LineTo(57,84));
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.millis(2000));
                pathTransition.setPath(path);
                pathTransition.setNode(cardplayer3imageview);
                //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition.setCycleCount(1);
                pathTransition.setAutoReverse(true);
                pathTransition.play();
            } else if (idop == 3) {
                cardplayer4imageview.setImage(card.getImage());
                Path path = new Path();
                path.getElements().add(new MoveTo(-403,146));
                path.getElements().add(new LineTo(57,84));
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.millis(2000));
                pathTransition.setPath(path);
                pathTransition.setNode(cardplayer4imageview);
                //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition.setCycleCount(1);
                pathTransition.setAutoReverse(true);
                pathTransition.play();
            }
        }
        else if(id==1){
            if(idop==0)
            {
                cardplayer4imageview.setImage(card.getImage());
                Path path = new Path();
                path.getElements().add(new MoveTo(-403,146));
                path.getElements().add(new LineTo(57,84));
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.millis(2000));
                pathTransition.setPath(path);
                pathTransition.setNode(cardplayer4imageview);
                //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition.setCycleCount(1);
                pathTransition.setAutoReverse(true);
                pathTransition.play();
            }
            else if(idop==2)
            {
                cardplayer2imageview.setImage(card.getImage());
                Path path = new Path();
                path.getElements().add(new MoveTo(396,127));
                path.getElements().add(new LineTo(57,84));
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.millis(2000));
                pathTransition.setPath(path);
                pathTransition.setNode(cardplayer2imageview);
                //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition.setCycleCount(1);
                pathTransition.setAutoReverse(true);
                pathTransition.play();
            }
            else if(idop==3)
            {
                cardplayer3imageview.setImage(card.getImage());
                Path path = new Path();
                path.getElements().add(new MoveTo(6,-184));
                path.getElements().add(new LineTo(57,84));
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.millis(2000));
                pathTransition.setPath(path);
                pathTransition.setNode(cardplayer3imageview);
                //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition.setCycleCount(1);
                pathTransition.setAutoReverse(true);
                pathTransition.play();
            }
        }
        else if(id==2)
        {
            if(idop==0)
            {
                cardplayer3imageview.setImage(card.getImage());
                Path path = new Path();
                path.getElements().add(new MoveTo(6,-184));
                path.getElements().add(new LineTo(57,84));
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.millis(2000));
                pathTransition.setPath(path);
                pathTransition.setNode(cardplayer3imageview);
                //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition.setCycleCount(1);
                pathTransition.setAutoReverse(true);
                pathTransition.play();
            }
            else if(idop==1)
            {
                cardplayer4imageview.setImage(card.getImage());
                Path path = new Path();
                path.getElements().add(new MoveTo(-403,146));
                path.getElements().add(new LineTo(57,84));
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.millis(2000));
                pathTransition.setPath(path);
                pathTransition.setNode(cardplayer4imageview);
                //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition.setCycleCount(1);
                pathTransition.setAutoReverse(true);
                pathTransition.play();
            }
            else if(idop==3)
            {
                cardplayer2imageview.setImage(card.getImage());
                Path path = new Path();
                path.getElements().add(new MoveTo(396,127));
                path.getElements().add(new LineTo(57,84));
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.millis(2000));
                pathTransition.setPath(path);
                pathTransition.setNode(cardplayer2imageview);
                //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition.setCycleCount(1);
                pathTransition.setAutoReverse(true);
                pathTransition.play();
            }
        }
        else if(id==3)
        {
            if(idop==0)
            {
                cardplayer2imageview.setImage(card.getImage());
                Path path = new Path();
                path.getElements().add(new MoveTo(396,127));
                path.getElements().add(new LineTo(57,84));
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.millis(2000));
                pathTransition.setPath(path);
                pathTransition.setNode(cardplayer2imageview);
                //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition.setCycleCount(1);
                pathTransition.setAutoReverse(true);
                pathTransition.play();
            }
            else if(idop==1)
            {
                cardplayer3imageview.setImage(card.getImage());
                Path path = new Path();
                path.getElements().add(new MoveTo(6,-184));
                path.getElements().add(new LineTo(57,84));
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.millis(2000));
                pathTransition.setPath(path);
                pathTransition.setNode(cardplayer3imageview);
                //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition.setCycleCount(1);
                pathTransition.setAutoReverse(true);
                pathTransition.play();
            }
            else if(idop==2)
            {
                cardplayer4imageview.setImage(card.getImage());
                Path path = new Path();
                path.getElements().add(new MoveTo(-403,146));
                path.getElements().add(new LineTo(57,84));
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.millis(2000));
                pathTransition.setPath(path);
                pathTransition.setNode(cardplayer4imageview);
                //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition.setCycleCount(1);
                pathTransition.setAutoReverse(true);
                pathTransition.play();
            }
        }

    }

    public void WinLabel()
    {
        if(winnerId==id)
        {
            winlabel.setText("You Win The Round");
        }
        else {
            winlabel.setText(players[winnerId]+" Wins The Round");
        }
    }

    public void ScoreLabel(int scr1,int scr2)
    {
        score.setText("SCORE:");
        if(scr1>scr2)
        {
            winnerId=0;
            winnerScore=scr1;
            loserscore=scr2;
        }
        else if(scr1<scr2)
        {
            winnerId=1;
            winnerScore=scr2;
            loserscore=scr1;
        }
        else {
            winnerId=5;
        }
        if(id==0||id==2){
            scoreLabel1.setText("Our Points: "+String.valueOf(scr1));
            scoreLabel2.setText("Their Points: "+String.valueOf(scr2));
        }
        else{
            scoreLabel2.setText("Their Points: "+String.valueOf(scr1));
            scoreLabel1.setText("Our Points: "+String.valueOf(scr2));
        }
    }

    public void RemoveTextfromWinLabel()
    {
        winlabel.setText(null);
    }

    public void func(ImageView im)
    {
        Polygon hexagon = new Polygon();

        //Adding coordinates to the hexagon
        hexagon.getPoints().addAll(new Double[]{
                200.0, 50.0,
                400.0, 50.0,
                450.0, 150.0,
                400.0, 250.0,
                200.0, 250.0,
                150.0, 150.0,
        });
        //Setting the fill color for the hexagon
        hexagon.setFill(Color.BLUE);

        //Creating a rotate transition
        RotateTransition rotateTransition = new RotateTransition();

        //Setting the duration for the transition
        rotateTransition.setDuration(Duration.millis(1000));

        //Setting the node for the transition
        rotateTransition.setNode(hexagon);

        //Setting the angle of the rotation
        rotateTransition.setByAngle(360);

        //Setting the cycle count for the transition
        rotateTransition.setCycleCount(50);

        //Setting auto reverse value to false
        rotateTransition.setAutoReverse(false);

        //Playing the animation


        rotateTransition.play();
    }


    public void PlayerNameLabelSetter(String user)
    {
        int i=0;
        StringTokenizer tokenizer=new StringTokenizer(user," ");
        while (tokenizer.hasMoreTokens()){
            players[i]=tokenizer.nextToken();
            System.out.println(players[i]);
            i++;
        }
        if(id==0){
            user1.setText(players[1]);
            user2.setText(players[2]);
            user3.setText(players[3]);
        }
        else if(id==1){
            user1.setText(players[2]);
            user2.setText(players[3]);
            user3.setText(players[0]);
        }
        else if(id==2){
            user1.setText(players[3]);
            user2.setText(players[0]);
            user3.setText(players[1]);
        }
        else if(id==3){
            user1.setText(players[0]);
            user2.setText(players[1]);
            user3.setText(players[2]);
        }
    }

    public void playerturnlabelsetter(int turn)
    {
        if(id==turn)
        {
            playerturnlabel.setText("YOUR TURN!");
        }
        else {
            playerturnlabel.setText(players[turn] + "'s turn");
        }
    }
    public void playerTurnRemove()
    {
        playerturnlabel.setText(null);
    }

    public void ImageSetterOfPlayers(int winner)
    {
        Image img1=new Image("sadFace.jpg");
        Image img2=new Image("happyFace.png");
        if(winner==0||winner==2)
        {
            if(id==0||id==2)
            {
                image1.setImage(img1);
                image2.setImage(img2);
                image3.setImage(img1);
            }
            else
            {
                image1.setImage(img2);
                image2.setImage(img1);
                image3.setImage(img2);
            }
        }
        else
        {
            if(id==0||id==2)
            {
                image1.setImage(img2);
                image2.setImage(img1);
                image3.setImage(img2);
            }
            else {
                image1.setImage(img1);
                image2.setImage(img2);
                image3.setImage(img1);
            }
        }
    }

    public void removeFace()
    {
        Image imageM1=new Image("Draw-a-Cute-Cartoon-Person-Step-14.jpg");
        Image imageM2=new Image("20-204683_smiling-face-of-a-child-face-boy-cartoon.png");
        Image imageM3=new Image("images.jpg");

        image1.setImage(imageM3);
        image2.setImage(imageM2);
        image3.setImage(imageM1);
    }

    public void endGame()
    {
        System.out.println("Winner's ID: "+winnerId);
        try{
            if(winnerId==0||winnerId==1){
                FXMLLoader loader=new FXMLLoader(getClass().getResource("EndPage.fxml"));
                Parent root=(Parent)loader.load();
                EndPage endPage=loader.getController();
                endPage.LabelSetter();
                Scene scene=new Scene(root);
                Stage stage=(Stage)rootpane.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("29 Cards");
                stage.show();
            }
            else{
                FXMLLoader loader=new FXMLLoader(getClass().getResource("drawMatch.fxml"));
                Parent root=(Parent)loader.load();
                draw dr=loader.getController();
                dr.labelSetter();
                Scene scene=new Scene(root);
                Stage stage=(Stage)rootpane.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("29 Cards");
                stage.show();
            }
        }catch (Exception e)
        {
                e.printStackTrace();
        }
    }

    @FXML
    public void displayPosition(MouseEvent event)
    {

    }
    public void CardGivingSound()
    {
        audioPlayer.play();
    }
    public void victoryOrloseSound()
    {
        File audioFileV = new File("src\\celebration.mp3");
        Media audioV = new Media(audioFileV.toURI().toString());
        victory= new MediaPlayer(audioV);
        File audioFileL = new File("src\\loser.mp3");
        Media audioL = new Media(audioFileL.toURI().toString());
        lose= new MediaPlayer(audioL);
        if(winnerId==0||winnerId==2){
            if(id==0||id==2){
                victory.play();
            }
            else {
                lose.play();
            }
        }
        else {
            if(id==0||id==2)
            {
                lose.play();
            }
            else {
                victory.play();
            }
        }
    }
}
