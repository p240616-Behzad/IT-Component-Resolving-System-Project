import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * IT COMPLAINT MANAGEMENT SYSTEM
 * FAST NUCES - Software Design Analysis Project
 * 
 * GROUP MEMBERS:
 * HAZIQA EIMAN (24P-0594) - Class Diagram & Compilation
 * BEHZAD TARIQ (24P-0616) - UML Diagram & UML Specification
 * HALEEMA SADIA (24P-0609) - Elicitation, Analysis, FR, NFR
 */

public class ComplaintSystem {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 13));
        UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 13));
        UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 13));
        UIManager.put("TextArea.font", new Font("Segoe UI", Font.PLAIN, 13));
        UIManager.put("Table.font", new Font("Segoe UI", Font.PLAIN, 13));
        UIManager.put("TableHeader.font", new Font("Segoe UI", Font.BOLD, 12));
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}

class AppColors {
    public static final Color PRIMARY = new Color(25, 118, 210);
    public static final Color PRIMARY_DARK = new Color(13, 71, 161);
    public static final Color PRIMARY_LIGHT = new Color(227, 242, 253);
    public static final Color SIDEBAR_BG = new Color(18, 38, 64);
    public static final Color SIDEBAR_HOVER = new Color(30, 50, 80);
    public static final Color SUCCESS = new Color(56, 142, 60);
    public static final Color WARNING = new Color(255, 152, 0);
    public static final Color DANGER = new Color(198, 40, 40);
    public static final Color BG = new Color(240, 244, 248);
    public static final Color WHITE = Color.WHITE;
    public static final Color TEXT_DARK = new Color(33, 33, 33);
    public static final Color TEXT_LIGHT = new Color(117, 117, 117);
    public static final Color BORDER = new Color(224, 224, 224);
}

// ==================== DATA STORE ====================
class DataStore {
    private static DataStore instance;
    private List<User> users;
    private List<Complaint> complaints;
    private List<Notification> notifications;
    private List<Feedback> feedbacks;
    private int complaintCounter = 1000;
    
    private DataStore() {
        users = new ArrayList<>();
        complaints = new ArrayList<>();
        notifications = new ArrayList<>();
        feedbacks = new ArrayList<>();
        seedData();
    }
    
    public static DataStore getInstance() {
        if (instance == null) instance = new DataStore();
        return instance;
    }
    
    private void seedData() {
        // Admin
        users.add(new User("ADM001", "Dr. Ayesha Khan", "admin@nu.edu.pk", "admin123", "admin"));
        // IT Staff
        users.add(new User("STF001", "Bilal Hussain", "staff@nu.edu.pk", "staff123", "staff"));
        
        // 10 TECHNICIANS with different emails and passwords
        String[][] techData = {
            {"TEC001", "Kamran Ali", "tech@nu.edu.pk", "tech123", "Hardware & Networking"},
            {"TEC002", "Sara Ahmed", "sara.tech@nu.edu.pk", "tech123", "Software Support"},
            {"TEC003", "Usman Khan", "usman.tech@nu.edu.pk", "tech123", "Network Admin"},
            {"TEC004", "Fatima Raza", "fatima.tech@nu.edu.pk", "tech123", "Hardware Repair"},
            {"TEC005", "Ali Hassan", "ali.tech@nu.edu.pk", "tech123", "System Admin"},
            {"TEC006", "Noor Fatima", "noor.tech@nu.edu.pk", "tech123", "Printer Specialist"},
            {"TEC007", "Zain Malik", "zain.tech@nu.edu.pk", "tech123", "Network Security"},
            {"TEC008", "Ayesha Bibi", "ayesha.tech@nu.edu.pk", "tech123", "Software Dev"},
            {"TEC009", "Hammad Riaz", "hammad.tech@nu.edu.pk", "tech123", "Database Admin"},
            {"TEC010", "Maha Noor", "maha.tech@nu.edu.pk", "tech123", "IT Support"}
        };
        for (String[] t : techData) {
            users.add(new User(t[0], t[1], t[2], t[3], "technician", t[4]));
        }
        
        // Regular users
               // Regular users (12 users)
        users.add(new User("USR001", "Zara Ahmed", "user@nu.edu.pk", "user123", "user"));
        users.add(new User("USR002", "Hassan Tariq", "hassan@nu.edu.pk", "pass123", "user"));
        users.add(new User("USR003", "Amina Sheikh", "amina@nu.edu.pk", "user123", "user"));
        users.add(new User("USR004", "Bilal Mahmood", "bilal@nu.edu.pk", "user123", "user"));
        users.add(new User("USR005", "Fatima Zahra", "fatima@nu.edu.pk", "user123", "user"));
        users.add(new User("USR006", "Omar Farooq", "omar@nu.edu.pk", "user123", "user"));
        users.add(new User("USR007", "Sana Tariq", "sana@nu.edu.pk", "user123", "user"));
        users.add(new User("USR008", "Danish Iqbal", "danish@nu.edu.pk", "user123", "user"));
        users.add(new User("USR009", "Nadia Hussain", "nadia@nu.edu.pk", "user123", "user"));
        users.add(new User("USR010", "Raza Ahmed", "raza@nu.edu.pk", "user123", "user"));
        
        // Sample complaints - assign to DIFFERENT technicians
        Complaint c1 = new Complaint("CMP1001", "USR001", "Zara Ahmed", "Laptop", 
            "Screen flickering badly, cannot work properly", "High", "TEC001", "Kamran Ali", "In Progress");
        Complaint c2 = new Complaint("CMP1002", "USR001", "Zara Ahmed", "Printer", 
            "Printer not connecting to network", "Critical", "TEC002", "Sara Ahmed", "Assigned");
        Complaint c3 = new Complaint("CMP1003", "USR001", "Zara Ahmed", "Projector", 
            "HDMI cable not working", "Low", "TEC003", "Usman Khan", "Resolved");
        Complaint c4 = new Complaint("CMP1004", "USR002", "Hassan Tariq", "Desktop PC", 
            "PC shuts down randomly after 10 minutes", "Medium", null, null, "Submitted");
        Complaint c5 = new Complaint("CMP1005", "USR002", "Hassan Tariq", "Monitor", 
            "Screen has dead pixels", "Low", "TEC004", "Fatima Raza", "Under Review");
        
               complaints.add(c1);
        complaints.add(c2);
        complaints.add(c3);
        complaints.add(c4);
        complaints.add(c5);
        
        // 5 MORE DEMO COMPLAINTS (all unassigned - for demo)
        Complaint c6 = new Complaint("CMP1006", "USR003", "Amina Sheikh", "Keyboard", 
            "Spacebar not working properly", "Medium", null, null, "Submitted");
        Complaint c7 = new Complaint("CMP1007", "USR004", "Bilal Mahmood", "Mouse", 
            "Mouse double-clicking on single click", "Low", null, null, "Submitted");
        Complaint c8 = new Complaint("CMP1008", "USR005", "Fatima Zahra", "Headphones", 
            "No sound from left ear", "High", null, null, "Submitted");
        Complaint c9 = new Complaint("CMP1009", "USR006", "Omar Farooq", "Webcam", 
            "Camera not detected by system", "Medium", null, null, "Submitted");
        Complaint c10 = new Complaint("CMP1010", "USR007", "Sana Tariq", "Scanner", 
            "Scanner producing blank pages", "Critical", null, null, "Submitted");
        
        complaints.add(c6);
        complaints.add(c7);
        complaints.add(c8);
        complaints.add(c9);
        complaints.add(c10);
        
        notifications.add(new Notification("USR001", "Your complaint CMP1001 is In Progress by Kamran Ali", "Status Update"));
        notifications.add(new Notification("TEC001", "New complaint CMP1001 assigned to you", "Assignment"));
        notifications.add(new Notification("TEC002", "New complaint CMP1002 assigned to you", "Assignment"));
        notifications.add(new Notification("TEC003", "New complaint CMP1003 assigned to you", "Assignment"));
        notifications.add(new Notification("TEC004", "New complaint CMP1005 assigned to you", "Assignment"));
        notifications.add(new Notification("USR001", "Your complaint CMP1003 has been Resolved. Please provide feedback!", "Resolution"));
        notifications.add(new Notification("USR002", "Your complaint CMP1004 has been submitted", "Status Update"));
        notifications.add(new Notification("USR002", "Your complaint CMP1005 is Under Review", "Status Update"));
        
        feedbacks.add(new Feedback("CMP1003", "USR001", 5, "Excellent service! Fixed quickly."));
    }
    
