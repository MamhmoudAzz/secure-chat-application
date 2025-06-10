package com.securechat.view;

import com.securechat.model.*;
import com.securechat.security.MessageProxy;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 * Modern chat view for secure messaging between users.
 * Provides interface for sending and receiving encrypted messages.
 */
public class ChatView extends JFrame implements Observer {
    private User user;
    private MessageListModel messageModel;
    private ParticipantListModel participantModel;
    private MessageProxy messageProxy;

    private DefaultListModel<String> msgListModel = new DefaultListModel<>();
    private JList<String> messageList = new JList<>(msgListModel);
    private JComboBox<String> participantDropdown = new JComboBox<>();
    private JTextField messageInput = new JTextField();
    private JButton sendButton = new JButton("Send");

    // Improved color scheme for better readability
    private static final Color PRIMARY_COLOR = new Color(0, 123, 255);
    private static final Color SECONDARY_COLOR = new Color(73, 80, 87);
    private static final Color SUCCESS_COLOR = new Color(40, 167, 69);
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color CARD_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(33, 37, 41);
    private static final Color SENT_MESSAGE_COLOR = new Color(0, 123, 255);
    private static final Color RECEIVED_MESSAGE_COLOR = new Color(108, 117, 125);

    /**
     * Creates a new modern chat view for the specified user.
     *
     * @param messageModel The message model for handling messages
     * @param user The current user
     * @param participantModel The participant model for user management
     * @param h Horizontal position for the window
     * @param v Vertical position for the window
     */
    public ChatView(MessageListModel messageModel, User user, ParticipantListModel participantModel, int h, int v) {
        this.user = user;
        this.messageModel = messageModel;
        this.participantModel = participantModel;
        this.messageProxy = new MessageProxy(messageModel);

        this.messageModel.addObserver(this);

        setTitle(user.getPseudoName() + " - Secure Chat");
        setSize(850, 700);
        setLocation(h, v);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setMinimumSize(new Dimension(850, 700));

        setupModernUI();
        initComponents();

        setVisible(true);
    }

    /**
     * Sets up modern UI styling for the chat window.
     */
    private void setupModernUI() {
        getContentPane().setBackground(BACKGROUND_COLOR);

        // Set readable fonts
        UIManager.put("List.font", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("ComboBox.font", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 14));
    }

    /**
     * Initializes the modern GUI components.
     */
    private void initComponents() {
        setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        // Main chat area
        JPanel chatPanel = createChatPanel();
        add(chatPanel, BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.SOUTH);

        // Sidebar panel
        JPanel sidebarPanel = createSidebarPanel();
        add(sidebarPanel, BorderLayout.EAST);
    }

