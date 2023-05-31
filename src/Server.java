

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable, Comparable<Server> {
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private int id;
    private AtomicInteger outNumber;
    private AtomicInteger outSum;

    public Server(int capacity) {

        tasks = new LinkedBlockingQueue<>(capacity);
        waitingPeriod = new AtomicInteger();
        outNumber = new AtomicInteger(0);
        outSum = new AtomicInteger(0);
    }
    
    public Server(int capacity, int id) {

        tasks = new LinkedBlockingQueue<>(capacity);
        waitingPeriod = new AtomicInteger(0);
        outNumber = new AtomicInteger(0);
        outSum = new AtomicInteger(0);
        this.id = id;
    }

    public void addTask(Task newTask) {
        //add task to queue
        //increment the waitingPeriod

        //System.out.println(Thread.currentThread().getName());
        tasks.add(newTask);

        waitingPeriod.addAndGet(newTask.getServiceTime());
        //System.out.println("Adding task to the queue " + newTask.toString() + "by thread:  " + Thread.currentThread().getName());
        //System.out.println("Current queue size: " + tasks.size());


    }

    public void run() {
        while (true) {
            try {
                //System.out.println("Executed by " + Thread.currentThread().getName());
                Task t;
                if(!tasks.isEmpty()){
                    t = tasks.peek();
                    Thread.sleep(t.getServiceTime() * 1000);
                    outNumber.addAndGet(1);
                    outSum.addAndGet(t.getInitialServiceTime());
                    tasks.poll();

                    waitingPeriod.addAndGet(-1 * t.getServiceTime());
                }
                //System.out.println("Accesed by " + Thread.currentThread().getName());
                //System.out.println("Task taken from the queue: " + t.toString());
                //System.out.println("Current queue size: " + tasks.size());

                //System.out.println("Task completed: " + t.toString());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public Task[] getTasks() {
        return tasks.toArray(new Task[tasks.size()]);
    }

    @Override
    public int compareTo(Server o) {
        int thisValue = this.waitingPeriod.get();
        int otherValue = o.waitingPeriod.get();
        //return Integer.compare(thisValue, otherValue);
        if(thisValue < otherValue) {
        	return -1;
        }
        return 1;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public int getOutNumber() {
        return outNumber.get();
    }

    public int getOutSum() {
        return outSum.get();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
