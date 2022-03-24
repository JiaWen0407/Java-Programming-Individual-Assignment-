import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Loo Jia Wen 0129868
 * Java individual assignment (Part 1)
 */

class Account {

    Scanner input = new Scanner(System.in);

    private int id;
    private String fName, lName, ic;
    private double balance, annualInterestRate;    
    private double depositAmount, withdrawAmount;
    double monthlyInterest;
    private Date dateCreated = new Date();
    
    //A no-arg constructor that creates a default account.
    public Account() {
        id = 0;
        balance = 0.00;
        annualInterestRate = 0.00;
    }

    //A constructor that creates an account with the specified id and initial balance.
    public Account(int id, String fName, String lName, String ic, double balance, double annualInterestRate, Date dateCreated) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.ic = ic;
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAnnualInterestRate() {
        annualInterestRate = 4.5 / 100;
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public Date getDate() {
        return dateCreated;
    }

    public double getMonthlyInterestRate() {
        monthlyInterest = annualInterestRate / 12;
        return monthlyInterest;
    }

    public double getMonthlyInterest() {
        return balance * monthlyInterest;
    }

    public double withdraw(double withdrawAmount) {
        if (balance >= withdrawAmount) {
            System.out.print(" * ");
            System.out.println("\n Amount withdrawed: RM " + withdrawAmount);
            return balance - withdrawAmount;
        } else {
            System.out.println("\n *Sorry, insufficient amount.* ");
            System.out.println(" *Transaction Failed.* ");
            return balance;
        }
    }

    public double deposit(double depositAmount) {
        return balance + depositAmount;
    }
}