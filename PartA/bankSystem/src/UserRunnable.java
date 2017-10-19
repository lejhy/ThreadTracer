import BankDB.*;

import java.util.Scanner;

public class UserRunnable implements Runnable {


    Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        new Driver();
        Runnable user1 = new UserRunnable(new Clerk());
        Runnable user2 = new UserRunnable(new Customer("Conner", "G21"));
    }

    //User thread will run continuously until user exits program. Multiple instances can be created for multiple users 'logging in'
    public void run(){
        while(!input.next().toString().equals("exit")){
            System.out.println("New account: 1");

        }
        System.exit(0);
    }

}
