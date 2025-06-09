package com.securechat.view;

import com.securechat.model.ParticipantListModel;
import com.securechat.model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Observer;
import java.util.Observable;

/**
 * Modern view for displaying the list of registered participants.
 * Shows user IDs and pseudonyms in a scrollable list with modern styling.
 */
public class ParticipantListView extends JFrame implements Observer {
    private ParticipantListModel participantModel;
    private JTextArea userListTextArea;

    // Modern color scheme
    private static final Color PRIMARY_COLOR = new Color(64, 128, 255);
    private static final Color SECONDARY_COLOR = new Color(108, 117, 125);
    private static final Color SUCCESS_COLOR = new Color(40, 167, 69);
    private static final Color BACKGROUND_COLOR = new Color(248, 249, 250);
    private static final Color CARD_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(33, 37, 41);

    /**
     * Creates a new modern participant list view.
     *
     * @param participantModel The model containing participant data
     */
    public ParticipantListView(ParticipantListModel participantModel) {
        this.participantModel = participantModel;
        this.participantModel.addObserver(this);

        setTitle("👥 Registered Users - Secure Chat");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setMinimumSize(new Dimension(400, 400));

        setupModernUI();
        initComponents();
        displayUsers();

        setVisible(true);
    }

    /**
     * Sets up modern UI styling.
     */
    private void setupModernUI() {
        getContentPane().setBackground(BACKGROUND_COLOR);

        // Set modern fonts
        UIManager.put("TextArea.font", new Font("Segoe UI", Font.PLAIN, 12));
        UIManager.put("Button.font", new Font("Segoe UI", Font.PLAIN, 12));
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 12));
    }

    /**
     * Initializes the modern GUI components.
     */
    private void initComponents() {
        setLayout(new BorderLayout());

        // Main container with padding
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Header panel
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Content panel
        JPanel contentPanel = createContentPanel();
        mainPanel.add(contentPanel, BorderLayout.CENTER);

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
        headerPanel.setBackground(CARD_COLOR);
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(222, 226, 230), 1),
            new EmptyBorder(20, 25, 20, 25)
        ));

        // Title section
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        titlePanel.setBackground(CARD_COLOR);

        JLabel iconLabel = new JLabel("👥");
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        iconLabel.setBorder(new EmptyBorder(0, 0, 0, 15));
        titlePanel.add(iconLabel);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(CARD_COLOR);

        JLabel titleLabel = new JLabel("Registered Users");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(TEXT_COLOR);
        textPanel.add(titleLabel);

        JLabel subtitleLabel = new JLabel("Active community members");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitleLabel.setForeground(SECONDARY_COLOR);
        textPanel.add(subtitleLabel);

        titlePanel.add(textPanel);
        headerPanel.add(titlePanel, BorderLayout.WEST);

        // Stats panel
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBackground(CARD_COLOR);

        JLabel countLabel = new JLabel("👤 " + participantModel.getParticipants().size() + " users");
        countLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        countLabel.setForeground(SUCCESS_COLOR);
        countLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        statsPanel.add(countLabel);

        JLabel statusLabel = new JLabel("🟢 Online");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        statusLabel.setForeground(SUCCESS_COLOR);
        statusLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        statsPanel.add(statusLabel);

        headerPanel.add(statsPanel, BorderLayout.EAST);

        return headerPanel;
    }

    /**
     * Creates the modern content panel.
     */
    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(new EmptyBorder(15, 0, 15, 0));

        // User list area
        userListTextArea = new JTextArea(20, 30);
        userListTextArea.setEditable(false);
        userListTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        userListTextArea.setBackground(CARD_COLOR);
        userListTextArea.setForeground(TEXT_COLOR);
        userListTextArea.setMargin(new Insets(20, 20, 20, 20));
        userListTextArea.setLineWrap(false);

        JScrollPane scrollPane = new JScrollPane(userListTextArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(222, 226, 230), 1));
        scrollPane.setBackground(CARD_COLOR);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        contentPanel.add(scrollPane, BorderLayout.CENTER);
        return contentPanel;
    }

    /**
     * Creates the modern footer panel.
     */
    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        footerPanel.setBackground(BACKGROUND_COLOR);

        JButton refreshButton = createModernButton("🔄 Refresh", PRIMARY_COLOR, Color.WHITE);
        refreshButton.addActionListener(e -> {
            displayUsers();
            showStatusMessage("User list refreshed!");
        });
        footerPanel.add(refreshButton);

        JButton closeButton = createModernButton("✖ Close", SECONDARY_COLOR, Color.WHITE);
        closeButton.addActionListener(e -> dispose());
        footerPanel.add(closeButton);

        return footerPanel;
    }

    /**
     * Creates a modern styled button.
     */
    private JButton createModernButton(String text, Color bgColor, Color textColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 11));
        button.setForeground(textColor);
        button.setBackground(bgColor);
        button.setBorder(new EmptyBorder(8, 16, 8, 16));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

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
     * Shows a status message to the user.
     */
    private void showStatusMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Status", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Called when the participant model is updated.
     */
    @Override
    public void update(Observable o, Object arg) {
        displayUsers();
    }

    /**
     * Updates the display with current user list in modern format.
     */
    public void displayUsers() {
        userListTextArea.setText(""); // Clear text area

        if (participantModel.getParticipants().isEmpty()) {
            userListTextArea.append("🚫 No users registered yet.\n\n");
            userListTextArea.append("💡 Tip: Create a new account to get started!");
            return;
        }

        // Header
        userListTextArea.append("🌟 SECURE CHAT COMMUNITY\n");
        userListTextArea.append(repeatString("═", 50) + "\n\n");

        userListTextArea.append("👤 USER ID       📝 USERNAME        🔐 STATUS\n");
        userListTextArea.append(repeatString("─", 50) + "\n");

        // User list with modern formatting
        for (User user : participantModel.getParticipants()) {
            String status = "🟢 Online";
            userListTextArea.append(String.format("   %-12s   %-15s   %s\n",
                user.getId(), user.getPseudoName(), status));
        }

        // Footer with statistics
        userListTextArea.append("\n" + repeatString("─", 50) + "\n");
        userListTextArea.append(String.format("📊 Total Users: %d  |  🔒 All Encrypted  |  🌐 Active Community\n",
            participantModel.getParticipants().size()));

        userListTextArea.append("\n💬 Ready to start secure conversations!");

        // Auto-scroll to top
        userListTextArea.setCaretPosition(0);
    }

    /**
     * Helper method to repeat a string n times (Java 8 compatible)
     * @param str the string to repeat
     * @param count the number of times to repeat
     * @return the repeated string
     */
    private String repeatString(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}
