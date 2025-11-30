package SOLID.ISP.ISPViolated;

import java.util.Arrays;
import java.util.List;

interface Account {
    void deposit(double amount);
    void withdraw(double amount);
    void openFixedDeposit(double amount, int durationInMonths);
    void applyForLoan(double amount, double salary);
    void payInstallment(double amount);
}

// Here ISP breaks, child classes forcefully need to override unnecessary methods

class SavingAccount implements Account {

    private final String accountType = "Saving Account";
    private double balance = 0;
    private double outstandingLoanAmount = 0;

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println(amount + " deposited in saving account");
    }

    @Override
    public void withdraw(double amount) {
        if(balance < amount) {
            System.out.println("Insufficient funds");
            return;
        }
        balance -= amount;
        System.out.println(amount + " withdrawn from the saving account");
    }

    @Override
    public void openFixedDeposit(double amount, int durationInMonths) {
        if(amount < balance) {
            System.out.println("Insufficient funds to open FD.");
            return;
        }
        balance -= amount;
        System.out.println("Saving account created FD of Rs." + amount);
    }

    @Override
    public void applyForLoan(double amount, double salary) {
        if(salary < 30000) {
            System.out.println("Sorry! You're not eligible for loan.");
            return;
        }
        outstandingLoanAmount += amount;
        System.out.println("Loan applied for " + accountType);
    }

    public void payInstallment(double installment) {
        outstandingLoanAmount -= installment;
        System.out.println("Installment paid for loan, remaining amount is " + outstandingLoanAmount);
    }
}

class FixedDepositAccount implements Account {

    private final String accountType = "Fixed Deposit Account";
    private double fixedDepositAmount = 0;

    @Override
    public void deposit(double amount) {
        throw new UnsupportedOperationException("Deposit unsupported for " + accountType);
    }

    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Withdrawal unsupported for " + accountType);
    }

    @Override
    public void openFixedDeposit(double amount, int durationInMonths) {
        fixedDepositAmount += amount;
        System.out.println("Saving account created FD of Rs." + amount + " for " + durationInMonths + " months");
    }

    @Override
    public void applyForLoan(double amount, double salary) {
        throw new UnsupportedOperationException("Applying loan unsupported for " + accountType);
    }

    public void payInstallment(double installment) {
        throw new UnsupportedOperationException("Paying installment unsupported for " + accountType);
    }
}

class LoanAccount implements Account {

    private final String accountType = "Loan Account";
    private double outstandingLoanAmount = 0;

    @Override
    public void deposit(double amount) {
        throw new UnsupportedOperationException("Deposit unsupported for " + accountType);
    }

    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Withdrawal unsupported for " + accountType);
    }

    @Override
    public void openFixedDeposit(double amount, int durationInMonths) {
        throw new UnsupportedOperationException("FDs are unsupported for " + accountType);
    }

    @Override
    public void applyForLoan(double amount, double salary) {
        if (salary < 30000) {
            System.out.println("Sorry! You're not eligible for loan.");
            return;
        }
        outstandingLoanAmount += amount;
        System.out.println("Loan applied for " + accountType);
    }

    public void payInstallment(double installment) {
        outstandingLoanAmount -= installment;
        System.out.println("Installment paid for loan, remaining amount is " + outstandingLoanAmount);
    }
}

class BankClient {
    private final List<Account> accounts;
    BankClient(List<Account> accounts) {
        this.accounts = accounts;
    }

    void deposit(double amount) {
        System.out.println("Depositing amount...");
        for(Account account : accounts) {
            try {
                account.deposit(amount);
            } catch (UnsupportedOperationException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println();
    }

    void withdraw(double amount) {
        System.out.println("Withdrawing amount...");
        for(Account account : accounts) {
            try {
                account.withdraw(amount);
            } catch (UnsupportedOperationException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println();
    }

    void openFixedDeposit(double amount, int durationInMonths) {
        System.out.println("Opening FD...");
        for(Account account : accounts) {
            try {
                account.openFixedDeposit(amount, durationInMonths);
            } catch (UnsupportedOperationException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println();
    }

    void applyForLoan(double amount, double salary) {
        System.out.println("Applying for loan...");
        for(Account account : accounts) {
            try {
                account.applyForLoan(amount, salary);
            } catch (UnsupportedOperationException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println();
    }

    void payInstallment(double installment) {
        System.out.println("Paying installment...");
        for(Account account : accounts) {
            try {
                account.payInstallment(installment);
            } catch (UnsupportedOperationException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}

public class ISPViolated {
    public static void main(String[] args) {
        Account savingAccount = new SavingAccount();
        Account fixedDepositAccount = new FixedDepositAccount();
        Account loanAccount = new LoanAccount();
        List<Account> accounts = Arrays.asList(savingAccount, fixedDepositAccount, loanAccount);

        BankClient client = new BankClient(accounts);
        client.deposit(500000);
        client.withdraw(20000);
        client.openFixedDeposit(70000, 12);
        client.applyForLoan(300000, 35000);
        client.payInstallment(15000);
    }
}
