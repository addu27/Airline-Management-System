import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddCustomer extends JFrame implements ActionListener {

    JTextField tfName, tfPhone, tfAadhar, tfNationality, tfAddress;
    JRadioButton rbMale, rbFemale;
    public AddCustomer(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("ADD CUSTOMER DETAILS");
        heading.setBounds(225, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(60, 80, 150, 25);
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblName);

        tfName = new JTextField();
        tfName.setBounds(220, 80, 150, 25);
        add(tfName);

        JLabel lblNationality = new JLabel("Nationality");
        lblNationality.setBounds(60, 130, 150, 25);
        lblNationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblNationality);

        tfNationality = new JTextField();
        tfNationality.setBounds(220, 130, 150, 25);
        add(tfNationality);

        JLabel lblAadhar = new JLabel("Aadhar Number");
        lblAadhar.setBounds(60, 180, 150, 25);
        lblAadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblAadhar);

        tfAadhar = new JTextField();
        tfAadhar.setBounds(220, 180, 150, 25);
        add(tfAadhar);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(60, 230, 150, 25);
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblAddress);

        tfAddress = new JTextField();
        tfAddress.setBounds(220, 230, 150, 25);
        add(tfAddress);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(60, 280, 150, 25);
        lblGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblGender);

        // group the gender button
        ButtonGroup genderGroup = new ButtonGroup();

        rbMale = new JRadioButton("Male");
        rbMale.setBounds(220, 280, 70, 25);
        rbMale.setBackground(Color.white);
        add(rbMale);

        rbFemale = new JRadioButton("Female");
        rbFemale.setBounds(300, 280, 70, 25);
        rbFemale.setBackground(Color.white);
        add(rbFemale);

        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);

        JLabel lblPhone = new JLabel("Phone Number");
        lblPhone.setBounds(60, 330, 150, 25);
        lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblPhone);

        tfPhone = new JTextField();
        tfPhone.setBounds(220, 330, 150, 25);
        add(tfPhone);

        JButton save = new JButton("SAVE");
        save.setBackground(Color.BLACK);
        save.setForeground(Color.white);
        save.setBounds(220, 380, 150, 30);
        save.addActionListener(this);
        add(save);

        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("icons/emp.png"));
        JLabel lblImage = new JLabel(image);
        lblImage.setBounds(450,80,280, 400);
        add(lblImage);


        setSize(900, 600);
        setLocation(300, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        String name = tfName.getText();
        String nationality = tfNationality.getText();
        String phone = tfPhone.getText();
        String address = tfAddress.getText();
        String aadhar = tfAadhar.getText();
        String gender = null;

        if(rbMale.isSelected()){
            gender = "Male";
        } else {
            gender = "Female";
        }

        try{
            Conn conn = new Conn();
            String query = "insert into passenger values('" + name + "' , '"+nationality+"' , '"+phone+"' , '"+address+"' , '"+aadhar+"' , '"+gender+"')";

            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Customer details added successfully");
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
