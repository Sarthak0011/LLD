package SOLID.LSP.LSPViolated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface Account {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
}

class SavingsAccount implements Account {

    private double balance = 0;

    @Override
    public void deposit(double amount) {
        this.balance += amount;
        System.out.println(amount + " deposited in savings account.");
    }

    @Override
    public void withdraw(double amount) {
        if(this.balance < amount) {
            System.out.println("Insufficient funds.");
            return;
        }
        this.balance -= amount;
        System.out.println(amount + " withdrawn from savings account.");
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
            System.out.println("Insufficient funds.");
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
        System.out.println(amount + " deposited in FD");
        this.balance += amount;
    }

    // Violated LSP
    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Unsupported operation.");
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
            try {
                account.deposit(1000);
                account.withdraw(500);
            } catch (UnsupportedOperationException ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }
    }
}

public class LSPViolated {
    public static void main(String[] args) {
        Account savingAccount = new SavingsAccount();
        Account currentAccount = new CurrentAccount();
        Account fixedDepositAccount = new FixedDepositAccount();

        List<Account> accounts = Arrays.asList(savingAccount, currentAccount, fixedDepositAccount);

        BankClient client = new BankClient(accounts);

        client.processTransaction();

    }
}
