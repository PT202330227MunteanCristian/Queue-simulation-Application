

//import org.example.BusinessLogic.SimulationManager;

import javax.swing.*;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationController {

    private SimulationView view;
    private SimulationManager gen;

    public SimulationController(SimulationView view) {
        this.view = view;
        view.addBtnListener(new BtnListener());
        view.addSecondBtnActionListener(new StopListener());
    }


    class BtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                int timeLimit = Integer.parseInt(view.getTimeLimit());
                int noServers = Integer.parseInt(view.getServers());
                int noClients = Integer.parseInt(view.getNoClients());
                int minProc = Integer.parseInt(view.getMinProc());
                int maxProc = Integer.parseInt(view.getMaxProc());
                if (minProc > maxProc){
                    throw new Exception("Bound smaller than origin");
                }
                int minArriv = Integer.parseInt(view.getMinArr());
                int maxArriv = Integer.parseInt(view.getMaxArr());
                if (minArriv> maxArriv){
                    throw new Exception("Bound smaller than origin");
                }
                view.getFrame().setVisible(false);
                view.setAnotherFrame();
                gen = new SimulationManager(view, timeLimit,minProc, maxProc, minArriv, maxArriv, noClients, noServers);
                Thread t = new Thread(gen);
                t.setName("MAIN");
                t.start();
            }catch(NumberFormatException i){
                view.showMessage("One field is wrong. Please re-try!");
            }catch (Exception f){
                view.showMessage(f.getMessage());
            }


        }
    }

    class StopListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            gen.setStop(true);
        }
    }
}