    public User authenticate(String email, String password) {
        return users.stream()
            .filter(u -> u.email.equals(email) && u.password.equals(password))
            .findFirst().orElse(null);
    }
    
    public List<Complaint> getComplaintsByUser(String userId) {
        List<Complaint> result = new ArrayList<>();
        for (Complaint c : complaints) {
            if (c.userId.equals(userId)) result.add(c);
        }
        return result;
    }
    
    public List<Complaint> getComplaintsByTechnician(String techId) {
        List<Complaint> result = new ArrayList<>();
        for (Complaint c : complaints) {
            if (techId.equals(c.technicianId) && 
                !c.status.equals("Closed")) result.add(c);
        }
        return result;
    }
    
    public List<Complaint> getUnassignedComplaints() {
        List<Complaint> result = new ArrayList<>();
        for (Complaint c : complaints) {
            if (c.technicianId == null && 
                !c.status.equals("Resolved") && 
                !c.status.equals("Closed")) result.add(c);
        }
        return result;
    }
    
    public List<Complaint> getAllComplaints() { return complaints; }
    
    public String addComplaint(String userId, String userName, String device, String desc, String priority) {
        String id = "CMP" + (++complaintCounter);
        Complaint c = new Complaint(id, userId, userName, device, desc, priority, null, null, "Submitted");
        complaints.add(c);
        notifications.add(new Notification(userId, 
            "✅ Your complaint " + id + " has been submitted successfully.", "Status Update"));
        return id;
    }
    
       public void assignTechnician(String complaintId, String techId, String techName) {
        System.out.println("DEBUG: Looking for " + complaintId + " in " + complaints.size() + " complaints");
        for (int i = 0; i < complaints.size(); i++) {
            Complaint c = complaints.get(i);
            System.out.println("DEBUG: Checking [" + i + "] " + c.id + " == " + complaintId + " ? " + c.id.equals(complaintId));
            if (c.id.equals(complaintId)) {
                System.out.println("DEBUG: FOUND at index " + i + "! Assigning " + techName + "...");
                c.technicianId = techId;
                c.technicianName = techName;
                c.status = "Assigned";
                notifications.add(new Notification(c.userId,
                    "🔧 Technician " + techName + " assigned to your complaint " + complaintId, "Assignment"));
                notifications.add(new Notification(techId,
                    "📋 New complaint " + complaintId + " assigned to you", "Assignment"));
                System.out.println("DEBUG: Assignment done! New status=" + c.status + " techId=" + c.technicianId);
                return;
            }
        }
        System.out.println("DEBUG: NOT FOUND! Complaint " + complaintId + " not in list!");
    }
    
    public void updateStatus(String complaintId, String newStatus, String techName) {
        for (Complaint c : complaints) {
            if (c.id.trim().equals(complaintId.trim())) {
                c.status = newStatus;
                String msg = "📢 Complaint " + complaintId + " status: " + newStatus;
                if (techName != null) msg += " (by " + techName + ")";
                notifications.add(new Notification(c.userId, msg, "Status Update"));
                if (newStatus.equals("Resolved")) {
                    notifications.add(new Notification(c.userId,
                        "✅ Your complaint " + complaintId + " resolved! Please submit feedback.", "Resolution"));
                }
                break;
            }
        }
    }
    
    public void addFeedback(String complaintId, String userId, int rating, String comment) {
        feedbacks.add(new Feedback(complaintId, userId, rating, comment));
        for (Complaint c : complaints) {
            if (c.id.equals(complaintId)) {
                c.status = "Closed";
                break;
            }
        }
    }
    
    public boolean hasFeedback(String complaintId) {
        return feedbacks.stream().anyMatch(f -> f.complaintId.equals(complaintId));
    }
    
    public List<Notification> getNotificationsForUser(String userId) {
        List<Notification> result = new ArrayList<>();
        for (int i = notifications.size() - 1; i >= 0; i--) {
            Notification n = notifications.get(i);
            if (n.userId.equals(userId)) result.add(n);
        }
        return result;
    }
    
    public List<Feedback> getAllFeedbacks() { return feedbacks; }
    
    public User findUserById(String id) {
        return users.stream().filter(u -> u.id.equals(id)).findFirst().orElse(null);
    }
    
    public List<User> getAllTechnicians() {
        List<User> techs = new ArrayList<>();
        for (User u : users) {
            if (u.role.equals("technician")) techs.add(u);
        }
        return techs;
    }
    
    public List<User> getAvailableTechnicians() {
        List<User> available = new ArrayList<>();
        for (User u : users) {
            if (u.role.equals("technician")) {
                long activeCount = complaints.stream()
                    .filter(c -> u.id.equals(c.technicianId) && 
                        !c.status.equals("Resolved") && !c.status.equals("Closed"))
                    .count();
                if (activeCount < 3) available.add(u);
            }
        }
        if (available.isEmpty()) return getAllTechnicians();
        return available;
    }
    
    public List<User> getAllUsers() { return users; }
        public void addUser(User user) { users.add(user); }
    public long countActiveForTech(String techId) {
        return complaints.stream()
            .filter(c -> techId.equals(c.technicianId) && 
                !c.status.equals("Resolved") && !c.status.equals("Closed"))
            .count();
    }
}

