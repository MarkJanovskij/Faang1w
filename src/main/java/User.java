import java.util.*;

public class User {
    // Creating class User ( Construction)
    private int id;
    private String name;
    private int age;
    private String occupation;
    private String address;

    public User(int id, String name, int age, String occupation, String address) {
        // Making information about User awailable to work with
        this.id = id;
        this.name = name;
        this.age = age;
        this.occupation = occupation;
        this.address = address;
    }

    //Getters for the fields
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

    //Making a map hash list to make User in a group and collect them there.
    public static Map<Integer, List<User>> groupUsers(List<User> users) {
        Map<Integer, List<User>> groupedUsers = new HashMap<>();
        // Key for the Hash Map will be the age
        for (User user : users) {
            int age = user.getAge();
            groupedUsers.computeIfAbsent(age, k -> new ArrayList<>()).add(user);
        }
        return groupedUsers;


    }

    public static void main(String[] args) {
        //Run the creation list of users
        List<User> users =new ArrayList<>();
        users.add(new User(1,"Mark",33,"Developer","Salzburg"));
        users.add(new User(2,"Vlad",30,"Senior Developer","Amsterdam"));
        users.add(new User(3,"Kany",25,"Tech Lead","Germany"));
        users.add(new User(4,"MaxFry",33,"Head Office"," Moscow"));

        //Group users by age
        Map<Integer, List<User>> groupedUsers = User.groupUsers(users);

        // (function)Print out the users by groups
        for (Map.Entry<Integer,List<User>> entry : groupedUsers.entrySet()){
            System.out.println("Age; "+ entry.getKey());
            for (User user : entry.getValue()){
                System.out.println("Name: "+ user.getName()+", Occupation: "+ user.getOccupation()+"Address: "+ user.getAddress());
            }
            System.out.println();
        }
    }
}
