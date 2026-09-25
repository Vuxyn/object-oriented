import java.util.ArrayList;

public class Bank {
    private String bankName;
    private final ArrayList<Customer> customers;

    public Bank() {
        this("Bank");
    }

    public Bank(String bankName) {
        this.bankName = (bankName == null || bankName.isBlank()) ? "Bank" : bankName;
        this.customers = new ArrayList<>();
    }

    public String getBankName() {
        return bankName;
    }

    public Customer addCustomer(String f, String l) {
        Customer cust = new Customer(f, l);
        customers.add(cust);
        return cust;
    }

    public void addCustomer(Customer cust) {
        if (cust == null) {
            return;
        }
        customers.add(cust);
    }

    public int getNumOfCustomers() {
        return customers.size();
    }

    public Customer getCustomer(int index) {
        return customers.get(index);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Customer> findCustomersByName(String keyword) {
        ArrayList<Customer> result = new ArrayList<>();
        if (keyword == null || keyword.isBlank()) {
            return result;
        }
        String key = keyword.toLowerCase();
        for (Customer c : customers) {
            if (c.getFullName().toLowerCase().contains(key)) {
                result.add(c);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return bankName + " (" + getNumOfCustomers() + " nasabah)";
    }
}
