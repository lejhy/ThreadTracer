package BankGUI;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame{
    private JPanel panel1;
    public static Dimension DEF_MAIN_SIZE = new Dimension(600, 300);

    public static void main(String[] args){
        new MainForm();
    }

    public MainForm(){
        this.setTitle("Bank of You");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(DEF_MAIN_SIZE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        InitialForm startForm = new InitialForm();
    }

}
