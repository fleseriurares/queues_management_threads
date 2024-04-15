package model;

import java.util.List;
import java.util.Random;

public class Task {
    private static int idCounter=1;
    private  int id;
    private int arrivalTime;





    private int serviceTime;


    public Task(int arrivalTimeMin, int arrivalTimeMax, int serviceTimeMin, int serviceTimeMax)
    {
        this.id = idCounter;
        idCounter++;
        this.arrivalTime = (new Random()).nextInt(arrivalTimeMin,arrivalTimeMax + 1);
        this.serviceTime = (new Random()).nextInt(serviceTimeMin,serviceTimeMax + 1);

    }


    public  int getId() {
        return id;
    }

    public void setId(int id1)
    {
        this.id = id;
    }



    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString()
    {
        return "(" + this.id + ", " +this.arrivalTime + ", " + this.serviceTime+")";
    }

    public static void printTasks(List<Task> list)
    {
        StringBuilder b = new StringBuilder();
        b.append("Clients: ");
        for(Task t: list)
        {
            b.append(t.toString()).append(", ");
        }

        System.out.println(b.toString().trim());
    }




    public static String printTasksToGUI(List<Task> list)
    {
        StringBuilder b = new StringBuilder();
        b.append("Clients: ");
        for(Task t: list)
        {
            b.append(t.toString()).append(", ");
        }

       return b.toString();
    }


}