// ==================== MODEL CLASSES ====================
class User {
    String id, name, email, password, role, specialization;
    public User(String id, String name, String email, String password, String role) {
        this(id, name, email, password, role, "General IT");
    }
    public User(String id, String name, String email, String password, String role, String specialization) {
        this.id = id; this.name = name; this.email = email;
        this.password = password; this.role = role;
        this.specialization = specialization;
    }
    public boolean isTechnician() { return "technician".equals(role); }
    public String getAvailability() {
        if (!isTechnician()) return "N/A";
        long active = DataStore.getInstance().countActiveForTech(id);
        return active < 3 ? "🟢 Available" : "🔴 Busy (" + active + " jobs)";
    }
}

class Complaint {
    String id, userId, userName, deviceType, description, priority;
    String technicianId, technicianName, status;
    String submittedDate;
    
    public Complaint(String id, String userId, String userName, String deviceType,
                     String description, String priority, String technicianId, 
                     String technicianName, String status) {
        this.id = id; this.userId = userId; this.userName = userName;
        this.deviceType = deviceType; this.description = description;
        this.priority = priority; this.technicianId = technicianId;
        this.technicianName = technicianName; this.status = status;
        this.submittedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm"));
    }
}

class Notification {
    String userId, message, type, time;
    boolean read;
    public Notification(String userId, String message, String type) {
        this.userId = userId; this.message = message; this.type = type;
        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM, HH:mm"));
        this.read = false;
    }
}

class Feedback {
    String complaintId, userId, comment, date;
    int rating;
    public Feedback(String complaintId, String userId, int rating, String comment) {
        this.complaintId = complaintId; this.userId = userId;
        this.rating = rating; this.comment = comment;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
}

// ==================== LOGIN FRAME ====================
class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    
    public LoginFrame() {
        setTitle("IT Complaint Management System - FAST NUCES");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, AppColors.PRIMARY_DARK, 
                    0, getHeight(), AppColors.PRIMARY);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(new Color(255, 255, 255, 20));
                g2.fillOval(-80, -80, 300, 300);
                g2.fillOval(getWidth()-180, getHeight()-180, 350, 350);
            }
        };
        leftPanel.setPreferredSize(new Dimension(380, 600));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        
        leftPanel.add(Box.createVerticalStrut(80));
        
        JLabel iconLabel = new JLabel("🖥️");
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 70));
        iconLabel.setForeground(Color.WHITE);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(iconLabel);
        leftPanel.add(Box.createVerticalStrut(25));
        
        JLabel title1 = new JLabel("IT Complaint");
        title1.setFont(new Font("Segoe UI", Font.BOLD, 34));
        title1.setForeground(Color.WHITE);
        title1.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(title1);
        
        JLabel title2 = new JLabel("Management System");
        title2.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title2.setForeground(Color.WHITE);
        title2.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(title2);
        leftPanel.add(Box.createVerticalStrut(15));
        
        JLabel uniLabel = new JLabel("FAST NUCES");
        uniLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        uniLabel.setForeground(new Color(180, 210, 255));
        uniLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(uniLabel);
        leftPanel.add(Box.createVerticalStrut(50));
        
        JPanel demoPanel = new JPanel();
        demoPanel.setOpaque(false);
        demoPanel.setLayout(new BoxLayout(demoPanel, BoxLayout.Y_AXIS));
        demoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        String[][] demos = {
            {"admin@nu.edu.pk / admin123", "System Admin"},
            {"staff@nu.edu.pk / staff123", "IT Staff"},
            {"tech@nu.edu.pk / tech123", "Technician (Kamran Ali)"},
            {"sara.tech@nu.edu.pk / tech123", "Technician (Sara Ahmed)"},
            {"usman.tech@nu.edu.pk / tech123", "Technician (Usman Khan)"},
            {"user@nu.edu.pk / user123", "Regular User"},
            {"hassan@nu.edu.pk / pass123", "Regular User 2"}
        };
        
        for (String[] demo : demos) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 2));
            row.setOpaque(false);
            JLabel role = new JLabel(demo[1] + ":");
            role.setFont(new Font("Segoe UI", Font.BOLD, 10));
            role.setForeground(new Color(180, 210, 255));
            JLabel cred = new JLabel(demo[0]);
            cred.setFont(new Font("Segoe UI", Font.PLAIN, 10));
            cred.setForeground(new Color(200, 200, 200));
            row.add(role); row.add(cred);
            demoPanel.add(row);
        }
        leftPanel.add(demoPanel);
        
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(AppColors.WHITE);
        
        JPanel formPanel = new JPanel();
        formPanel.setBackground(AppColors.WHITE);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        
        JLabel welcomeLabel = new JLabel("Welcome Back");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeLabel.setForeground(AppColors.TEXT_DARK);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(welcomeLabel);
        formPanel.add(Box.createVerticalStrut(5));
        
        JLabel subLabel = new JLabel("Sign in to your account");
        subLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subLabel.setForeground(AppColors.TEXT_LIGHT);
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(subLabel);
        formPanel.add(Box.createVerticalStrut(35));
        
        JLabel emailLabel = new JLabel("Email Address");
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        emailLabel.setForeground(AppColors.TEXT_DARK);
        emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(emailLabel);
        formPanel.add(Box.createVerticalStrut(5));
        
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(350, 42));
        emailField.setPreferredSize(new Dimension(350, 42));
        emailField.setBorder(new RoundedBorder(8, AppColors.BORDER));
        formPanel.add(emailField);
        formPanel.add(Box.createVerticalStrut(18));
        
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        passLabel.setForeground(AppColors.TEXT_DARK);
        passLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(passLabel);
        formPanel.add(Box.createVerticalStrut(5));
        
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(350, 42));
        passwordField.setPreferredSize(new Dimension(350, 42));
        passwordField.setBorder(new RoundedBorder(8, AppColors.BORDER));
        passwordField.addActionListener(e -> doLogin());
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(30));
        
        JButton loginBtn = new JButton("Sign In");
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        loginBtn.setBackground(AppColors.PRIMARY);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setMaximumSize(new Dimension(350, 46));
        loginBtn.setPreferredSize(new Dimension(350, 46));
        loginBtn.setFocusPainted(false);
        loginBtn.setBorderPainted(false);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginBtn.addActionListener(e -> doLogin());
        loginBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { loginBtn.setBackground(AppColors.PRIMARY_DARK); }
            public void mouseExited(MouseEvent e) { loginBtn.setBackground(AppColors.PRIMARY); }
        });
                formPanel.add(loginBtn);
        
        formPanel.add(Box.createVerticalStrut(15));
        
        // Register button
        JButton registerBtn = new JButton("Create New Account");
        registerBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        registerBtn.setBackground(AppColors.WHITE);
        registerBtn.setForeground(AppColors.PRIMARY);
        registerBtn.setMaximumSize(new Dimension(350, 40));
        registerBtn.setPreferredSize(new Dimension(350, 40));
        registerBtn.setFocusPainted(false);
        registerBtn.setBorder(BorderFactory.createLineBorder(AppColors.PRIMARY, 2));
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        registerBtn.addActionListener(e -> showRegisterDialog());
        registerBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { 
                registerBtn.setBackground(AppColors.PRIMARY_LIGHT); 
            }
            public void mouseExited(MouseEvent e) { 
                registerBtn.setBackground(AppColors.WHITE); 
            }
        });
        formPanel.add(registerBtn);
        
        rightPanel.add(formPanel);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }
    
        private void showRegisterDialog() {
        JDialog dialog = new JDialog(this, "Create New Account", true);
        dialog.setSize(420, 450);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(AppColors.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Title
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        JLabel title = new JLabel("Register New Account");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(AppColors.PRIMARY);
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(title, gbc);
        
        // Full Name
        gbc.gridy = 1; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 32));
        panel.add(nameField, gbc);
        
        // Email
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        JTextField regEmailField = new JTextField();
        regEmailField.setPreferredSize(new Dimension(200, 32));
        panel.add(regEmailField, gbc);
        
        // Phone
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        JTextField phoneField = new JTextField();
        phoneField.setPreferredSize(new Dimension(200, 32));
        panel.add(phoneField, gbc);
        
        // Password
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.add(passLabel, gbc);
        gbc.gridx = 1;
        JPasswordField regPassField = new JPasswordField();
        regPassField.setPreferredSize(new Dimension(200, 32));
        panel.add(regPassField, gbc);
        
        // Role
        gbc.gridx = 0; gbc.gridy = 5;
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.add(roleLabel, gbc);
        gbc.gridx = 1;
        String[] roles = {"User", "IT Staff", "IT Technician"};
        JComboBox<String> roleBox = new JComboBox<>(roles);
        roleBox.setPreferredSize(new Dimension(200, 32));
        panel.add(roleBox, gbc);
        
        // Status
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        JLabel statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panel.add(statusLabel, gbc);
        
        // Buttons
        gbc.gridy = 7;
        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnRow.setBackground(AppColors.WHITE);
        
        JButton createBtn = new JButton("Create Account");
        createBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        createBtn.setBackground(AppColors.SUCCESS);
        createBtn.setForeground(Color.WHITE);
        createBtn.setFocusPainted(false);
        createBtn.setBorderPainted(false);
        createBtn.setPreferredSize(new Dimension(160, 36));
        
        createBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = regEmailField.getText().trim();
            String phone = phoneField.getText().trim();
            String password = new String(regPassField.getPassword());
            String roleStr = (String) roleBox.getSelectedItem();
            
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                statusLabel.setForeground(AppColors.DANGER);
                statusLabel.setText("❌ Please fill all required fields!");
                return;
            }
            
            String role = "user";
            switch (roleStr) {
                case "IT Staff": role = "staff"; break;
                case "IT Technician": role = "technician"; break;
                default: role = "user";
            }
            
            // Generate unique ID
            String id = "REG" + (100 + DataStore.getInstance().getAllUsers().size());
            
            User newUser = new User(id, name, email, password, role);
            DataStore.getInstance().addUser(newUser);
            
            statusLabel.setForeground(AppColors.SUCCESS);
            statusLabel.setText("✅ Account created! You can now login.");
            createBtn.setEnabled(false);
            
            // Auto-fill login fields
            emailField.setText(email);
            passwordField.setText(password);
            
                       javax.swing.Timer t = new javax.swing.Timer(2000, ev -> dialog.dispose());
            t.setRepeats(false);
            t.start();
        });
        btnRow.add(createBtn);
        
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        cancelBtn.setBackground(AppColors.TEXT_LIGHT);
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);
        cancelBtn.setBorderPainted(false);
        cancelBtn.setPreferredSize(new Dimension(100, 36));
        cancelBtn.addActionListener(e -> dialog.dispose());
        btnRow.add(cancelBtn);
        
        panel.add(btnRow, gbc);
        
        dialog.add(panel, BorderLayout.CENTER);
        dialog.setVisible(true);
    }
    private void doLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        User user = DataStore.getInstance().authenticate(email, password);
        if (user == null) {
            JOptionPane.showMessageDialog(this, "Invalid email or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }
                              final User savedUser = store.findUserById(currentUser.id);
                        SwingUtilities.invokeLater(() -> {
                            dispose();
                            new DashboardFrame(savedUser);
                        });
    }
}

