import java.util.ArrayList;

public class Customer {
    private String firstName;
    private String lastName;
    private ArrayList<Account> accounts;

    public Customer(String first, String last) {
        this.firstName = first;
        this.lastName = last;
        this.accounts = new ArrayList<Account>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void addAccount(Account acct) {
        accounts.add(acct);
    }

    public Account getAccount(int index) {
        return accounts.get(index);
    }

    public int getNumOfAccounts() {
        return accounts.size();
    }

    public void setAccount(Account acct) {
        if (accounts.isEmpty()) {
            accounts.add(acct);
        } else {
            accounts.set(0, acct);
        }
    }

    public Account getAccount() {
        if (accounts.isEmpty()) {
            return null;
        }
        return accounts.get(0);
    }
}
