package com.securechat.view;

import com.securechat.model.ParticipantListModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Modern registration view for new user sign-up.
 * Allows new users to create accounts with ID, pseudonym, and password.
 */
public class RegisterView extends JFrame implements Observer {
    private ParticipantListModel participantModel;
    private JTextField idField, pseudoField;
    private JPasswordField passwordField;
    private JButton registerButton;

    // Improved color scheme for better readability
    private static final Color PRIMARY_COLOR = new Color(0, 123, 255);
    private static final Color SECONDARY_COLOR = new Color(73, 80, 87);
    private static final Color SUCCESS_COLOR = new Color(40, 167, 69);
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color CARD_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(33, 37, 41);

    /**
     * Creates a new modern registration view.
     *
     * @param participantModel The model for managing participants
     * @param h Horizontal position for the window
     * @param v Vertical position for the window
     */
    public RegisterView(ParticipantListModel participantModel, int h, int v) {
        this.participantModel = participantModel;
        this.participantModel.addObserver(this);

        setTitle("Create New Account - Secure Chat");
        setSize(520, 600);
        setLocation(h, v);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setMinimumSize(new Dimension(520, 600));

        setupModernUI();
        initComponents();

        setVisible(true);
    }

    /**
     * Sets up modern UI styling.
     */
    private void setupModernUI() {
        getContentPane().setBackground(BACKGROUND_COLOR);

        // Set readable fonts
        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("PasswordField.font", new Font("Arial", Font.PLAIN, 14));
    }

    /**
     * Initializes the modern GUI components.
     */
    private void initComponents() {
        setLayout(new BorderLayout());

        // Main container with padding
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        // Header panel
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Registration card panel
        JPanel registrationCard = createRegistrationCard();
        mainPanel.add(registrationCard, BorderLayout.CENTER);

        // Footer panel
        JPanel footerPanel = createFooterPanel();
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Creates the modern header panel.
     */
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_COLOR);
        headerPanel.setBorder(new EmptyBorder(0, 0, 30, 0));

        // Logo and title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(BACKGROUND_COLOR);

        JLabel logoLabel = new JLabel("●");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 32));
        logoLabel.setForeground(PRIMARY_COLOR);
        titlePanel.add(logoLabel);

        JLabel titleLabel = new JLabel("  Create Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(TEXT_COLOR);
        titlePanel.add(titleLabel);

        headerPanel.add(titlePanel, BorderLayout.CENTER);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Join the secure chat community", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setForeground(SECONDARY_COLOR);
        headerPanel.add(subtitleLabel, BorderLayout.SOUTH);

        return headerPanel;
    }

    /**
     * Creates the modern registration card.
     */
    private JPanel createRegistrationCard() {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(CARD_COLOR);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(222, 226, 230), 1),
            new EmptyBorder(30, 30, 30, 30)
        ));

        // Welcome text
        JLabel welcomeLabel = new JLabel("Create your secure account", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(TEXT_COLOR);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setBorder(new EmptyBorder(0, 0, 25, 0));
        cardPanel.add(welcomeLabel);

        // User ID field
        cardPanel.add(createInputField("● User ID:", idField = new JTextField()));
        cardPanel.add(Box.createVerticalStrut(20));

        // Username field
        cardPanel.add(createInputField("● Username:", pseudoField = new JTextField()));
        cardPanel.add(Box.createVerticalStrut(20));

        // Password field
        cardPanel.add(createInputField("● Password:", passwordField = new JPasswordField()));
        cardPanel.add(Box.createVerticalStrut(30));

        // Register button
        JButton registerBtn = createModernButton("Create Account", SUCCESS_COLOR, Color.BLACK);
        registerBtn.addActionListener(e -> handleRegistration());
        cardPanel.add(registerBtn);

        return cardPanel;
    }

    /**
     * Creates the footer panel.
     */
    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(BACKGROUND_COLOR);
        footerPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        JButton closeButton = createModernButton("Cancel", SECONDARY_COLOR, Color.BLACK);
        closeButton.addActionListener(e -> dispose());
        footerPanel.add(closeButton);

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
            BorderFactory.createLineBorder(new Color(206, 212, 218), 2),
            new EmptyBorder(12, 15, 12, 15)
        ));
        field.setBackground(Color.WHITE);
        field.setPreferredSize(new Dimension(0, 45));

        // Add focus effects
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
                    new EmptyBorder(9, 11, 9, 11)
                ));
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(206, 212, 218), 1),
                    new EmptyBorder(10, 12, 10, 12)
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
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    /**
     * Handles the registration process.
     */
    private void handleRegistration() {
        String id = idField.getText().trim();
        String pseudo = pseudoField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (id.isEmpty() || pseudo.isEmpty() || password.isEmpty()) {
            showModernDialog("Please fill in all fields.", "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (id.length() < 2 || pseudo.length() < 2) {
            showModernDialog("User ID and Username must be at least 2 characters long.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (password.length() < 3) {
            showModernDialog("Password must be at least 3 characters long.", "Weak Password", JOptionPane.WARNING_MESSAGE);
            return;
        }

        participantModel.registerParticipant(id, pseudo, password);
        clearFields();
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
     * Clears all input fields.
     */
    private void clearFields() {
        idField.setText("");
        pseudoField.setText("");
        passwordField.setText("");
    }

    /**
     * Called when the participant model is updated.
     */
    @Override
    public void update(Observable o, Object arg) {
        showModernDialog("Account created successfully! You can now log in.", "Registration Complete", JOptionPane.INFORMATION_MESSAGE);
        dispose(); // Close the registration window
    }
}
