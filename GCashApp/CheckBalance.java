package GCashApp;

import java.util.ArrayList;
import java.util.Scanner;

public class CheckBalance {
    private static Scanner scanner = new Scanner(System.in);

    public static void displayBalance() {
        if (UserAuthentication.currentUser == null) {
            System.out.println("\nNo user logged in.");
            return;
        }

        // Show current user's balance
        System.out.println("\n==============================================================");
        System.out.println("                     ~ ACCOUNT BALANCE ~");
        System.out.println("==============================================================");
        System.out.println("NAME: " + UserAuthentication.currentUser.getName());
        System.out.println("TOTAL AMOUNT: ₱" + String.format("%.2f", UserAuthentication.currentUser.getBalance()));
        System.out.println("--------------------------------------------------------------");
        System.out.println("                     TRANSACTION HISTORY");
        System.out.println("--------------------------------------------------------------");
        System.out.println("TRANSACTION NUMBER   AMOUNT      FROM_USER_ID    TO_USER_ID");
        System.out.println("--------------------------------------------------------------");

        String myId = UserAuthentication.currentUser.getId();
        ArrayList<Transaction> myTransactions = new ArrayList<>();

        for (Transaction tx : Main.transactions) {
            if (tx.getTransferFromId().equals(myId) || tx.getTransferToId().equals(myId)) {
                myTransactions.add(tx);
            }
        }

        if (myTransactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            printTransactions(myTransactions);
        }

        
        System.out.println("\nWould you like to Search User ID?");
        System.out.println("1. Yes");
        System.out.println("2. No, Exit");
        System.out.print("\nChoose Input: ");
        String choice = scanner.nextLine().trim();

        if (choice.equals("1")) {
            System.out.print("Enter User ID to search: ");
            String searchUserId = scanner.nextLine().trim();

            ArrayList<Transaction> filteredTx = new ArrayList<>();
            for (Transaction tx : myTransactions) {
                if (tx.getTransferFromId().equalsIgnoreCase(searchUserId) ||
                    tx.getTransferToId().equalsIgnoreCase(searchUserId)) {
                    filteredTx.add(tx);
                }
            }

            if (filteredTx.isEmpty()) {
                System.out.println("\nNo transactions found for User ID: " + searchUserId);
            } else {
                System.out.println("\nFiltered Transactions for User ID: " + searchUserId);
                System.out.println("--------------------------------------------------------------");
                System.out.println("TRANSACTION NUMBER   AMOUNT      FROM USER_ID    TO USER_ID");
                System.out.println("--------------------------------------------------------------");
                printTransactions(filteredTx);
            }
        }
    }

    private static void printTransactions(ArrayList<Transaction> transactions) {
        for (Transaction tx : transactions) {
            System.out.printf("%-20s ₱%-10.2f %-15s %-15s%n",
                tx.getId(),
                tx.getAmount(),
                tx.getTransferFromId(),
                tx.getTransferToId());
        }
    }
}
