package sample;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class game implements Serializable {
    public player[] gameplayer;
    public int[] cardsondeck;
    public static int playerturn;
    public static int bidstate;

    public game()
    {
        playerturn=0;
        bidstate=0;
        gameplayer = new player[4];
        for(int i=0;i<4;i++)
        {
            gameplayer[i]=new player();
        }
        cardsondeck = new int[32];
        for (int i = 0; i < 32; i++) {
            cardsondeck[i] = i + 1;
            System.out.println(cardsondeck[i]);
        }

        shuffleArray(cardsondeck);
        distributecards();

    }

    public void shuffleArray(int[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public void distributecards() {

        for(int i=0;i<4;i++)
        {
            for(int j=0;j<8;j++) {
                gameplayer[i].hand[j] = cardsondeck[4*i +j];
            }
        }

    }

}
