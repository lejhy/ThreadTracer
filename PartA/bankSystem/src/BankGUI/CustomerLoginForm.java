package BankGUI;

import javax.swing.*;
import java.awt.*;

public class CustomerLoginForm extends JFrame{

    public static void main(String[] args){
        new CustomerLoginForm();
    }

    public static Dimension DEF_CLOGIN_SIZE = new Dimension(300, 150);
    public  static Dimension DEF_BUTTON_SIZE = new Dimension(100, 50);

    JTextField nameField;
    JTextField postcodeField;
    JButton cancelButton;
    JButton loginButton;

    public CustomerLoginForm(){
        nameField = new JTextField();
        postcodeField = new JTextField();
        cancelButton = new JButton("Cancel");
        loginButton = new JButton("Log In");

        this.setTitle("Customer Log In");
        this.setSize(DEF_CLOGIN_SIZE);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new GridLayout(3, 2));

        this.add(new JLabel("Name: "));
        this.add(nameField);
        this.add(new JLabel("Postcode: "));
        this.add(postcodeField);

        cancelButton.setSize(DEF_BUTTON_SIZE);
        this.add(cancelButton);

        loginButton.setSize(DEF_BUTTON_SIZE);
        this.add(loginButton);




        this.setVisible(true);
    }
}
