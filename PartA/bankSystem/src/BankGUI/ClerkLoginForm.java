package BankGUI;

import javax.swing.*;
import java.awt.*;

public class ClerkLoginForm extends JFrame {

    public static void main(String[] args){
        new ClerkLoginForm();
    }

    public static Dimension DEF_CLERK_LOGIN_SIZE = new Dimension(300, 150);

    JTextField passField;
    JButton loginButton;
    JButton cancelButton;
    JPanel bottomPanel;

    public ClerkLoginForm(){
        this.setTitle("Clerk Login");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(DEF_CLERK_LOGIN_SIZE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(3, 1));

        this.add(new JLabel("Please enter your Clerk code:"));

        passField = new JTextField();
        this.add(passField);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));

        cancelButton = new JButton("Cancel");
        bottomPanel.add(cancelButton);

        loginButton = new JButton("Log In");
        bottomPanel.add(loginButton);

        this.add(bottomPanel);





        this.setVisible(true);
    }

}
