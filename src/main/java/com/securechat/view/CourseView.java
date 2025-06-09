package com.securechat.view;

import com.securechat.model.CourseModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.util.Observer;
import java.util.Observable;

/**
 * Modern course view for displaying educational content.
 * Supports text files and provides basic PDF file path display.
 */
public class CourseView extends JFrame implements Observer {
    private CourseModel courseModel;
    private JTextArea courseContentArea;

    // Modern color scheme
    private static final Color PRIMARY_COLOR = new Color(64, 128, 255);
    private static final Color SECONDARY_COLOR = new Color(108, 117, 125);
    private static final Color SUCCESS_COLOR = new Color(40, 167, 69);
    private static final Color BACKGROUND_COLOR = new Color(248, 249, 250);
    private static final Color CARD_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(33, 37, 41);

    /**
     * Creates a new modern course view.
     *
     * @param courseModel The course model containing course data
     * @param h Horizontal position for the window
     * @param v Vertical position for the window
     */
    public CourseView(CourseModel courseModel, int h, int v) {
        this.courseModel = courseModel;
        this.courseModel.addObserver(this);

        setTitle("📚 Course Materials - ID: " + courseModel.getCourseId());
        setSize(800, 700);
        setLocation(h, v);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setMinimumSize(new Dimension(600, 500));

        setupModernUI();
        initComponents();
        loadCourseFile(courseModel.getCoursePath());

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

        JLabel iconLabel = new JLabel("📚");
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        iconLabel.setBorder(new EmptyBorder(0, 0, 0, 15));
        titlePanel.add(iconLabel);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(CARD_COLOR);

        JLabel titleLabel = new JLabel("Course Materials");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(TEXT_COLOR);
        textPanel.add(titleLabel);

        JLabel idLabel = new JLabel("Course ID: " + courseModel.getCourseId());
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        idLabel.setForeground(SECONDARY_COLOR);
        textPanel.add(idLabel);

        titlePanel.add(textPanel);
        headerPanel.add(titlePanel, BorderLayout.WEST);

        // File info
        JPanel fileInfoPanel = new JPanel();
        fileInfoPanel.setLayout(new BoxLayout(fileInfoPanel, BoxLayout.Y_AXIS));
        fileInfoPanel.setBackground(CARD_COLOR);

        JLabel fileLabel = new JLabel("📄 " + getFileName(courseModel.getCoursePath()));
        fileLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        fileLabel.setForeground(PRIMARY_COLOR);
        fileLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        fileInfoPanel.add(fileLabel);

        JLabel pathLabel = new JLabel(courseModel.getCoursePath());
        pathLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        pathLabel.setForeground(SECONDARY_COLOR);
        pathLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        fileInfoPanel.add(pathLabel);

        headerPanel.add(fileInfoPanel, BorderLayout.EAST);

        return headerPanel;
    }

    /**
     * Creates the modern content panel.
     */
    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(new EmptyBorder(15, 0, 15, 0));

        // Content area
        courseContentArea = new JTextArea();
        courseContentArea.setEditable(false);
        courseContentArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        courseContentArea.setBackground(CARD_COLOR);
        courseContentArea.setForeground(TEXT_COLOR);
        courseContentArea.setMargin(new Insets(20, 20, 20, 20));
        courseContentArea.setLineWrap(true);
        courseContentArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(courseContentArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(222, 226, 230), 1));
        scrollPane.setBackground(CARD_COLOR);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Add a subtle shadow effect
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(222, 226, 230), 1),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));

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
            loadCourseFile(courseModel.getCoursePath());
            showStatusMessage("Content refreshed successfully!");
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
     * Gets the filename from a file path.
     */
    private String getFileName(String path) {
        if (path == null) return "Unknown";
        int lastSlash = Math.max(path.lastIndexOf('/'), path.lastIndexOf('\\'));
        return lastSlash >= 0 ? path.substring(lastSlash + 1) : path;
    }

    /**
     * Shows a status message to the user.
     */
    private void showStatusMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Status", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Loads and displays the course file content.
     * 
     * @param path The file path to load
     */
    private void loadCourseFile(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                courseContentArea.setText("Course file not found: " + path + 
                    "\n\nPlease ensure the course materials are available in the resources directory.");
                return;
            }

            if (path.endsWith(".txt")) {
                StringBuilder content = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                }
                courseContentArea.setText(content.toString());
            } else if (path.endsWith(".pdf")) {
                courseContentArea.setText("[PDF Document]\n\n" +
                    "PDF display is not supported in this version.\n" +
                    "File Path: " + path + "\n\n" +
                    "To view this PDF file, please open it with an external PDF viewer.");
            } else {
                courseContentArea.setText("Unsupported file type: " + path + "\n\n" +
                    "Supported formats:\n" +
                    "- Text files (.txt)\n" +
                    "- PDF files (.pdf) - path display only");
            }
        } catch (IOException e) {
            courseContentArea.setText("Failed to load course file.\n\n" +
                "Error: " + e.getMessage() + "\n\n" +
                "Please check that the file exists and is readable.");
        }
    }

    /**
     * Called when the course model is updated.
     */
    @Override
    public void update(Observable o, Object arg) {
        loadCourseFile(courseModel.getCoursePath());
        setTitle("📚 Course Materials - ID: " + courseModel.getCourseId());
    }
}
