
package GCashApp;

import java.util.ArrayList;
import java.util.Scanner;

public class UserAuthentication {
    public static ArrayList<User> users = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    public static User currentUser = null;

    static {
        users.add(new User("ID01", "ACC01", "Alodia Alueta", "alodia.c.alueta@gmail.com", "09162834555", "0000", 50000000));
        users.add(new User("ID02", "ACC02", "Marco Yimyaem",    "marcoyimyaem@gmail.com", "09222222222", "2222", 30000));
        users.add(new User("ID03", "ACC03", "Daniel Kang",     "danielkang@yahoo.com", "09333333333", "3333", 7000));
        users.add(new User("ID04", "ACC04", "JL Gaspar","jlgaspar@gmail.com","09444444444", "4444", 4000));
        users.add(new User("ID05", "ACC05", "Steven Kim","stevenkim@gmail.com","09555555555", "5555", 6000));
    }

    public static void launchApp() {
        while (currentUser == null) {
            System.out.println("\n===========================");
            System.out.println("   ~ GCASH APPLICATION ~");
            System.out.println("===========================");
            System.out.println("1. Log-In");
            System.out.println("2. Register");
            System.out.print("\nChoose input: ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                login();
            } else if (choice.equals("2")) {
                register();
            } else {
                System.out.println("\n>> Invalid option. Please try again.");
            }
        }
    }

    private static void login() {
        System.out.println("\n===========================");
        System.out.println("     ~ EXISTING USER ~");
        System.out.println("===========================");
        System.out.print("Enter your Number: ");
        String number = scanner.nextLine().trim();
        if (!number.matches("\\d{11}")) {
            System.out.println("\n>> Invalid number. Must be 11 digits.");
            return;
        }

        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine().trim();
        if (!pin.matches("\\d{4}")) {
            System.out.println("\n>> Invalid PIN. Must be 4 digits.");
            return;
        }

        System.out.println("\n~ USER AUTHENTICATION ~");
        System.out.print("Enter your Account ID: ");
        String accountId = scanner.nextLine().trim();

        for (User u : users) {
            if (u.getNumber().equals(number)
             && u.getPin().equals(pin)
             && u.getAccountId().equals(accountId)) 
            {
                currentUser = u;
                System.out.println("\n>> SUCCESSFUL");
                return;
            }
        }
        System.out.println("\n>> WRONG ID");
    }

    private static void register() {
        System.out.println("\n===========================");
        System.out.println("        ~ NEW USER ~");
        System.out.println("===========================");
        System.out.print("Create an ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Create an Account ID: ");
        String accountId = scanner.nextLine().trim();
        System.out.print("Enter your Full Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter your Email: ");
        String email = scanner.nextLine().trim();
        
        System.out.print("Enter your Number: ");
        String number = scanner.nextLine().trim();
        if (!number.matches("\\d{11}")) {
            System.out.println("\n>> FAILED (WRONG NUMBER)");
            return;
        }
        System.out.print("Create PIN: ");
        String pin = scanner.nextLine().trim();
        if (!pin.matches("\\d{4}")) {
            System.out.println("\n>> FAILED (4 PIN NUMBERS REQUIRED)");
            return;
        }

        User u = new User(id, accountId, name, email, number, pin, 0);
        users.add(u);
        currentUser = u;
        System.out.println("\n>> SUCCESSFUL");
    }

    public static void myAccount() {
        if (currentUser == null) return;
        System.out.println("\n===========================");
        System.out.println("       ~ My Account ~");
        System.out.println("===========================");
        System.out.println("ID: " + currentUser.getId());
        System.out.println("Name: " + currentUser.getName());
        System.out.println("Email: " + currentUser.getEmail());
        System.out.println("Number: " + currentUser.getNumber());
        System.out.println("\n===========================");
        System.out.println("\nWould you like to Change your PIN?");
        System.out.println("1. YES");
        System.out.println("2. NO, please Exit");
        System.out.println("\nChoose Input: ");
        String choice = scanner.nextLine().trim();

        if (choice.equals("1")) {
            System.out.print("Enter your CURRENT PIN: ");
            String oldPin = scanner.nextLine().trim();
            if (oldPin.equals(currentUser.getPin())) {
                System.out.print("Set NEW PIN (4 digits): ");
                String newPin = scanner.nextLine().trim();
                if (newPin.matches("\\d{4}")) {
                    currentUser.setPin(newPin);
                    System.out.println("\n>> SUCCESSFUL");
                } else {
                    System.out.println("\n >> WRONG PIN, 4 PIN NUMBERS REQUIRED");
                }
            } else {
                System.out.println("\n >> WRONG CURRENT PIN");
            }
        }
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static class User {
        private String id, accountId, name, email, number, pin;
        private double balance;

        public User(String id, String accountId, String name, String email,
                    String number, String pin, double balance)
        {
            this.id = id;
            this.accountId = accountId;
            this.name = name;
            this.email = email;
            this.number = number;
            this.pin = pin;
            this.balance = balance;
        }

        public String getId()        { return id; }
        public String getAccountId() { return accountId; }
        public String getName()      { return name; }
        public String getEmail()     { return email; }
        public String getNumber()    { return number; }
        public String getPin()       { return pin; }
        public double getBalance()   { return balance; }

        public void setPin(String pin)       { this.pin = pin; }
        public void addBalance(double amt)   { this.balance += amt; }
        public void subtractBalance(double amt) { this.balance -= amt; }
    }
}
