package GCashApp;

import java.util.ArrayList;

public class CheckBalance {

    public static void displayBalance() {
        if (UserAuthentication.currentUser != null) {
            System.out.println("\n===========================");
            System.out.println("    ~ ACCOUNT BALANCE ~");
            System.out.println("===========================");
            System.out.println("NAME: " + UserAuthentication.currentUser.getName());
            System.out.println("TOTAL AMOUNT: ₱" +
                String.format("%.2f", UserAuthentication.currentUser.getBalance()));
            System.out.println("-------------------------------------------------------------------");
            System.out.println("                      TRANSACTION HISTORY");
            System.out.println("-------------------------------------------------------------------");
            System.out.printf("%-20s %-12s %-15s %-15s %-15s%n",
                "ID", "AMOUNT", "TYPE", "FROM", "TO");
            System.out.println("-------------------------------------------------------------------");
            String myId = UserAuthentication.currentUser.getId();
            ArrayList<Transaction> allTx = Main.transactions;

            boolean hasTransaction = false;
            for (Transaction tx : allTx) {
                if (tx.getTransferFromId().equals(myId) || tx.getTransferToId().equals(myId)) {
                    hasTransaction = true;

                    String from = tx.getTransferFromId();
                    String to = tx.getTransferToId();
                    String type = tx.getType();
                    double amt = tx.getAmount();

                    System.out.printf("%-20s ₱%-10.2f %-15s %-15s %-15s%n",
                        tx.getId(), amt, type, from, to);
                }
            }

            if (!hasTransaction) {
                System.out.println("No transactions found.");
            }

            //System.out.println("\n1. EXIT");
        } else {
            System.out.println("\nNo user logged in.");
        }
    }
}
