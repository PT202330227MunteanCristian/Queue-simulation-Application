




import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;


    public Scheduler(int maxNoServers, int maxTasksPerServer){
        servers = new ArrayList<>();
        for(int i = 0; i < maxNoServers; i++){
            Server tempServer = new Server(maxTasksPerServer, i);
            servers.add(tempServer);
            Thread t = new Thread(tempServer);
            t.setName("Thread: " + i);
            t.start();
        }
    }

    public void dispatchTask(Task t){
    	//TODO: afisare inainte si dupa sortare, verificat daca sortarea se face bine. Daca sortarea se face bine, dar datele din cozi (waiting time) nu e suma la elemen din cozi, inseamna ca ai o prob la incrementat waiting time-ul cu task.getprocTime(), sau decrementezi prea repede/incet waiting time-ul serverului
        Collections.sort(servers);
        //TODO: DACA IL SORTEZI vezi ca aici ai hardcodat sa inserezi tot timpul in primul.
        servers.get(0).addTask(t);
    }

    public List<Server> getServers(){
        return servers;
    }




}
