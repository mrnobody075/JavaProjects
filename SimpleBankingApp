import java.io.*;
import java.util.*;

public class SimpleBankingApp {

    private static final String FILE_NAME = "banking_data.txt";
    private static Map<String, BankAccount> accounts = new HashMap<>();

    public static void main(String[] args) {
        loadAccounts();
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("\nSimple Banking Application");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createAccount(scanner);
                    break;
                case "2":
                    depositMoney(scanner);
                    break;
                case "3":
                    withdrawMoney(scanner);
                    break;
                case "4":
                    checkBalance(scanner);
                    break;
                case "5":
                    viewTransactionHistory(scanner);
                    break;
                case "6":
                    saveAccounts();
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("6"));

        scanner.close();
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = Double.parseDouble(scanner.nextLine());

        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account already exists.");
        } else {
            BankAccount account = new BankAccount(accountNumber, balance);
            accounts.put(accountNumber, account);
            System.out.println("Account created successfully.");
        }
    }

    private static void depositMoney(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter deposit amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdrawMoney(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter withdrawal amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            if (account.withdraw(amount)) {
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void checkBalance(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Current balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void viewTransactionHistory(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Transaction history:");
            for (String transaction : account.getTransactionHistory()) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void loadAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            accounts = (Map<String, BankAccount>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    private static void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Nested class for BankAccount
    private static class BankAccount implements Serializable {
        private String accountNumber;
        private double balance;
        private List<String> transactionHistory;

        public BankAccount(String accountNumber, double balance) {
            this.accountNumber = accountNumber;
            this.balance = balance;
            this.transactionHistory = new ArrayList<>();
            transactionHistory.add("Account created with initial balance: $" + balance);
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount + ", New balance: $" + balance);
        }

        public boolean withdraw(double amount) {
            if (amount > balance) {
                return false;
            }
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount + ", New balance: $" + balance);
            return true;
        }

        public List<String> getTransactionHistory() {
            return transactionHistory;
        }
    }
}
