import java.util.ArrayList;

public class Customer {
    private String firstName;
    private String lastName;
    private final ArrayList<Account> accounts;

    public Customer(String f, String l) {
        this.firstName = f;
        this.lastName = l;
        this.accounts = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setAccount(Account acct) {
        if (acct == null) {
            return;
        }
        if (accounts.isEmpty()) {
            accounts.add(acct);
        } else {
            accounts.set(0, acct);
        }
    }

    public Account getAccount() {
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    public void addAccount(Account acct) {
        if (acct == null) {
            return;
        }
        accounts.add(acct);
    }

    public Account getAccount(int index) {
        return accounts.get(index);
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public int getNumOfAccounts() {
        return accounts.size();
    }

    public double getTotalBalance() {
        double total = 0;
        for (Account acc : accounts) {
            total += acc.getBalance();
        }
        return total;
    }

    @Override
    public String toString() {
        return getFullName() + " (" + getNumOfAccounts() + " akun, total: "
                + String.format("%.2f", getTotalBalance()) + ")";
    }
}
