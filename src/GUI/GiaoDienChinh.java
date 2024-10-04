package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;

import ManHinh.ThongTinChung;

public class GiaoDienChinh extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel centerPanel;
	private CardLayout cardLayout;
	private RoundedButton selectedButton;

	public GiaoDienChinh() {
		setTitle("Hệ thống quản lý khách sạn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel sidebarPanel = createSidebar();

		JPanel rightPanel = new JPanel(new BorderLayout());
		JPanel leftHeaderPanel = createLeftHeader();
		JPanel rightHeaderPanel = createRightHeader();
		JPanel headerPanel = createHeader();
		centerPanel = createCenter();

		headerPanel.add(leftHeaderPanel, BorderLayout.WEST);
		headerPanel.add(rightHeaderPanel, BorderLayout.EAST);
		rightPanel.add(headerPanel, BorderLayout.NORTH);
		rightPanel.add(centerPanel, BorderLayout.CENTER);
		add(sidebarPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.CENTER);
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);

	}

	private JPanel createSidebar() {
		JPanel sidebar = new JPanel();
		sidebar.setPreferredSize(new Dimension(260, getHeight()));
		sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
		sidebar.setBackground(new Color(24, 24, 28));

		Border matteBorder = BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(45, 45, 48));
		Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border combinedBorder = BorderFactory.createCompoundBorder(matteBorder, emptyBorder);
		sidebar.setBorder(combinedBorder);

		Box sidebarMenu = Box.createVerticalBox();
		sidebarMenu.setAlignmentX(CENTER_ALIGNMENT);
		String[][] menuItems = { { "Thông tin chung", "ThongTin" }, { "Tra cứu", "TraCuu" },
				{ "Đặt phòng", "DatPhong" }, { "Đặt dịch vụ", "DatDichVu" }, { "Lập Hóa Đơn", "LapHoaDon" },
				{ "Quản lý phòng", "QuanLyPhong" }, { "Quản lý khách hàng", "QuanLyKhachHang" },
				{ "Quản lý dịch vụ", "QuanLyDichVu" }, { "Quản lý hóa đơn", "QuanLyHoaDon" },
				{ "Thống kê", "ThongKe" }, };
		for (String[] item : menuItems) {
			RoundedButton menuButton = new RoundedButton(item[0], new ImageIcon("imgs/" + item[1] + "Icon.png"), 5);
			menuButton.setHorizontalAlignment(SwingConstants.LEFT);
			menuButton.setIconTextGap(19);
			menuButton.setFont(FontManager.getManrope(Font.PLAIN, 16));
			menuButton.setMaximumSize(new Dimension(230, 50));
			menuButton.setBackground(new Color(24, 24, 28));
			menuButton.setForeground(Color.WHITE);
			menuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			menuButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					if (selectedButton != menuButton) {
						menuButton.setBackground(new Color(34, 43, 83));
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if (selectedButton != menuButton) {
						menuButton.setBackground(new Color(24, 24, 28));
					}

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					if (selectedButton != menuButton) {
						selectedButton.setBackground(new Color(24, 24, 28));
						selectedButton = menuButton;
						selectedButton.setBackground(new Color(91, 122, 249));
						cardLayout.show(centerPanel, item[1]);
					}
				}
			});
			if (item[1] == "ThongTin") {
				selectedButton = menuButton;
				selectedButton.setBackground(new Color(91, 122, 249));
			}
			sidebarMenu.add(menuButton);
			sidebarMenu.add(Box.createVerticalStrut(20));
		}
		JLabel sidebarLogo = new JLabel(new ImageIcon("imgs/SidebarLogo.png"));
		sidebarLogo.setAlignmentX(CENTER_ALIGNMENT);
		sidebar.add(Box.createVerticalStrut(15));
		sidebar.add(sidebarLogo);
		sidebar.add(Box.createVerticalStrut(40));
		sidebar.add(sidebarMenu);

		return sidebar;
	}

	private JPanel createLeftHeader() {
		JPanel leftHeader = new JPanel(null);
		leftHeader.setPreferredSize(new Dimension(1360, 80));
		leftHeader.setBackground(new Color(24, 24, 28));
		leftHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(45, 45, 48)));

		JLabel phoneIcon = new JLabel(new ImageIcon("imgs/PhoneIcon.png"));
		JLabel phoneLabel = new JLabel("1900-1000-0000");
		phoneIcon.setBounds(1070, 25, 30, 30);
		phoneLabel.setFont(FontManager.getManrope(Font.BOLD, 24));
		phoneLabel.setForeground(new Color(10, 213, 118));
		phoneLabel.setBounds(1120, 20, 200, 40);
		leftHeader.add(phoneIcon);
		leftHeader.add(phoneLabel);
		return leftHeader;
	}

	private JPanel createRightHeader() {
		JPanel rightHeader = new JPanel();
		rightHeader.setPreferredSize(new Dimension(300, 80));
		rightHeader.setBackground(new Color(24, 24, 28));
		return rightHeader;
	}

	private JPanel createHeader() {
		JPanel header = new JPanel(new BorderLayout());
		header.setPreferredSize(new Dimension(1660, 80));
		header.setBackground(new Color(24, 24, 28));
		return header;
	}

	private JPanel createCenter() {
		cardLayout = new CardLayout();
		JPanel center = new JPanel(cardLayout);

		// Tạo các màn hình khác nhau
		JPanel thongTinPanel = new ThongTinChung();
		JPanel traCuuPanel = new JPanel();
		JPanel datPhongPanel = new JPanel();
		JPanel datDichVuPanel = new JPanel();
		JPanel lapHoaDonPanel = new JPanel();
		JPanel quanLyPhongPanel = new JPanel();
		JPanel quanLyKhachHangPanel = new JPanel();
		JPanel quanLyDichVuPanel = new JPanel();
		JPanel quanLyHoaDonPanel = new JPanel();
		JPanel thongKePanel = new JPanel();

		// Thêm các màn hình vào centerPanel
		center.add(thongTinPanel, "ThongTin");
		center.add(traCuuPanel, "TraCuu");
		center.add(datPhongPanel, "DatPhong");
		center.add(datDichVuPanel, "DatDichVu");
		center.add(lapHoaDonPanel, "LapHoaDon");
		center.add(quanLyPhongPanel, "QuanLyPhong");
		center.add(quanLyKhachHangPanel, "QuanLyKhachHang");
		center.add(quanLyDichVuPanel, "QuanLyDichVu");
		center.add(quanLyHoaDonPanel, "QuanLyHoaDon");
		center.add(thongKePanel, "ThongKe");

		return center;
	}

	public static void main(String[] args) {
		try {
            // Thiết lập Look and Feel "Nimbus"
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Khởi chạy ứng dụng Swing của bạn
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GiaoDienChinh();
            }
        });
	}
}