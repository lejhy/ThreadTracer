package BankDB;

import java.util.Scanner;


//UserRunnable is essentially a user's 'session' when they are 'logged in'
public class UserRunnable implements Runnable {

    User user;
    Bank bank;

    public UserRunnable(User u, Bank b){
        user = u;
        bank = b;
    }

    public void run(){

        System.out.println(Thread.currentThread().getName() + "thread with ID: " + Thread.currentThread().getId() + " has started.");


    }




}
