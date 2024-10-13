package manhinh;

import gui.CustomHeaderRenderer;
import gui.FontManager;
import gui.RoundedPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;



public class LapHoaDon extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
private JTextField txttimkiem;
private DefaultTableModel tableModel;
private JTable table;
private JButton btnlaphd;
private JLabel day;
private DefaultTableModel tableModel1;
private JTable table1;


public LapHoaDon() {
	setBackground(new Color (16, 16, 20));
			Box b = Box.createVerticalBox();
			b.add(Box.createVerticalStrut(10));
			 JTextField searchField = new JTextField("Tìm kiếm");
		        Border emptyBorder = BorderFactory.createEmptyBorder(13, 52, 12, 0);
		        searchField.setBounds(0, 0, 280, 45);
		        searchField.setBorder(emptyBorder);
		        searchField.setBackground(new Color(40, 40, 44));
		        searchField.setForeground(new Color(255, 255, 255, 125));
		        searchField.setFont(FontManager.getManrope(Font.PLAIN, 15));
		        CompoundBorder combinedBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(83, 152, 255)), emptyBorder);
		        searchField.addFocusListener(new FocusAdapter() {
		            @Override
		            public void focusGained(FocusEvent e) {
		                searchField.setBorder(combinedBorder);
		                if (searchField.getText().equals("Tìm kiếm")) {
		                    searchField.setText("");
		                    searchField.setForeground(Color.WHITE);
		                }
		            }

		            @Override
		            public void focusLost(FocusEvent e) {
		                searchField.setBorder(emptyBorder);
		                if (searchField.getText().isEmpty()) {
		                    searchField.setForeground(new Color(255, 255, 255, 125));
		                    searchField.setText("Tìm kiếm");
		                }
		            }
		        });

		        JLabel searchIcon = new JLabel(new ImageIcon("imgs/TimKiemIcon.png"));
		        searchIcon.setBounds(12, 12, 24, 24);
		        
		        JPanel searchPanel = new JPanel();
		        searchPanel.setOpaque(false);
		        searchPanel.setLayout(null);
		        Dimension searchPanelSize = new Dimension(280, 45);
		        searchPanel.setPreferredSize(searchPanelSize);
		        searchPanel.setMinimumSize(searchPanelSize);
		        searchPanel.setMaximumSize(searchPanelSize);
		        
		        searchPanel.add(searchIcon);
		        searchPanel.add(searchField);
		        Box bsearch = Box.createHorizontalBox();
		        bsearch.add(Box.createHorizontalStrut(0));
		        bsearch.add(searchPanel);
		        bsearch.add(Box.createGlue());
		        b.add(bsearch);
			b.add(Box.createVerticalStrut(20));
			
			JLabel titleLabel = new JLabel("Danh sách khách hàng");
			titleLabel.setFont(FontManager.getManrope(Font.BOLD, 16));
			titleLabel.setForeground(Color.white);

			RoundedPanel titlePanel = new RoundedPanel(10, 0, new Color(27, 112, 213));
			titlePanel.setPreferredSize(new Dimension(1642, 50));
			titlePanel.setMinimumSize(new Dimension(1642, 50));
			titlePanel.setMaximumSize(new Dimension(1642, 50));
			titlePanel.setOpaque(false);
			titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));

			titlePanel.add(Box.createHorizontalStrut(15));
			titlePanel.add(titleLabel);
			titlePanel.add(Box.createHorizontalGlue());
			b.add(titlePanel);
			b.add(Box.createVerticalStrut(5));
			Box b2 = Box.createHorizontalBox();
			String [] colName = {"Mã đặt phòng","Loại phòng","Tên phòng","Phòng","Trạng thái","Tên khách","Ngày đến","Ngày đi","Số đêm"};
			Object [][] data = {
					{"PDP2024-001","STAN","Phòng đơn","A001","Đang sử dụng","Cao Thành Đông","25/09/2024","27/09/2024",2},
					{"PDP2024-002","SUIT","Phòng đôi","B002","Đang sử dụng","Trần Văn Hậu","25/09/2024","26/09/2024",1},
					{"PDP2024-003","DELU","Phòng gia đình","C004","Đã đặt trước","Trần Thế Gian","24/09/2024","25/09/2024",1},
					{"PDP2024-004","SUPE","Phòng VIP","D002","Đã đặt trước","Huỳnh Kim Đảm","26/09/2024","28/09/2024",2},
					
					{"PDP2024-001","STAN","Phòng đơn","A001","Đang sử dụng","Cao Thành Đông","25/09/2024","27/09/2024",2},
					{"PDP2024-002","SUIT","Phòng đôi","B002","Đang sử dụng","Trần Văn Hậu","25/09/2024","26/09/2024",1},
					{"PDP2024-003","DELU","Phòng gia đình","C004","Đã đặt trước","Trần Thế Gian","24/09/2024","25/09/2024",1},
					{"PDP2024-004","SUPE","Phòng VIP","D002","Đã đặt trước","Huỳnh Kim Đảm","26/09/2024","28/09/2024",2},
			};
			tableModel = new DefaultTableModel(data,colName);
			JScrollPane scroll;
			b2.add(scroll = new JScrollPane( table = new JTable(tableModel),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
			table.setBackground(new Color(24, 24, 28));
			table.setForeground(Color.WHITE);
			table.setFont(FontManager.getManrope(Font.PLAIN, 14));
			table.setRowHeight(55);
			
			JTableHeader header = table.getTableHeader();
			header.setDefaultRenderer(new CustomHeaderRenderer(new Color(38, 38, 42), Color.white));
			header.setPreferredSize(new Dimension(header.getPreferredSize().width, 55));
			header.setReorderingAllowed(false);
			
			scroll.setBounds(30, 380, 1642, 200);
			scroll.setBorder(BorderFactory.createEmptyBorder(9, 9, 9, 9));
			scroll.getViewport().setOpaque(false);
			scroll.setViewportBorder(null);
			b.add(b2);
			b.add(Box.createVerticalStrut(300));
			Box bbutton = Box.createHorizontalBox();
			bbutton.add(Box.createHorizontalStrut(1400));
			bbutton.add(btnlaphd = new JButton("Lập hóa đơn"));
			btnlaphd.setFont(FontManager.getManrope(Font.PLAIN, 15));
			btnlaphd.setForeground(Color.WHITE);
			btnlaphd.setBackground(new Color(66, 99, 235));
			btnlaphd.setOpaque(false);
			btnlaphd.setPreferredSize(new Dimension(200, 50));
			btnlaphd.setMinimumSize(new Dimension(200, 50));
			btnlaphd.setMaximumSize(new Dimension(200, 50));
			btnlaphd.addActionListener(this);
			b.add(bbutton);
			add(b,BorderLayout.CENTER);
			
}
//Hoa don
public void openHoaDon() {
    // Tạo JDialog hiển thị hóa đơn
    JDialog dialog = new JDialog();
    dialog.setTitle("Hóa đơn");
    dialog.setSize(700, 850);
    dialog.setLayout(new BorderLayout());

    // Thiết lập màu nền cho JDialog
    dialog.getContentPane().setBackground(new Color(40, 40, 44));
    Box bdialog = Box.createVerticalBox();
    
    // Tạo JPanel cho phần header của hóa đơn
    
    JPanel pheader = new JPanel();
    pheader.setLayout(new BoxLayout(pheader, BoxLayout.Y_AXIS));
    pheader.setBackground(new Color(16, 16, 20));
    pheader.setBackground(new Color(40, 40, 44));
    // Thêm tiêu đề và ngày tháng vào JPanel
    JLabel titleDialog = new JLabel("   HÓA ĐƠN");
    titleDialog.setForeground(Color.WHITE);
    titleDialog.setFont(new Font("SansSerif", Font.BOLD, 22));
    
    JLabel day = new JLabel("Ngày 27 tháng 09 năm 2024");
    day.setForeground(Color.WHITE);
    
    Box bheader = Box.createHorizontalBox();
    pheader.add(titleDialog);
    pheader.add(day);
    bheader.add(pheader);
    bdialog.add(bheader);
    bdialog.add(Box.createVerticalStrut(10)); // Khoảng cách giữa header và các thành phần khác

    // Tạo JPanel cho thông tin khách hàng
    JPanel customerInfoPanel = new JPanel();
    customerInfoPanel.setBackground(new Color(16, 16, 20));
    customerInfoPanel.setForeground(Color.WHITE);
    customerInfoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    customerInfoPanel.setBackground(new Color(40, 40, 44));
    customerInfoPanel.setBorder(BorderFactory.createTitledBorder(""));
    customerInfoPanel.setPreferredSize(new Dimension(700, 200));
	customerInfoPanel.setMinimumSize(new Dimension(700,200));
	customerInfoPanel.setMaximumSize(new Dimension(700,200));
    // Thêm các thông tin khách hàng
   
    customerInfoPanel.add(createLabel("Họ tên khách hàng:"));
    customerInfoPanel.add(createLabel("Cao Thành Đông"));
    customerInfoPanel.add(createLabel("Số điện thoại:"));
    customerInfoPanel.add(createLabel("0909123456"));
    customerInfoPanel.add(createLabel("Số CCCD:"));
    customerInfoPanel.add(createLabel("01524001238"));
    customerInfoPanel.add(createLabel("Ngày nhận phòng:"));
    customerInfoPanel.add(createLabel("25/09/2024"));
    customerInfoPanel.add(createLabel("Ngày trả phòng:"));
    customerInfoPanel.add(createLabel("27/09/2024"));
    
    bdialog.add(customerInfoPanel);
    bdialog.add(Box.createVerticalStrut(10));

    // Tạo bảng hiển thị các dịch vụ
    String[] columnNames = {"STT", "Tên dịch vụ", "Đơn vị tính", "Số lượng", "Đơn giá", "Thành tiền"};
    Object[][] data = {
        {1, "Phòng đơn", "Đêm", 2, "300.000đ", "600.000đ"},
        {2, "Pepsi", "Lon", 3, "15.000đ", "45.000đ"},
        {3, "Nước suối", "Chai", 1, "10.000đ", "10.000đ"},
        {4, "Massage", "Giờ", 1, "200.000đ", "200.000đ"}
    };
    
     tableModel1 = new DefaultTableModel(data, columnNames);
  
    JScrollPane scroll1;
	bdialog.add(scroll1 = new JScrollPane( table1 = new JTable(tableModel1),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED ));

	table1.setRowHeight(55);
    table1.setBackground(new Color(40, 40, 44));
    table1.setForeground(Color.WHITE);
    table1.setFont(FontManager.getManrope(Font.PLAIN, 15));
    
    JTableHeader header1 = table1.getTableHeader();
	header1.setDefaultRenderer(new CustomHeaderRenderer(new Color(88,84,92), Color.white));
	header1.setPreferredSize(new Dimension(header1.getPreferredSize().width, 55));
	header1.setFont(FontManager.getManrope(Font.PLAIN, 15));
	header1.setReorderingAllowed(false);
	
	scroll1.setPreferredSize(new Dimension(700,400));
	scroll1.setMinimumSize(new Dimension(700,400));
	scroll1.setMaximumSize(new Dimension(700,400));
	scroll1.setBorder(BorderFactory.createEmptyBorder(9, 9, 9, 9));
	scroll1.getViewport().setOpaque(false);
	scroll1.setViewportBorder(null);
    // Tổng tiền
	Box btotal = Box.createHorizontalBox();
    JLabel totalLabel = new JLabel("Tổng tiền: 905.000đ", SwingConstants.RIGHT);
    totalLabel.setForeground(Color.WHITE);
   // totalLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
    btotal.add(Box.createHorizontalStrut(500));
    btotal.add(totalLabel);
    bdialog.add(btotal);
    bdialog.add(Box.createVerticalStrut(90));
    // Thêm nút Xác nhận và Xuất hóa đơn
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.setBackground(new Color(40, 40, 44));

    JButton btnXacNhan = new JButton("Xác nhận");
    btnXacNhan.setPreferredSize(new Dimension(100, 40));
    btnXacNhan.setBackground(new Color(34, 139, 34));
    btnXacNhan.setForeground(Color.WHITE);

    JButton btnXuatHD = new JButton("Xuất hóa đơn");
    btnXuatHD.setPreferredSize(new Dimension(120, 40));
    btnXuatHD.setBackground(new Color(70, 130, 180));
    btnXuatHD.setForeground(Color.WHITE);

    buttonPanel.add(btnXacNhan);
    buttonPanel.add(btnXuatHD);

    bdialog.add(buttonPanel);

    // Thêm Box chính vào JDialog
    dialog.add(bdialog, BorderLayout.CENTER);

    // Đặt JDialog xuất hiện ở giữa màn hình
    dialog.setLocationRelativeTo(null);
    dialog.setResizable(false);
    dialog.setUndecorated(true);
    dialog.setVisible(true);
}

private static JLabel createLabel(String text) {
    JLabel label = new JLabel(text);
    label.setForeground(Color.WHITE);
    return label;
}


@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	openHoaDon();

}
}
