package sample;

import javafx.scene.image.Image;

/**
 *
 *
 * @author Swapnil
 *
 * variable:
 * 1.suit=clubs,spades,diamonds,hearts
 * 2.cardname="7","8","9","10","Ace","Jack","King","Queen","2";
 * 3.facevalue=0,0,2,1,1,3,0,0,0;
 * 4.precendence=1-36
 * 5.image=1-36;

*/

public class cards {
    public String suit,cardname;
    public int facevalue;
    private Image image;
    public int precendence;

    public cards(String cardname, String suit, int facevalue, Image image) {
        this.cardname = cardname;
        this.suit = suit;
        this.facevalue = facevalue;
        this.image = image;
    }

    public cards() {

    }

    /**
     *
     *
     * @author Swapnil
     *
     * variable:
     * 1.suit=clubs,spades,diamonds,hearts
     * 2.cardname="7","8","9","10","Ace","Jack","King","Queen","2";
     * 3.facevalue=0,0,2,1,1,3,0,0,0;
     * 4.precendence
     * 5.image=1-36;

     */
    public cards(int n) {
        if(n==1)
        {
            suit="clubs";
            cardname="7";
            facevalue=0;
            image= new Image("7 of clubs.png");
            precendence=8;
        }

        else if(n==2)
        {
            suit="clubs";
            cardname="8";
            facevalue=0;
            image= new Image("8 of clubs.png");
            precendence=7;
        }

        else if(n==3)
        {
            suit="clubs";
            cardname="9";
            facevalue=2;
            image= new Image("9 of clubs.png");
            precendence=2;
        }

        else if(n==4)
        {
            suit="clubs";
            cardname="10";
            facevalue=1;
            image= new Image("10 of clubs.png");
            precendence=4;
        }

        else if(n==5)
        {
            suit="clubs";
            cardname="ace";
            facevalue=1;
            //Image= ImageIO.read(new File("Aceofclubs.jpg"));
            image= new Image("ace of clubs.png");

            precendence=3;
        }

        else if(n==6)
        {
            suit="clubs";
            cardname="jack";
            facevalue=3;
            //Image= ImageIO.read(new File("Jackofclubs.jpg"));
            image= new Image("jack of clubs.png");

            precendence=1;
        }

        else if(n==7)
        {
            suit="clubs";
            cardname="king";
            facevalue=0;
            //Image= ImageIO.read(new File("Kingofclubs.jpg"));
            image= new Image("king of clubs.png");

            precendence=5;
        }

        else if(n==8)
        {
            suit="clubs";
            cardname="queen";
            facevalue=0;
            //Image= ImageIO.read(new File("Queenofclubs.jpg"));
            image= new Image("queen of clubs.png");

            precendence=6;
        }

        else if(n==35)
        {
            suit="clubs";
            cardname="2";
            facevalue=0;
           //Image= ImageIO.read(new File("trumpofclubs.jpg"));
            image= new Image("2 of clubs.png");

            precendence=0;
        }

        else if(n==10)
        {
            suit="spades";
            cardname="7";
            facevalue=0;
            //Image= ImageIO.read(new File("7ofspade.jpg"));
            image= new Image("7 of spades.png");

            precendence=16;
        }

        else if(n==11)
        {
            suit="spades";
            cardname="8";
            facevalue=0;
           // Image= ImageIO.read(new File("8ofspade.jpg"));
            image= new Image("8 of spades.png");

            precendence=15;
        }

        else if(n==12)
        {
            suit="spades";
            cardname="9";
            facevalue=2;
            //Image= ImageIO.read(new File("9ofspade.jpg"));
            image= new Image("9 of spades.png");

            precendence=10;
        }

        else if(n==13)
        {
            suit="spades";
            cardname="10";
            facevalue=1;
            //Image= ImageIO.read(new File("10ofspade.jpg"));
            image= new Image("10 of spades.png");

            precendence=12;
        }

        else if(n==14)
        {
            suit="spades";
            cardname="ace";
            facevalue=1;
            //Image= ImageIO.read(new File("Aceofspade.jpg"));
            image= new Image("ace of spades.png");

            precendence=11;
        }

        else if(n==15)
        {
            suit="spades";
            cardname="jack";
            facevalue=3;
            //Image= ImageIO.read(new File("Jackofspade.jpg"));
            image= new Image("jack of spades.png");

            precendence=9;
        }

        else if(n==16)
        {
            suit="spades";
            cardname="king";
            facevalue=0;
            //Image= ImageIO.read(new File("Kingofspade.jpg"));
            image= new Image("king of spades.png");

            precendence=13;
        }

        else if(n==17)
        {
            suit="spades";
            cardname="queen";
            facevalue=0;
            //Image= ImageIO.read(new File("Queenofspade.jpg"));
            image= new Image("queen of spades.png");

            precendence=14;
        }

        else if(n==34)
        {
            suit="spades";
            cardname="2";
            facevalue=0;
            //Image= ImageIO.read(new File("trumpofspade.jpg"));
            image= new Image("2 of spades.png");

            precendence=0;
        }

        else if(n==19)
        {
            suit="diamonds";
            cardname="7";
            facevalue=0;
            //Image= ImageIO.read(new File("7ofdiamond.jpg"));
            image= new Image("7 of diamonds.png");

            precendence=24;
        }

        else if(n==20)
        {
            suit="diamonds";
            cardname="8";
            facevalue=0;
            //Image= ImageIO.read(new File("8ofdiamond.jpg"));
            image= new Image("8 of diamonds.png");

            precendence=23;
        }

        else if(n==21)
        {
            suit="diamonds";
            cardname="9";
            facevalue=2;
            //Image= ImageIO.read(new File("9ofdiamond.jpg"));
            image= new Image("9 of diamonds.png");

            precendence=18;
        }

        else if(n==22)
        {
            suit="diamonds";
            cardname="10";
            facevalue=1;
            //Image= ImageIO.read(new File("10ofdiamond.jpg"));
            image= new Image("10 of diamonds.png");

            precendence=20;
        }

        else if(n==23)
        {
            suit="diamonds";
            cardname="ace";
            facevalue=1;
            //Image= ImageIO.read(new File("Aceofdiamond.jpg"));
            image= new Image("ace of diamonds.png");

            precendence=19;
        }

        else if(n==24)
        {
            suit="diamonds";
            cardname="jack";
            facevalue=3;
            //Image= ImageIO.read(new File("Jackofdiamond.jpg"));
            image= new Image("jack of diamonds.png");

            precendence=17;
        }

        else if(n==25)
        {
            suit="diamonds";
            cardname="king";
            facevalue=0;
            //Image= ImageIO.read(new File("Kingofdiamond.jpg"));
            image= new Image("king of diamonds.png");

            precendence=21;
        }

        else if(n==26)
        {
            suit="diamonds";
            cardname="queen";
            facevalue=0;
            //Image= ImageIO.read(new File("Queenofdiamond.jpg"));
            image= new Image("queen of diamonds.png");

            precendence=22;
        }

        else if(n==33)
        {
            suit="diamonds";
            cardname="2";
            facevalue=0;
            //Image= ImageIO.read(new File("trumpofdiamond.jpg"));
            image= new Image("2 of diamonds.png");

            precendence=0;
        }


        else if(n==28)
        {
            suit="hearts";
            cardname="7";
            facevalue=0;
            //Image= ImageIO.read(new File("7ofhearts.jpg"));
            image= new Image("7 of hearts.png");

            precendence=32;
        }

        else if(n==29)
        {
            suit="hearts";
            cardname="8";
            facevalue=0;
            //Image= ImageIO.read(new File("8ofhearts.jpg"));
            image= new Image("8 of hearts.png");

            precendence=31;
        }

        else if(n==30)
        {
            suit="hearts";
            cardname="9";
            facevalue=2;
            //Image= ImageIO.read(new File("9ofhearts.jpg"));
            image= new Image("9 of hearts.png");

            precendence=26;
        }

        else if(n==31)
        {
            suit="hearts";
            cardname="10";
            facevalue=1;
            //Image= ImageIO.read(new File("10ofhearts.jpg"));
            image= new Image("10 of hearts.png");

            precendence=28;
        }

        else if(n==32)
        {
            suit="hearts";
            cardname="ace";
            facevalue=1;
            //Image= ImageIO.read(new File("Aceofhearts.jpg"));
            image= new Image("ace of hearts.png");

            precendence=27;
        }


        else if(n==27)
        {
            suit="hearts";
            cardname="jack";
            facevalue=3;
            //Image= ImageIO.read(new File("Jackofhearts.jpg"));
            image= new Image("jack of hearts.png");

            precendence=24;
        }

        else if(n==18)
        {
            suit="hearts";
            cardname="king";
            facevalue=0;
            //Image= ImageIO.read(new File("Kingofhearts.jpg"));
            image= new Image("king of hearts.png");

            precendence=29;
        }

        else if(n==9)
        {
            suit="hearts";
            cardname="queen";
            facevalue=0;
            //Image= ImageIO.read(new File("Queenofhearts.jpg"));
            image= new Image("queen of hearts.png");

            precendence=30;
        }

        else if(n==36)
        {
            suit="hearts";
            cardname="2";
            facevalue=0;
            //Image= ImageIO.read(new File("trumpofhearts.jpg"));
            image= new Image("2 of hearts.png");

            precendence=0;
        }

    }

    @Override
    public String toString() {
        return "cards{" +
                "cardname='" + cardname + '\'' +
                ", suit='" + suit + '\'' +
                ", facevalue=" + facevalue +
                ", Image=" + image +
                ", Precendence="+precendence+
                '}';
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getFacevalue() {
        return facevalue;
    }

    public void setFacevalue(int facevalue) {
        this.facevalue = facevalue;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
