package com.securechat.security;

import com.securechat.model.User;
import com.securechat.model.Message;
import com.securechat.model.MessageListModel;

/**
 * Proxy class for handling encrypted message operations.
 * Provides secure message sending and decryption capabilities.
 */
public class MessageProxy {
    private MessageListModel messageModel;

    /**
     * Creates a new MessageProxy with the specified message model.
     * 
     * @param messageModel The message model to use for storing messages
     */
    public MessageProxy(MessageListModel messageModel) {
        this.messageModel = messageModel;
    }

    /**
     * Sends an encrypted message from sender to receiver.
     * The message is encrypted using the receiver's public key.
     * 
     * @param sender The user sending the message
     * @param receiver The user receiving the message
     * @param messageText The plain text message to send
     * @throws RuntimeException if encryption fails
     */
    public void sendEncryptedMessage(User sender, User receiver, String messageText) {
        try {
            String encrypted = RSAUtil.encrypt(messageText, receiver.getPublicKey());
            messageModel.sendMessage(sender.getPseudoName(), receiver.getPseudoName(), messageText, encrypted);

            // Log to console for debugging
            System.out.println("[INFO] Message sent from ID: " + sender.getId() + " to ID: " + receiver.getId());
            System.out.println("Encrypted Content: " + encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }

    /**
     * Decrypts a message for the specified receiver.
     * Only the intended receiver can decrypt the message using their private key.
     * 
     * @param message The encrypted message to decrypt
     * @param receiver The user attempting to decrypt the message
     * @return The decrypted message if successful, original content if not the intended receiver,
     *         or error message if decryption fails
     */
    public String decryptMessage(Message message, User receiver) {
        try {
            if (message.getReceiver().equals(receiver.getPseudoName())) {
                return RSAUtil.decrypt(message.getContent(), receiver.getPrivateKey());
            } else {
                return message.getContent();
            }
        } catch (Exception e) {
            return "[Unable to decrypt message]";
        }
    }
}
