package SOLID.ISP.ISPFollowed;

import java.util.Arrays;
import java.util.List;

class FixedDeposit {
    double amount;
    int durationInMonths;
    FixedDeposit(double amount, int durationInMonths) {
        this.amount = amount;
        this.durationInMonths = durationInMonths;
    }

    @Override
    public String toString() {
        return amount + " for " + durationInMonths + " months";
    }
}

class Loan {
    double amount;
    double salary;
    Loan(double amount, double salary) {
        this.amount = amount;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Loan amount: " + amount + ", Salary: " + salary;
    }
}

interface DepositService {
    void deposit(double amount);
}
interface WithdrawalService {
    void withdraw(double amount);
}
interface FixedDepositService {
    void openFixedDeposit(double amount, int durationInMonths);
}
interface LoanService {
    void applyForLoan(double amount, double salary);
    void payInstallment(double installment);
}

class SavingAccount implements DepositService, WithdrawalService, FixedDepositService, LoanService {

    private final String accountType = "Saving Account";
    private double balance = 0;
    private double outstandingLoanAmount = 0;

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println(amount + " deposited in " + accountType);
    }

    @Override
    public void openFixedDeposit(double amount, int durationInMonths) {
        if(balance < amount) {
            System.out.println("Insufficient funds to start FD");
            return;
        }
        FixedDeposit fd = new FixedDeposit(amount, durationInMonths);
        System.out.println("FD created by saving account :" + fd);
    }

    @Override
    public void applyForLoan(double amount, double salary) {
        if(salary < 30000) {
            System.out.println("Sorry! You're not eligible for loan");
            return;
        }
        Loan loan = new Loan(amount, salary);
        outstandingLoanAmount += amount;
        System.out.println("Loan created by saving account :" + loan);
    }

    @Override
    public void payInstallment(double installment) {
        outstandingLoanAmount -= installment;
        System.out.println("Installment paid by saving account.");
    }

    @Override
    public void withdraw(double amount) {
        if(balance < amount) {
            System.out.println("Insufficient funds to withdraw.");
            return;
        }
        balance -= amount;
        System.out.println(amount + " withdrawn from the saving account.");
    }
}

class FixedDepositAccount implements FixedDepositService, DepositService {

    private final String accountType = "Fixed Deposit Account";
    private double balance = 0;

    @Override
    public void openFixedDeposit(double amount, int durationInMonths) {
        if(balance < amount) {
            System.out.println("Insufficient funds to start FD");
            return;
        }
        FixedDeposit fd = new FixedDeposit(amount, durationInMonths);
        System.out.println("FD created by FD account :" + fd);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println(amount + " deposited in " + accountType);
    }
}

class LoanAccount implements LoanService {

    private final String accountType = "Loan Account";
    private double outstandingLoanAmount = 0;

    @Override
    public void applyForLoan(double amount, double salary) {
        if(salary < 30000) {
            System.out.println("Sorry! You're not eligible for loan");
            return;
        }
        Loan loan = new Loan(amount, salary);
        outstandingLoanAmount += amount;
        System.out.println("Loan created by loan account :" + loan);
    }

    @Override
    public void payInstallment(double installment) {
        outstandingLoanAmount -= installment;
        System.out.println("Installment paid by loan account.");
    }
}

class BankClient {
    private final List<DepositService> depositServices;
    private final List<WithdrawalService> withdrawalServices;
    private final List<FixedDepositService> fixedDepositServices;
    private final List<LoanService> loanServices;

    BankClient(List<DepositService> depositServices,
               List<WithdrawalService> withdrawalServices,
               List<FixedDepositService> fixedDepositServices,
               List<LoanService> loanServices) {
        this.depositServices = depositServices;
        this.withdrawalServices = withdrawalServices;
        this.fixedDepositServices = fixedDepositServices;
        this.loanServices = loanServices;
    }

    public void deposit(double amount) {
        System.out.println("Depositing...");
        for (DepositService depositService: depositServices) {
            depositService.deposit(amount);
        }
        System.out.println();
    }

    public void withdraw(double amount) {
        System.out.println("Withdrawing...");
        for (WithdrawalService withdrawalService : withdrawalServices) {
            withdrawalService.withdraw(amount);
        }
        System.out.println();
    }

    public void openFixedDeposit(double amount, int durationInMonths) {
        System.out.println("Creating FD...");
        for (FixedDepositService fixedDepositService : fixedDepositServices) {
            fixedDepositService.openFixedDeposit(amount, durationInMonths);
        }
        System.out.println();
    }

    public void applyForLoan(double amount, double salary) {
        System.out.println("Applying for loan...");
        for (LoanService loanService : loanServices) {
            loanService.applyForLoan(amount, salary);
        }
        System.out.println();
    }

    public void payInstallment(double installment) {
        System.out.println("Paying installment...");
        for (LoanService loanService : loanServices) {
            loanService.payInstallment(installment);
        }
        System.out.println();
    }
}

public class ISPFollowed {
    public static void main(String[] args) {
        SavingAccount savingAccount = new SavingAccount();
        FixedDepositAccount fixedDepositAccount = new FixedDepositAccount();
        LoanAccount loanAccount = new LoanAccount();

        List<DepositService> depositServices = Arrays.asList(savingAccount, fixedDepositAccount);
        List<WithdrawalService> withdrawalServices = Arrays.asList(savingAccount);
        List<FixedDepositService> fixedDepositServices = Arrays.asList(savingAccount, fixedDepositAccount);
        List<LoanService> loanServices = Arrays.asList(savingAccount, loanAccount);

        BankClient client = new BankClient(depositServices, withdrawalServices, fixedDepositServices, loanServices);

        client.deposit(500000);
        client.withdraw(30000);
        client.openFixedDeposit(100000, 12);
        client.applyForLoan(300000, 35000);
        client.payInstallment(50000);
    }
}
