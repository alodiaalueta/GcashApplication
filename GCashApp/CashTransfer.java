package GCashApp;

import java.util.ArrayList;
import java.util.Scanner;

public class CashTransfer {
    private static Scanner scanner = new Scanner(System.in);

    public static void transfer(ArrayList<Transaction> transactions) {
        String txId = "TX" + System.currentTimeMillis();
        System.out.println("\n" + txId + "\n");
        System.out.println("      CASH TRANSFER \n");

        UserAuthentication.User sender = UserAuthentication.currentUser;
        System.out.print("Enter Mobile Number: ");
        String number = scanner.nextLine().trim();
        if (!number.matches("\\d{11}")) {
            System.out.println("Invalid number. Please enter 11 digits.");
            return;
        }

        System.out.print("Enter Amount: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        System.out.println("\nConfirm Transaction");
        System.out.println("a. YES");
        System.out.println("b. NO, PLEASE EXIT");
        String choice = scanner.nextLine().trim();

        if (choice.equalsIgnoreCase("a")) {
            UserAuthentication.User recipient = null;
            for (UserAuthentication.User u : UserAuthentication.getUsers()) {
                if (u.getNumber().equals(number)) {
                    recipient = u;
                    break;
                }
            }
            if (recipient == null) {
                System.out.println("NON EXISTENT ACCOUNT");
                return;
            }
            if (sender.getBalance() < amount) {
                System.out.println("Insufficient balance.");
                return;
            }

            sender.subtractBalance(amount);
            recipient.addBalance(amount);
            transactions.add(new Transaction(
                amount,
                recipient.getName(),
                recipient.getAccountId(),
                recipient.getId(),   
                sender.getId(),      
                "CASH_TRANSFER"
            ));
            System.out.println("Cash Transfer Successful!");
        } else {
            System.out.println("HOME DASHBOARD");
        }
    }
}
