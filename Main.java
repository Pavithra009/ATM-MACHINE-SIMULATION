import java.util.*;

class ATMUser {
    String userId;
    String pin;
    double balance;
    List<String> transactionHistory;

    public ATMUser(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean authenticate(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public void viewBalance() {
        System.out.println("Current balance: $" + balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            transactionHistory.add("Withdrawal of $" + amount);
            System.out.println("Withdrawal successful. Current balance: $" + balance);
        }
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
        } else {
            balance += amount;
            transactionHistory.add("Deposit of $" + amount);
            System.out.println("Deposit successful. Current balance: $" + balance);
        }
    }

    public void transfer(double amount, String recipientId, Map<String, ATMUser> users) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }
        ATMUser recipient = users.get(recipientId);
        if (recipient == null) {
            System.out.println("Recipient's account not found.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            transactionHistory.add("Transfer of $" + amount + " to " + recipientId);
            recipient.balance += amount;
            recipient.transactionHistory.add("Transfer received from " + userId);
            System.out.println("Transfer successful. Current balance: $" + balance);
        }
    }

    public void viewTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}

public class ATMSystem {  
    private static Map<String, ATMUser> users = new HashMap<>();

    public static void main(String[] args) {
        users.put("user1", new ATMUser("user1", "12345", 0));
        users.put("user2", new ATMUser("user2", "67890", 1000));
        users.put("user3", new ATMUser("user3", "01234", 32000));
        users.put("user4", new ATMUser("user4", "56789", 400000));

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your user ID: ");
        String userId = scanner.nextLine().trim();

        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine().trim();

        ATMUser user = users.get(userId);

        if (user != null && user.authenticate(pin)) {
            while (true) {
                System.out.println("\nATM Menu:");
                System.out.println("1. View Balance");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. View Transaction History");
                System.out.println("6. Quit");
                System.out.print("Enter your choice: ");

                String choice = scanner.nextLine().trim();

                if (choice.equals("6")) {
                    System.out.println("Thank you for using the ATM.\n----- Have a nice day -----!");
                    break;
                }

                double amount = 0;
                String recipientId = "";

                if (choice.equals("2") || choice.equals("3") || choice.equals("4")) {
                    System.out.print("Enter amount: ");
                    while (!scanner.hasNextDouble()) {
                        System.out.println("Invalid input. Please enter a numeric value.");
                        scanner.next();
                    }
                    amount = scanner.nextDouble();
                    scanner.nextLine();
                }

                if (choice.equals("4")) {
                    System.out.print("Enter recipient's user ID: ");
                    recipientId = scanner.nextLine().trim();
                }

                switch (choice) {
                    case "1":
                        user.viewBalance();
                        break;
                    case "2":
                        user.withdraw(amount);
                        break;
                    case "3":
                        user.deposit(amount);
                        break;
                    case "4":
                        user.transfer(amount, recipientId, users);
                        break;
                    case "5":
                        user.viewTransactionHistory();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid user ID or PIN. Please try again.");
        }

        scanner.close();
    }
}
