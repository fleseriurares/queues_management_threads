import business_logic.SimulationManager;
import model.Server;
import model.Task;

import java.util.List;

public class Main {

    public static void main(String[] args)
    {
       // System.out.println("ok");
        Task s1 = new Task(1,10,2,4);
        Task s2 = new Task(1,5,2,4);
        Task s3 = new Task(1,3,2,4);

        Server serv1 = new Server();

        List<Task> list = SimulationManager.randomGenerate(4,2,10,3,5);
        for(Task t: list)
        {
            serv1.addTask(t);
        }

        Thread t1 = new Thread(serv1);
       // System.out.println(s1);
       // System.out.println(s2);
        //System.out.println(s3);
        t1.start();
    }


}
