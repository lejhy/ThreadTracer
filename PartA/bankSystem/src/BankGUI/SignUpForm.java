package BankGUI;

import javax.swing.*;
import java.awt.*;

public class SignUpForm extends JFrame {

    public static Dimension DEF_SIGNUP_SIZE = new Dimension(300, 150);
    public  static Dimension DEF_BUTTON_SIZE = new Dimension(100, 50);

    JButton cancelButton;
    JButton signUpButton;
    JTextField nameField;
    JTextField postcodeField;

    public  static void main(String[] args){

        new SignUpForm();

    }

    public SignUpForm(){
        nameField = new JTextField();
        postcodeField = new JTextField();
        cancelButton = new JButton("Cancel");
        signUpButton = new JButton("Sign Up");

        this.setTitle("Sign Up");
        this.setSize(DEF_SIGNUP_SIZE);
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

        signUpButton.setSize(DEF_BUTTON_SIZE);
        this.add(signUpButton);




        this.setVisible(true);


    }

}
