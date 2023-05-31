



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static java.lang.Thread.sleep;

public class SimulationManager implements Runnable {
    public int timeLimit;
    public int maxProcessingTime;
    public int minProcessingTime;

    public int minArrivalTime;
    public int maxArrivalTime;
    public int numberOfServer;
    public int numberOfClients;

    private Scheduler scheduler;

    private SimulationView view;

    private List<Task> generatedTask;
    private float avgServiceTime;
    private float avgWaitingTime;
    private int avgWaitingSum;
    private float peakHour;

    private File output;

    private boolean stop = false;

    public SimulationManager(SimulationView view, int timeLimit, int minProcessingTime, int maxProcessingTime, int minArrivalTime, int maxArrivalTime, int numberOfClients, int numberOfServer) {
        this.view = view;
        this.timeLimit = timeLimit;
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.numberOfClients = numberOfClients;
        this.numberOfServer = numberOfServer;
        generatedTask = new ArrayList<>();
        generateNRandomTasks();
        //avgServiceTime = getAvgServTime();
        output = new File("output.txt");

        scheduler = new Scheduler(numberOfServer, numberOfClients);


    }


    private void generateNRandomTasks() { 
        Random rand = new Random();

        for (int i = 0; i < numberOfClients; i++) {
            Task temp = new Task(i, rand.nextInt(minArrivalTime, maxArrivalTime), rand.nextInt(minProcessingTime, maxProcessingTime));
            generatedTask.add(temp);
        }

        Collections.sort(generatedTask);

    }

    public float getAvgServTime() {
        float tempSum = 0;
        for (Task i : generatedTask) {
            tempSum += i.getServiceTime();
        }
        return tempSum / generatedTask.size();
    }

    public int getOcuppiedNumber(List<Server> servers) {
        int returned = 0;
        for (Server i : servers) {
            Task[] takks = i.getTasks();

            returned += takks.length;

        }
        return returned;
    }

    public float getServiceAVG(List<Server> servers) {
        int returned = 0;
        int number = 0;
        float result = 0;
        for (Server i : servers) {
            returned += i.getOutSum();
            number += i.getOutNumber();
        }
        result =  returned / (float)number;
        if (number == 0)
        {
            return -1;
        }
        return result;
    }



    @Override
    public void run() {
        int currentTime = 0;
        int maxPeak = getOcuppiedNumber(scheduler.getServers());
        int timeFound = currentTime;
        System.out.println("SIMULATION STARTED");
        try {
            FileWriter writer = new FileWriter(output);
            while (currentTime < timeLimit) {
                if (!stop) {
                    if (generatedTask.size() == 0) {
                        int count = 0;
                        for(Server i: scheduler.getServers()){
                            count += i.getTasks().length;
                        }
                        if(count == 0){
                            break;
                        }
                    }
                    for (int i = 0; i < generatedTask.size(); i++) {
                        if (generatedTask.get(i).getArrivalTime() == currentTime) {
                            scheduler.dispatchTask(generatedTask.get(i));
                            generatedTask.remove(generatedTask.get(i));
                            i = -1;
                        }
                    }

                    String showed = "";
                    writer.write("Time:" + currentTime + "\n");
                    showed += "Time:" + currentTime + System.lineSeparator();
                    writer.write("Waiting: " + generatedTask.toString() + "\n");
                    showed += "Waiting: " + generatedTask.toString() + System.lineSeparator();
                    List<Server> servers = new ArrayList<>(scheduler.getServers());
                    servers.sort(Comparator.comparingInt(Server::getId));
                    if (getOcuppiedNumber(servers) > maxPeak) {
                        maxPeak = getOcuppiedNumber(servers);
                        timeFound = currentTime;
                    }
                    peakHour = maxPeak;
                    for (Server i : servers) {
                        Task[] taakks = i.getTasks();
                        if (taakks.length == 0) {
                            writer.write("CLOSED" + "\n");
                            showed += "CLOSED" + System.lineSeparator();
                        } else {
                            writer.write("Queue for server " + i.getId() + ":\n");
                            showed += "Queue for server " + i.getId() + ":\n";
                            int flag = 0;
                            for (Task j : taakks) {
                                writer.write(j + " " + "\n");
                                showed += j.toString() + " " + System.lineSeparator();
                                if (j.getServiceTime() == 1) {
                                    avgWaitingSum += (currentTime - j.getArrivalTime() + 1);
                                }
                                if (flag == 0) {
                                    j.setServiceTime(j.getServiceTime() - 1);
                                    flag++;
                                }
                            }
                        }
                    }

                    writer.write("\n");
                    writer.write("\n");
                    view.setTextArea(showed);

                    currentTime++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            String toShow = "";
            avgWaitingTime = avgWaitingSum / (float)numberOfClients;
            this.avgServiceTime = getServiceAVG(scheduler.getServers());
            writer.write("Time finished!" + "\n" + "Average service time: " + this.avgServiceTime + "\n"); // -1 means that no clients were served;
            toShow += "Time finished!" + System.lineSeparator() + "Average service time: " + this.avgServiceTime + System.lineSeparator();
            writer.write("Average waiting time: " + this.avgWaitingTime + "\n");
            toShow += "Average waiting time: " + this.avgWaitingTime+ System.lineSeparator();
            writer.write("Peak clients: " + this.peakHour + " found at time " + timeFound + "\n");
            toShow += "Peak clients: " + this.peakHour + " found at time " + timeFound + System.lineSeparator();

            writer.close();
            System.out.println("Wrote succesfully in file: " + output.getAbsolutePath());
            toShow += "Wrote succesfully in file: " + output.getAbsolutePath();
            view.setTextArea(toShow);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
