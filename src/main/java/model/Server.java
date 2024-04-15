package model;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{

    private BlockingQueue<Task> tasks;
    private static AtomicInteger waitingPeriod = new AtomicInteger(0);

    private static AtomicInteger noTasks = new AtomicInteger(0);

    private static AtomicInteger timeSpentInQueue = new AtomicInteger(0);

    private static AtomicInteger serviceTime = new AtomicInteger(0);

    public Server()
    {
        this.tasks= new LinkedBlockingQueue<>(1000);
        this.waitingPeriod.getAndSet(0);
        this.noTasks.getAndSet(0);

    }


    public void addTask(Task newTask)
    {
        this.tasks.add(newTask);
        noTasks.addAndGet(1);
        serviceTime.addAndGet(newTask.getServiceTime());
        waitingPeriod.addAndGet(newTask.getServiceTime());
    }





    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public void setTasks(BlockingQueue<Task> tasks) {
        this.tasks = tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public static AtomicInteger getTimeSpentInQueue() {
        return timeSpentInQueue;
    }

    public static AtomicInteger getNoTasks() {
        return noTasks;
    }

    public static AtomicInteger getServiceTime() {
        return serviceTime;
    }

    @Override
    public void run() {
        while (true){
            Task task1 = this.getTasks().peek();
                if(tasks.size() > 1) {
                    this.timeSpentInQueue.addAndGet(this.tasks.size() - 1);
                }

           //this.timeSpentInQueue.addAndGet(-1);
            if(task1 != null)
            {
                //printQueue();
            try {

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            task1.setServiceTime(task1.getServiceTime() - 1);
            if(task1.getServiceTime() == 0)
            {
                this.getTasks().remove(task1);
            }
            this.waitingPeriod.decrementAndGet();
            }
        }
    }
    public void printQueue(int i)
    {
        StringBuilder b = new StringBuilder();
        b.append("Queue " + i + ": ");
        for(Task t: this.tasks)
        {
            b.append(t.toString()).append(", ");
        }

        System.out.println(b.toString().trim());
    }



    public String printQueueToGUI(int i)
    {
        StringBuilder b = new StringBuilder();
        b.append("Queue " + i + ": ");
        if(this.tasks.isEmpty())
        {
            return "Queue " + i + ": closed";
        }
        for(Task t: this.tasks)
        {
            b.append(t.toString()).append(", ");
        }

       // System.out.println(b.toString().trim());
        return b.toString();
    }
}

