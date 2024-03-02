import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class User {
    private String userId;
    private String pin;
    private double balance;
    private List<String> transactionHistory;

    public User(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public boolean validatePin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: +" + amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawal: -" + amount);
            return true;
        } else {

            return false;

        }
    }

    public boolean transfer(User recipient, double amount) {
        if (amount <= balance) {

            balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add("Transfer: -" + amount + " to " + recipient.getUserId());
            return true;
            
        } else {
            return false;
        }
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}

public class ATMSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;

    public static void main(String[] args) {
        initialize();

        System.out.println("Welcome to the ATM System!");
        System.out.print("Enter user id: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (authenticate(userId, pin)) {
            displayMenu();
        } else {
            System.out.println("Invalid user id or PIN. Exiting...");
        }
    }

    private static void initialize() {
        // Initialize users (for demonstration purposes, you may replace with actual user database)
        User user1 = new User("user1", "1234");
        User user2 = new User("user2", "5678");
        currentUser = user1; // For demonstration, setting a default user
    }

    private static boolean authenticate(String userId, String pin) {

        // For demonstration, checking against a single user
        if (currentUser.getUserId().equals(userId) && currentUser.validatePin(pin)) {
            return true;
        } else {
            return false;
        }
    }

    private static void displayMenu()
     {
        while (true) {

            System.out.println("\nATM Menu:");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Transaction History");
            System.out.println("6. Quit");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:

                    System.out.println("Your balance: " + currentUser.getBalance());
                    break;
                case 2:

                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    currentUser.deposit(depositAmount);
                    System.out.println("Deposit successful.");
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    if (currentUser.withdraw(withdrawalAmount)) {
                        System.out.println("Withdrawal successful.");
                    } else
                     {
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case 4:

                    System.out.print("Enter recipient user id: ");
                    String recipientId = scanner.next();
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    User recipient = (currentUser.getUserId().equals(recipientId)) ? currentUser : null; // For demonstration, using same user as recipient
                    if (recipient != null && currentUser.transfer(recipient, transferAmount)) {
                        System.out.println("Transfer successful.");
                    } else {
                        System.out.println("Transfer failed. Either recipient not found or insufficient funds.");
                    }
                    break;
                case 5:

                    System.out.println("Transaction History:");
                    for (String transaction : currentUser.getTransactionHistory()) {
                        System.out.println(transaction);
                    }
                    break;
                case 6:

                    System.out.println("Thank you for using the ATM. Goodbye!");
                    System.exit(0);
                default:

                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
