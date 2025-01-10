import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import com.toedter.calendar.JDateChooser;

public class Cancel extends JFrame implements ActionListener {

    JTextField tfpnr;
    JLabel tfName, tfFcode, tfCancel, tfdate;
    JButton fetchButton, cancelBtn;
    JDateChooser dcdate;

    public Cancel(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        Random random = new Random();

        JLabel heading = new JLabel("Cancellation");
        heading.setBounds(180, 20, 250, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/cancel.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblImage = new JLabel(image);
        lblImage.setBounds(470,120,250, 250);
        add(lblImage);

        JLabel lblpnr = new JLabel("PNR Number");
        lblpnr.setBounds(60, 80, 150, 25);
        lblpnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblpnr);

        tfpnr = new JTextField();
        tfpnr.setBounds(220, 80, 150, 25);
        add(tfpnr);

        fetchButton = new JButton("Show Details");
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

        JLabel lblCancellationNo = new JLabel("Cancellation No");
        lblCancellationNo.setBounds(60, 180, 150, 25);
        lblCancellationNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblCancellationNo);

        tfCancel = new JLabel("" + random.nextInt(1000000));
        tfCancel.setBounds(220, 180, 150, 25);
        add(tfCancel);

        JLabel lblFcode = new JLabel("Flight Code");
        lblFcode.setBounds(60, 230, 150, 25);
        lblFcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblFcode);

        tfFcode = new JLabel();
        tfFcode.setBounds(220, 230, 150, 25);
        add(tfFcode);

        JLabel lblDate = new JLabel("Date");
        lblDate.setBounds(60, 330, 150, 25);
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblDate);

        tfdate = new JLabel();
        tfdate.setBounds(220, 330, 150, 25);
        add(tfdate);

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(Color.BLACK);
        cancelBtn.setForeground(Color.white);
        cancelBtn.setBounds(220, 380, 150, 25);
        cancelBtn.addActionListener(this);
        add(cancelBtn);

        setSize(800, 450);
        setLocation(350, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getSource() == fetchButton){
            String pnr = tfpnr.getText();

            try{
                Conn conn = new Conn();
                String query = "select * from reservation where PNR = '"+pnr+"'";

                ResultSet rs = conn.s.executeQuery(query);

                if(rs.next()){
                    tfName.setText(rs.getString("name"));
                    tfFcode.setText(rs.getString("flightcode"));
                    tfdate.setText(rs.getString("ddate"));

                } else {
                    JOptionPane.showMessageDialog(null, "Please enter valid PNR");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(ae.getSource() == cancelBtn){
            String name = tfName.getText();
            String pnr = tfpnr.getText();
            String cancelNo = tfCancel.getText();
            String fcode = tfFcode.getText();
            String date = tfdate.getText();

            try{
                Conn conn = new Conn();
//                String query = "insert into cancel values ( '"+pnr+"' , '"+name+"', '"+cancelNo+"' , '"+fcode+"' , '"+date+"') ";

                String query = "INSERT INTO cancel (pnr, name, cancelno, fcode, ddate) VALUES ('" + pnr + "', '" + name + "', '" + cancelNo + "', '" + fcode + "', '" + date + "')";


                conn.s.executeUpdate(query);

                conn.s.executeUpdate("delete from reservation where PNR = '"+pnr+"'");

                JOptionPane.showMessageDialog(null, "Ticket Canceled");
                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
