package com.securechat.model;

import java.security.MessageDigest;
import java.util.Observable;
import java.util.Vector;

/**
 * Model class for managing participants/users in the chat application.
 * Handles user registration, authentication, and participant management.
 */
public class ParticipantListModel extends Observable {
    private Vector<User> participants = new Vector<>();

    /**
     * Registers a new participant with the specified credentials.
     * The password is automatically hashed using SHA-256.
     * 
     * @param id The unique identifier for the user
     * @param pseudo The user's pseudonym/display name
     * @param rawPassword The user's plain text password (will be hashed)
     */
    public void registerParticipant(String id, String pseudo, String rawPassword) {
        String hashedPassword = sha256(rawPassword);
        User newUser = new User(id, pseudo, hashedPassword);
        participants.add(newUser);
        setChanged();
        notifyObservers();
    }

    /**
     * Attempts to log in a user with the provided credentials.
     * 
     * @param pseudo The user's pseudonym
     * @param passwordHash The SHA-256 hash of the user's password
     * @return true if login is successful, false otherwise
     */
    public boolean login(String pseudo, String passwordHash) {
        for (User user : participants) {
            if (user.getPseudoName().equals(pseudo) && user.getPassword().equals(passwordHash)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Retrieves a user by their pseudonym.
     * 
     * @param pseudo The pseudonym to search for
     * @return The User object if found, null otherwise
     */
    public User getUserByPseudo(String pseudo) {
        for (User user : participants) {
            if (user.getPseudoName().equals(pseudo)) {
                return user;
            }
        }
        return null;
    }
    
    /**
     * Verifies user credentials by ID and password.
     * 
     * @param id The user's ID
     * @param password The user's password
     * @return true if credentials are valid, false otherwise
     */
    public boolean signUp(String id, String password) {
        for (User u : participants) {
            if (u.getId().equals(id) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves a user by their unique ID.
     * 
     * @param id The ID to search for
     * @return The User object if found, null otherwise
     */
    public User getUserById(String id) {
        for (User u : participants) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Gets all registered participants.
     * 
     * @return Vector containing all registered users
     */
    public Vector<User> getParticipants() {
        return participants;
    }

    /**
     * Computes the SHA-256 hash of the input string.
     * 
     * @param input The string to hash
     * @return The hexadecimal representation of the SHA-256 hash
     * @throws RuntimeException if hashing fails
     */
    private String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Failed to compute SHA-256 hash", ex);
        }
    }
}
