package GCashApp;

import java.util.ArrayList;

public class Transactions {
    public static void displayTransactions(ArrayList<Transaction> transactions) {
        System.out.println("\n===============================================================================");
        System.out.println("                           ~ TRANSACTION HISTORY ~");
        System.out.println("===============================================================================");
        System.out.printf("%-15s %-15s %-30s %-25s%n",
            "Amount", "Type", "Recipient Email", "Status: Successful");

        String me = UserAuthentication.currentUser.getId();
        for (Transaction t : transactions) {
            if (t.getTransferFromId().equals(me) || t.getTransferToId().equals(me)) {
                String status = t.getType().equals("CASH_IN") ? "Cash In" : "Cash Transfer";
                String recipientEmail;
                if (status.equals("CASH_IN")) {
                    recipientEmail = UserAuthentication.currentUser.getEmail();
                } else {
                    UserAuthentication.User rec = null;
                    for (UserAuthentication.User u : UserAuthentication.getUsers()) {
                        if (u.getId().equals(t.getTransferToId())) {
                            rec = u;
                            break;
                        }
                    }
                    recipientEmail = (rec != null) ? rec.getEmail() : "";
                }
                System.out.printf("%-15.2f %-15s %-30s %-25s%n",
                    t.getAmount(), status, recipientEmail, status);
            }
        }
        
    }
}
