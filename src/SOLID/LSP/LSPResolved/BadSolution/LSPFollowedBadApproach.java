package SOLID.LSP.LSPResolved.BadSolution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface Account {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
}

class SavingAccount implements Account {

    private double balance;

    @Override
    public void deposit(double amount) {
        this.balance += amount;
        System.out.println(amount + " deposited in saving account.");
    }

    @Override
    public void withdraw(double amount) {
        if(this.balance < amount) {
            System.out.println("Insufficient funds.");
            return;
        }
        this.balance -= amount;
        System.out.println(amount + " withdrawn from saving account");
    }

    @Override
    public double getBalance() {
        return this.balance;
    }
}

class CurrentAccount implements Account {

    private double balance = 0;

    @Override
    public void deposit(double amount) {
        this.balance += amount;
        System.out.println(amount + " deposited in current account.");
    }

    @Override
    public void withdraw(double amount) {
        if(this.balance < amount) {
            System.out.println("Insufficient funds");
            return;
        }
        this.balance -= amount;
        System.out.println(amount + " withdrawn from the current account.");
    }

    @Override
    public double getBalance() {
        return this.balance;
    }
}

class FixedDepositAccount implements Account {

    private double balance = 0;

    @Override
    public void deposit(double amount) {
        this.balance += amount;
        System.out.println(amount + " deposited in fixed account.");
    }

    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public double getBalance() {
        return this.balance;
    }
}

class BankClient {
    private final List<Account> accounts;

    BankClient(List<Account> accounts) {
        this.accounts = accounts;
    }

    void processTransaction() {
        for(Account account : accounts) {
            account.deposit(1000);

            // handled the LSP but this breaks the OCP
            if(account instanceof FixedDepositAccount) {
                System.out.println("Skipping this operation for this account.");
                continue;
            }
            account.withdraw(500);
        }
    }
}


public class LSPFollowedBadApproach {
    public static void main(String[] args) {

        Account savingAccount = new SavingAccount();
        Account currentAccount = new CurrentAccount();
        Account fixedDepositAccount = new FixedDepositAccount();

        List<Account> accounts = Arrays.asList(savingAccount, currentAccount, fixedDepositAccount);

        BankClient client = new BankClient(accounts);
        client.processTransaction();
    }
}
