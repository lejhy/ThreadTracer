import java.util.HashSet;
import java.util.Set;

public class Customer{

    private String ID;
    private String name;
    private String postcode;
    private Set<Account> accounts;

    public Customer(String ID, String name, String postcode){
        this.ID = ID;
        this.name = name;
        this.postcode = postcode;
        accounts = new HashSet<Account>();
    }

    public String getID() { return ID; }

    public String getName() {
        return name;
    }

    public String getPostcode() { return postcode; }

    public Set<Account> getAccounts() { return accounts; }

    public boolean addAccount(Account account) { return accounts.add(account); }

    public boolean removeAccount(Account account) { return accounts.remove(account); }

}
