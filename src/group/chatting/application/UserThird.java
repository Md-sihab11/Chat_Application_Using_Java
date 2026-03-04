package group.chatting.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import javax.swing.border.EmptyBorder;
import java.net.Socket;

// Background Theme Panel (UserOne er moto)
class CustomThemePanelThird extends JPanel {
    private Image bg;
    public CustomThemePanelThird(String path) {
        try {
            bg = new ImageIcon(ClassLoader.getSystemResource(path)).getImage();
        } catch (Exception e) {}
        setLayout(new BorderLayout());
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bg != null) g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}

public class UserThird implements ActionListener, Runnable {

    JTextField text;
    CustomThemePanelThird mainChatPanel;
    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame("Chat ONN - Shakib");

    BufferedReader reader;
    BufferedWriter writer;
    String name2 = "DIU";
    String name = "Shakib"; // Name updated to Shakib
    JScrollPane sp;

    UserThird() {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1200, 800);
        f.setLocationRelativeTo(null);
        f.setLayout(new BorderLayout());

        // --- 1. LEFT SIDEBAR (UserOne Style) ---
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(300, 0));
        sidebar.setBackground(new Color(15, 15, 15));
        sidebar.setLayout(null);
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.DARK_GRAY));
        f.add(sidebar, BorderLayout.WEST);

        // Logo
        JLabel logo = new JLabel("Chat ONN");
        logo.setFont(new Font("SansSerif", Font.BOLD, 24));
        logo.setForeground(new Color(110, 69, 255));
        logo.setBounds(30, 20, 200, 30);
        sidebar.add(logo);

        // MY PROFILE (Shakib)
        addProfileToSidebar(sidebar, "icons/mirzapur.png", name2, 70);

        // ONLINE FRIENDS SECTION
        JLabel onlineTitle = new JLabel("Online Friends");
        onlineTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        onlineTitle.setForeground(Color.GRAY);
        onlineTitle.setBounds(30, 220, 200, 20);
        sidebar.add(onlineTitle);

        // Other Online Friends for Shakib
        addFriendToSidebar(sidebar, "icons/mirzapur.png", "Akash", 255);
        addFriendToSidebar(sidebar, "icons/mirzapur.png", "Shohag", 315);
        addFriendToSidebar(sidebar, "icons/mirzapur.png", "Alif", 375);

        // Active Group selection
        JLabel activeChat = new JLabel("  DIU_COMBINE_STUDY Chat");
        activeChat.setBounds(15, 460, 270, 50);
        activeChat.setFont(new Font("SansSerif", Font.BOLD, 15));
        activeChat.setForeground(Color.WHITE);
        activeChat.setBackground(new Color(50, 50, 50));
        activeChat.setOpaque(true);
        sidebar.add(activeChat);

        // --- 2. MAIN CHAT AREA ---
        mainChatPanel = new CustomThemePanelThird("icons/Theme1.jpg");
        f.add(mainChatPanel, BorderLayout.CENTER);

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(0, 0, 0, 180));
        header.setPreferredSize(new Dimension(0, 70));
        mainChatPanel.add(header, BorderLayout.NORTH);

        JLabel gName = new JLabel("    COMBINE_STUDY");
        gName.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        gName.setForeground(Color.WHITE);
        header.add(gName, BorderLayout.WEST);

        // Header Icons + 3 Dot Menu (UserOne structure)
        JPanel headIcons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 25, 20));
        headIcons.setOpaque(false);
        headIcons.add(getIconLabel("icons/phone.png", 30, 30));
        headIcons.add(getIconLabel("icons/video.png", 35, 30));

        JLabel moreIcon = getIconLabel("icons/3icon.png", 15, 30);
        moreIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        headIcons.add(moreIcon);

        // POPUP MENU (UserOne structure)
        JPopupMenu moreMenu = new JPopupMenu();
        JMenuItem addPeople = new JMenuItem("Add People");
        addPeople.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        moreMenu.add(addPeople);

        moreIcon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                moreMenu.show(moreIcon, e.getX() - 100, e.getY() + 30);
            }
        });

        addPeople.addActionListener(e -> {
            JOptionPane.showMessageDialog(f, "Enter User ID to add in DIU Group", "Add People", JOptionPane.QUESTION_MESSAGE);
        });

        header.add(headIcons, BorderLayout.EAST);

        // Message Area
        vertical.setOpaque(false);
        sp = new JScrollPane(vertical);
        sp.setBorder(null);
        sp.setOpaque(false);
        sp.getViewport().setOpaque(false);
        mainChatPanel.add(sp, BorderLayout.CENTER);

        // --- 3. INPUT AREA (Exactly like UserOne) ---
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setPreferredSize(new Dimension(0, 100));
        mainChatPanel.add(bottomPanel, BorderLayout.SOUTH);

        JPanel inputWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        inputWrapper.setOpaque(false);

        JPanel iconGroup = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        iconGroup.setOpaque(false);
        iconGroup.add(getIconLabel("icons/Plus.png", 25, 25));
        iconGroup.add(getIconLabel("icons/Camera.png", 25, 25));
        iconGroup.add(getIconLabel("icons/Gallery.png", 25, 25));
        inputWrapper.add(iconGroup);

        text = new JTextField(38);
        text.setPreferredSize(new Dimension(0, 45));
        text.setBackground(new Color(30, 30, 30));
        text.setForeground(Color.WHITE);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        text.setCaretColor(Color.WHITE);
        text.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY),
                new EmptyBorder(0, 15, 0, 15)
        ));
        inputWrapper.add(text);

        JButton send = new JButton("Send");
        send.setPreferredSize(new Dimension(90, 45));
        send.setBackground(new Color(110, 69, 255));
        send.setForeground(Color.WHITE);
        send.setFocusPainted(false);
        send.addActionListener(this);
        inputWrapper.add(send);

        bottomPanel.add(inputWrapper, BorderLayout.CENTER);

        f.setVisible(true);
        connectSocket();
        loadMessagesFromDB();
    }

    // --- Helper Methods (Copied from UserOne Structure) ---
    private void addProfileToSidebar(JPanel sidebar, String path, String uName, int y) {
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource(path));
            Image i2 = i1.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            JLabel pic = new JLabel(new ImageIcon(i2));
            pic.setBounds(30, y, 70, 70);
            sidebar.add(pic);
            JLabel nameLbl = new JLabel(uName);
            nameLbl.setFont(new Font("SansSerif", Font.BOLD, 18));
            nameLbl.setForeground(Color.WHITE);
            nameLbl.setBounds(30, y + 80, 200, 25);
            sidebar.add(nameLbl);
            JLabel status = new JLabel("● Online");
            status.setForeground(Color.GREEN);
            status.setBounds(30, y + 105, 100, 20);
            sidebar.add(status);
        } catch (Exception e) {}
    }

    private void addFriendToSidebar(JPanel sidebar, String path, String fName, int y) {
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource(path));
            Image i2 = i1.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            JLabel pic = new JLabel(new ImageIcon(i2));
            pic.setBounds(30, y, 45, 45);
            sidebar.add(pic);
            JLabel nameLbl = new JLabel(fName);
            nameLbl.setFont(new Font("SansSerif", Font.BOLD, 14));
            nameLbl.setForeground(Color.WHITE);
            nameLbl.setBounds(85, y + 2, 150, 20);
            sidebar.add(nameLbl);
            JLabel status = new JLabel("Online");
            status.setForeground(new Color(0, 200, 0));
            status.setFont(new Font("SansSerif", Font.PLAIN, 12));
            status.setBounds(85, y + 22, 100, 15);
            sidebar.add(status);
        } catch (Exception e) {}
    }

    private JLabel getIconLabel(String path, int w, int h) {
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource(path));
            Image i2 = i1.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(i2));
        } catch (Exception e) { return new JLabel(); }
    }

    private void connectSocket() {
        try {
            Socket socket = new Socket("localhost", 2003);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {}
    }

    private void loadMessagesFromDB() {
        try (Connection conn = DataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT sender, message, timestamp FROM messages ORDER BY id ASC");
             java.sql.ResultSet rs = stmt.executeQuery()) {
            while(rs.next()) {
                displayMessage(rs.getString("sender") + ": " + rs.getString("message"), rs.getString("timestamp"), rs.getString("sender").equals(name));
            }
        } catch (Exception e) {}
    }

    private void displayMessage(String out, String time, boolean isSelf) {
        JPanel panel = formatLabel(out, time, isSelf);
        JPanel p = new JPanel(new FlowLayout(isSelf ? FlowLayout.RIGHT : FlowLayout.LEFT));
        p.setOpaque(false);
        p.add(panel);
        vertical.add(p);
        vertical.add(Box.createVerticalStrut(15));
        SwingUtilities.invokeLater(() -> sp.getVerticalScrollBar().setValue(sp.getVerticalScrollBar().getMaximum()));
        f.repaint(); f.validate();
    }

    public static JPanel formatLabel(String out, String timeString, boolean isSelf) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel output = new JLabel("<html><p style=\"width: 200px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(isSelf ? new Color(110, 69, 255) : new Color(60, 60, 60));
        output.setForeground(Color.WHITE);
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(10, 15, 10, 15));
        panel.add(output);
        JLabel time = new JLabel(timeString);
        time.setForeground(Color.LIGHT_GRAY);
        time.setFont(new Font("Tahoma", Font.PLAIN, 10));
        panel.add(time);
        return panel;
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String msg = text.getText().trim();
            if(msg.isEmpty()) return;
            String time = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
            displayMessage(name + ": " + msg, time, true);
            writer.write(name + ": " + msg + "\r\n");
            writer.flush();
            text.setText("");
        } catch (Exception e) {}
    }

    public void run() {
        try {
            String msg;
            while((msg = reader.readLine()) != null) {
                if (msg.contains(name)) continue;
                displayMessage(msg, new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime()), false);
            }
        } catch (Exception e) {}
    }

    public static void main(String[] args) {
        new Thread(new UserThird()).start();
    }
}