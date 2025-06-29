
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
                System.out.println("\n>> Unrecognized input. Please select a valid option.");
            }
        }
    }

    private static void login() {
        System.out.println("\n=================================");
        System.out.println("        ~ EXISTING USER ~");
        System.out.println("=================================");
        System.out.print("Enter your Mobile Number: ");
        String number = scanner.nextLine().trim();
        if (!number.matches("\\d{11}")) {
            System.out.println("\n>> Invalid Mobile Number entered. It must be exactly 11 digits.");
            return;
        }

        System.out.print("Enter your PIN Number: ");
        String pin = scanner.nextLine().trim();
        if (!pin.matches("\\d{4}")) {
            System.out.println("\n>> Failed: 4-digit PIN Number is required.");
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
                System.out.println("\n>> You have successfully logged in.");
                return;
            }
        }
        System.out.println("\n>> Login failed: Incorrect Mobile/PIN/AccountID entered.");
    }

    private static void register() {
    System.out.println("\n=================================");
    System.out.println("           ~ NEW USER ~");
    System.out.println("=================================");

    System.out.print("Create a User ID: ");
    String id = scanner.nextLine().trim();
    for (User u : users) {
        if (u.getId().equalsIgnoreCase(id)) {
            System.out.println("\n>> Registration failed: User ID already exists.");
            return;
        }
    }

    System.out.print("Create an Account ID: ");
    String accountId = scanner.nextLine().trim();
    for (User u : users) {
        if (u.getAccountId().equalsIgnoreCase(accountId)) {
            System.out.println("\n>> Registration failed: Account ID already exists.");
            return;
        }
    }

    System.out.print("Enter your Full Name: ");
    String name = scanner.nextLine().trim();

    System.out.print("Enter your Email: ");
    String email = scanner.nextLine().trim();

    System.out.print("Enter your Mobile Number: ");
    String number = scanner.nextLine().trim();
    if (!number.matches("\\d{11}")) {
        System.out.println("\n>> Invalid mobile number entered. It must be exactly 11 digits.");
        return;
    }
    for (User u : users) {
        if (u.getNumber().equals(number)) {
            System.out.println("\n>> Registration failed: Mobile Number already exists.");
            return;
        }
    }

    System.out.print("Create PIN: ");
    String pin = scanner.nextLine().trim();
    if (!pin.matches("\\d{4}")) {
        System.out.println("\n>> Failed: 4-digit PIN Number is required.");
        return;
    }

   
    User u = new User(id, accountId, name, email, number, pin, 0);
    users.add(u);
    currentUser = u;
    System.out.println("\n>> Your account has been successfully created.");
}


    public static void myAccount() {
        if (currentUser == null) return;
        System.out.println("\n=====================================");
        System.out.println("            ~ My Account ~");
        System.out.println("=====================================");
        System.out.println("User_ID: " + currentUser.getId());
        System.out.println("Name: " + currentUser.getName());
        System.out.println("Email: " + currentUser.getEmail());
        System.out.println("Number: " + currentUser.getNumber());
        System.out.println("=====================================");
        System.out.println("Would you like to Change your PIN Number?");
        System.out.println("1. YES");
        System.out.println("2. NO, please Exit");
        System.out.println("\nChoose Input: ");
        String choice = scanner.nextLine().trim();

        if (choice.equals("1")) {
            System.out.print("\nEnter your CURRENT PIN Number: ");
            String oldPin = scanner.nextLine().trim();
            if (oldPin.equals(currentUser.getPin())) {
                System.out.print("Set NEW PIN Number (4 digits): ");
                String newPin = scanner.nextLine().trim();
                if (newPin.matches("\\d{4}")) {
                    currentUser.setPin(newPin);
                    System.out.println("\n>> You have successfully changed your PIN Number.");
                } else {
                    System.out.println("\n >> Failed: 4-digit PIN Number is required.");
                }
            } else {
                System.out.println("\n >> The current PIN Number you entered is incorrect.");
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
