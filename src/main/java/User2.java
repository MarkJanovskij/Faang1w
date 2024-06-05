import java.util.*;
import java.util.Scanner;

// Here I wanted to make an interface where new users can add their info and avoid collision for the same person info happens to be.
public class User2 {
    private static int nextId = 1;

    // Creating User construction again
    private int id;
    private String name;
    private int age;
    private String occupation;
    private String address;

    // Constructor for existing users with an ID
    public User2(int id, String name, int age, String occupation, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.occupation = occupation;
        this.address = address;
    }

    // Constructor for new users without an ID (ID will be assigned automatically)
    public User2(String name, int age, String occupation, String address) {
        this.id = nextId++;
        this.name = name;
        this.age = age;
        this.occupation = occupation;
        this.address = address;
    }

    // Getter fields
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getAddress() {
        return address;
    }

    // Group Users by age
    public static Map<Integer, List<User2>> groupUsers(List<User2> users) {
        Map<Integer, List<User2>> groupedUsers = new HashMap<>();
        for (User2 user : users) {
            int age = user.getAge();
            groupedUsers.computeIfAbsent(age, k -> new ArrayList<>()).add(user);
        }
        return groupedUsers;
    }

    // Check if there are duplicate users
    public static boolean isDuplicateUser(List<User2> users, User2 newUser) {
        for (User2 user : users) {
            if (user.getName().equals(newUser.getName()) &&
                    user.getAge() == newUser.getAge() &&
                    user.getOccupation().equals(newUser.getOccupation()) &&
                    user.getAddress().equals(newUser.getAddress())) {
                return true;
            }
        }
        return false;
    }

    // Main method to run the program
    public static void main(String[] args) {
        List<User2> users = new ArrayList<>();
        users.add(new User2(1, "Mark", 33, "Developer", "Salzburg"));
        users.add(new User2(2, "Vlad", 30, "Senior Developer", "Amsterdam"));
        users.add(new User2(3, "Kany", 25, "Tech Lead", "Germany"));
        users.add(new User2(4, "MaxFry", 33, "Head Office", "Moscow"));

        // Set the nextId to one more than the highest existing ID
        int maxId = users.stream().mapToInt(User2::getId).max().orElse(0);
        nextId = maxId + 1;

        Scanner scanner = new Scanner(System.in);
        String name;
        int age;
        String occupation;
        String address;

        // New user info input
        while (true) {
            System.out.println("Enter new user info: ");
            System.out.print("Name: ");
            name = scanner.nextLine();
            System.out.print("Age: ");
            age = Integer.parseInt(scanner.nextLine());
            System.out.print("Occupation/Job: ");
            occupation = scanner.nextLine();
            System.out.print("Address: ");
            address = scanner.nextLine();

            // Creating new user and avoiding duplicates
            User2 newUser = new User2(name, age, occupation, address);
            if (isDuplicateUser(users, newUser)) {
                System.out.println("This user already exists.");
            } else {
                users.add(newUser);
                System.out.println("User added successfully with ID: " + newUser.getId());
            }

            System.out.print("Do you want to add another user? (yes/no): ");
            if (!scanner.nextLine().equalsIgnoreCase("yes")) {
                break;
            }
        }
        scanner.close();

        // Show groups by ages and users
        Map<Integer, List<User2>> groupedUsers = User2.groupUsers(users);
        for (Map.Entry<Integer, List<User2>> entry : groupedUsers.entrySet()) {
            System.out.println("Age: " + entry.getKey());
            for (User2 user : entry.getValue()) {
                System.out.println("Name: " + user.getName() + ", Occupation: " + user.getOccupation() + ", Address: " + user.getAddress());
            }
            System.out.println();
        }
    }
}
