


public class Task implements Comparable<Task> {
    private int ID;
    private int arrivalTime;
    private int serviceTime; //this is the time thats left
    private int initialServiceTime;

    public Task(int ID, int aTime, int sTime) {
        this.ID = ID;
        this.arrivalTime = aTime;
        this.serviceTime = sTime;
        this.initialServiceTime = sTime;

    }


    @Override
    public int compareTo(Task o) {
        return Integer.compare(this.arrivalTime, o.arrivalTime);
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int number) {
        this.serviceTime = number;
    }



    ;

    public String toString() {
        return "[" + ID + " , " + arrivalTime + ", " + serviceTime + "]";
    }

    public int getInitialServiceTime() {
        return initialServiceTime;
    }
}
