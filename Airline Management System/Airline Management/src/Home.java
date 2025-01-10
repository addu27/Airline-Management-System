import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Home extends JFrame implements ActionListener {

    public Home(){

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/plane.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1545, 800 );
        add(image);

        JLabel heading = new JLabel("AIR INDIA WELCOMES YOU");
        heading.setBounds(500, 40, 1000, 40);
        heading.setForeground(Color.BLUE);
        heading.setFont(new Font("Tahoma",Font.PLAIN, 36));
        image.add(heading);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu details = new JMenu("Details");
        menuBar.add(details);

        JMenuItem flightDetails = new JMenuItem("Flight Details");
        flightDetails.addActionListener(this);
        details.add(flightDetails);

        JMenuItem customerDetails = new JMenuItem("Add Customer Details");
        customerDetails.addActionListener(this);
        details.add(customerDetails);

        JMenuItem bookFlights = new JMenuItem("Book Flight");
        bookFlights.addActionListener(this);
        details.add(bookFlights);

        JMenuItem journeyDetails = new JMenuItem("Journey Details");
        journeyDetails.addActionListener(this);
        details.add(journeyDetails);

        JMenuItem ticketCancellation = new JMenuItem("Cancel Ticket");
        ticketCancellation.addActionListener(this);
        details.add(ticketCancellation);

        JMenu ticket = new JMenu("Ticket");
        menuBar.add(ticket);

        JMenuItem boardingPass = new JMenuItem("Boarding Pass");
        boardingPass.addActionListener(this);
        ticket.add(boardingPass);

        setExtendedState(JFrame.MAXIMIZED_BOTH);  // open frame in full screen
        setVisible(true);// it will create a frame ^ above are the attributes of frame

    }

    // abstract function is created for ActionListener class in this function action of buttons are assigned to the buttons
    public void actionPerformed(ActionEvent ae){
        String text = ae.getActionCommand();

        if(text.equals("Add Customer Details")){
            new AddCustomer();
        } else if (text.equals("Flight Details")) {
            new FlightInfo();
        } else if (text.equals("Book Flight")) {
            new BookFlight();
        } else if (text.equals("Journey Details")) {
            new JourneyDetails();
        } else if (text.equals("Cancel Ticket")) {
            new Cancel();
        } else if (text.equals("Boarding Pass")) {
            new BoardingPass();
        }
    }

}

