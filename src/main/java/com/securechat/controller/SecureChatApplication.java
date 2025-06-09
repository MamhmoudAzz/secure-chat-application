package com.securechat.controller;

import com.securechat.model.ParticipantListModel;
import com.securechat.model.MessageListModel;
import com.securechat.view.LoginView;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Main application controller for the Secure Chat application.
 * Initializes the application and sets up the initial state.
 */
public class SecureChatApplication {
    
    /**
     * Main entry point for the Secure Chat application.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Set system look and feel for better UI appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Warning: Could not set system look and feel: " + e.getMessage());
        }

        // Run the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            initializeApplication();
        });
    }

    /**
     * Initializes the application by creating models and launching the login view.
     */
    private static void initializeApplication() {
        System.out.println("Starting Secure Chat Application...");
        
        // Create the models
        ParticipantListModel participantModel = new ParticipantListModel();
        MessageListModel messageModel = new MessageListModel();

        // Pre-populate with demo users for testing
        setupDemoUsers(participantModel);

        // Launch the login screen
        new LoginView(participantModel, messageModel);
        
        System.out.println("Application initialized successfully.");
    }

    /**
     * Sets up demo users for testing purposes.
     * In a production environment, this would be replaced with database initialization.
     * 
     * @param participantModel The participant model to populate
     */
    private static void setupDemoUsers(ParticipantListModel participantModel) {
        System.out.println("Setting up demo users...");
        
        // Create demo users with different roles
        participantModel.registerParticipant("1", "admin", "admin123");
        participantModel.registerParticipant("2", "alice", "password");
        participantModel.registerParticipant("3", "bob", "123456");
        participantModel.registerParticipant("4", "charlie", "secure");
        
        System.out.println("Demo users created:");
        System.out.println("- admin / admin123");
        System.out.println("- alice / password");
        System.out.println("- bob / 123456");
        System.out.println("- charlie / secure");
    }
}
