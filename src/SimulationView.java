

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SimulationView extends JFrame {

    private JFrame frame;
    private JFrame frame2;
    private JTextField timeField;
    private JTextField serversField;
    private JTextField clientField;
    private JTextField minProcField;
    private JTextField maxProcField;
    private JTextField minArrivField;
    private JTextField maxArrivField;
    private JButton button;

    private JButton  stopButton = new JButton("STOP");

    private JTextArea textArea;


    public SimulationView(){
        frame = new JFrame();
        frame.setBounds(100, 100, 550, 650);				
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JPanel colorPor = new JPanel();
        colorPor.setBounds(0, 0, 550, 650);
        colorPor.setBackground(Color.darkGray);

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 180, 485);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
     Font labelFont = new Font("Arial", Font.BOLD, 16);
     JLabel timeLabel = new JLabel("Time Limit");
     timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
     timeLabel.setBounds(3, 27, 177, 14);
     timeLabel.setFont(labelFont);
     timeLabel.setForeground(Color.WHITE); // set the font color to white
     panel.setBackground(new Color(44, 62, 80)); // a dark blue-gray color
     panel.setBorder(BorderFactory.createLineBorder(new Color(127, 140, 141), 2)); // a light gray color
     panel.add(timeLabel);

        JLabel serversLabel = new JLabel("Number of servers");
        serversLabel.setHorizontalAlignment(SwingConstants.CENTER);
        serversLabel.setBounds(3, 87, 176, 14);
        panel.add(serversLabel);
        
        serversLabel.setHorizontalAlignment(SwingConstants.CENTER);
        serversLabel.setFont(labelFont);
        serversLabel.setForeground(Color.WHITE); // set the font color to white

        JLabel clientsLabel = new JLabel("Number of clients");
        clientsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clientsLabel.setBounds(0, 128, 180, 14);
        panel.add(clientsLabel);
        
        clientsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clientsLabel.setFont(labelFont);
        clientsLabel.setForeground(Color.WHITE); // set the font color to white

        JLabel minProcTime = new JLabel("Minimum Proc. Time");
        minProcTime.setHorizontalAlignment(SwingConstants.CENTER);
        minProcTime.setBounds(3, 226, 177, 14);
        panel.add(minProcTime);
        
        minProcTime.setHorizontalAlignment(SwingConstants.CENTER);
        minProcTime.setFont(labelFont);
        minProcTime.setForeground(Color.WHITE); // set the font color to white

        JLabel maxProcTime = new JLabel("Maximum Proc. Time");
        maxProcTime.setHorizontalAlignment(SwingConstants.CENTER);
        maxProcTime.setBounds(2, 260, 179, 14);
        panel.add(maxProcTime);
        
        maxProcTime.setHorizontalAlignment(SwingConstants.CENTER);
        maxProcTime.setFont(labelFont);
        maxProcTime.setForeground(Color.WHITE); // set the font color to white

        JLabel minArrivLabel = new JLabel("Minimum Arriv Time");
        minArrivLabel.setHorizontalAlignment(SwingConstants.CENTER);
        minArrivLabel.setBounds(-1, 364, 177, 14);
        panel.add(minArrivLabel);
        
        minArrivLabel.setHorizontalAlignment(SwingConstants.CENTER);
        minArrivLabel.setFont(labelFont);
        minArrivLabel.setForeground(Color.WHITE); // set the font color to white

        JLabel maxArrivLabel = new JLabel("Maximum Arriv Time");
        maxArrivLabel.setHorizontalAlignment(SwingConstants.CENTER);
        maxArrivLabel.setBounds(1, 397, 177, 14);
        panel.add(maxArrivLabel);
        
        maxArrivLabel.setHorizontalAlignment(SwingConstants.CENTER);
        maxArrivLabel.setFont(labelFont);
        maxArrivLabel.setForeground(Color.WHITE); // set the font color to white

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(229, 12, 292, 486);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        timeField = new JTextField();
        timeField.setBounds(26, 20, 100, 30);
        panel_1.add(timeField);
        timeField.setColumns(10);

        serversField = new JTextField();
        serversField.setBounds(26, 83, 100, 30);
        panel_1.add(serversField);
        serversField.setColumns(10);

        clientField = new JTextField();
        clientField.setColumns(10);
        clientField.setBounds(26, 118, 100, 30);
        panel_1.add(clientField);

        minProcField = new JTextField();
        minProcField.setColumns(10);
        minProcField.setBounds(26, 219, 100, 30);
        panel_1.add(minProcField);

        maxProcField = new JTextField();
        maxProcField.setColumns(10);
        maxProcField.setBounds(26, 254, 100, 30);
        panel_1.add(maxProcField);

        minArrivField = new JTextField();
        minArrivField.setColumns(10);
        minArrivField.setBounds(26, 359, 100, 30);
        panel_1.add(minArrivField);

        maxArrivField = new JTextField();
        maxArrivField.setColumns(10);
        maxArrivField.setBounds(26, 393, 100, 30);
        panel_1.add(maxArrivField);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(152, 522, 219, 70);
        frame.getContentPane().add(panel_2);
        panel_2.setLayout(new GridLayout(0, 1, 0, 0));

        button = new JButton("START!");
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLUE);
        panel_2.add(button);
        
        panel_1.setBackground(Color.darkGray);
        panel_2.setBackground(Color.darkGray);
        
        frame.add(colorPor);
        frame.setVisible(true);

    }

   


    public void addBtnListener(ActionListener btnListener){
        this.button.addActionListener(btnListener);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JFrame getFrame2(){
        return frame2;
    }


    public void setAnotherFrame(){

        frame2 = new JFrame();
        frame2.setBounds(100, 100, 550, 650);
        frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame2.getContentPane().setLayout(null);


        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 515, 555);
        frame2.getContentPane().add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);


        stopButton.setBounds(222, 577, 89, 23);
        frame2.getContentPane().add(stopButton);
        frame2.setVisible(true);



    }

    public void setTextArea(String s){
        textArea.setText(s);
    }

    public String getTimeLimit(){
        return this.timeField.getText();
    }

    public String getNoClients(){
        return this.clientField.getText();
    }

    public String getServers(){
        return this.serversField.getText();
    }

    public String getMinProc(){
        return this.minProcField.getText();
    }

    public String getMaxProc(){
        return this.maxProcField.getText();
    }

    public String getMinArr(){
        return this.minArrivField.getText();
    }

    public String getMaxArr(){
        return this.maxArrivField.getText();
    }

    void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void addSecondBtnActionListener(ActionListener e){
        stopButton.addActionListener(e);
    }
}
