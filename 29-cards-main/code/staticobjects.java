package allin;

public class staticobjects {

    //keeps track of the playerturn
    public static int[] playerturn=new int[10];
    //total number of cards played in the round
    public static int[] numberofcardsplayed=new int[10];
    //the player who plays 1st card
    public static int[] playerofround=new int[10];
    //suit of the round
    public  static String[] suit=new String[10];
    //player who will win the round
    public static int[] whowinstheround=new int[10];
    //
    public static int[] gamestart=new int[10];

    //card played by player1
    public static cards[] card1=new cards[10];
    //card played by player1
    public static cards[] card2=new cards[10];
    //card played by player1
    public static cards[] card3=new cards[10];
    //card played by player1
    public static cards[] card4=new cards[10];

    //username labels
    public static String[] username=new String[10];

    //scores
    public static int[] score=new int[10];
    //for the four players in a 2D string
    public static String[][] PlayerNames=new String[10][4];
    //for all names in a string
    // public static String userName=new String("");
    //how many rounds are played
    public static int[] roundplayed=new int[10];

    static {
        for(int i=0;i<10;i++)
        {
            playerturn[i]=0;
        }
    }


    /*public static cards getCard1() {
        return card1;
    }*/

    public static void setCard1(cards card1,int gid) {
        staticobjects.card1[gid] = card1;
        numberofcardsplayed[gid]++;
        playerturn[gid]++;
    }

    /*public static cards getCard2() {
        return card2;
    }*/

    public static void setCard2(cards card2,int gid) {
        staticobjects.card2[gid] = card2;
        numberofcardsplayed[gid]++;
        playerturn[gid]++;
    }

    /*public static cards getCard3() {
        return card3;
    }*/

    public static void setCard3(cards card3,int gid) {
        staticobjects.card3[gid] = card3;
        numberofcardsplayed[gid]++;
        playerturn[gid]++;
    }


    public static void setCard4(cards card4,int gid) {
        staticobjects.card4[gid] = card4;
        numberofcardsplayed[gid]++;
        playerturn[gid]++;
    }

    public static String getroundsuit(int gid)
    {
        if(playerofround[gid]==0)
        {
            suit[gid]=card1[gid].getSuit();
        }
        else if(playerofround[gid]==1)
        {
            suit[gid]=card2[gid].getSuit();
        }
        else if(playerofround[gid]==2)
        {
            suit[gid]= card3[gid].getSuit();
        }
        else
            suit[gid]=card4[gid].getSuit();
        return suit[gid];
    }

    public static int getwinnerplayer(int gid)
    {
        String prec=new String("j9a1kq87");
        for(int i=0;i<8;i++) {
            if ((card1[gid].getCardname()).charAt(0) == prec.charAt(i) && card1[gid].suit.equals(suit[gid])) {
                whowinstheround[gid] = 0;
                System.out.println(card1[gid].cardname.charAt(0));
                break;
            } else if ((card2[gid].getCardname()).charAt(0) == prec.charAt(i) && card2[gid].suit.equals(suit[gid])) {
                whowinstheround[gid] = 1;
                System.out.println(card2[gid].cardname.charAt(0));
                break;
            } else if ((card3[gid].getCardname()).charAt(0) == prec.charAt(i) && card3[gid].suit.equals(suit[gid])) {
                whowinstheround[gid] = 2;
                System.out.println(card3[gid].cardname.charAt(0));
                break;
            } else if ((card4[gid].getCardname()).charAt(0) == prec.charAt(i) && card4[gid].suit.equals(suit[gid])) {
                whowinstheround[gid] = 3;
                System.out.println(card4[gid].cardname.charAt(0));
                break;
            }
        }
        System.out.println("Cardname: "+card1[gid].cardname);
        System.out.println("Cardname: "+card2[gid].cardname);
        System.out.println("Cardname: "+card3[gid].cardname);
        System.out.println("Cardname: "+card4[gid].cardname);
        System.out.println("Facevalue: "+card1[gid].facevalue);
        System.out.println("Facevalue: "+card2[gid].facevalue);
        System.out.println("Facevalue: "+card3[gid].facevalue);
        System.out.println("Facevalue: "+card4[gid].facevalue);
        ScorePerRound(gid);
        System.out.println("Score of a round: "+score);
        return whowinstheround[gid];
    }

    public staticobjects()
    {
        playerturn=new int[10];
        numberofcardsplayed=new int[10];
        playerofround=new int[10];
        suit=new String[10];
        whowinstheround=new int[10];
        card1=new cards[10];
        card2=new cards[10];
        card3=new cards[10];
        card4=new cards[10];
        username=new String[10];
        for(int i=0;i<10;i++)
        {
            PlayerNames[i]=new String[4];
        }
    }

    public static void ScorePerRound(int gid){
        score[gid]=(card1[gid].facevalue+card2[gid].facevalue+card3[gid].facevalue+card4[gid].facevalue);
    }
    public static void getAllPlayersName(int gid){
        username[gid]=PlayerNames[gid][0]+" "+PlayerNames[gid][1]+" "+PlayerNames[gid][2]+" "+PlayerNames[gid][3];
        System.out.println("users are in staticobjects: "+username[gid]);
    }
}

