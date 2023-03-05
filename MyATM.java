/*

Oasis Infobyte Internship:- Task No: 3
Project Name: ATM Interface 
Student name: Waghmare Lambodar 


This  project consists of five different classes and is a console-based application. When the system starts the user is
prompted with user id and user pin. On entering the details successfully, then ATM functionalities
are unlocked. The project allows to perform following operations:

1.	Transactions History
2.	Withdraw
3.	Deposit
4.	Transfer
5.	Quit

 */


import java.util.Scanner;
import java.util.ArrayList;

//JAVA GUI Window to show the Project name
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class MyATM {


    private double balance;
    private String userId;
    private String password;
    private TransactionsHistory transactionsHistory;



    //Main myATM class 

    public MyATM(String userId, String password) {
        this.balance = 0.0;
        this.userId = userId;
        this.password = password;
        this.transactionsHistory = new TransactionsHistory();
    }

    public boolean login(String userId, String password) {
        if (this.userId.equals(userId) && this.password.equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid credentials. Please try again.");
            return false;
        }
    }

    public void withdraw(double amount) {
        Withdraw.withdraw(amount, this.balance, this.transactionsHistory);
        this.balance -= amount;
    }

    public void deposit(double amount) {
        Deposit.deposit(amount, this.balance, this.transactionsHistory);
        this.balance += amount;
    }

    public void transfer(double amount, String recipientUserId) {
        Transfer.transfer(amount, this.balance, recipientUserId, this.transactionsHistory);
        this.balance -= amount;
    }

    public void checkBalance() {
        CheckBalance.checkBalance(this.balance);
    }

    public void showTransactionHistory() {
        this.transactionsHistory.showTransactionHistory();
    }

    public static void main(String[] args) {


        //login Id and Passward 

        MyATM atm = new MyATM("MyUserId", "MyPassword");
        Scanner scanner = new Scanner(System.in);        
        System.out.println("Welcome to the ATM!");






         JFrame frame = new JFrame("Decorative Text Example");
         frame.setSize(700, 150);
         frame.setAlwaysOnTop(true);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, Color.WHITE,
                        getWidth(), getHeight(), Color.GRAY);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };


         JLabel label = new JLabel("Welcome to ATM Service !");
         label.setForeground(Color.RED);
         label.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30));
         panel.add(label);
         frame.add(panel);
         frame.setVisible(true);
         boolean loggedIn = false;





        do {
            System.out.print("Enter your user ID: ");
            String userId = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            loggedIn = atm.login(userId, password);
        } while (!loggedIn);

        boolean quit = false;

        do {
            System.out.println(" ");
            System.out.println("Please choose an option:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Transfer");
            System.out.println("4. Check balance");
            System.out.println("5. Show transaction history");
            System.out.println("6. Quit");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (option) {
                case 1:
                    System.out.println(" ");
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); // consume the newline character
                    atm.withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.println(" ");
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // consume the newline character
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.println(" ");
                    System.out.print("Enter the amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    scanner.nextLine(); // consume the newline character
                    System.out.print("Enter the recipient's user ID: ");
                    String recipientUserId = scanner.nextLine();
                    atm.transfer(transferAmount, recipientUserId);
                    break;
                case 4:
                    atm.checkBalance();
                    break;
                case 5:
                    atm.showTransactionHistory();
                    break;
                case 6:
                    quit = true;
                   
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (!quit);




        frame.setAlwaysOnTop(false);
        frame.setVisible(false);
        System.out.println("Thank you for using the ATM!");
        

       

    }


}



//TransactionsHistory class 

class TransactionsHistory {
    private ArrayList<String> transactionHistory;

    public TransactionsHistory() {
        this.transactionHistory = new ArrayList<String>();
    }

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public void showTransactionHistory() {
        System.out.println(" ");
        System.out.println("Transaction history:");
        System.out.println(" ");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}


//class Withdraw
class Withdraw {
    public static void withdraw(double amount, double balance, TransactionsHistory transactionsHistory) {
        if (amount <= 0.0 || amount > balance) {
            System.out.println("Invalid amount!");
            return;
        }

        balance -= amount;
        String transaction = "-₹" + amount + " withdrawn";
        transactionsHistory.addTransaction(transaction);
        System.out.println("₹" + amount + " withdrawn successfully.");
        System.out.println("Current balance:₹ " + balance);
        System.out.println(" ");
    }
}


//class Deposit
class Deposit {
    public static void deposit(double amount, double balance, TransactionsHistory transactionsHistory) {
        if (amount <= 0.0) {
            System.out.println("Invalid amount!");
            return;
        }

        balance += amount;
        String transaction = "+₹" + amount + " deposited";
        transactionsHistory.addTransaction(transaction);
        System.out.println("₹" + amount + " deposited successfully.");
        System.out.println("Current balance: ₹" + balance);
        System.out.println( " ");
    }
}


// Transfer 
class Transfer {
    public static void transfer(double amount, double balance, String recipientUserId, TransactionsHistory transactionsHistory) {
        if (amount <= 0.0 || amount > balance) {
            System.out.println("Invalid amount!");
            return;
        }

        balance -= amount;
        String transaction = "-₹" + amount + " transferred to " + recipientUserId;
        transactionsHistory.addTransaction(transaction);
        System.out.println("₹" + amount + " transferred to " + recipientUserId + " successfully.");
        System.out.println("Current balance: ₹" + balance);
        System.out.println( " ");
    }
}


//class CheckBalance

class CheckBalance {
    public static void checkBalance(double balance) {
        if (balance == 0.0) {
            System.out.println(" ");
            System.out.println("Your balance is currently 0.");
        } else {
            System.out.println(" ");
            System.out.println("Your current balance is ₹" + balance);
        }
    }
}
