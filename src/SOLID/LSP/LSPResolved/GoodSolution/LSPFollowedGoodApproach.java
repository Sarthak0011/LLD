package SOLID.LSP.LSPResolved.GoodSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface DepositOnlyAccount {
    void deposit(double amount);
    double getBalance();
}

interface WithdrawableAccount extends DepositOnlyAccount{
    void withdraw(double amount);
}

class SavingAccount implements WithdrawableAccount {

    private double balance = 0;

    @Override
    public void withdraw(double amount) {
        if(this.balance < amount) {
            System.out.println("Insufficient funds.");
            return;
        }
        this.balance -= amount;
        System.out.println(amount + " withdrawn from the saving account.");
    }

    @Override
    public void deposit(double amount) {
        this.balance += amount;
        System.out.println(amount + " deposited in saving account.");
    }

    @Override
    public double getBalance() {
        return this.balance;
    }
}

class CurrentAccount implements WithdrawableAccount {

    private double balance = 0;

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
    public void deposit(double amount) {
        this.balance += amount;
        System.out.println(amount + " deposited in the current account.");
    }

    @Override
    public double getBalance() {
        return this.balance;
    }
}

class FixedDepositAccount implements DepositOnlyAccount {

    private double balance = 0;

    @Override
    public void deposit(double amount) {
        this.balance += amount;
        System.out.println(amount + " deposited in fixed deposit.");
    }

    @Override
    public double getBalance() {
        return this.balance;
    }
}

class BankClient {
    // LSP followed
    private final List<DepositOnlyAccount> depositOnlyAccounts;
    private final List<WithdrawableAccount> withdrawableAccounts;

    BankClient(List<WithdrawableAccount> withdrawableAccounts, List<DepositOnlyAccount> depositOnlyAccounts) {
        this.depositOnlyAccounts = depositOnlyAccounts;
        this.withdrawableAccounts = withdrawableAccounts;
    }

    public void processTransaction() {
        for (WithdrawableAccount account : withdrawableAccounts) {
            account.deposit(1000);
            account.withdraw(500);
        }
        for (DepositOnlyAccount account : depositOnlyAccounts) {
            account.deposit(1000);
        }
    }
}

public class LSPFollowedGoodApproach {
    public static void main(String[] args) {
        WithdrawableAccount savingAccount = new SavingAccount();
        WithdrawableAccount currentAccount = new CurrentAccount();
        DepositOnlyAccount depositOnlyAccount = new FixedDepositAccount();

        List<WithdrawableAccount> withdrawableAccounts = Arrays.asList(savingAccount, currentAccount);
        List<DepositOnlyAccount> depositOnlyAccounts = Arrays.asList(depositOnlyAccount);

        BankClient client = new BankClient(withdrawableAccounts, depositOnlyAccounts);
        client.processTransaction();
    }
}
