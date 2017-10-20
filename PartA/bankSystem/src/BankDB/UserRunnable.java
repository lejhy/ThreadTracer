package BankDB;

import java.util.Scanner;


//UserRunnable is essentially a user's 'session' when they are 'logged in'
public class UserRunnable implements Runnable {

    User user;
    Scanner input = new Scanner(System.in);

    public UserRunnable(User u){
        user = u;
    }

    //User thread will run continuously until user exits program. Multiple instances can be created for multiple users 'logging in'
    public void run(){
        while(!input.next().toString().equals("exit")){
            System.out.println("New account: 1");
            System.out.println("");

        }
        System.exit(0);
    }




}
