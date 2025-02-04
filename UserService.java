//package services;

//import models.User;
import java.util.*;
import java.io.*;

public class UserService {
    private User[] users = new User[10]; 
    private int count = 0;
    private final String FILE_PATH = "Userdata.txt";

    public UserService() {
        loadUsersFromFile();
    }

    public void registerUser(String username, String email, String password, String role) {
        if (count == users.length) {
            expandArray();
        }
        users[count++] = new User(username, email, password, role);
        saveUserToFile(username, email, password, role);
        System.out.println("User registered successfully: " + username);
    }

    public User login(String username, String password) {
        for (int i = 0; i < count; i++) {
            if (users[i].getUsername().equals(username) && users[i].getPassword().equals(password)) {
                return users[i];
            }
        }
        System.err.println("Login failed: Invalid credentials.");
        return null;
    }

    private void expandArray() {
        User[] newUsers = new User[users.length * 2]; // Double the size
        System.arraycopy(users, 0, newUsers, 0, users.length);
        users = newUsers;
    }

    private void saveUserToFile(String username, String email, String password, String role) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(username + "," + email + "," + password + "," + role + "\n");
            System.out.println("User saved to file: " + username); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    if (count == users.length) {
                        expandArray();
                    }
                    users[count++] = new User(parts[0], parts[1], parts[2], parts[3]);
                    System.out.println("User loaded from file: " + parts[0]); 
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading users from file.");
        }
    }
}