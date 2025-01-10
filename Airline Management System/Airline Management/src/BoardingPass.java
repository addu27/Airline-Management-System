import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import com.toedter.calendar.JDateChooser;

public class BoardingPass extends JFrame implements ActionListener {

    JTextField tfPnr;
    JLabel tfName, tfNationality, tfSrc, tfDest, tfFname,tfFcode,dcdate;
    JButton fetchButton;

    public BoardingPass(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("AIR INDIA");
        heading.setBounds(380, 10, 450, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        add(heading);

        JLabel subHeading = new JLabel("Boarding Pass");
        subHeading.setBounds(360, 50, 300, 30);
        subHeading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        subHeading.setForeground(Color.BLUE);
        add(subHeading);

        JLabel lblPnr = new JLabel("PNR Details");
        lblPnr.setBounds(60, 100, 150, 25);
        lblPnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblPnr);

        tfPnr = new JTextField();
        tfPnr.setBounds(220, 100, 150, 25);
        add(tfPnr);

        fetchButton = new JButton("Enter");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.white);
        fetchButton.setBounds(380, 100, 125, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);

        JLabel lblName = new JLabel("NAME");
        lblName.setBounds(60, 140, 150, 25);
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblName);

        tfName = new JLabel();
        tfName.setBounds(220, 140, 150, 25);
        add(tfName);

        JLabel lblNationality = new JLabel("NATIONALITY");
        lblNationality.setBounds(60, 180, 150, 25);
        lblNationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblNationality);

        tfNationality = new JLabel();
        tfNationality.setBounds(220, 180, 150, 25);
        add(tfNationality);

        JLabel lblSrc = new JLabel("SRC");
        lblSrc.setBounds(60, 220, 150, 25);
        lblSrc.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblSrc);

        tfSrc = new JLabel();
        tfSrc.setBounds(220, 220, 150, 25);
        add(tfSrc);

        JLabel lblDest = new JLabel("DEST");
        lblDest.setBounds(380, 220, 150, 25);
        lblDest.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblDest);

        tfDest = new JLabel();
        tfDest.setBounds(540, 220, 150, 25);
        add(tfDest);

        JLabel lblFname = new JLabel("Flight Name");
        lblFname.setBounds(60, 260, 150, 25);
        lblFname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblFname);

        tfFname = new JLabel();
        tfFname.setBounds(220, 260, 150, 25);
        add(tfFname);

        JLabel lblCode = new JLabel("Flight Code");
        lblCode.setBounds(380, 260, 150, 25);
        lblCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblCode);

        tfFcode = new JLabel();
        tfFcode.setBounds(540, 260, 150, 25);
        add(tfFcode);

        JLabel lblDate = new JLabel("Date");
        lblDate.setBounds(60, 300, 150, 25);
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblDate);

        dcdate = new JLabel();
        dcdate.setBounds(220, 300, 150, 25);
        add(dcdate);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/airindia.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 230, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblImage = new JLabel(image);
        lblImage.setBounds(600,0,300, 300);
        add(lblImage);

        setSize(1000, 450);
        setLocation(300, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getSource() == fetchButton){
            String pnr = tfPnr.getText();

            try{
                Conn conn = new Conn();
                String query = "select * from reservation where PNR = '"+pnr+"'";

                ResultSet rs = conn.s.executeQuery(query);

                if(rs.next()){
                    tfName.setText(rs.getString("name"));
                    tfFcode.setText(rs.getString("flightcode"));
                    dcdate.setText(rs.getString("ddate"));
                    tfNationality.setText(rs.getString("nationality"));
                    tfSrc.setText(rs.getString("src"));
                    tfDest.setText(rs.getString("des"));
                    tfFname.setText(rs.getString("flightname"));
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter valid PNR");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}
