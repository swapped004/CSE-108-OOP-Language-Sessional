package allin;
import java.net.*;
import java.util.Vector;


public class Server {
    Vector<WorkerThread> clients=new Vector<WorkerThread>();
    public static String loggedinmembers;

    static {
        loggedinmembers = new String();
    }


    public static void main(String[] args) throws Exception {
        new Server().connection();
    }

    public void sendTOallClients(int id,int gid,int card,String end,int score,int turn,int round)
    {
        System.out.println("Got there");
        for(WorkerThread wt:clients)
        {
            if(wt.getGid()==gid) {
                System.out.println("Got in the loop");
                wt.sendCard(id, card, end, score,turn,round);
            }
        }
    }

    public void sendNameToClients(int gid,String user){
        System.out.println("Got there new");
        for(WorkerThread workerThread:clients){
            if(workerThread.getGid()==gid){
                workerThread.sendName(user);
            }
        }
    }


    public void connection() throws Exception {
        int workerThreadCount = 0;
        ServerSocket welcomeSocket = new ServerSocket(6789);
        int id = 0;
        game g;

        int group_id = 0;
        while (true) {
            g=new game();
            staticobjects.username[group_id]=new String("");
            //staticobjects.PlayerNames[group_id]=new String[4];
            //clients[group_id]=new Vector<WorkerThread>();

            while (workerThreadCount < 4) {
                Socket connectionSocket = welcomeSocket.accept();
                WorkerThread wt = new WorkerThread(connectionSocket, id, group_id, g, this);
                clients.add(wt);
                workerThreadCount++;
                System.out.println("Client [" +group_id+","+ id + "] is now connected. No. of worker threads = " + workerThreadCount);
                id++;
            }

            for (WorkerThread wt : clients) {

                if(wt.getGid()==group_id) {
                    Thread t = new Thread(wt);
                    t.start();
                }
            }
            group_id++;
            workerThreadCount=0;
            id=0;
        }
    }

}

