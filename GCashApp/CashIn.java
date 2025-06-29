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

        System.out.println("Choose Where to Receive Money From...");
        System.out.print("Enter Account ID: ");
        String accId = scanner.nextLine().trim();

        if (accId.equals(current.getAccountId())) {
            System.out.println("\n>> Transaction failed: You cannot cash in from your own account.");
            return;
        }

        System.out.print("Enter PIN Number: ");
        String pin = scanner.nextLine().trim();

        if (!pin.matches("\\d{4}")) {
            System.out.println("\n>> Wrong input: PIN Number must be exactly 4 digits.");
            return;
        }

        System.out.print("Enter Amount: ₱ ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("\n>> Invalid input: Amount should only contain numbers.");
            return;
        }

        if (amount <= 0) {
            System.out.println("\n>> Invalid amount. Please enter an amount greater than ₱0.");
            return;
        }

        UserAuthentication.User found = null;
        for (UserAuthentication.User u : users) {
            if (u.getAccountId().equals(accId)) {
                if (u.getPin().equals(pin)) {
                    found = u;
                    break;
                } else {
                    System.out.println("\n>> Transaction failed: PIN Number does not match.");
                    return;
                }
            }
        }

        if (found == null) {
            System.out.println("\n>> Transaction failed: Account ID does not exist.");
            return;
        }

        if (found.getBalance() < amount) {
            System.out.println("\n>> Transaction failed: Insufficient funds.");
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

        System.out.println("\n>> Cash-In Successful");
    }
}
