import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import com.toedter.calendar.JDateChooser;

public class BookFlight extends JFrame implements ActionListener {

    JTextField tfAadhar;
    JLabel tfName, tfNationality, tfAddress, labelGender, tfFname,tfFcode;
    JButton bookFlight, fetchButton, flight;
    Choice source, destination;
    JDateChooser dcdate;

    public BookFlight(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(420, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel lblAadhar = new JLabel("Aadhar Number");
        lblAadhar.setBounds(60, 80, 150, 25);
        lblAadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblAadhar);

        tfAadhar = new JTextField();
        tfAadhar.setBounds(220, 80, 150, 25);
        add(tfAadhar);

        fetchButton = new JButton("Fetch User");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.white);
        fetchButton.setBounds(380, 80, 125, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(60, 130, 150, 25);
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblName);

        tfName = new JLabel();
        tfName.setBounds(220, 130, 150, 25);
        add(tfName);

        JLabel lblNationality = new JLabel("Nationality");
        lblNationality.setBounds(60, 180, 150, 25);
        lblNationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblNationality);

        tfNationality = new JLabel();
        tfNationality.setBounds(220, 180, 150, 25);
        add(tfNationality);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(60, 230, 150, 25);
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblAddress);

        tfAddress = new JLabel();
        tfAddress.setBounds(220, 230, 150, 25);
        add(tfAddress);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(60, 280, 150, 25);
        lblGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblGender);

        labelGender = new JLabel("Gender");
        labelGender.setBounds(220, 280, 150, 25);
        add(labelGender);

        JLabel lblSource = new JLabel("Source");
        lblSource.setBounds(60, 330, 150, 25);
        lblSource.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblSource);

        source = new Choice();
        source.setBounds(220, 330, 150, 25);
        add(source);

        JLabel lblDestination = new JLabel("Destination");
        lblDestination.setBounds(60, 380, 150, 25);
        lblDestination.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblDestination);

        destination = new Choice();
        destination.setBounds(220, 380, 150, 25);
        add(destination);

        try {
            Conn conn = new Conn();
            String query = "select * from flight";
            ResultSet rs = conn.s.executeQuery(query);

            while (rs.next()){
                source.add(rs.getString("source"));
                destination.add(rs.getString("destination"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        flight = new JButton("Fetch Flights");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.white);
        flight.setBounds(380, 380, 150, 25);
        flight.addActionListener(this);
        add(flight);

        JLabel lblFname = new JLabel("Flight Name");
        lblFname.setBounds(60, 430, 150, 25);
        lblFname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblFname);

        tfFname = new JLabel();
        tfFname.setBounds(220, 430, 150, 25);
        add(tfFname);

        JLabel lblCode = new JLabel("Flight Code");
        lblCode.setBounds(60, 480, 150, 25);
        lblCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblCode);

        tfFcode = new JLabel();
        tfFcode.setBounds(220, 480, 150, 25);
        add(tfFcode);

        JLabel lblDate = new JLabel("Date of Travel");
        lblDate.setBounds(60, 530, 150, 25);
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblDate);

        dcdate = new JDateChooser();
        dcdate.setBounds(220, 530, 150, 25);
        add(dcdate);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/details.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 320, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblImage = new JLabel(image);
        lblImage.setBounds(550,80,500, 410);
        add(lblImage);

        bookFlight = new JButton("Book Flight");
        bookFlight.setBackground(Color.BLACK);
        bookFlight.setForeground(Color.white);
        bookFlight.setBounds(220, 580, 150, 25);
        bookFlight.addActionListener(this);
        add(bookFlight);


        setSize(1100, 700);
        setLocation(200, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getSource() == fetchButton){
            String aadhar = tfAadhar.getText();

            try{
                Conn conn = new Conn();
                String query = "select * from passenger where aadhar = '"+aadhar+"'";

                ResultSet rs = conn.s.executeQuery(query);

                if(rs.next()){
                    tfName.setText(rs.getString("name"));
                    tfNationality.setText(rs.getString("nationality"));
                    tfAddress.setText(rs.getString("address"));
                    labelGender.setText(rs.getString("gender"));

                } else {
                    JOptionPane.showMessageDialog(null, "Please enter valid aadhar number");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(ae.getSource() == flight){
            String src = source.getSelectedItem();
            String dest = destination.getSelectedItem();

            try{
                Conn conn = new Conn();
                String query = "select * from flight where source = '"+src+"' and destination = '"+dest+"' ";

                ResultSet rs = conn.s.executeQuery(query);

                if(rs.next()){
                    tfFname.setText(rs.getString("f_name"));
                    tfFcode.setText(rs.getString("f_code"));

                } else {
                    JOptionPane.showMessageDialog(null, "No flights found");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // storing the data when flight is booked
            Random random = new Random();
            String aadhar =  tfAadhar.getText();
            String name =  tfName.getText();
            String nationality = tfNationality.getText();
            String flightName = tfFname.getText();
            String flightCode = tfFcode.getText();
            String src =  source.getSelectedItem();
            String dest = destination.getSelectedItem();
            String  ddate = ((JTextField)dcdate.getDateEditor().getUiComponent()).getText();

            try{
                Conn conn = new Conn();
                String query = "insert into reservation values ('PNR-"+random.nextInt(1000000)+"', 'TIC-"+random.nextInt(1000000)+"' , '"+aadhar+"' , '"+name+"', '"+nationality+"' , '"+flightName+"' , '"+flightCode+"' , '"+src+"' ,  '"+dest+"' , '"+ddate+"' )";

                conn.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null,"Ticket Booked Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
