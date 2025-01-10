import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JButton submit, reset, close;
    JTextField tfUsername;
    JPasswordField tfPassword;

    public Login(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(20, 20, 100, 20);  // set the dimensions to the Username
        add(lblUsername);

        tfUsername = new JTextField();
        tfUsername.setBounds(130, 20, 200, 20);
        add(tfUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(20, 60, 100, 20);  // set the dimensions to the Password
        add(lblPassword);

        tfPassword = new JPasswordField();
        tfPassword.setBounds(130, 60, 200, 20);
        add(tfPassword);

        // buttons are added on the frame
        reset = new JButton("Reset");
        reset.setBounds(40,120,120, 20);
        reset.addActionListener(this);
        add(reset);


        submit = new JButton("Submit");
        submit.setBounds(190, 120, 120, 20);
        submit.addActionListener(this);
        add(submit);

        close = new JButton("Close");
        close.setBounds(120, 160, 120, 20);
        close.addActionListener(this);
        add(close);

        setSize(400, 250);
        setLocation(600, 250);
        setVisible(true);// it will create a frame ^ above are the attributes of frame



    }

    // abstract function is created for ActionListener class in this function action of buttons are assigned to the buttons
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == submit){
            String username = tfUsername.getText();
            String password = tfPassword.getText();

            try{
                Conn c = new Conn();

                String query = "select * from login where username = '" + username + "' and password = '" + password + "'";
                ResultSet rs = c.s.executeQuery(query);

                if(rs.next()){
                    new Home();
                    setVisible(false);
                } else{
                    JOptionPane.showMessageDialog(null, "Invalid Username or password");
                    setVisible(false);
                }

            }catch (Exception e){
                e.printStackTrace();
            }


        } else if (ae.getSource() == close) {
            setVisible(false);
        } else if (ae.getSource() == reset) {
            tfUsername.setText("");
            tfPassword.setText("");
        }
    }

}
