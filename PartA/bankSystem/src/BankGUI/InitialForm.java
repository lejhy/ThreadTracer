package BankGUI;

import javax.swing.*;
import java.awt.*;

public class InitialForm extends JFrame{
    public static Dimension DEF_INITIAL_SIZE = new Dimension(250, 100);
    public static Dimension DEF_BUTTON_SIZE = new Dimension(200, 75);

    public  static void main(String[] args){

        new InitialForm();

    }

    public InitialForm(){
        JPanel topPanel;
        JPanel bottomPanel;

        JPanel bottomLeftPanel;
        JPanel bottomRightPanel;

        JButton loginButton;
        JButton signUpButton;

        this.setTitle("Bank of You");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setMaximumSize(DEF_INITIAL_SIZE);
        this.setMinimumSize(DEF_INITIAL_SIZE);
        this.setLocationRelativeTo(null);
        this. setResizable(false);
        this.setLayout(new GridLayout(2, 1));

        topPanel = new JPanel();
        bottomPanel = new JPanel();
        topPanel.add(new JLabel("Welcome to Your Bank!"));
        this.add(topPanel);
        this.add(bottomPanel);

        bottomPanel.setLayout(new GridLayout(1, 2));
        bottomLeftPanel = new JPanel();
        bottomRightPanel = new JPanel();
        bottomPanel.add(bottomLeftPanel);
        bottomPanel.add(bottomRightPanel);


        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");
        loginButton.setSize(DEF_BUTTON_SIZE);
        signUpButton.setSize(DEF_BUTTON_SIZE);
        bottomLeftPanel.add(loginButton);
        bottomRightPanel.add(signUpButton);

        this.setVisible(true);

    }

}
