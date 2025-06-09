package com.securechat.model;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import com.securechat.security.RSAUtil;

/**
 * Represents a user in the secure chat application.
 * Each user has a unique ID, pseudonym, password, and RSA key pair for encryption.
 */
public class User {
    private String id;
    private String pseudo;
    private String password;
    private KeyPair keyPair;

    /**
     * Creates a new user with the specified credentials.
     * Automatically generates an RSA key pair for the user.
     * 
     * @param id The unique identifier for the user
     * @param pseudo The user's display name/pseudonym
     * @param password The user's password (should be hashed before storage)
     */
    public User(String id, String pseudo, String password) {
        this.id = id;
        this.pseudo = pseudo;
        this.password = password;
        this.keyPair = RSAUtil.generateKeyPair();
    }

    /**
     * Gets the user's unique identifier.
     * 
     * @return The user's ID
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the user's pseudonym/display name.
     * 
     * @return The user's pseudonym
     */
    public String getPseudoName() {
        return pseudo;
    }

    /**
     * Gets the user's password.
     * 
     * @return The user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the user's public key for encryption.
     * 
     * @return The user's public key
     */
    public PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    /**
     * Gets the user's private key for decryption.
     * 
     * @return The user's private key
     */
    public PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }

    /**
     * Returns the string representation of the user (pseudonym).
     * 
     * @return The user's pseudonym
     */
    @Override
    public String toString() {
        return pseudo;
    }
}
