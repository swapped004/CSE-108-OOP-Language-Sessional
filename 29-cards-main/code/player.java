package sample;

import java.io.Serializable;

public class player implements Serializable {
    /**
     * @author Swapnil
     * turn=0,1;
     *call=16-28;
     * hand=1-36;
     */
    public int bidwinner;
    public int call;
    public  int[] hand;

    public player(int call, int[] hand) {
        this.call = call;
        this.hand = hand;
    }

    public player()
    {
        hand=new int[8];
        call=0;
        bidwinner=0;
    }

    public void sorthand()
    {


    }




}
