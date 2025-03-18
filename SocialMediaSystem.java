import java.util.ArrayList;

public class SocialMediaSystem {
    class UserNode {
        int userId;
        String name;
        int age;
        ArrayList<Integer> friendIds;
        UserNode next;

        public UserNode(int userId, String name, int age) {
            this.userId = userId;
            this.name = name;
            this.age = age;
            this.friendIds = new ArrayList<>();
            this.next = null;
        }

        @Override
        public String toString() {
            return "User ID: " + userId + ", Name: " + name + ", Age: " + age + ", Friends: " + friendIds;
        }
    }

    UserNode head;

    public SocialMediaSystem() {
        this.head = null;
    }

    // Method to add user
    public void addUser(int userId, String name, int age) {
        UserNode newUser = new UserNode(userId, name, age);
        if (head == null) {
            head = newUser;
        }
        else {
            UserNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newUser;
        }
    }

    // Method to add friend connection
    public void addFriendConnection(int userId1, int userId2) {
        UserNode user1 = findUserById(userId1);
        UserNode user2 = findUserById(userId2);

        if (user1 != null && user2 != null) {
            if (!user1.friendIds.contains(userId2)) {
                user1.friendIds.add(userId2);
            }
            if (!user2.friendIds.contains(userId1)) {
                user2.friendIds.add(userId1);
            }
            System.out.println("Friend connection added between User " + userId1 + " and User " + userId2);
        }
        else {
            System.out.println("One or both users not found.");
        }
    }

    // Method to remove friend connection
    public void removeFriendConnection(int userId1, int userId2) {
        UserNode user1 = findUserById(userId1);
        UserNode user2 = findUserById(userId2);

        if (user1 != null && user2 != null) {
            user1.friendIds.remove(Integer.valueOf(userId2));
            user2.friendIds.remove(Integer.valueOf(userId1));
            System.out.println("\nFriend connection removed between User " + userId1 + " and User " + userId2);
        }
        else {
            System.out.println("One or both users not found.");
        }
    }

    // Method to find mutual friends
    public ArrayList<Integer> findMutualFriends(int userId1, int userId2) {
        UserNode user1 = findUserById(userId1);
        UserNode user2 = findUserById(userId2);

        ArrayList<Integer> mutualFriends = new ArrayList<>();
        if (user1 != null && user2 != null) {
            for (int friendId : user1.friendIds) {
                if (user2.friendIds.contains(friendId)) {
                    mutualFriends.add(friendId);
                }
            }
        }
        return mutualFriends;
    }

    // Method to display friends
    public void displayFriends(int userId) {
        UserNode user = findUserById(userId);
        if (user != null) {
            System.out.println("Friends: " + user.friendIds);
        }
        else {
            System.out.println("User not found.");
        }
    }

    // Method to find user by Id
    public UserNode findUserById(int userId) {
        UserNode current = head;
        while (current != null) {
            if (current.userId == userId) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Method to find user by name
    public UserNode findUserByName(String name) {
        UserNode current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Method to count friends
    public void countFriends() {
        UserNode current = head;
        while (current != null) {
            System.out.println("User " + current.userId + " (" + current.name + ") has " + current.friendIds.size() + " friends.");
            current = current.next;
        }
    }

    // Method to display all users
    public void displayAllUsers() {
        UserNode current = head;
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }

    public static void main(String[] args) {
        SocialMediaSystem socialNetwork = new SocialMediaSystem();

        socialNetwork.addUser(1, "Sehaj", 22);
        socialNetwork.addUser(2, "Sanya", 21);
        socialNetwork.addUser(3, "Charlie", 28);
        socialNetwork.addUser(4, "Alice", 25);

        socialNetwork.addFriendConnection(1, 2);
        socialNetwork.addFriendConnection(1, 3);
        socialNetwork.addFriendConnection(2, 3);
        socialNetwork.addFriendConnection(2, 4);

        System.out.println("\nAll Users:");
        socialNetwork.displayAllUsers();

        System.out.println("\nMutual Friends between Sehaj and Sanya: " + socialNetwork.findMutualFriends(1, 2));

        System.out.println("\nFriends of Sanya:");
        socialNetwork.displayFriends(2);

        System.out.println("\nSearch User by Name Charlie:");
        System.out.println(socialNetwork.findUserByName("Charlie"));

        System.out.println("\nFriend Counts:");
        socialNetwork.countFriends();

        socialNetwork.removeFriendConnection(2, 4);

        System.out.println("\nAll Users after removing Sanya and Alice connection:");
        socialNetwork.displayAllUsers();
    }
}

/*
Output:
    Friend connection added between User 1 and User 2
    Friend connection added between User 1 and User 3
    Friend connection added between User 2 and User 3
    Friend connection added between User 2 and User 4

    All Users:
    User ID: 1, Name: Sehaj, Age: 22, Friends: [2, 3]
    User ID: 2, Name: Sanya, Age: 21, Friends: [1, 3, 4]
    User ID: 3, Name: Charlie, Age: 28, Friends: [1, 2]
    User ID: 4, Name: Alice, Age: 25, Friends: [2]

    Mutual Friends between Sehaj and Sanya: [3]

    Friends of Sanya:
    Friends: [1, 3, 4]

    Search User by Name Charlie:
    User ID: 3, Name: Charlie, Age: 28, Friends: [1, 2]

    Friend Counts:
    User 1 (Sehaj) has 2 friends.
    User 2 (Sanya) has 3 friends.
    User 3 (Charlie) has 2 friends.
    User 4 (Alice) has 1 friends.

    Friend connection removed between User 2 and User 4

    All Users after removing Sanya and Alice connection:
    User ID: 1, Name: Sehaj, Age: 22, Friends: [2, 3]
    User ID: 2, Name: Sanya, Age: 21, Friends: [1, 3]
    User ID: 3, Name: Charlie, Age: 28, Friends: [1, 2]
    User ID: 4, Name: Alice, Age: 25, Friends: []
 */