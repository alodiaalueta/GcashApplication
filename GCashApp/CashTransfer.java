package GCashApp;

import java.util.ArrayList;
import java.util.Scanner;

public class CashTransfer {
    private static Scanner scanner = new Scanner(System.in);

    public static void transfer(ArrayList<Transaction> transactions) {
        String txId = "TX" + System.currentTimeMillis();
        System.out.println("\n===================================");
        System.out.println("Transaction ID: " + txId );
        System.out.println("===================================");
        System.out.println("       ~ CASH TRANSFER ~");
        System.out.println("Choose the recipient to transfer money to...");
        UserAuthentication.User sender = UserAuthentication.currentUser;
        System.out.print("Enter Mobile Number: ");
        String number = scanner.nextLine().trim();
        
        if (!number.matches("\\d{11}")) {
            System.out.println("\n>> Invalid Mobile Number entered. It must be exactly 11 digits.");
            return;
        }

        if (number.equals(sender.getNumber())) {
            System.out.println("\n>> Transaction failed: You cannot Cash Transfer to your own account.");
            return;
        }

        UserAuthentication.User recipient = null;
        for (UserAuthentication.User u : UserAuthentication.getUsers()) {
            if (u.getNumber().equals(number)) {
                recipient = u;
                break;
            }
        }

        if (recipient == null) {
            System.out.println("Transaction failed: Account does not exist.");
            return;
        }

        double amount = -1;
        while (amount <= 0) {
            System.out.print("Enter Amount: ₱ ");
            String inputAmount = scanner.nextLine().trim();

            try {
                amount = Double.parseDouble(inputAmount);

                if (amount <= 0) {
                    System.out.println("\n>> Invalid amount. Please enter an amount greater than ₱0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n>> Invalid input: Amount should only contain numbers. \n");
            }
        }

        System.out.println("\nConfirm Transaction");
        System.out.println("a. YES");
        System.out.println("b. NO, PLEASE EXIT");
        System.out.println("\nChoose Input: ");
        String choice = scanner.nextLine().trim();

        if (choice.equalsIgnoreCase("a")) {
            if (sender.getBalance() < amount) {
                System.out.println("\n>> Transaction failed: Insufficient funds.");
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
            System.out.println("\n >> Cash-Transfer Successful");
        } 
    }
}
