public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.addCustomer("Jane", "Smith");
        bank.addCustomer("Owen", "Bryant");

        bank.getCustomer(0).addAccount(new Account(500.00));
        bank.getCustomer(1).addAccount(new Account(200.00));

        bank.getCustomer(0).getAccount(0).deposit(100.00);
        bank.getCustomer(0).getAccount(0).withdraw(200.00);
        bank.getCustomer(1).getAccount(0).deposit(50.00);

        for (int i = 0; i < bank.getNumOfCustomers(); i++) {
            Customer c = bank.getCustomer(i);
            System.out.println(c.getFirstName() + " " + c.getLastName()
                    + " : " + c.getAccount(0).getBalance());
        }
    }
}
