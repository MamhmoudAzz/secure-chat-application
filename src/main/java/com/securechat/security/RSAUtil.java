package com.securechat.security;

import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;

/**
 * Utility class for RSA encryption and decryption operations.
 * Provides methods to generate key pairs, encrypt, and decrypt messages.
 */
public class RSAUtil {
    
    /**
     * Generates a new RSA key pair with 2048-bit key size.
     * 
     * @return A new KeyPair containing public and private keys
     * @throws RuntimeException if key generation fails
     */
    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            return generator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate RSA key pair", e);
        }
    }

    /**
     * Encrypts a plain text message using the provided public key.
     * 
     * @param plainText The message to encrypt
     * @param publicKey The public key to use for encryption
     * @return Base64-encoded encrypted message
     * @throws RuntimeException if encryption fails
     */
    public static String encrypt(String plainText, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Failed to encrypt message", e);
        }
    }

    /**
     * Decrypts a Base64-encoded encrypted message using the provided private key.
     * 
     * @param cipherText The Base64-encoded encrypted message
     * @param privateKey The private key to use for decryption
     * @return The decrypted plain text message
     * @throws RuntimeException if decryption fails
     */
    public static String decrypt(String cipherText, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(cipherText));
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Failed to decrypt message", e);
        }
    }
}
