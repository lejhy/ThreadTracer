import java.util.HashMap;
import java.util.List;

public class Senario {

//    public void Senario1() {
//
//
//        //Create Thread Groups.
//        //Create Runnable.
//        //Create Thread with runnable target, also assign Threads to Groups.
//        //Start Threads.
//
//        String temp; //A placeholder for any string value we take from the user. Values generally aren't used more than once anyway.
//        String name;
//        String postcode;
//        boolean validCategory = false;
//        BankDB.Bank bank = new BankDB.Bank();
//        ThreadGroup clerkThreads = new ThreadGroup(Thread.currentThread().getThreadGroup(), "clerks"); //main ThreadGroup set as parent.
//        ThreadGroup customersThreads = new ThreadGroup(Thread.currentThread().getThreadGroup(), "customers"); //main ThreadGroup set as parent.
//        Clerk clerk1 = new Clerk(); //A clerk wouldn't sign up for an account, so would need create the Clerk along with the Bank.
//        Customer customer1 = new Customer("Conner", "G21");
//        Customer customer2 = new Customer("Kuba", "G1");
//
//        Thread cust1Thread = new Thread(new UserRunnable(customer1, bank));
//        Thread cust2Thread = new Thread(new UserRunnable(customer2, bank));
//
//        bank.openAccount(customer1, Bank.CURRENT_ACCOUNT, "Current Account 1");
//
//        cust1Thread.start();
//
//        //Both will access the same account simultaneously.
//
//
//        //Threads are created and are all started.
//        //At the moment does not do much.
//
//
////        bank.joiningCustomer("Conner", "G21");
////        bank.openAccount(bank.customerSearch("Conner", "G21"), 1);
////        bank.openAccount(bank.customerSearch("Conner", "G21"), 2);
////        bank.openAccount(bank.customerSearch("Conner", "G21"), 3);
////        System.out.println("Checking first account interest...");
////        System.out.println(bank.getCustomerDB().get(bank.customerSearch("Conner", "G21")).get(0).getInterest());
////
////        System.out.print("Search account: ");
////        int num = userInput.nextInt();
////        Account acc = bank.accountSearch(num);
////        if(acc != null) {
////            System.out.println("Account balance: " + acc.getBalance());
////        }
////
////        System.out.print("Make a deposit: ");
////        double val = userInput.nextDouble();
////        acc.deposit(val);
////        System.out.println("Balance: "+acc.getBalance());
////
////        System.out.print("Make a withdrawal: ");
////        val = userInput.nextDouble();
////        System.out.println("Withdrawn: "+acc.withdraw(val));
////        System.out.println("Balance: "+acc.getBalance());
//
//    }

    /*
    public void Senario2(){

        Bank bank = new Bank();

        Customer customer1 = new Customer("name1","ps1");
        Customer customer2 = new Customer("name2","ps2");

        bank.openAccount(customer1,Bank.CURRENT_ACCOUNT,"accountname1");

        bank.joiningCustomer(customer1.name,customer1.postcode);

        HashMap <Customer,List<Account>> hashMap= bank.getCustomerDB();
        List<Account> list1 = hashMap.get(customer1);
        Account account1 = list1.get(0);


        Thread customer1Thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                account1.deposit(100);
            }
        });

        customer1Thread1.start();

        Thread customer1Thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                account1.deposit(100);
            }
        });

        customer1Thread2.start();

    }
    */

    public void Senario3(){
        Bank b = new Bank();
        Customer c1 = new Customer("ID1", "Geaorge McWashiton", "G114u");
        b.addCustomer(c1);
        int accNum = b.openAccount(c1.getID(), Account.Type.SAVINGS, "Saving up for a teddy bear");
        Account a = b.getAccount(accNum);
        Thread t1 = new Thread(()->a.deposit(1000));
        Thread t2 = new Thread(()->a.deposit(1000));
        System.out.println("***Senario3 : Adding 2 deposits to the bank account current value £" + a.balance);
        t1.start();
        t2.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("***Senario3 : Ohh sleep was disturbed?");
        }
        System.out.println("***Senario3 : The threads should have been added by now with account ballances at £" + a.balance);
    }

    public void Senario4(){
        Bank b = new Bank();
        Customer c1 = new Customer("ID1", "Geaorge McWashiton", "G11 4U");
        Customer c2 = new Customer("ID2", "Wallace William"   , "T54 A1");
        b.addCustomer(c1);
        b.addCustomer(c2);
        int accNum = b.openAccount(c1.getID(), Account.Type.SAVINGS, "Saving up for a teddy bear");
        Account a = b.getAccount(accNum);
        System.out.println("***Senario4 : Adding user to account");
        a.addOwner(c2);
        System.out.println("***Senario4 : Has the " + c2.getAccounts().contains(a));
        System.out.println("***Senario4 : The owners of the account are : " + a.getOwners());
    }


    public static void main(String[] args){
        new Senario().Senario4();
    }
}
