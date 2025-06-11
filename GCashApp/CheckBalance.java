// CheckBalance.java
package GCashApp;

public class CheckBalance {
    public static void displayBalance() {
        if (UserAuthentication.currentUser != null) {
            System.out.println("\n   ~ ACCOUNT BALANCE ~");
            System.out.println("NAME: " + UserAuthentication.currentUser.getName());
            System.out.println("TOTAL AMOUNT: ₱" +
                String.format("%.2f", UserAuthentication.currentUser.getBalance()));
            System.out.println("\nID           AMOUNT       USER_ID");
            System.out.printf("%-12s ₱%-11.2f %s%n",
                UserAuthentication.currentUser.getId(),
                UserAuthentication.currentUser.getBalance(),
                UserAuthentication.currentUser.getId());
            System.out.println("\n1. EXIT");
        } else {
            System.out.println("\nNo user logged in.");
        }
    }
}
