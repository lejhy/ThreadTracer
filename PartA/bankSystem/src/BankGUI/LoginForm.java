package BankGUI;


import javax.swing.*;
import java.awt.*;

//The LoginForm essentially creates and runs the threads as the users 'login'
public class LoginForm extends JFrame{

    public static Dimension DEF_LOGIN_SIZE = new Dimension(300, 150);
    public  static Dimension DEF_BUTTON_SIZE = new Dimension(100, 50);

    public  static void main(String[] args){

        new LoginForm();
    }
    JComboBox<String> dropDown;
    JButton loginButton;

    public LoginForm(){
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Login");
        this.setSize(DEF_LOGIN_SIZE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new GridLayout(3, 1));
        this.add(new JLabel("Are you a Customer or Clerk?"));

        dropDown = new JComboBox<String>();

        dropDown.addItem("Customer");
        dropDown.addItem("Clerk");
        dropDown.setSelectedIndex(0);
        this.add(dropDown);

        loginButton = new JButton("Log In");
        loginButton.setSize(DEF_BUTTON_SIZE);
        this.add(loginButton);


        this.setVisible(true);
    }



}
