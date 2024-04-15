package business_logic;

import GUI.ThreadApp;
import model.Server;
import model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;
//import static sun.swing.MenuItemLayoutHelper.max;

public class SimulationManager implements Runnable{
    public ThreadApp view;
    public int timeLimit = 100;
    public int start = 0;
    public int maxArrivalTime = 10;
    public int minArrivalTime = 2;
    public int maxServiceTime=4;
    public int minServiceTime=2;

    public int numberOfServers = 2;
    public int numberOfTasks = 10;

    public Scheduler.SelectionPolicy selectionPolicy = Scheduler.SelectionPolicy.SHORTEST_QUEUE;

    private Scheduler scheduler;

    private Double avgWaitingTime;

    private List<Task> tasks;
    private double avgServiceTime;
    private int peakHour = 0;

    public SimulationManager(ThreadApp view)
    {
        this.view=view;
        this.getData(view);
        this.scheduler = new Scheduler(numberOfServers,numberOfTasks);
        this.scheduler.changeStrategy(selectionPolicy);
        this.tasks = randomGenerate(numberOfTasks,minArrivalTime,maxArrivalTime,minServiceTime,maxServiceTime);
        this.avgWaitingTime = 0.0;
    }

    public static List<Task> randomGenerate(int N, int minAT, int maxAT, int minST, int maxST)
    {
        List<Task> list = new ArrayList<>();
        for(int i=0 ; i < N; i++)
        {
            Task t = new Task(minAT,maxAT,minST,maxST);
            list.add(t);
        }
        for(int i=1; i<N; i++) {
            Task aux = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).getArrivalTime() > aux.getArrivalTime()) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, aux);
        }
        return list;
    }

    public Double getAvgWaitingTime()
    {
        this.avgWaitingTime = Server.getTimeSpentInQueue().doubleValue() / Server.getNoTasks().doubleValue();
        return this.avgWaitingTime;
    }

    public Double getAvgServiceTime()
    {
        this.avgServiceTime = Server.getServiceTime().doubleValue() / Server.getNoTasks().doubleValue();
        return this.avgServiceTime;
    }



    public void run() {
        int time = 0, max = 0, ind = 1;
        List<Task> remList = new LinkedList<Task>();
        while(time <= timeLimit && ind == 1)
        {   remList.clear();
            if(this.tasks.isEmpty() && this.scheduler.isEmpty()) {
                ind = 0;
            }
            for(Task t: tasks) {
                if(t.getArrivalTime()>time) {
                    break;}
                else {
                    if (t.getArrivalTime() == time) {
                        scheduler.dispatchTask(t);
                        remList.add(t);} } }
            if(this.scheduler.getPHaux() > max) {
                max = this.scheduler.getPHaux(); this.peakHour = time;}
            tasks.removeAll(remList); //Task.printTasks(tasks);
            scheduler.displayServers();
            this.view.setTextArea1("TIME " + time + "\n" + Task.printTasksToGUI(tasks) + "\n" + scheduler.displayServersToGUI() + "\n");
            this.view.setTextArea2("TIME " + time + "\n" + Task.printTasksToGUI(tasks) + "\n" + scheduler.displayServersToGUI() + "\n");
            ThreadApp.highlightText(time,view,scheduler);
            time ++;
             try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);}
        }
        view.setAwtTF(getAvgWaitingTime()); view.setAstTF(getAvgServiceTime()); view.setPhTF(this.peakHour);
        try {
            FileWriter myWriter = new FileWriter("fisierJava.txt");
            myWriter.write(view.getTextArea1() + "\n" + "Average waiting time: " + getAvgWaitingTime() + "\n" + "Average service time: " + getAvgServiceTime() + "\n" + "Peak Hour: " + this.peakHour);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();}
    }



    public void getData(ThreadApp view)
    {
        this.numberOfServers = Integer.parseInt(view.getNoQTF());
        this.numberOfTasks = Integer.parseInt(view.getNoCTF());
        this.timeLimit = Integer.parseInt(view.getSimTimeTF());
        this.maxArrivalTime = Integer.parseInt(view.getMaxATTF());
        this.minArrivalTime = Integer.parseInt(view.getMinATTF());
        this.maxServiceTime = Integer.parseInt(view.getMaxSTTF());
        this.minServiceTime = Integer.parseInt(view.getMinSTTF());
        this.timeLimit = Integer.parseInt(view.getSimTimeTF());
        if(view.getStrategyCB().toString().equals("Shortest Time"))
        {
            this.selectionPolicy = Scheduler.SelectionPolicy.SHORTEST_TIME;
        }
        else
        {
            this.selectionPolicy = Scheduler.SelectionPolicy.SHORTEST_QUEUE;
        }
        this.start=1;
    }

    public static void main(ThreadApp view)
    {

        try {
            SimulationManager simM = new SimulationManager(view);
            Thread t = new Thread(simM);
            t.start();
        }
        catch(Exception e)
        {
            view.setTextArea1("Nu ai introdus bine. (Nu ai completat tot)");
        }


    }
}
