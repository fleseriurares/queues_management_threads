package business_logic;

import business_logic.Strategy;
import model.Server;
import model.Task;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
    @Override
    public synchronized void addTask(List<Server> servers, Task t) {
       // System.out.println("ok3");
        int minQueue = servers.getFirst().getTasks().size();
        Server servToAdd = servers.getFirst();
        for(Server serv:servers)
        {
            if(serv.getTasks().size() < minQueue)
            {
                servToAdd = serv;
            }
        }
        servToAdd.addTask(t);
    }
}