// ==================== ROUNDED BORDER ====================
class RoundedBorder extends AbstractBorder {
    private int radius;
    private Color color;
    public RoundedBorder(int radius, Color color) { this.radius = radius; this.color = color; }
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        g2.dispose();
    }
    @Override
    public Insets getBorderInsets(Component c) { return new Insets(radius/2+2, radius/2+2, radius/2+2, radius/2+2); }
}

// ==================== MAIN DASHBOARD ====================
class DashboardFrame extends JFrame {
    private User currentUser;
    private DataStore store = DataStore.getInstance();
    private JPanel contentPanel;
    private CardLayout cardLayout;
    
    public DashboardFrame(User user) {
        this.currentUser = user;
        setTitle("IT Complaint Management System - " + getRoleName());
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(900, 600));
        setLayout(new BorderLayout());
        
        add(createTopBar(), BorderLayout.NORTH);
        add(createSidebar(), BorderLayout.WEST);
        
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(AppColors.BG);
        
        add(contentPanel, BorderLayout.CENTER);
        cardLayout.show(contentPanel, "HOME");
        setVisible(true);
    }
    
    private void rebuildContentPanel() {
        contentPanel.removeAll();
        
        contentPanel.add(createHomePanel(), "HOME");
        
        if (currentUser.role.equals("user")) {
            contentPanel.add(createNewComplaintPanel(), "NEW_COMPLAINT");
        }
        contentPanel.add(createMyComplaintsPanel(), "MY_COMPLAINTS");
        
        if (currentUser.role.equals("staff") || currentUser.role.equals("admin")) {
            contentPanel.add(createAssignPanel(), "ASSIGN");
            contentPanel.add(createAllComplaintsPanel(), "ALL_COMPLAINTS");
        }
        
        if (currentUser.role.equals("technician")) {
            contentPanel.add(createTechPanel(), "TECH_PANEL");
        }
        
        contentPanel.add(createNotificationsPanel(), "NOTIFICATIONS");
        
        if (currentUser.role.equals("admin")) {
            contentPanel.add(createFeedbackPanel(), "FEEDBACK");
            contentPanel.add(createManageUsersPanel(), "MANAGE_USERS");
            contentPanel.add(createReportsPanel(), "REPORTS");
        }
        
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private String getRoleName() {
        switch (currentUser.role) {
            case "admin": return "System Admin";
            case "staff": return "IT Staff";
            case "technician": return "IT Technician - " + currentUser.name;
            default: return "User Dashboard";
        }
    }
    
    private JPanel createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(AppColors.WHITE);
        topBar.setPreferredSize(new Dimension(0, 55));
        topBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, AppColors.BORDER));
        
        JLabel title = new JLabel("   " + getRoleName() + " — " + currentUser.name);
        title.setFont(new Font("Segoe UI", Font.BOLD, 15));
        title.setForeground(AppColors.TEXT_DARK);
        topBar.add(title, BorderLayout.WEST);
        
        JPanel rightSide = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 12));
        rightSide.setBackground(AppColors.WHITE);
        
        JLabel notifLabel = new JLabel("🔔 Notifications");
        notifLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        notifLabel.setForeground(AppColors.PRIMARY);
        notifLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        notifLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                rebuildContentPanel();
                cardLayout.show(contentPanel, "NOTIFICATIONS");
            }
        });
        rightSide.add(notifLabel);
        
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        logoutBtn.setBackground(AppColors.DANGER);
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutBtn.addActionListener(e -> { dispose(); new LoginFrame(); });
        rightSide.add(logoutBtn);
        
        topBar.add(rightSide, BorderLayout.EAST);
        return topBar;
    }
    
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(AppColors.SIDEBAR_BG);
        sidebar.setPreferredSize(new Dimension(210, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        
        JLabel logo = new JLabel("  IT-CMS");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        logo.setForeground(Color.WHITE);
        logo.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 0));
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(logo);
        
        addSidebarButton(sidebar, "🏠  Dashboard", "HOME");
        if (currentUser.role.equals("user")) {
            addSidebarButton(sidebar, "📝  New Complaint", "NEW_COMPLAINT");
        }
        addSidebarButton(sidebar, "📋  My Complaints", "MY_COMPLAINTS");
        
        if (currentUser.role.equals("staff") || currentUser.role.equals("admin")) {
            addSidebarButton(sidebar, "⚡  Assign Technician", "ASSIGN");
            addSidebarButton(sidebar, "📊  All Complaints", "ALL_COMPLAINTS");
        }
        if (currentUser.role.equals("technician")) {
            addSidebarButton(sidebar, "🔧  Work Panel", "TECH_PANEL");
        }
        addSidebarButton(sidebar, "🔔  Notifications", "NOTIFICATIONS");
        
        if (currentUser.role.equals("admin")) {
            addSidebarButton(sidebar, "⭐  Feedback", "FEEDBACK");
            addSidebarButton(sidebar, "👥  Manage Users", "MANAGE_USERS");
            addSidebarButton(sidebar, "📈  Reports", "REPORTS");
        }
        
        sidebar.add(Box.createVerticalGlue());
        JLabel roleLabel = new JLabel("  " + getRoleName());
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        roleLabel.setForeground(new Color(150, 180, 210));
        roleLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 5, 0));
        roleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(roleLabel);
        return sidebar;
    }
    
    private void addSidebarButton(JPanel sidebar, String text, String target) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setForeground(new Color(200, 220, 240));
        btn.setBackground(AppColors.SIDEBAR_BG);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setMaximumSize(new Dimension(210, 44));
        btn.setPreferredSize(new Dimension(210, 44));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(AppColors.SIDEBAR_HOVER); }
            public void mouseExited(MouseEvent e) { btn.setBackground(AppColors.SIDEBAR_BG); }
        });
             btn.addActionListener(e -> {
            rebuildContentPanel();
            SwingUtilities.invokeLater(() -> cardLayout.show(contentPanel, target));
        });
        sidebar.add(btn);
    }
    
    // ==================== HOME PANEL ====================
    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(AppColors.BG);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel welcome = new JLabel("Welcome, " + currentUser.name + "!");
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcome.setForeground(AppColors.TEXT_DARK);
        panel.add(welcome, BorderLayout.NORTH);
        
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        statsPanel.setBackground(AppColors.BG);
        
        List<Complaint> myComplaints;
        if (currentUser.role.equals("technician")) {
            myComplaints = store.getComplaintsByTechnician(currentUser.id);
        } else if (currentUser.role.equals("admin") || currentUser.role.equals("staff")) {
            myComplaints = store.getAllComplaints();
        } else {
            myComplaints = store.getComplaintsByUser(currentUser.id);
        }
        
        int total = myComplaints.size();
        int open = (int) myComplaints.stream().filter(c -> 
            !c.status.equals("Resolved") && !c.status.equals("Closed")).count();
        int resolved = (int) myComplaints.stream().filter(c -> 
            c.status.equals("Resolved") || c.status.equals("Closed")).count();
        
        statsPanel.add(createStatCard(String.valueOf(total), "Total", AppColors.PRIMARY));
        statsPanel.add(createStatCard(String.valueOf(open), "Open", AppColors.WARNING));
        statsPanel.add(createStatCard(String.valueOf(resolved), "Resolved", AppColors.SUCCESS));
        statsPanel.add(createStatCard(String.valueOf(store.getNotificationsForUser(currentUser.id).size()), "Notifications", AppColors.PRIMARY_DARK));
        
        panel.add(statsPanel, BorderLayout.CENTER);
        
        JPanel quickPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        quickPanel.setBackground(AppColors.WHITE);
        quickPanel.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(10, AppColors.BORDER),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)));
        
        JLabel quickLabel = new JLabel("Quick Actions:  ");
        quickLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        quickPanel.add(quickLabel);
        
        if (currentUser.role.equals("user")) {
            JButton newBtn = createStyledButton("+ New Complaint", AppColors.PRIMARY);
            newBtn.addActionListener(e -> cardLayout.show(contentPanel, "NEW_COMPLAINT"));
            quickPanel.add(newBtn);
        }
        
        JButton trackBtn = createStyledButton("View Complaints", AppColors.SUCCESS);
        trackBtn.addActionListener(e -> {
            rebuildContentPanel();
            cardLayout.show(contentPanel, "MY_COMPLAINTS");
        });
        quickPanel.add(trackBtn);
        
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(AppColors.BG);
        southPanel.add(quickPanel, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);
        return panel;
    }
    
    private JPanel createStatCard(String value, String label, Color color) {
        JPanel card = new JPanel();
        card.setBackground(AppColors.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(10, AppColors.BORDER),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        valueLabel.setForeground(color);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(valueLabel);
        card.add(Box.createVerticalStrut(5));
        JLabel labelLabel = new JLabel(label);
        labelLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        labelLabel.setForeground(AppColors.TEXT_LIGHT);
        labelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(labelLabel);
        return card;
    }
    
    // ==================== NEW COMPLAINT ====================
    private JPanel createNewComplaintPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 20));
        panel.setBackground(AppColors.BG);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel title = new JLabel("Submit New Complaint");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panel.add(title, BorderLayout.NORTH);
        
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(AppColors.WHITE);
        form.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(10, AppColors.BORDER),
            BorderFactory.createEmptyBorder(25, 25, 25, 25)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0; gbc.gridy = 0;
        form.add(createLabel("Device Type:"), gbc);
        gbc.gridx = 1;
        String[] devices = {"Laptop", "Desktop PC", "Printer", "Projector", "Network Switch", "UPS", "Monitor", "Server", "Other"};
        JComboBox<String> deviceBox = new JComboBox<>(devices);
        deviceBox.setPreferredSize(new Dimension(300, 35));
        form.add(deviceBox, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        form.add(createLabel("Priority:"), gbc);
        gbc.gridx = 1;
        String[] priorities = {"Low", "Medium", "High", "Critical"};
        JComboBox<String> priorityBox = new JComboBox<>(priorities);
        priorityBox.setPreferredSize(new Dimension(300, 35));
        priorityBox.setSelectedIndex(1);
        form.add(priorityBox, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        form.add(createLabel("Issue Description:"), gbc);
        gbc.gridx = 1;
        JTextArea descArea = new JTextArea(5, 30);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setBorder(new RoundedBorder(5, AppColors.BORDER));
        JScrollPane scroll = new JScrollPane(descArea);
        scroll.setPreferredSize(new Dimension(400, 120));
        form.add(scroll, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        JLabel statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        form.add(statusLabel, gbc);
        
        gbc.gridy = 4;
        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonRow.setBackground(AppColors.WHITE);
        
        JButton submitBtn = createStyledButton("Submit Complaint", AppColors.PRIMARY);
        submitBtn.addActionListener(e -> {
            String device = (String) deviceBox.getSelectedItem();
            String priority = (String) priorityBox.getSelectedItem();
            String desc = descArea.getText().trim();
            if (desc.isEmpty()) {
                statusLabel.setForeground(AppColors.DANGER);
                statusLabel.setText("❌ Please describe the issue!");
                return;
            }
            String id = store.addComplaint(currentUser.id, currentUser.name, device, desc, priority);
            statusLabel.setForeground(AppColors.SUCCESS);
            statusLabel.setText("✅ Complaint " + id + " submitted successfully!");
            descArea.setText("");
            priorityBox.setSelectedIndex(1);
            rebuildContentPanel();
        });
        buttonRow.add(submitBtn);
        
        JButton clearBtn = createStyledButton("Clear", AppColors.TEXT_LIGHT);
        clearBtn.addActionListener(e -> { descArea.setText(""); statusLabel.setText(" "); });
        buttonRow.add(clearBtn);
        form.add(buttonRow, gbc);
        panel.add(form, BorderLayout.CENTER);
        return panel;
    }
    
    // ==================== MY COMPLAINTS (with Feedback) ====================
    private JPanel createMyComplaintsPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setBackground(AppColors.BG);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel title = new JLabel("My Complaints");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panel.add(title, BorderLayout.NORTH);
        
        String[] cols = {"ID", "Device", "Priority", "Status", "Technician", "Date", "Action"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return c == 6; }
        };
        
        List<Complaint> myList;
        if (currentUser.role.equals("admin") || currentUser.role.equals("staff")) {
            myList = store.getAllComplaints();
        } else {
            myList = store.getComplaintsByUser(currentUser.id);
        }
        
        for (Complaint c : myList) {
            String action = "";
            if ((c.status.equals("Resolved") || c.status.equals("Closed")) && !store.hasFeedback(c.id)) {
                action = "⭐ Give Feedback";
            } else if (store.hasFeedback(c.id)) {
                action = "✓ Done";
            }
            model.addRow(new Object[]{c.id, c.deviceType, c.priority, c.status,
                c.technicianName != null ? c.technicianName : "Not assigned",
                c.submittedDate, action});
        }
        
        JTable table = new JTable(model);
        table.setRowHeight(38);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.getTableHeader().setBackground(AppColors.PRIMARY);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setPreferredSize(new Dimension(0, 40));
        
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();
                if (col == 6 && row >= 0) {
                    String action = (String) model.getValueAt(row, 6);
                    String complaintId = (String) model.getValueAt(row, 0);
                    if (action != null && action.contains("Give Feedback")) {
                        showFeedbackDialog(complaintId, model, row);
                    }
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new RoundedBorder(10, AppColors.BORDER));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    // ==================== FEEDBACK DIALOG ====================
    private void showFeedbackDialog(String complaintId, DefaultTableModel model, int row) {
        JDialog dialog = new JDialog(this, "Submit Feedback - " + complaintId, true);
        dialog.setSize(420, 380);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(AppColors.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 2;
        
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel title = new JLabel("Rate Your Experience");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(title, gbc);
        
        gbc.gridy = 1; gbc.gridwidth = 1;
        panel.add(createLabel("Rating (1-5):"), gbc);
        gbc.gridx = 1;
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(5, 1, 5, 1));
        spinner.setPreferredSize(new Dimension(80, 30));
        panel.add(spinner, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(createLabel("Comments:"), gbc);
        gbc.gridx = 1;
        JTextArea commentArea = new JTextArea(4, 20);
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        commentArea.setBorder(new RoundedBorder(5, AppColors.BORDER));
        panel.add(new JScrollPane(commentArea), gbc);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        JLabel statusLabel = new JLabel(" ");
        panel.add(statusLabel, gbc);
        
        gbc.gridy = 4;
        JButton submitBtn = createStyledButton("Submit Feedback", AppColors.SUCCESS);
        submitBtn.addActionListener(e -> {
            int rating = (int) spinner.getValue();
            String comment = commentArea.getText().trim();
            store.addFeedback(complaintId, currentUser.id, rating, comment);
            model.setValueAt("✓ Done", row, 6);
            statusLabel.setForeground(AppColors.SUCCESS);
            statusLabel.setText("✅ Thank you! Feedback submitted.");
            submitBtn.setEnabled(false);
                       javax.swing.Timer t2 = new javax.swing.Timer(1500, ev -> dialog.dispose());
            t2.setRepeats(false);
            t2.start();
        });
        panel.add(submitBtn, gbc);
        
        dialog.add(panel, BorderLayout.CENTER);
        dialog.setVisible(true);
    }
    
    // ==================== ASSIGN PANEL (Dropdown with all techs) ====================
       private JPanel createAssignPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setBackground(AppColors.BG);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel title = new JLabel("Assign Technician to Complaint");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panel.add(title, BorderLayout.NORTH);
        
        final java.util.List<Complaint> unassigned = store.getUnassignedComplaints();
        
        if (unassigned.isEmpty()) {
            JLabel empty = new JLabel("No unassigned complaints.");
            empty.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            empty.setForeground(AppColors.SUCCESS);
            empty.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(empty, BorderLayout.CENTER);
            return panel;
        }
        
        final java.util.List<User> allTechs = store.getAllTechnicians();
        
        String[] techOptions = new String[allTechs.size()];
        for (int j = 0; j < allTechs.size(); j++) {
            User t = allTechs.get(j);
            long active = store.countActiveForTech(t.id);
            techOptions[j] = t.name + " - " + t.specialization + " (" + (active < 3 ? "Available" : "Busy:" + active + " jobs") + ")";
        }
        
        String[] cols = {"Select", "ID", "User", "Device", "Priority", "Description"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return c == 0; }
            @Override public Class<?> getColumnClass(int c) { return c == 0 ? Boolean.class : String.class; }
        };
        
        for (Complaint c : unassigned) {
            model.addRow(new Object[]{false, c.id, c.userName, c.deviceType, c.priority, c.description});
        }
        
        JTable table = new JTable(model);
        table.setRowHeight(35);
        table.getTableHeader().setBackground(AppColors.PRIMARY);
        table.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scroll = new JScrollPane(table);
        panel.add(scroll, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        bottomPanel.setBackground(AppColors.BG);
        
        final JComboBox<String> techDropdown = new JComboBox<>(techOptions);
        techDropdown.setPreferredSize(new Dimension(300, 35));
        bottomPanel.add(new JLabel("Assign to: "));
        bottomPanel.add(techDropdown);
        
        JButton assignBtn = createStyledButton("Assign Selected", AppColors.PRIMARY);
        assignBtn.addActionListener(e -> {
            int techIdx = techDropdown.getSelectedIndex();
            if (techIdx < 0 || techIdx >= allTechs.size()) return;
            
            User selectedTech = allTechs.get(techIdx);
            boolean assignedAny = false;
            
            for (int i = 0; i < model.getRowCount(); i++) {
                Boolean selected = (Boolean) model.getValueAt(i, 0);
                if (selected != null && selected) {
                    String compId = (String) model.getValueAt(i, 1);
                    store.assignTechnician(compId, selectedTech.id, selectedTech.name);
                    assignedAny = true;
                }
            }
            
            if (assignedAny) {
                JOptionPane.showMessageDialog(DashboardFrame.this, 
                    "Assigned to " + selectedTech.name + "!");
            }
            
            // Refresh the panel
            dispose();
            new DashboardFrame(currentUser);
        });
        bottomPanel.add(assignBtn);
        
        panel.add(bottomPanel, BorderLayout.SOUTH);
        return panel;
    }
    // ==================== ALL COMPLAINTS ====================
    private JPanel createAllComplaintsPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setBackground(AppColors.BG);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel title = new JLabel("All Complaints");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panel.add(title, BorderLayout.NORTH);
        
        String[] cols = {"ID", "User", "Device", "Priority", "Status", "Technician"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        
        for (Complaint c : store.getAllComplaints()) {
            model.addRow(new Object[]{c.id, c.userName, c.deviceType, c.priority, c.status,
                c.technicianName != null ? c.technicianName : "Unassigned"});
        }
        
        JTable table = new JTable(model);
        table.setRowHeight(38);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.getTableHeader().setBackground(AppColors.PRIMARY);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setPreferredSize(new Dimension(0, 40));
        
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    if (row >= 0) {
                        String id = (String) model.getValueAt(row, 0);
                        String[] statuses = {"Submitted", "Under Review", "Diagnosing", "In Progress", "Resolved", "Closed"};
                        String newStatus = (String) JOptionPane.showInputDialog(
                            DashboardFrame.this, "Update status for " + id + ":", 
                            "Update Status", JOptionPane.PLAIN_MESSAGE, null, statuses, statuses[0]);
                        if (newStatus != null) {
                            store.updateStatus(id, newStatus, currentUser.name);
                            rebuildContentPanel();
                            cardLayout.show(contentPanel, "ALL_COMPLAINTS");
                        }
                    }
                }
            }
        });
        
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(new RoundedBorder(10, AppColors.BORDER));
        panel.add(scroll, BorderLayout.CENTER);
        
        JLabel hint = new JLabel("💡 Double-click a row to update status");
        hint.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        hint.setForeground(AppColors.TEXT_LIGHT);
        hint.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panel.add(hint, BorderLayout.SOUTH);
        return panel;
    }
    
    // ==================== TECHNICIAN PANEL ====================
    private JPanel createTechPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setBackground(AppColors.BG);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel title = new JLabel("My Assigned Complaints — " + currentUser.name);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panel.add(title, BorderLayout.NORTH);
        
        List<Complaint> assigned = store.getComplaintsByTechnician(currentUser.id);
        
        if (assigned.isEmpty()) {
            JLabel empty = new JLabel("📭 No complaints assigned to you!");
            empty.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            empty.setForeground(AppColors.TEXT_LIGHT);
            empty.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(empty, BorderLayout.CENTER);
            return panel;
        }
        
        JPanel listPanel = new JPanel();
        listPanel.setBackground(AppColors.BG);
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        
        for (Complaint c : assigned) {
            JPanel card = new JPanel(new BorderLayout(10, 5));
            card.setBackground(AppColors.WHITE);
            card.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(8, AppColors.BORDER),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));
            card.setMaximumSize(new Dimension(800, 100));
            
            String info = "<html><b>" + c.id + "</b> — " + c.deviceType + " | " + c.userName + 
                "<br>Issue: " + c.description.substring(0, Math.min(80, c.description.length())) + 
                "...<br>Status: <font color='#1976D2'><b>" + c.status + "</b></font> | " + c.submittedDate + "</html>";
            JLabel infoLabel = new JLabel(info);
            card.add(infoLabel, BorderLayout.CENTER);
            
            JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            btnPanel.setBackground(AppColors.WHITE);
            
            JButton updateBtn = createStyledButton("Update Status", AppColors.PRIMARY);
            final String cid = c.id;
            updateBtn.addActionListener(e -> {
                String[] statuses = {"Under Review", "Diagnosing", "In Progress", "Resolved"};
                String newStatus = (String) JOptionPane.showInputDialog(
                    DashboardFrame.this, "Update status for " + cid + ":", "Update Status",
                    JOptionPane.PLAIN_MESSAGE, null, statuses, statuses[0]);
                if (newStatus != null) {
                    store.updateStatus(cid, newStatus, currentUser.name);
                    JOptionPane.showMessageDialog(DashboardFrame.this, 
                        "✅ Status updated to: " + newStatus);
                    rebuildContentPanel();
                    cardLayout.show(contentPanel, "TECH_PANEL");
                }
            });
            btnPanel.add(updateBtn);
            card.add(btnPanel, BorderLayout.EAST);
            
            listPanel.add(card);
            listPanel.add(Box.createVerticalStrut(10));
        }
        
        JScrollPane scroll = new JScrollPane(listPanel);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }
    
    // ==================== NOTIFICATIONS ====================
    private JPanel createNotificationsPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setBackground(AppColors.BG);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel title = new JLabel("Notifications");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panel.add(title, BorderLayout.NORTH);
        
        List<Notification> notifs = store.getNotificationsForUser(currentUser.id);
        
        if (notifs.isEmpty()) {
            JLabel empty = new JLabel("No notifications yet!");
            empty.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            empty.setForeground(AppColors.TEXT_LIGHT);
            empty.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(empty, BorderLayout.CENTER);
            return panel;
        }
        
        JPanel listPanel = new JPanel();
        listPanel.setBackground(AppColors.BG);
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        
        for (Notification n : notifs) {
            JPanel card = new JPanel(new BorderLayout(10, 5));
            card.setBackground(n.read ? AppColors.WHITE : AppColors.PRIMARY_LIGHT);
            card.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(8, AppColors.BORDER),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));
            card.setMaximumSize(new Dimension(800, 75));
            
            JLabel msg = new JLabel("<html>" + n.message + "</html>");
            msg.setFont(new Font("Segoe UI", n.read ? Font.PLAIN : Font.BOLD, 13));
            card.add(msg, BorderLayout.CENTER);
            
            JLabel time = new JLabel(n.time + " • " + n.type);
            time.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            time.setForeground(AppColors.TEXT_LIGHT);
            card.add(time, BorderLayout.SOUTH);
            n.read = true;
            
            listPanel.add(card);
            listPanel.add(Box.createVerticalStrut(8));
        }
        
        JScrollPane scroll = new JScrollPane(listPanel);
        scroll.setBorder(null);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }
    
    // ==================== FEEDBACK PANEL (Admin) ====================
    private JPanel createFeedbackPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setBackground(AppColors.BG);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel title = new JLabel("User Feedback");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panel.add(title, BorderLayout.NORTH);
        
        List<Feedback> feedbacks = store.getAllFeedbacks();
        
        if (feedbacks.isEmpty()) {
            JLabel empty = new JLabel("No feedback yet!");
            empty.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            empty.setForeground(AppColors.TEXT_LIGHT);
            empty.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(empty, BorderLayout.CENTER);
            return panel;
        }
        
        JPanel listPanel = new JPanel();
        listPanel.setBackground(AppColors.BG);
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        
        for (Feedback fb : feedbacks) {
            JPanel card = new JPanel(new BorderLayout(10, 5));
            card.setBackground(AppColors.WHITE);
            card.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(8, AppColors.BORDER),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));
            card.setMaximumSize(new Dimension(800, 85));
            
            String stars = "⭐".repeat(fb.rating);
            String userInfo = store.findUserById(fb.userId) != null ? 
                store.findUserById(fb.userId).name : fb.userId;
            String info = "<html><b>" + fb.complaintId + "</b> by " + userInfo + "<br>" + 
                stars + " (" + fb.rating + "/5) — " + 
                (fb.comment.isEmpty() ? "No comment" : fb.comment) + "</html>";
            JLabel infoLabel = new JLabel(info);
            card.add(infoLabel, BorderLayout.CENTER);
            
            JLabel date = new JLabel(fb.date);
            date.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            date.setForeground(AppColors.TEXT_LIGHT);
            card.add(date, BorderLayout.SOUTH);
            
            listPanel.add(card);
            listPanel.add(Box.createVerticalStrut(8));
        }
        
        JScrollPane scroll = new JScrollPane(listPanel);
        scroll.setBorder(null);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }
    
    // ==================== MANAGE USERS (Admin Only) ====================
    private JPanel createManageUsersPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setBackground(AppColors.BG);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel title = new JLabel("Manage Users");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panel.add(title, BorderLayout.NORTH);
        
        String[] cols = {"ID", "Name", "Email", "Role", "Specialization", "Status"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        
        for (User u : store.getAllUsers()) {
            model.addRow(new Object[]{u.id, u.name, u.email, u.role, 
                u.specialization, u.getAvailability()});
        }
        
        JTable table = new JTable(model);
        table.setRowHeight(35);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.getTableHeader().setBackground(AppColors.PRIMARY);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setPreferredSize(new Dimension(0, 40));
        
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(new RoundedBorder(10, AppColors.BORDER));
        panel.add(scroll, BorderLayout.CENTER);
        
        JLabel total = new JLabel("Total Users: " + store.getAllUsers().size() + 
            " | Technicians: " + store.getAllTechnicians().size());
        total.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        total.setForeground(AppColors.TEXT_LIGHT);
        total.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panel.add(total, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // ==================== REPORTS (Admin Only) ====================
    private JPanel createReportsPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setBackground(AppColors.BG);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel title = new JLabel("System Reports");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panel.add(title, BorderLayout.NORTH);
        
        JPanel statsGrid = new JPanel(new GridLayout(2, 3, 15, 15));
        statsGrid.setBackground(AppColors.BG);
        
        List<Complaint> all = store.getAllComplaints();
        long submitted = all.stream().filter(c -> c.status.equals("Submitted")).count();
        long assigned = all.stream().filter(c -> c.status.equals("Assigned")).count();
        long inProgress = all.stream().filter(c -> 
            c.status.equals("In Progress") || c.status.equals("Under Review") || 
            c.status.equals("Diagnosing")).count();
        long resolved = all.stream().filter(c -> 
            c.status.equals("Resolved") || c.status.equals("Closed")).count();
        long unassigned = all.stream().filter(c -> c.technicianId == null && 
            !c.status.equals("Closed")).count();
        
        statsGrid.add(createStatCard(String.valueOf(all.size()), "Total", AppColors.PRIMARY));
        statsGrid.add(createStatCard(String.valueOf(submitted), "Submitted", AppColors.WARNING));
        statsGrid.add(createStatCard(String.valueOf(assigned), "Assigned", new Color(255, 152, 0)));
        statsGrid.add(createStatCard(String.valueOf(inProgress), "In Progress", new Color(156, 39, 176)));
        statsGrid.add(createStatCard(String.valueOf(resolved), "Resolved/Closed", AppColors.SUCCESS));
        statsGrid.add(createStatCard(String.valueOf(unassigned), "Need Assignment", AppColors.DANGER));
        
        panel.add(statsGrid, BorderLayout.CENTER);
        
        JLabel info = new JLabel("Total Technicians: " + store.getAllTechnicians().size() + 
            " | Total Users: " + store.getAllUsers().size());
        info.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        info.setForeground(AppColors.TEXT_LIGHT);
        info.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panel.add(info, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // ==================== HELPERS ====================
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(AppColors.TEXT_DARK);
        return label;
    }
    
    private JButton createStyledButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(180, 38));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(bgColor.darker()); }
            public void mouseExited(MouseEvent e) { btn.setBackground(bgColor); }
        });
        return btn;
    }
    
    private String getPriorityColor(String priority) {
        switch (priority) {
            case "Critical": return "#C62828";
            case "High": return "#E65100";
            case "Medium": return "#F57F17";
            default: return "#2E7D32";
        }
    }
}