package GCashApp;

import java.util.ArrayList;
import java.util.Scanner;

public class CashIn {
    private static Scanner scanner = new Scanner(System.in);

    public static void cashInMenu(ArrayList<Transaction> transactions) {
        System.out.println("\n===========================");
        System.out.println("       ~ CASH IN ~");
        System.out.println("===========================");

        ArrayList<UserAuthentication.User> users = UserAuthentication.getUsers();
        UserAuthentication.User current = UserAuthentication.currentUser;

        
        System.out.println("* Cash In From *  ");
        System.out.print("Enter Account ID: ");
        String accId = scanner.nextLine().trim();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine().trim();
        System.out.print("Enter Amount: ");
        double amount;

        try {
            amount = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("\n >> Invalid amount.");
            return;
        }

        UserAuthentication.User found = null;
        for (UserAuthentication.User u : users) {
            if (u.getAccountId().equals(accId) && u.getPin().equals(pin)) {
                found = u;
                break;
            }
        }

        if (found != null) {
            if (found.getBalance() < amount) {
                System.out.println("\n >> FAILED! INSUFFICIENT FUNDS IN SOURCE ACCOUNT");
                return;
            }

            
            found.subtractBalance(amount);

            
            current.addBalance(amount);

           
            transactions.add(new Transaction(
                amount,
                current.getName(),
                current.getAccountId(),
                current.getId(),     
                found.getId(),       
                "CASH_IN"
            ));
            System.out.println("\n >> CASH IN SUCCESSFUL!");
        } else {
            System.out.println("\n >> FAILED! NON EXISTING ACCOUNT");
        }
    }
}
