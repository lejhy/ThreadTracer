package BankDB;

public class Customer{

    private String ID;
    private String name;
    private String postcode;

    public Customer(String ID, String name, String postcode){
        this.ID = ID;
        this.name = name;
        this.postcode = postcode;
    }

    public String getID() { return ID; }

    public String getName() {
        return name;
    }

    public String getPostcode() { return postcode; }

}
