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
        if(user.getClass().isInstance(Customer.class)){
            System.out.println(user.getClass().getName() + "has logged in.");
        }
        System.out.println();
        while(!input.next().toString().equals("exit")){


        }
        System.exit(0);
    }




}
