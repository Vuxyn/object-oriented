public class Bank {
    private int balance;
    public Bank next;

    public Bank(int balance){
        this.balance = balance;
        this.next = null;
    }

    public void deposit(int value){ 
        System.out.println("Deposit: " + value);
        this.balance += value; 
    }
    public int withdraw(int amount){ 
        System.out.println("Withdraw:" + amount);
        this.balance -= amount;
        return amount;
    }
    public int getBalance(){ return this.balance; }

    public void info(){
        System.out.println("Current balance: " + getBalance());
    }

}