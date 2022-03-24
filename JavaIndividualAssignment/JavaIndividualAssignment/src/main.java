import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Loo Jia Wen 0129868
 * Java individual assignment (Part 1)
 */

public class main {

    public static void main(String[] args) throws ParseException {
        List<Account> accounts = new ArrayList<>();
        Date dateCreated = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        int option = 0, id = 0, noAccountAdd = 0, searchId = 0, edit = 0, duration = 0;
        double balance = 0.00, annualInterestRate, monthlyInterestRate = 0.00, monthlyInterest = 0.00, depositAmount = 0.00, withdrawAmount = 0.00, tempBalance = 0.00;
        String fName = "", lName = "", ic = " ";        
        Scanner input = new Scanner(System.in);
        boolean found;
        
        //Reading records from dataAccounts.txt
        try {
            BufferedReader reader = new BufferedReader(new FileReader("dataAccounts.txt"));
            String line = null; 

            while ((line = reader.readLine()) != null && line.length() > 0) {
                String tmp[] = line.split(","); //Data split by comma
                //Read data
                id = Integer.parseInt(tmp[0]);
                fName = tmp[1];
                lName = tmp[2];
                ic = tmp[3];
                balance = Double.parseDouble(tmp[4]);
                annualInterestRate = Double.parseDouble(tmp[5]);
                dateCreated = dateFormat.parse(tmp[6]);

                //Add data to the array list
                accounts.add(new Account(id, fName, lName, ic, balance, annualInterestRate, dateCreated));
            }
            System.out.println("\n Account data added!");
            reader.close();
        } catch (IOException e) {
        }

        //
        do {
            System.out.println("\n---------------------- BANK  ---------------------");
            System.out.println("1. Create a new account");
            System.out.println("2. Edit an account information ");
            System.out.println("3. Check account");
            System.out.println("4. Check Balance");
            System.out.println("5. Deposit");
            System.out.println("6. Withdraws ");
            System.out.println("7. Check monthly interest ");
            System.out.println("----------------------------------------------------");
            System.out.println(" ");

            System.out.print("Please choose your option (1-8): ");
            option = input.nextInt();

            switch (option) {

                case 1:
                    System.out.println("\n--------------- Create new account ----------------");
                    System.out.print(" Add new account (Yes = 1 OR No = 0):  ");
                    do {
                        noAccountAdd = input.nextInt();
                        if (noAccountAdd != 1 && noAccountAdd != 0) {
                            System.out.print(" Please select 1 OR 0: ");
                        }
                    } while (noAccountAdd != 1 && noAccountAdd != 0);

                    if (noAccountAdd == 0) {
                        System.out.println("\n * No account created * ");
                    }

                    for (int i = 0; i < noAccountAdd; i++) {
                        System.out.println("\n***\n \t Account Information ");
                        System.out.println(" \t ------------------- ");
                        System.out.print(" Account holder ID (4 digits): ");
                        do {
                            id = input.nextInt();
                            if (id <= 1000 || id >= 10000) {
                                System.out.print("\t *Id must be in 4 digits...*");
                                System.out.print("\n Enter ID again: ");
                            }
                        } while (id <= 1000 || id >= 10000);

                        System.out.print(" First Name: ");
                        fName = input.next();

                        System.out.print(" Last Name: ");
                        lName = input.next();

                        System.out.print(" IC (XXXXXXXX-XX-XXXX): ");
                        ic = input.next();

                        System.out.print(" Balance: RM ");
                        do {
                            balance = input.nextDouble();
                        } while (balance < 0);

                        annualInterestRate = 4.5;
                        System.out.println(" Annual Interest Rate: " + annualInterestRate + "%");

                        System.out.println(" Account Creation Date: " + dateFormat.format(dateCreated));

                        accounts.add(new Account(id, fName, lName, ic, balance, annualInterestRate, dateCreated));

                        System.out.println("\n * New account created * ");

                        System.out.println("\n-------------------------------------------------------");
                    }
                    break;

                case 2:
                    System.out.println("\n----------------------------------------------------");
                    System.out.print("\nSearch account ID to edit: ");
                    searchId = input.nextInt();
                    found = false;

                    for (Account x : accounts) {
                        if (searchId == x.getId()) {
                            System.out.println("\n-----------------Edit account Information -----------------");
                            System.out.println("\n Current Information: \n Account holder ID: " + x.getId() + "\n Name: " + x.getFName() + " " + x.getLName() + "\n IC: " + x.getIc() + "\n Balance: RM " + x.getBalance() + "\n Annual interest rate: " + x.getAnnualInterestRate() + "\n Account creation date: " + dateFormat.format(x.getDate()));
                            System.out.println("\n----------------------------------");
                            System.out.println("Which information to edit?");
                            System.out.println(" 1. First Name");
                            System.out.println(" 2. Last Name");
                            System.out.println(" 3. IC");
                            System.out.println(" 4. No edit!");
                            System.out.println("----------------------------------");
                            System.out.print("\n Enter (1-4): ");

                            do {
                                edit = input.nextInt();

                                if (edit != 1 && edit != 2 && edit != 3 && edit != 4) {
                                    System.out.print("Please Enter (1-4): ");
                                }
                            } while (edit != 1 && edit != 2 && edit != 3 && edit != 4);

                            System.out.println("\n **");

                            if (edit == 1) {
                                System.out.print("\n Enter first name: ");
                                fName = input.next();
                                x.setFName(fName);
                            } else if (edit == 2) {
                                System.out.print("\n Enter last name: ");
                                lName = input.next();
                                x.setLName(lName);
                            } else if (edit == 3) {
                                System.out.print("\n Enter IC: ");
                                ic = input.next();
                                x.setIc(ic);
                            } else if (edit == 4) {
                                System.out.println("\n **No information edited**");
                            }

                            found = true;
                            System.out.println("\n **New information updated**");
                            System.out.println("\n-----------------------------------------------------------");
                        }
                    }

                    if (found == false) {
                        System.out.println("\n **This account is not in the list.**");
                    }
                    break;

                case 3:
                    System.out.print("\nSearch account ID to edit: ");
                    searchId = input.nextInt();
                    found = false;

                    for (Account x : accounts) {
                        if (searchId == x.getId()) {
                            System.out.println("\n------------- Account Information -----------------");
                            System.out.println("\n Account holder ID: " + x.getId() + "\n Name: " + x.getFName() + " " + x.getLName() + "\n IC: " + x.getIc() + "\n Balance: RM " + x.getBalance() + "\n Annual interest rate: " + x.getAnnualInterestRate() + "\n Account creation date: " + dateFormat.format(x.getDate()));
                            System.out.println("\n---------------------------------------------------");
                            found = true;
                        }
                    }
                    if (found == false) {
                        System.out.println("\n **This account is not created yet.**");

                    }
                    break;

                case 4:
                    System.out.print("\nSearch account ID to edit: ");
                    searchId = input.nextInt();
                    found = false;

                    for (Account x : accounts) {
                        if (searchId == x.getId()) {
                            System.out.println("\n------------- Check balance -----------------");
                            System.out.println("\n Account holder ID: " + x.getId() + "\n Name: " + x.getFName() + " " + x.getLName() + "\n Balance: RM " + x.getBalance());
                            System.out.println("\n---------------------------------------------");
                            found = true;
                        }
                    }
                    if (found == false) {
                        System.out.println("\n **This account is not in the list.**");
                    }
                    break;

                case 5:
                    System.out.print("\nSearch account ID to edit: ");
                    searchId = input.nextInt();
                    found = false;

                    for (Account x : accounts) {
                        if (searchId == x.getId()) {
                            System.out.println("\n------------- Deposit -----------------");
                            System.out.println("\n Current Information: \n Account holder ID: " + x.getId() + "\n Name: " + x.getFName() + " " + x.getLName() + "\n Balance: RM " + x.getBalance());
                            System.out.println("\n---------------------------------------");
                            System.out.print("\n Please enter deposit ammount: RM ");
                            depositAmount = input.nextDouble();

                            x.setBalance(x.deposit(depositAmount));

                            System.out.println("\n Current Balance: RM " + x.getBalance());
                            System.out.println("\n---------------------------------------------");
                            found = true;
                        }
                    }

                    if (found == false) {
                        System.out.println("\n **This account is not in the list.**");
                    }

                    break;

                case 6:
                    System.out.print("\nSearch account ID to edit: ");
                    searchId = input.nextInt();
                    found = false;

                    for (Account x : accounts) {
                        if (searchId == x.getId()) {
                            System.out.println("\n------------------ Withdraw ------------------");
                            System.out.println("\n Current Information: \n Account holder ID: " + x.getId() + "\n Name: " + x.getFName() + " " + x.getLName() + "\n Balance: RM " + x.getBalance());
                            System.out.println("\n---------------------------------------");
                            System.out.print("\n Please enter withdraw ammount: RM ");
                            withdrawAmount = input.nextDouble();

                            x.setBalance(x.withdraw(withdrawAmount));
                            System.out.println("\n Current Balance: RM " + x.getBalance());
                            System.out.println("\n---------------------------------------------");
                            found = true;
                        }
                    }

                    if (found == false) {
                        System.out.println("\n **This account is not in the list.**");
                    }

                    break;

                case 7:
                    System.out.print("\nSearch account ID to edit: ");
                    searchId = input.nextInt();
                    found = false;

                    for (Account x : accounts) {

                        if (searchId == x.getId()) {
                            System.out.println("\n-------------------- Monthly Interest ----------------------");
                            System.out.println("\n Current Information: \n Account holder ID: " + x.getId() + "\n Name: " + x.getFName() + " " + x.getLName() + "\n Balance: RM " + x.getBalance() + "\n Annual Interest Rate: " + x.getAnnualInterestRate() + "\n Monthly Interest Rate: " + x.getMonthlyInterestRate());
                            System.out.println("\n**** ");
                            System.out.print("\nEnter interest's durations (Years): ");
                            duration = input.nextInt();
                            System.out.println("\n****");

                            tempBalance = x.getBalance();

                            for (int i = 1; i <= duration; i++) {

                                System.out.println("Year " + i + ": ");
                                for (int j = 1; j <= 12; j++) {
                                    tempBalance += x.getMonthlyInterest();
                                    System.out.println("\t" + j + " month's interest: RM " + tempBalance);
                                }

                                System.out.println("------------------------------------------------------------");
                                found = true;
                            }
                        }
                    }
                    if (found == false) {
                        System.out.println("\n **This account is not in the list.**");
                    }
                    break;

                default:
                    System.out.println("\n Invalid selection...");
                    break;
            }

            //Writing accounts data into a text file
            try {
                FileWriter fw = new FileWriter("dataAccounts.txt");
                Writer output = new BufferedWriter(fw);
                for (Account x : accounts) {
                    output.write( x.getId() + ", " + x.getFName() + ", " + x.getLName() + ", " + x.getIc() + ", " + x.getBalance() + ", " + x.getAnnualInterestRate() + ", "  + dateFormat.format(x.getDate()) + "\n");
                }
                output.close();
            } catch (Exception e) {
                System.out.print("Cannot create the file...");
            }

        } while (option != 1 || option != 2 || option != 3 || option != 4 || option != 5 || option != 6 || option != 7);

    }
}