package movie_booking;

import java.util.*;

public class method {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<User> users = new ArrayList<>();
   

    public static void managerUser() {

        while (true) {

            System.out.println("\nUser Manager");
            System.out.println("a. Add User");
            System.out.println("b. View User (By ID)");
            System.out.println("c. Update User (By ID)");
            System.out.println("d. Delete User (By ID)");
            System.out.println("e. Exit");
            System.out.print("Enter option: ");

            String option = sc.nextLine().trim().toLowerCase();

            switch (option) {
                case "a": addUser(); break;
                case "b": viewUserById(); break;
                case "c": updateUserById(); break;
                case "d": deleteUserById(); break;
                case "e": return;
                default: System.out.println("Invalid option!");
            }
        }
    }

    //  ADD USER 
    private static void addUser() {

        System.out.println("\n--- Add User ---");

        System.out.print("First Name: ");
        String fn = sc.nextLine();

        System.out.print("Last Name: ");
        String ln = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Phone Number: ");
        String phone = sc.nextLine();

        System.out.print("Password: ");
        String pw = sc.nextLine();

        System.out.print("Do you want to SAVE this user? (y/n): ");
        String confirm = sc.nextLine().trim().toLowerCase();

        if (confirm.equals("y")) {
            User u = new User(fn, ln, email, phone, pw);
            users.add(u);
            
            Bufferwriter.saveUsersToFile(users);
            
            System.out.println("User saved! Generated ID = " + u.getId());
        } else {
            System.out.println("User not saved.");
        }
    }

    // VIEW USER BY ID 
    private static void viewUserById() {

        System.out.print("\nEnter User ID to View: ");
        int id = readInt();

        User u = findUserById(id);
        if (u == null) {
            System.out.println("User not found!");
            return;
        }

        System.out.println("\n--- User Details ---");
        System.out.println("ID       : " + u.getId());
        System.out.println("Name     : " + u.getFirstName() + " " + u.getLastName());
        System.out.println("Email    : " + u.getEmail());
        System.out.println("Phone No : " + u.getPhoneNo());
    }

    // UPDATE USER BY ID 
    private static void updateUserById() {

        System.out.print("\nEnter User ID to Update: ");
        int id = readInt();

        User u = findUserById(id);
        if (u == null) {
            System.out.println("User not found!");
            return;
        }

        System.out.println("\nLeave blank to KEEP old value.");

        // Show old values
        System.out.println("Old First Name: " + u.getFirstName());
        System.out.print("New First Name: ");
        String fn = sc.nextLine();

        System.out.println("Old Last Name: " + u.getLastName());
        System.out.print("New Last Name: ");
        String ln = sc.nextLine();

        System.out.println("Old Email: " + u.getEmail());
        System.out.print("New Email: ");
        String email = sc.nextLine();

        System.out.println("Old Phone: " + u.getPhoneNo());
        System.out.print("New Phone: ");
        String phone = sc.nextLine();

        System.out.print("New Password: ");
        String pw = sc.nextLine();

        System.out.print("Do you want to SAVE changes? (y/n): ");
        String confirm = sc.nextLine().trim().toLowerCase();

        if (confirm.equals("y")) {

            if (!fn.isEmpty()) u.setFirstName(fn);
            if (!ln.isEmpty()) u.setLastName(ln);
            if (!email.isEmpty()) u.setEmail(email);
            if (!phone.isEmpty()) u.setPhoneNo(phone);
            if (!pw.isEmpty()) u.setPassword(pw);
            
            Bufferwriter.saveUsersToFile(users);

            System.out.println("User updated successfully!");
        } else {
            System.out.println("Changes discarded.");
        }
    }

    //  DELETE USER BY ID 
    private static void deleteUserById() {

        System.out.print("\nEnter User ID to Delete: ");
        int id = readInt();

        Iterator<User> it = users.iterator();

        while (it.hasNext()) {
            User u = it.next();
            if (u.getId() == id) {

                System.out.print("Are you sure you want to delete this user? (y/n): ");
                String confirm = sc.nextLine();

                if (confirm.equalsIgnoreCase("y")) {
                    it.remove();
                    
                    Bufferwriter.saveUsersToFile(users);
                    
                    System.out.println("User deleted successfully!");
                } else {
                    System.out.println("Deletion cancelled.");
                }
                return;
            }
        }

        System.out.println("User not found!");
    }

    // --------------------- Helper: find user ---------------------
    private static User findUserById(int id) {
        for (User u : users) {
            if (u.getId() == id) return u;
        }
        return null;
    }

    // --------------------- Helper: safe int read ---------------------
    private static int readInt() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number!");
            return -1;
        }
    }
}