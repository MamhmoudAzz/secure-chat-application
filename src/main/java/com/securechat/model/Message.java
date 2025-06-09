package com.securechat.model;

/**
 * Represents a message in the secure chat application.
 * Contains both the original plain text and encrypted content.
 */
public class Message {
    private String sender;
    private String receiver;
    private String encryptedContent;
    private String originalContent;

    /**
     * Creates a new message with the specified details.
     * 
     * @param sender The pseudonym of the message sender
     * @param receiver The pseudonym of the message receiver
     * @param originalContent The original plain text message
     * @param encryptedContent The encrypted version of the message
     */
    public Message(String sender, String receiver, String originalContent, String encryptedContent) {
        this.sender = sender;
        this.receiver = receiver;
        this.originalContent = originalContent;
        this.encryptedContent = encryptedContent;
    }

    /**
     * Gets the sender's pseudonym.
     * 
     * @return The sender's pseudonym
     */
    public String getSender() {
        return sender;
    }

    /**
     * Gets the receiver's pseudonym.
     * 
     * @return The receiver's pseudonym
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Gets the encrypted content of the message.
     * 
     * @return The encrypted message content
     */
    public String getContent() {
        return encryptedContent;
    }

    /**
     * Gets the original plain text content of the message.
     * 
     * @return The original message content
     */
    public String getOriginalContent() { 
        return originalContent; 
    }

    /**
     * Returns the string representation of the message (original content).
     * 
     * @return The original message content
     */
    @Override
    public String toString() {
        return originalContent;
    }
}
