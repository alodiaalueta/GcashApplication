package GCashApp;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            UserAuthentication.launchApp();

            while (UserAuthentication.currentUser != null) {
                showHomeDashboard();
                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "1":
                        CashIn.cashInMenu(transactions);
                        break;
                    case "2":
                        CashTransfer.transfer(transactions);
                        break;
                    case "3":
                        Transactions.displayTransactions(transactions);
                        break;
                    case "4":
                        UserAuthentication.myAccount();
                        break;
                    case "5":
                        CheckBalance.displayBalance();
                        break;
                    case "6":
                        UserAuthentication.currentUser = null;
                        break;
                    default:
                        System.out.println("Invalid input. Try again.");
                }
            }
        }
    }

    private static void showHomeDashboard() {
        System.out.println("\n    HOME DASHBOARD");
        System.out.println("\nName: " + UserAuthentication.currentUser.getName());
        System.out.println("Mobile Number: " + UserAuthentication.currentUser.getNumber());
        System.out.println("\n1. Cash In");
        System.out.println("2. Cash Transfer");
        System.out.println("3. Transaction History");
        System.out.println("4. My Account");
        System.out.println("5. Check Balance");
        System.out.println("6. Exit / Log out");
        System.out.print("\nChoose input: ");
    }
}
