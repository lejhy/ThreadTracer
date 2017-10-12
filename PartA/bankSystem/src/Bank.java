import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bank {

    HashMap<Customer, List<Account>> openAccounts;


    public Bank(){
        openAccounts = new HashMap<Customer, List<Account>>();
    }

    public boolean joiningCustomer(String name, String postcode){
        for(Customer customer : openAccounts.keySet()) {

            if (customer.getName().equals(name) && customer.getPostcode().equals(postcode)) { //If the customer has already joined
                System.out.println("You have already enrolled!");
                return false;
            }

        }
        openAccounts.put(new Customer(name, postcode), new ArrayList<Account>());
        return true;
    }

    public boolean openAccount(Customer customer, int t){

        switch (t){
            case 1 :
                //openAccounts.put();
                break;
            case 2 :
                //
                break;
            case 3 :
                //
                break;
            default :
                System.out.println("Invalid selection, please try again.");
        }

        return true;
    }

    public Customer customerSearch(String name, String postcode){
        for(Customer customer : openAccounts.keySet()){
            if(customer.getName().equals(name) && customer.getPostcode().equals(postcode)){
                return customer;
            }
        }
        System.out.println("Customer not found.");
        return null;
    }



}