    /**
     * Creates the modern header panel.
     */
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(CARD_COLOR);
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(222, 226, 230)),
            new EmptyBorder(15, 20, 15, 20)
        ));

        // User info
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        userPanel.setBackground(CARD_COLOR);

        JLabel avatarLabel = new JLabel("●");
        avatarLabel.setFont(new Font("Arial", Font.BOLD, 24));
        avatarLabel.setForeground(PRIMARY_COLOR);
        avatarLabel.setBorder(new EmptyBorder(0, 0, 0, 15));
        userPanel.add(avatarLabel);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(CARD_COLOR);

        JLabel nameLabel = new JLabel(user.getPseudoName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setForeground(TEXT_COLOR);
        textPanel.add(nameLabel);

        JLabel statusLabel = new JLabel("● End-to-End Encrypted");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        statusLabel.setForeground(SUCCESS_COLOR);
        textPanel.add(statusLabel);

        userPanel.add(textPanel);
        headerPanel.add(userPanel, BorderLayout.WEST);

        // Online indicator
        JLabel onlineLabel = new JLabel("● Online");
        onlineLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        onlineLabel.setForeground(SUCCESS_COLOR);
        headerPanel.add(onlineLabel, BorderLayout.EAST);

        return headerPanel;
    }

    /**
     * Creates the modern chat panel with message list.
     */
    private JPanel createChatPanel() {
        JPanel chatPanel = new JPanel(new BorderLayout());
        chatPanel.setBackground(BACKGROUND_COLOR);
        chatPanel.setBorder(new EmptyBorder(10, 10, 0, 10));

        // Style the message list
        messageList.setFont(new Font("Arial", Font.PLAIN, 15));
        messageList.setBackground(CARD_COLOR);
        messageList.setBorder(new EmptyBorder(15, 20, 15, 20));
        messageList.setSelectionBackground(new Color(230, 240, 255));
        messageList.setCellRenderer(new ModernMessageRenderer());

        JScrollPane scrollPane = new JScrollPane(messageList);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(222, 226, 230), 1));
        scrollPane.setBackground(CARD_COLOR);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        chatPanel.add(scrollPane, BorderLayout.CENTER);
        return chatPanel;
    }

    /**
     * Creates the modern input panel.
     */
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(CARD_COLOR);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(222, 226, 230)),
            new EmptyBorder(15, 20, 15, 20)
        ));

        // Message input field
        messageInput.setFont(new Font("Arial", Font.PLAIN, 16));
        messageInput.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(206, 212, 218), 2),
            new EmptyBorder(12, 18, 12, 18)
        ));
        messageInput.setBackground(Color.WHITE);

        // Add placeholder effect
        messageInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                messageInput.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
                    new EmptyBorder(9, 14, 9, 14)
                ));
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                messageInput.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(206, 212, 218), 1),
                    new EmptyBorder(10, 15, 10, 15)
                ));
            }
        });

        inputPanel.add(messageInput, BorderLayout.CENTER);

        // Send button
        sendButton = createModernButton("► Send", PRIMARY_COLOR, Color.BLACK);
        sendButton.setPreferredSize(new Dimension(130, 50));
        sendButton.addActionListener(e -> sendMessage());
        inputPanel.add(sendButton, BorderLayout.EAST);

        // Add Enter key support
        messageInput.addActionListener(e -> sendMessage());

        return inputPanel;
    }

    /**
     * Creates the modern sidebar panel.
     */
    private JPanel createSidebarPanel() {
        JPanel sidebarPanel = new JPanel(new BorderLayout());
        sidebarPanel.setBackground(CARD_COLOR);
        sidebarPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(222, 226, 230)),
            new EmptyBorder(15, 15, 15, 15)
        ));
        sidebarPanel.setPreferredSize(new Dimension(200, 0));

        // Title
        JLabel titleLabel = new JLabel("► Send to:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setBorder(new EmptyBorder(0, 0, 15, 0));
        sidebarPanel.add(titleLabel, BorderLayout.NORTH);

        // Participant dropdown
        participantDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        participantDropdown.setBackground(Color.WHITE);
        participantDropdown.setBorder(new EmptyBorder(10, 12, 10, 12));
        updateParticipantDropdown();
        sidebarPanel.add(participantDropdown, BorderLayout.CENTER);

        return sidebarPanel;
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
            new EmptyBorder(12, 20, 12, 20)
        ));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
     * Sends a message to the selected recipient.
     */
    private void sendMessage() {
        String receiverName = (String) participantDropdown.getSelectedItem();
        String content = messageInput.getText().trim();

        if (content.isEmpty()) {
            return;
        }

        if (receiverName != null) {
            // Remove the icon prefix if present
            if (receiverName.startsWith("● ")) {
                receiverName = receiverName.substring(2);
            }
            User receiver = participantModel.getUserByPseudo(receiverName);
            if (receiver != null) {
                messageProxy.sendEncryptedMessage(user, receiver, content);
                messageInput.setText("");
                messageInput.requestFocus();
            }
        }
    }

    /**
     * Updates the participant dropdown with available users.
     */
    private void updateParticipantDropdown() {
        participantDropdown.removeAllItems();
        for (User u : participantModel.getParticipants()) {
            if (!u.getPseudoName().equals(user.getPseudoName())) {
                participantDropdown.addItem("● " + u.getPseudoName());
            }
        }
    }

    /**
     * Called when the message model is updated.
     */
    @Override
    public void update(Observable o, Object arg) {
        msgListModel.clear();
        for (Message msg : messageModel.getMessages()) {
            if (msg.getSender().equals(user.getPseudoName())) {
                // Sender sees their original (clear) message
                msgListModel.addElement("► You → " + msg.getReceiver() + ": " + msg.getOriginalContent());
            } else if (msg.getReceiver().equals(user.getPseudoName())) {
                // Receiver decrypts encrypted message
                String displayed = messageProxy.decryptMessage(msg, user);
                msgListModel.addElement("◄ " + msg.getSender() + " → You: " + displayed);
            }
        }

        // Auto-scroll to bottom
        SwingUtilities.invokeLater(() -> {
            if (msgListModel.getSize() > 0) {
                messageList.ensureIndexIsVisible(msgListModel.getSize() - 1);
            }
        });
    }

    /**
     * Custom renderer for modern message display.
     */
    private class ModernMessageRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JPanel messagePanel = new JPanel(new BorderLayout());
            messagePanel.setOpaque(true);
            messagePanel.setBorder(new EmptyBorder(8, 10, 8, 10));

            String message = value.toString();
            boolean isSentMessage = message.startsWith("► You →");

            // Create message bubble
            JPanel bubble = new JPanel(new BorderLayout());
            bubble.setBorder(new EmptyBorder(8, 12, 8, 12));

            if (isSentMessage) {
                bubble.setBackground(new Color(0, 123, 255, 30));
                messagePanel.setBackground(CARD_COLOR);
            } else {
                bubble.setBackground(new Color(108, 117, 125, 20));
                messagePanel.setBackground(CARD_COLOR);
            }

            if (isSelected) {
                messagePanel.setBackground(new Color(230, 240, 255));
            }

            // Message text
            JLabel messageLabel = new JLabel("<html><div style='width: 350px;'>" +
                message.replace("<", "&lt;").replace(">", "&gt;") + "</div></html>");
            messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            messageLabel.setForeground(TEXT_COLOR);

            bubble.add(messageLabel, BorderLayout.CENTER);

            if (isSentMessage) {
                messagePanel.add(bubble, BorderLayout.EAST);
            } else {
                messagePanel.add(bubble, BorderLayout.WEST);
            }

            return messagePanel;
        }
    }
}
