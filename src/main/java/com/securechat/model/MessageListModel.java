package com.securechat.model;

import java.util.Observable;
import java.util.Vector;

/**
 * Model class for managing messages in the chat application.
 * Handles message storage and notification of observers when new messages are added.
 */
public class MessageListModel extends Observable {
    private Vector<Message> messageList = new Vector<>();

    /**
     * Sends a message by adding it to the message list.
     * Notifies all observers when a new message is added.
     * 
     * @param sender The pseudonym of the message sender
     * @param receiver The pseudonym of the message receiver
     * @param original The original plain text message
     * @param encrypted The encrypted version of the message
     */
    public void sendMessage(String sender, String receiver, String original, String encrypted) {
        messageList.add(new Message(sender, receiver, original, encrypted));
        setChanged();
        notifyObservers();
    }

    /**
     * Gets all messages in the chat.
     * 
     * @return Vector containing all messages
     */
    public Vector<Message> getMessages() {
        return messageList;
    }
}
