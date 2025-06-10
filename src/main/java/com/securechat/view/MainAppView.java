package com.securechat.view;

import com.securechat.model.ParticipantListModel;
import com.securechat.model.User;
import com.securechat.model.MessageListModel;
import com.securechat.model.CourseModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Modern main application view displayed after successful login.
 * Provides access to chat and course features.
 */
public class MainAppView extends JFrame {
    private User user;
    private MessageListModel messageModel;
    private ParticipantListModel participantModel;

    // Improved color scheme for better readability
    private static final Color PRIMARY_COLOR = new Color(0, 123, 255);
    private static final Color SECONDARY_COLOR = new Color(73, 80, 87);
    private static final Color SUCCESS_COLOR = new Color(40, 167, 69);
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color CARD_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(33, 37, 41);

    /**
     * Creates the modern main application view for the logged-in user.
     *
     * @param user The logged-in user
     * @param messageModel The message model for chat functionality
     * @param participantModel The participant model for user management
     */
    public MainAppView(User user, MessageListModel messageModel, ParticipantListModel participantModel) {
        this.user = user;
        this.messageModel = messageModel;
        this.participantModel = participantModel;

        setTitle("Secure Chat - Dashboard");
        setSize(650, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setMinimumSize(new Dimension(650, 550));

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
    }

    /**
     * Initializes the modern GUI components.
     */
    private void initComponents() {
        setLayout(new BorderLayout());

        // Main container with padding
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Header panel
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Content panel with cards
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
        headerPanel.setBackground(BACKGROUND_COLOR);
        headerPanel.setBorder(new EmptyBorder(0, 0, 30, 0));

        // Welcome section
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBackground(BACKGROUND_COLOR);

        JLabel welcomeLabel = new JLabel("Welcome back, " + user.getPseudoName() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 26));
        welcomeLabel.setForeground(TEXT_COLOR);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomePanel.add(welcomeLabel);

        welcomePanel.add(Box.createVerticalStrut(8));

        JLabel subtitleLabel = new JLabel("Ready to start secure conversations?");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setForeground(SECONDARY_COLOR);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomePanel.add(subtitleLabel);

        headerPanel.add(welcomePanel, BorderLayout.CENTER);

        // User info card
        JPanel userInfoCard = new JPanel(new FlowLayout(FlowLayout.CENTER));
        userInfoCard.setBackground(CARD_COLOR);
        userInfoCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(222, 226, 230), 1),
            new EmptyBorder(10, 15, 10, 15)
        ));

        JLabel userIdLabel = new JLabel("User ID: " + user.getId());
        userIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userIdLabel.setForeground(SECONDARY_COLOR);
        userInfoCard.add(userIdLabel);

        headerPanel.add(userInfoCard, BorderLayout.SOUTH);

        return headerPanel;
    }

    /**
     * Creates the modern content panel with feature cards.
     */
    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(new EmptyBorder(0, 0, 30, 0));

        // Chat card
        JPanel chatCard = createFeatureCard(
            "💬", "Secure Chat",

            "Start encrypted conversations with other users",
            PRIMARY_COLOR,
            e -> openChatView()
        );
        contentPanel.add(chatCard);

        // Course card
        JPanel courseCard = createFeatureCard(
            "📚", "Course Materials",

            "Access cybersecurity learning resources",
            SUCCESS_COLOR,
            e -> openCourseView()
        );
        contentPanel.add(courseCard);

        return contentPanel;
    }

    /**
     * Creates a modern feature card.
     */
    private JPanel createFeatureCard(String icon, String title, String description, Color accentColor, java.awt.event.ActionListener action) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(CARD_COLOR);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(222, 226, 230), 1),
            new EmptyBorder(25, 20, 25, 20)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Icon
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Arial", Font.BOLD, 36));
        iconLabel.setForeground(accentColor);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(iconLabel);

        card.add(Box.createVerticalStrut(18));

        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(titleLabel);

        card.add(Box.createVerticalStrut(12));

        // Description
        JLabel descLabel = new JLabel("<html><center>" + description + "</center></html>");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descLabel.setForeground(SECONDARY_COLOR);
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(descLabel);

        card.add(Box.createVerticalStrut(20));

        // Button
        JButton button = createModernButton("Open", accentColor, Color.WHITE);
        button.addActionListener(action);
        card.add(button);

        // Add hover effect
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(new Color(248, 249, 250));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(CARD_COLOR);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                action.actionPerformed(null);
            }
        });

        return card;
    }

    /**
     * Creates the footer panel.
     */
    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(BACKGROUND_COLOR);

        JButton logoutButton = createModernButton("Logout", new Color(220, 53, 69), Color.BLACK);
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginView(participantModel, messageModel);
        });
        footerPanel.add(logoutButton);

        return footerPanel;
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
            new EmptyBorder(12, 25, 12, 25)
        ));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
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
     * Opens the chat view window.
     */
    private void openChatView() {
        new ChatView(messageModel, user, participantModel, this.getX() + 50, this.getY() + 50);
    }

    /**
     * Opens the course view window.
     */
    private void openCourseView() {
        // Use a relative path for the course file
        String coursePath = "resources/course-content.txt";
        CourseModel courseModel = new CourseModel(1, coursePath);
        new CourseView(courseModel, this.getX() + 100, this.getY() + 100);
    }
}
