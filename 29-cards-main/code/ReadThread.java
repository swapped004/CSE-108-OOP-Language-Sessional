package sample;

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class ReadThread implements Runnable {

    private Socket connectionSocket;
    private BufferedReader inFromServer;
    private PrintWriter outToServer;
    private int thisplayerid;
    private int otherplayerid;
    private int otherplayercard;
    private int roundofplayer;
    private player1Controller pc;
    private String end;
    private int mode;
    private int id;
    private int number;
    private int score;
    private int turn;
    private String valid_card;
    public int sub_score1=0;
    public int sub_score2=0;
    public String user=new String("");
    public int roundplayed=0;


    public ReadThread(Socket connectionSocket,player1Controller pc)
    {
        this.connectionSocket=connectionSocket;
        this.pc=pc;
        //this.inFromServer=inFromServer;
        //this.thisplayerid=id;
        mode=0;
        roundofplayer=0;
        number=0;
        turn=0;
        valid_card=new String();
    }

    @Override
    public void run() {
        try{
            int count=0;
            while (true)
            {
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                PrintWriter outToServer = new PrintWriter(connectionSocket.getOutputStream());

                if(mode==1) {
                    String s=new String();
                    //otherplayerid=Integer.parseInt(inFromServer.readLine());
                    if(count==0)
                    {
                        count++;
                    }
                    pc.setValid_card(valid_card);
                    System.out.println("From ReadThread: "+valid_card);
                    System.out.println("mode= "+mode);
                    System.out.println("Waiting");
                    otherplayerid = Integer.parseInt(inFromServer.readLine());
                    System.out.println("jdjkjdf");
                    otherplayercard=Integer.parseInt(inFromServer.readLine());
                    this.setValid_card(otherplayercard);
                    System.out.println("kop");
                    end=inFromServer.readLine();
                    score=Integer.parseInt(inFromServer.readLine());
                    turn=Integer.parseInt(inFromServer.readLine());
                    roundplayed=Integer.parseInt(inFromServer.readLine());
                    System.out.println(turn);
                    System.out.println("Round played: "+roundplayed);
                    pc.setPlayerturn(turn);
                    if(roundplayed!=8){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                            pc.playerturnlabelsetter(turn);
                        }
                        });
                    }
                    else
                    {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                pc.playerTurnRemove();
                            }
                        });
                    }
                    System.out.println("Score per round in readthread: "+score);
                    System.out.println("id=" + otherplayerid + "  hand=" + otherplayercard);
                    pc.opponentcardsetter(otherplayerid,otherplayercard);
                    if(!end.equals("RESUME"))
                    {
                        roundofplayer=Integer.parseInt(end);
                        System.out.println("The winner of the round is"+(roundofplayer+1));
                        if(roundofplayer==0||roundofplayer==2){
                            sub_score1+=score;
                            System.out.println("Score for team1: "+sub_score1);
                        }
                        else{
                            sub_score2+=score;
                            System.out.println("Score for team2: "+sub_score2);
                        }
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                pc.ScoreLabel(sub_score1,sub_score2);
                                pc.setWinnerID(turn);
                                pc.WinLabel();
                                pc.ImageSetterOfPlayers(roundofplayer);
                                pc.victoryOrloseSound();
                            }
                        });
                        Thread.sleep(2500);
                        pc.removeallgivencards();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                pc.removeFace();
                            }
                        });
                        valid_card=new String();
                        if(roundplayed==8)
                        {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    pc.endGame();
                                }
                            });
                        }
                    }
                    else{
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                pc.RemoveTextfromWinLabel();
                            }
                        });
                    }
                }
                if(mode==0) {
                    System.out.println("mode= "+mode);
                    int[] cardsondeck=new int[32];
                    System.out.println("invited player: "+invite.username);
                    //outToServer.println(invite.username);
                    //outToServer.flush();
                    String combined=inFromServer.readLine();
                    StringTokenizer st=new StringTokenizer(combined);
                    id=Integer.parseInt(st.nextToken());
                    pc.setId(id);
                    for(int i=0;i<cardsondeck.length;i++)
                    {
                        cardsondeck[i]=Integer.parseInt(st.nextToken());
                        System.out.println("cardsondeck{"+i+"}="+cardsondeck[i]);
                    }
                    pc.distributecards(cardsondeck);
                    pc.imageshower(id);

                    PrintWriter out = new PrintWriter(connectionSocket.getOutputStream());
                    out.println(invite.username);
                    out.flush();
                    mode=1;
                    System.out.println("Username sent successfully");
                    user=inFromServer.readLine();
                    System.out.println("Names in ReadThread: "+user);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            pc.PlayerNameLabelSetter(user);
                            pc.playerturnlabelsetter(0);
                            pc.removebar();
                            pc.ScoreLabel(00,00);
                        }
                    });
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setValid_card(int n)
    {
        cards card=new cards(n);
        valid_card+=card.suit;
        valid_card+=" ";
    }
}
