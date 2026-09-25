public class Account {
    private static int nextNumber = 1001;

    private final String accountNumber;
    private double balance;

    public Account(double initBalance) {
        this("ACC" + (nextNumber++), initBalance);
    }

    public Account(String accountNumber, double initBalance) {
        if (accountNumber == null || accountNumber.isBlank()) {
            accountNumber = "ACC" + (nextNumber++);
        }
        if (initBalance < 0) {
            initBalance = 0;
        }
        this.accountNumber = accountNumber;
        this.balance = initBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amt) {
        if (amt <= 0) {
            return false;
        }
        balance += amt;
        return true;
    }

    public boolean withdraw(double amt) {
        if (amt <= 0 || amt > balance) {
            return false;
        }
        balance -= amt;
        return true;
    }

    @Override
    public String toString() {
        return accountNumber + " | saldo: " + String.format("%.2f", balance);
    }
}
