public class Bank {
    private Customer[] customers;
    private int numberOfCustomers;

    public Bank() {
        customers = new Customer[10];
        numberOfCustomers = 0;
    }

    public void addCustomer(String f, String l) {
        Customer cust = new Customer(f, l);
        customers[numberOfCustomers] = cust;
        numberOfCustomers++;
    }

    public int getNumOfCustomers() {
        return numberOfCustomers;
    }

    public Customer getCustomer(int index) {
        if (index >= 0 && index < numberOfCustomers) {
            return customers[index];
        }
        return null;
    }

    public Customer getCustomer(String name) {
        String key = name.toLowerCase();
        for (int i = 0; i < numberOfCustomers; i++) {
            String full = (customers[i].getFirstName() + " " + customers[i].getLastName()).toLowerCase();
            if (full.contains(key)) {
                return customers[i];
            }
        }
        return null;
    }
}
