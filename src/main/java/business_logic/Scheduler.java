package business_logic;

import model.Server;
import model.Task;

import java.util.LinkedList;
import java.util.List;

public class Scheduler {

    private List<Server> servers;
    private int maxNoServ;
    private int maxTasksPerServ;
    private Strategy strategy;




    public Scheduler(int maxNS, int maxTPS)
    {
        this.servers = new LinkedList<Server>();
        Thread[] t = new Thread[maxNS + 1];
        this.maxNoServ = maxNS;
        for( int i=0; i<maxNS; i++)
        {
            Server s1 = new Server();
            this.servers.add(s1);
            t[i]= new Thread(s1);
            t[i].start();
        }
    }

    public int getPHaux()
    {
        int size = 0;
       for(Server s: servers)
       {
           size += s.getTasks().size();
       }
       return size;
    }

    public enum SelectionPolicy{
        SHORTEST_QUEUE, SHORTEST_TIME
    }

    public void changeStrategy(SelectionPolicy policy)
    {
        if( policy == SelectionPolicy.SHORTEST_QUEUE)
        {
            this.strategy = new ConcreteStrategyQueue();
        }
        else if( policy == SelectionPolicy.SHORTEST_TIME)
        {
            this.strategy = new ConcreteStrategyTime();
        }
    }

    public List<Server> getServers() {
        return servers;
    }

    public synchronized void dispatchTask(Task t)
    {
        this.strategy.addTask(this.servers,t);
    }

    public void displayServers()
    {   int i=1;

        for(Server s: servers)
        {
            s.printQueue(i);
            i++;
        }
    }

    public boolean isEmpty()
    {
        for(Server s: this.servers)
        {
            if(!s.getTasks().isEmpty())
            {
                return false;
            }
        }
        return true;
    }

    public String displayServersToGUI()
    {   int i=1;
        StringBuilder b = new StringBuilder();


        for(Server s: servers)
        {
            b.append(s.printQueueToGUI(i) + "\n");
          //  s.printQueue(i);
            i++;
        }
        return b.toString();
    }



}
