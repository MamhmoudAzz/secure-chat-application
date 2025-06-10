package com.securechat.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.securechat.model.*;

import java.awt.*;
import java.awt.event.*;
import java.security.MessageDigest;

/**
 * Modern login view for user authentication.
 * Provides interface for existing users to log in and new users to register.
 */
public class LoginView extends JFrame {
    private ParticipantListModel participantModel;
    private JTextField pseudoField;
    private JPasswordField passwordField;

    // Improved color scheme for better readability
    private static final Color PRIMARY_COLOR = new Color(0, 123, 255);
    private static final Color SECONDARY_COLOR = new Color(73, 80, 87);
    private static final Color SUCCESS_COLOR = new Color(40, 167, 69);
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color CARD_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(33, 37, 41);
    private static final Color BORDER_COLOR = new Color(206, 212, 218);

    /**
     * Creates a new modern login view.
     *
     * @param participantModel The model for managing participants
     * @param messageModel The model for managing messages
     */
    public LoginView(ParticipantListModel participantModel, MessageListModel messageModel) {
        this.participantModel = participantModel;

        setTitle("Secure Chat - Login");
        setSize(520, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setMinimumSize(new Dimension(520, 650));

        // Set modern look and feel
        setupModernUI();
        initComponents(messageModel);

        setVisible(true);
    }

    /**
     * Sets up modern UI styling.
     */
    private void setupModernUI() {
        getContentPane().setBackground(BACKGROUND_COLOR);

        // Try to set system look and feel with modern styling
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Customize UI defaults for better readability
            UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 14));
            UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 14));
            UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 14));
            UIManager.put("PasswordField.font", new Font("Arial", Font.PLAIN, 14));

        } catch (Exception e) {
            System.err.println("Could not set look and feel: " + e.getMessage());
        }
    }

    /**
     * Initializes the modern UI components.
     */
    private void initComponents(MessageListModel messageModel) {
        setLayout(new BorderLayout());

        // Main container with padding
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        // Header panel
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Login card panel
        JPanel loginCard = createLoginCard(messageModel);
        mainPanel.add(loginCard, BorderLayout.CENTER);

        // Footer panel
        JPanel footerPanel = createFooterPanel();
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Creates the modern header panel with logo and title.
     */
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_COLOR);
        headerPanel.setBorder(new EmptyBorder(0, 0, 30, 0));

        // Logo and title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(BACKGROUND_COLOR);

        JLabel logoLabel = new JLabel("🔐");
        logoLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 32));

        logoLabel.setForeground(PRIMARY_COLOR);
        titlePanel.add(logoLabel);

        JLabel titleLabel = new JLabel("  Secure Chat");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(TEXT_COLOR);
        titlePanel.add(titleLabel);

        headerPanel.add(titlePanel, BorderLayout.CENTER);

        // Subtitle
        JLabel subtitleLabel = new JLabel("End-to-end encrypted messaging", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setForeground(SECONDARY_COLOR);
        headerPanel.add(subtitleLabel, BorderLayout.SOUTH);

        return headerPanel;
    }

    /**
     * Creates the modern login card with form fields.
     */
    private JPanel createLoginCard(MessageListModel messageModel) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(CARD_COLOR);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(222, 226, 230), 1),
            new EmptyBorder(30, 30, 30, 30)
        ));

        // Welcome text
        JLabel welcomeLabel = new JLabel("Welcome back!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(TEXT_COLOR);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setBorder(new EmptyBorder(0, 0, 25, 0));
        cardPanel.add(welcomeLabel);

        // Username field
        cardPanel.add(createInputField("● Username:", pseudoField = new JTextField()));
        cardPanel.add(Box.createVerticalStrut(20));

        // Password field
        cardPanel.add(createInputField("● Password:", passwordField = new JPasswordField()));
        cardPanel.add(Box.createVerticalStrut(30));

        // Login button
        JButton loginButton = createModernButton("Sign In", PRIMARY_COLOR, Color.BLACK);
        loginButton.addActionListener(e -> attemptLogin(messageModel));
        cardPanel.add(loginButton);

        cardPanel.add(Box.createVerticalStrut(15));

        // Register button
        JButton registerButton = createModernButton("Create New Account", BACKGROUND_COLOR, Color.BLACK);
        registerButton.addActionListener(e -> new RegisterView(participantModel, getX() + 50, getY() + 50));
        cardPanel.add(registerButton);

        return cardPanel;
    }

    /**
     * Creates the footer panel with demo user info.
     */
    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(BACKGROUND_COLOR);
        footerPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        JLabel demoLabel = new JLabel("<html><center><b>Demo Users:</b><br>" +
            "admin/admin123 &nbsp;&nbsp;•&nbsp;&nbsp; alice/password &nbsp;&nbsp;•&nbsp;&nbsp; bob/123456</center></html>", SwingConstants.CENTER);
        demoLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        demoLabel.setForeground(SECONDARY_COLOR);
        footerPanel.add(demoLabel, BorderLayout.CENTER);

        JButton exitButton = createModernButton("Exit", new Color(220, 53, 69), Color.BLACK);
        exitButton.addActionListener(e -> System.exit(0));
        exitButton.setPreferredSize(new Dimension(80, 30));
        footerPanel.add(exitButton, BorderLayout.EAST);

        return footerPanel;
    }

    /**
     * Creates a modern input field with label.
     */
    private JPanel createInputField(String labelText, JTextField field) {
        JPanel fieldPanel = new JPanel(new BorderLayout());
        fieldPanel.setBackground(CARD_COLOR);
        fieldPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(TEXT_COLOR);
        label.setBorder(new EmptyBorder(0, 0, 8, 0));
        fieldPanel.add(label, BorderLayout.NORTH);

        field.setFont(new Font("Arial", Font.PLAIN, 16));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 2),
            new EmptyBorder(12, 15, 12, 15)
        ));
        field.setBackground(Color.WHITE);
        field.setPreferredSize(new Dimension(0, 45));

        // Add focus effects
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(PRIMARY_COLOR, 3),
                    new EmptyBorder(11, 14, 11, 14)
                ));
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(BORDER_COLOR, 2),
                    new EmptyBorder(12, 15, 12, 15)
                ));
            }
        });

        fieldPanel.add(field, BorderLayout.CENTER);
        return fieldPanel;
    }

    /**
     * Creates a modern styled button.
     */
    private JButton createModernButton(String text, Color bgColor, Color textColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(textColor);
        button.setBackground(bgColor);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(bgColor.darker(), 1),
            new EmptyBorder(15, 25, 15, 25)
        ));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
        button.setOpaque(true);

        // Add hover effects
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (bgColor.equals(PRIMARY_COLOR)) {
                    button.setBackground(new Color(48, 112, 239));
                } else if (bgColor.equals(BACKGROUND_COLOR)) {
                    button.setBackground(new Color(233, 236, 239));
                } else {
                    button.setBackground(bgColor.darker());
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    /**
     * Attempts to log in the user with the provided credentials.
     */
    private void attemptLogin(MessageListModel messageModel) {
        String pseudo = pseudoField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (pseudo.isEmpty() || password.isEmpty()) {
            showModernDialog("Please fill in all fields.", "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!isInputSafe(pseudo) || !isInputSafe(password)) {
            showModernDialog("Invalid characters detected in input!", "Security Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String hashed = sha256(password);
        if (participantModel.login(pseudo, hashed)) {
            User user = participantModel.getUserByPseudo(pseudo);
            new MainAppView(user, messageModel, participantModel);
            dispose();
        } else {
            showModernDialog("Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Shows a modern styled dialog.
     */
    private void showModernDialog(String message, String title, int messageType) {
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 13));
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    /**
     * Computes SHA-256 hash of the input string.
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
            throw new RuntimeException(ex);
        }
    }

    /**
     * Validates input for security (prevents basic injection attacks).
     */
    private boolean isInputSafe(String input) {
        String[] blacklistedPatterns = {
            ".*[';#\\\\/].*",                      // single quote, semicolon, hash, backslash, slash
            ".*\\*.*",                             // asterisk
            ".*--.*",                              // double hyphen (SQL comment)
            "(?i).*\\b(SELECT|INSERT|UPDATE|DELETE|DROP|OR|AND)\\b.*"  // SQL keywords (case-insensitive)
        };
    
        for (String pattern : blacklistedPatterns) {
            if (input.matches(pattern)) {
                return false;
            }
        }
        return true;
    }    
}
