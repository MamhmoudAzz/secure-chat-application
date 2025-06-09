package com.securechat.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.security.KeyPair;
import java.security.PublicKey;
import java.security.PrivateKey;

/**
 * Unit tests for RSAUtil class.
 * Tests encryption, decryption, and key generation functionality.
 */
public class RSAUtilTest {
    
    private KeyPair keyPair;
    private PublicKey publicKey;
    private PrivateKey privateKey;
    
    @BeforeEach
    void setUp() {
        keyPair = RSAUtil.generateKeyPair();
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
    }
    
    @Test
    void testGenerateKeyPair() {
        KeyPair testKeyPair = RSAUtil.generateKeyPair();
        
        assertNotNull(testKeyPair, "Key pair should not be null");
        assertNotNull(testKeyPair.getPublic(), "Public key should not be null");
        assertNotNull(testKeyPair.getPrivate(), "Private key should not be null");
        assertEquals("RSA", testKeyPair.getPublic().getAlgorithm(), "Should use RSA algorithm");
        assertEquals("RSA", testKeyPair.getPrivate().getAlgorithm(), "Should use RSA algorithm");
    }
    
    @Test
    void testEncryptDecrypt() {
        String originalMessage = "Hello, this is a test message!";
        
        // Encrypt the message
        String encryptedMessage = RSAUtil.encrypt(originalMessage, publicKey);
        assertNotNull(encryptedMessage, "Encrypted message should not be null");
        assertNotEquals(originalMessage, encryptedMessage, "Encrypted message should be different from original");
        
        // Decrypt the message
        String decryptedMessage = RSAUtil.decrypt(encryptedMessage, privateKey);
        assertNotNull(decryptedMessage, "Decrypted message should not be null");
        assertEquals(originalMessage, decryptedMessage, "Decrypted message should match original");
    }
    
    @Test
    void testEncryptWithDifferentKeys() {
        String message = "Test message";
        KeyPair anotherKeyPair = RSAUtil.generateKeyPair();
        
        String encrypted1 = RSAUtil.encrypt(message, publicKey);
        String encrypted2 = RSAUtil.encrypt(message, anotherKeyPair.getPublic());
        
        assertNotEquals(encrypted1, encrypted2, "Same message encrypted with different keys should produce different results");
    }
    
    @Test
    void testEncryptEmptyString() {
        String emptyMessage = "";
        
        String encrypted = RSAUtil.encrypt(emptyMessage, publicKey);
        String decrypted = RSAUtil.decrypt(encrypted, privateKey);
        
        assertEquals(emptyMessage, decrypted, "Empty string should encrypt and decrypt correctly");
    }
    
    @Test
    void testEncryptSpecialCharacters() {
        String specialMessage = "Special chars: !@#$%^&*()_+-=[]{}|;':\",./<>?";
        
        String encrypted = RSAUtil.encrypt(specialMessage, publicKey);
        String decrypted = RSAUtil.decrypt(encrypted, privateKey);
        
        assertEquals(specialMessage, decrypted, "Special characters should encrypt and decrypt correctly");
    }
    
    @Test
    void testEncryptUnicodeCharacters() {
        String unicodeMessage = "Unicode: 你好世界 🌍 🔒 ñáéíóú";
        
        String encrypted = RSAUtil.encrypt(unicodeMessage, publicKey);
        String decrypted = RSAUtil.decrypt(encrypted, privateKey);
        
        assertEquals(unicodeMessage, decrypted, "Unicode characters should encrypt and decrypt correctly");
    }
    
    @Test
    void testDecryptWithWrongKey() {
        String message = "Test message";
        KeyPair wrongKeyPair = RSAUtil.generateKeyPair();
        
        String encrypted = RSAUtil.encrypt(message, publicKey);
        
        assertThrows(RuntimeException.class, () -> {
            RSAUtil.decrypt(encrypted, wrongKeyPair.getPrivate());
        }, "Decrypting with wrong key should throw RuntimeException");
    }
    
    @Test
    void testEncryptWithNullKey() {
        String message = "Test message";
        
        assertThrows(RuntimeException.class, () -> {
            RSAUtil.encrypt(message, null);
        }, "Encrypting with null key should throw RuntimeException");
    }
    
    @Test
    void testDecryptWithNullKey() {
        String message = "Test message";
        String encrypted = RSAUtil.encrypt(message, publicKey);
        
        assertThrows(RuntimeException.class, () -> {
            RSAUtil.decrypt(encrypted, null);
        }, "Decrypting with null key should throw RuntimeException");
    }
    
    @Test
    void testDecryptInvalidCipherText() {
        assertThrows(RuntimeException.class, () -> {
            RSAUtil.decrypt("invalid_cipher_text", privateKey);
        }, "Decrypting invalid cipher text should throw RuntimeException");
    }
}
