
public class BankDemo {

    static Bank head;
    static Bank tail;

    public static void main(String[] args) {
        Bank bank = new Bank(100000);

        System.out.println("Welcome to Bank BCA");
        bank.info();
        bank.deposit(500000);
        bank.withdraw(150000);
        bank.info();

        addBank(200000);


    }

    static void addBank(int balance){
        if(head.next == null) return;
        Bank newBank = new Bank(balance);
        tail.next = newBank;
        newBank = tail;
        newBank.next = null; 
    }

    static void deposit(int value){
        
    }
}