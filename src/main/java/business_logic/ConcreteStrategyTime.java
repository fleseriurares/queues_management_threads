package business_logic;

import business_logic.Strategy;
import model.Server;
import model.Task;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {
    @Override
    public synchronized void addTask(List<Server> servers, Task t) {

        int minTime = servers.getFirst().getWaitingPeriod().get();

        Server servToAdd = servers.getFirst();
        for(Server serv:servers)
        {
            if(serv.getWaitingPeriod().get() < minTime)
            {
                servToAdd = serv;
            }
        }
      //  System.out.println("ok2");
        servToAdd.addTask(t);
    }
}
