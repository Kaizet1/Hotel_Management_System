package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DangNhap extends JFrame {
    private static final long serialVersionUID = 1L;

    private static final Color BACKGROUND_COLOR = new Color(16, 16, 20);
    private static final Color BUTTON_COLOR_SUBMIT = new Color(66, 99, 235);
    private static final Color BUTTON_COLOR_EXIT = new Color(151, 69, 35);
    private static final Color BUTTON_COLOR_SUBMIT_HOVER = new Color(85, 128, 255);
    private static final Color BUTTON_COLOR_EXIT_HOVER = new Color(195, 87, 40);
    private static final int MAX_WIDTH = 600;
    private static final int BUTTON_HEIGHT = 55;

    public DangNhap() {
        buildUI();
        setVisible(true);
    }

    private void buildUI() {
        setTitle("Hệ thống quản lý khách sạn");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setSize(1920, 1080);

        JPanel leftPanel = createLeftPanel();
        leftPanel.setFocusable(true);
        leftPanel.requestFocusInWindow();
        JPanel rightPanel = createRightPanel();

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }

    private JPanel createLeftPanel() {
        BackgroundPanel leftPanel = null;
		try {
			leftPanel = new BackgroundPanel("imgs/LeftBackGround.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
        leftPanel.setPreferredSize(new Dimension(960, getHeight()));
        leftPanel.setBackground(BACKGROUND_COLOR);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        ImageIcon logoIcon = new ImageIcon("imgs/Logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(CENTER_ALIGNMENT);

        RoundedPanel titlePanel = createTitlePanel();
        leftPanel.add(Box.createVerticalStrut(220));
        leftPanel.add(logoLabel);
        leftPanel.add(Box.createVerticalStrut(50));
        leftPanel.add(titlePanel);

        return leftPanel;
    }

    private RoundedPanel createTitlePanel() {
        RoundedPanel titlePanel = new RoundedPanel(65, 2, new Color(0, 0, 0, 230), new Color(219, 228, 255));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);
        titlePanel.setMaximumSize(new Dimension(650, 190));

        JLabel titleLabel = new JLabel("Hệ thống quản lý khách sạn");
        titleLabel.setFont(FontManager.getMerriweather(Font.PLAIN, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        titlePanel.add(Box.createVerticalGlue());
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalGlue());

        return titlePanel;
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(960, getHeight()));
        rightPanel.setBackground(BACKGROUND_COLOR);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JLabel welcomeLabel = createLabel("Welcome Back!", new Font("merriweather", Font.PLAIN, 36), new Color(219, 228, 255));

        // Tên đăng nhập
        JLabel userLabel = createLabel("Tên đăng nhập", new Font("Manrope Regular", Font.PLAIN, 14), Color.white);
        JLabel starLabel = createLabel("*", new Font("Manrope Regular", Font.PLAIN, 14), Color.red);
        JTextField userField = new JTextField("Nhập tên đăng nhập");
        userField.setMaximumSize(new Dimension(MAX_WIDTH, 55));
        userField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        userField.setBackground(new Color(40, 40, 44));
        userField.setForeground(new Color(255, 255, 255, 127));
        userField.setFont(FontManager.getManrope(Font.PLAIN, 16));
        userField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (userField.getText().equals("Nhập tên đăng nhập")) {
                	userField.setText("");
                	userField.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (userField.getText().isEmpty()) {
                	userField.setForeground(new Color(255, 255, 255, 127));
                	userField.setText("Nhập tên đăng nhập");
                }
            }
        });

        // Mật khẩu
        JLabel passLabel = createLabel("Mật khẩu", new Font("Manrope Regular", Font.PLAIN, 14), Color.white);
        JLabel star2Label = createLabel("*", new Font("Manrope Regular", Font.PLAIN, 14), Color.red);
        JPasswordField passField = new JPasswordField("Nhập mật khẩu");
        passField.setMaximumSize(new Dimension(MAX_WIDTH, 55));
        passField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        passField.setBackground(new Color(40, 40, 44));
        passField.setForeground(new Color(255, 255, 255, 127));
        passField.setFont(FontManager.getManrope(Font.PLAIN, 16));
        passField.setEchoChar('\0');
        passField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(passField.getPassword()).equals("Nhập mật khẩu")) {
                	passField.setText("");
                	passField.setForeground(Color.WHITE);
                	passField.setEchoChar('•');
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(passField.getPassword()).isEmpty()) {
                	passField.setForeground(new Color(255, 255, 255, 127));
                	passField.setText("Nhập mật khẩu");
                	passField.setEchoChar('\0');
                }
            }
        });
        JButton submitButton = createButton("Xác nhận", BUTTON_COLOR_SUBMIT, BUTTON_COLOR_SUBMIT_HOVER);
        JButton exitButton = createButton("Thoát", BUTTON_COLOR_EXIT, BUTTON_COLOR_EXIT_HOVER);
        
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new GiaoDienChinh();
            	dispose();
            }
            
        });
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        Box userBox = Box.createHorizontalBox();
        userBox.setMaximumSize(new Dimension(MAX_WIDTH, 20));
        userBox.add(userLabel);
        userBox.add(starLabel);

        Box passBox = Box.createHorizontalBox();
        passBox.setMaximumSize(new Dimension(MAX_WIDTH, 20));
        passBox.add(passLabel);
        passBox.add(star2Label);

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.setMaximumSize(new Dimension(MAX_WIDTH, BUTTON_HEIGHT));
        buttonBox.add(submitButton);
        buttonBox.add(Box.createHorizontalStrut(13));
        buttonBox.add(exitButton);

        rightPanel.add(Box.createVerticalStrut(300));
        rightPanel.add(welcomeLabel);
        rightPanel.add(Box.createVerticalStrut(45));
        rightPanel.add(userBox);
        rightPanel.add(Box.createVerticalStrut(11));
        rightPanel.add(userField);
        rightPanel.add(Box.createVerticalStrut(34));
        rightPanel.add(passBox);
        rightPanel.add(Box.createVerticalStrut(11));
        rightPanel.add(passField);
        rightPanel.add(Box.createVerticalStrut(56));
        rightPanel.add(buttonBox);

        return rightPanel;
    }

    private JLabel createLabel(String text, Font font, Color foreground) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(foreground);
        label.setAlignmentX(CENTER_ALIGNMENT);
        return label;
    }

    private JButton createButton(String text, Color backgroundColor, Color hoverColor) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(295, BUTTON_HEIGHT));
        button.setFocusPainted(false); // Không tô màu khi có focus
        button.setBorderPainted(false); 
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(backgroundColor);
        button.setForeground(Color.white);
        button.setFont(FontManager.getManrope(Font.PLAIN, 16));
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(backgroundColor);
            }
        });
        
        return button;
    }
}
