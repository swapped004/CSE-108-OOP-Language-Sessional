package allin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class WorkerThread implements Runnable {
    private Socket connectionSocket;
    private int id;
    private String username;
    //private game g;
    private int[] cardsondeck;
    private BufferedReader inFromClient;
    private PrintWriter outToClient;
    private boolean round;
    private int mode;
    private String end;
    public Server server;
    private int start;
    private boolean game_start;
    private int gid;
    private int count;

    public WorkerThread(Socket connectionSocket,int id,int group_id,game g,Server server)
    {
        this.connectionSocket=connectionSocket;
        this.id=id;
        this.gid=group_id;
        cardsondeck=new int[g.cardsondeck.length];
        round=false;
        for(int i=0;i<cardsondeck.length;i++)
        {
            cardsondeck[i]=g.cardsondeck[i];
        }
        //mode=-1;
        mode=0;
        start=0;
        this.server=server;
        end=new String("RESUME");
        game_start=false;
        count=0;
    }

    public void run() {
        String Id_hand;

        try {
            while (true) {

                inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                outToClient = new PrintWriter(connectionSocket.getOutputStream());

                if (mode == 1) {
                    System.out.println("mode= "+mode);
                    while (!round) {
                        //outToClient = new PrintWriter(connectionSocket.getOutputStream());
                        Id_hand = inFromClient.readLine();
                        int playerId;
                        int playerHand;
                        StringTokenizer stringTokenizer = new StringTokenizer(Id_hand);
                        playerId = Integer.parseInt(stringTokenizer.nextToken());
                        playerHand = Integer.parseInt(stringTokenizer.nextToken());
                        cards card = new cards(playerHand);
                        System.out.println(card.cardname);
                        if (id == 0)
                            staticobjects.setCard1(card,gid);
                        else if (id == 1)
                            staticobjects.setCard2(card,gid);
                        else if (id == 2)
                            staticobjects.setCard3(card,gid);
                        else if (id == 3)
                            staticobjects.setCard4(card,gid);

                        if (staticobjects.numberofcardsplayed[gid] == 4) {
                            staticobjects.roundplayed[gid]++;
                            System.out.println("Round: "+staticobjects.roundplayed[gid]);
                            // end=new String("STOP");
                            getwhowinstheround();
                            end = new String(String.valueOf(staticobjects.whowinstheround[gid]));
                        }
                        if(staticobjects.playerturn[gid]>=4) {
                            staticobjects.playerturn[gid]=0;
                        }
                        System.out.println(playerId + " " + playerHand);
                        System.out.println("Score of a round in WorkerThread: "+ staticobjects.score[gid]);
                        server.sendTOallClients(id,gid, playerHand, end,staticobjects.score[gid],staticobjects.playerturn[gid],staticobjects.roundplayed[gid]);
                        end = new String("RESUME");
                        System.out.println("card sent successfully");
                    }
                }
                else if (mode == 0) {
                    System.out.println("mode= "+mode);
                    //inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                    //outToClient = new PrintWriter(connectionSocket.getOutputStream());
                    //outToClient.println(3);
                    String s = new String(String.valueOf(id));
                    s += " ";
                    for (int i = 0; i < cardsondeck.length; i++) {
                        s += String.valueOf(cardsondeck[i]);
                        s += " ";
                        System.out.println("cardsondeck[" + i + "]=" + cardsondeck[i]);
                    }

                    outToClient.println(s);
                    outToClient.flush();
                    System.out.println(s);
                    System.out.println("Successfully sent");

                    BufferedReader in = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                    //username = in.readLine();
                    while((username=in.readLine())!=null)
                    {
                        break;
                    }
                    System.out.println("User name from user input: "+username);
                    staticobjects.PlayerNames[gid][id]=username;
                    if(id==3&&count==0){
                        Thread.sleep(500);
                        staticobjects.getAllPlayersName(gid);
                        System.out.println("name1: "+staticobjects.PlayerNames[gid][0]);
                        System.out.println("name2: "+staticobjects.PlayerNames[gid][1]);
                        System.out.println("name3: "+staticobjects.PlayerNames[gid][2]);
                        System.out.println("name4: "+staticobjects.PlayerNames[gid][3]);
                        System.out.println("All names in worker thread: "+staticobjects.username[gid]);
                        server.sendNameToClients(gid,staticobjects.username[gid]);
                        count++;
                    }
                    System.out.println("id "+id+"succeded");
                    mode = 1;
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void getAllPlayersName()
    {
        staticobjects.username[gid]=staticobjects.PlayerNames[gid][0]+" "+staticobjects.PlayerNames[gid][1]+" "+staticobjects.PlayerNames[gid][2]+" "+staticobjects.PlayerNames[gid][3];
    }

    public void setmode()
    {
        mode=0;
    }
    public void sendCard(int id,int card,String end,int score,int turn,int round)
    {
        try {
            //outToClient = new PrintWriter(connectionSocket.getOutputStream());
            outToClient.println(id);
            outToClient.println(card);
            outToClient.println(end);
            outToClient.println(score);
            outToClient.println(turn);
            outToClient.println(round);
            outToClient.flush();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void sendName(String user)
    {
        try {
            //outToClient = new PrintWriter(connectionSocket.getOutputStream());
            outToClient.println(user);
            outToClient.flush();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public int getGid() {
        return gid;
    }

    public void getwhowinstheround()
    {
        staticobjects.getroundsuit(gid);
        System.out.println(staticobjects.suit[gid]);
        staticobjects.getwinnerplayer(gid);
        staticobjects.playerturn[gid]=staticobjects.whowinstheround[gid];
        System.out.println("player "+(staticobjects.whowinstheround[gid]+1)+" wins the round");
        staticobjects.numberofcardsplayed[gid]=0;
        staticobjects.playerofround[gid]=staticobjects.whowinstheround[gid];
        //staticobjects.whowinstheround[gid]=0;
        staticobjects.card1[gid]=null;
        staticobjects.card2[gid]=null;
        staticobjects.card3[gid]=null;
        staticobjects.card4[gid]=null;
    }

}

