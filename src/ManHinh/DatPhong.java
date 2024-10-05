package ManHinh;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import GUI.CustomHeaderRenderer;
import GUI.FontManager;


public class DatPhong extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, 
		lbl7, lbl8, lbl9, lbl10, lbl11, lbl12;
	private JTextField tf1, tf2, tf3, tf4, 
		tf5 ,tf6, tf7, tf8, tf11;
	JComboBox tf10;
	JComboBox tf9;
	//private JLabel star;
	private DefaultTableModel tableModel;
	private JTable table;
	private JLabel star;
	public DatPhong() {
		setBackground(new Color (16, 16, 20));
		setBounds(100, 100, 863, 495);
		//formDK
		//formPanel = new JPanel();
		setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(formPanel);
		setLayout(null);
		//Labels and TextFields
		int x1 = 94, x2 = 35, h = 259, w = 45; //Đặt kích thước cho JTextField
		//Ngay Dat
		lbl1 = new JLabel("Ngày đặt");
		//JLabel star2Label = new JLabel("*", new Font("Manrope Regular", Font.PLAIN, 14), Color.red);
		star = new JLabel("*");
		star.setForeground(Color.RED);
		add(star);
		lbl1.setFont(FontManager.getManrope(Font.BOLD, 15));
		lbl1.setForeground(Color.WHITE);
		lbl1.setBounds(94, 12, 80, 20);
		add(lbl1);
		
		tf1 = new JTextField();
		tf1.setBounds(94, 35, 259, 45);
		tf1.setColumns(10);
		tf1.setBackground(new Color(40, 40, 44));
		tf1.setBorder(null);;
		add(tf1);
		//Ngay Den
		lbl2 = new JLabel("Ngày đến*");
		lbl2.setFont(FontManager.getManrope(Font.BOLD, 15));
		lbl2.setForeground(Color.WHITE);
		lbl2.setBounds(400, 12, 80, 20);
		add(lbl2);
		
		tf2 = new JTextField();
		tf2.setBounds(400, 35, h, w);
		tf2.setColumns(10);
		tf2.setBackground(new Color(40, 40, 44));
		tf2.setBorder(null);
		add(tf2);
		//Ngay Di
		lbl3 = new JLabel("Ngày đi");
		lbl3.setFont(FontManager.getManrope(Font.BOLD, 15));
		lbl3.setForeground(Color.WHITE);
		lbl3.setBounds(706, 12, 69, 20);
		add(lbl3);
		
		tf3 = new JTextField();
		tf3.setBounds(706, 35, h, w);
		tf3.setColumns(10);
		tf3.setBackground(new Color(40, 40, 44));
		tf3.setBorder(null);
		add(tf3);
		//Ten khach hang
		lbl4 = new JLabel("Tên khách hàng");
		lbl4.setFont(FontManager.getManrope(Font.BOLD, 15));
		lbl4.setForeground(Color.WHITE);
		lbl4.setBounds(1012, 12, 120, 20);
		add(lbl4);
		
		tf4 = new JTextField();
		tf4.setBounds(1012, 35, h, w);
		tf4.setColumns(10);
		tf4.setBackground(new Color(40, 40, 44));
		tf4.setBorder(null);
		add(tf4);
		//Ngay sinh
		lbl5 = new JLabel("Ngày sinh");
		lbl5.setFont(FontManager.getManrope(Font.BOLD, 15));
		lbl5.setForeground(Color.WHITE);
		lbl5.setBounds(94, 120, 120, 20);
		add(lbl5);
		
		tf5 = new JTextField();
		tf5.setBounds(94, 140, h, w);
		tf5.setColumns(10);
		tf5.setBackground(new Color(40, 40, 44));
		tf5.setBorder(null);
		add(tf5);
		//So dien thoai
		lbl6 = new JLabel("Số điện thoại");
		lbl6.setFont(FontManager.getManrope(Font.BOLD, 15));
		lbl6.setForeground(Color.WHITE);
		lbl6.setBounds(400, 120, 120, 20);
		add(lbl6);
		
		tf6 = new JTextField();
		tf6.setBounds(400, 140, h, w);
		tf6.setColumns(10);
		tf6.setBackground(new Color(40, 40, 44));
		tf6.setBorder(null);
		add(tf6);
		//Email
		lbl7 = new JLabel("Email");
		lbl7.setFont(FontManager.getManrope(Font.BOLD, 15));
		lbl7.setForeground(Color.WHITE);
		lbl7.setBounds(706, 120, 120, 20);
		add(lbl7);
		
		tf7 = new JTextField();
		tf7.setBounds(706, 140, h, w);
		tf7.setColumns(10);
		tf7.setBackground(new Color(40, 40, 44));
		tf7.setBorder(null);
		add(tf7);
		//CCCD
		lbl8 = new JLabel("CCCD");
		lbl8.setFont(FontManager.getManrope(Font.BOLD, 15));
		lbl8.setForeground(Color.WHITE);
		lbl8.setBounds(1012, 120, 120, 20);
		add(lbl8);
		
		tf8 = new JTextField();
		tf8.setBounds(1012, 140, h, w);
		tf8.setColumns(10);
		tf8.setBackground(new Color(40, 40, 44));
		tf8.setBorder(null);
		add(tf8);
		//Loai phong
		lbl9 = new JLabel("Loại phòng");
		lbl9.setFont(FontManager.getManrope(Font.BOLD, 15));
		lbl9.setForeground(Color.WHITE);
		lbl9.setBounds(94, 228, 120, 20);
		add(lbl9);
		
		String[] values = {"Phòng đơn: 2 còn trống", "Phòng đôi: 2 còn trống", "Phòng ba: 2 còn trống"};
		tf9 = new JComboBox(values);
		tf9.setBounds(94, 248, h, w);
		tf9.setBackground(new Color(40, 40, 44));
		tf9.setFont(FontManager.getManrope(Font.BOLD, 15));
		tf9.setForeground(Color.WHITE);
		tf9.setBorder(null);
		add(tf9);
		//So phong
		lbl10 = new JLabel("Số phòng");
		lbl10.setFont(FontManager.getManrope(Font.BOLD, 15));
		lbl10.setForeground(Color.WHITE);
		lbl10.setBounds(400, 228, 120, 20);
		add(lbl10);
		
		tf10 = new JComboBox();
		tf10.setBounds(400, 248, h, w);
		tf10.setBackground(new Color(40, 40, 44));
		tf10.setBorder(null);
		add(tf10);
		//So khach
		lbl11 = new JLabel("Số khách");
		lbl11.setFont(FontManager.getManrope(Font.BOLD, 15));
		lbl11.setForeground(Color.WHITE);
		lbl11.setBounds(706, 228, 120, 20);
		add(lbl11);
		
		tf11 = new JTextField();
		tf11.setBounds(706, 248, h, w);
		tf11.setColumns(10);
		tf11.setBackground(new Color(40, 40, 44));
		tf11.setBorder(null);
		add(tf11);
		// Lich su dạt phong
		lbl12 = new JLabel("Lịch sử đặt phòng");
		lbl12.setFont(FontManager.getManrope(Font.BOLD, 15));
		lbl12.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		lbl12.setForeground(Color.WHITE);
		lbl12.setBounds(30, 320, 200, 20);
		lbl12.setBackground(new Color(27, 112, 213));
		add(lbl12);
		//Table
		String[] colNames = {"Mã đặt phòng", "Loại phòng", "Tên phòng", "Phòng", "Trạng thái", "Tên khách", 
                "Ngày đến", "Ngày đi", "Số đêm"};

		// Table Data
		Object[][] data = {
		{"PDP2024-001", "STAN", "Phòng đơn", "A001", "Đang sử dụng", "Cao Thành Đông", "25/09/2024", "27/09/2024", 2},
		{"PDP2024-002", "SUIT", "Phòng đôi", "B002", "Đang sử dụng", "Trần Văn Hậu", "25/09/2024", "26/09/2024", 1},
		{"PDP2024-003", "DELU", "Phòng gia đình", "C004", "Đã đặt trước", "Trần Thế Gian", "24/09/2024", "25/09/2024", 1},
		{"PDP2024-004", "SUPE", "Phòng VIP", "D002", "Đã đặt trước", "Huỳnh Kim Bấm", "26/09/2024", "28/09/2024", 2}, 
		{"PDP2024-005", "SUIT", "Phòng đơn", "E003", "Đã đặt trước", "Huỳnh Kam Đỉm", "26/09/2024", "28/09/2024", 2}
		};
		//Khoi tao table model
		DefaultTableModel tableModel = new DefaultTableModel(data, colNames);
		
		// Tạo JTable từ DefaultTableModel
		JTable table = new JTable(tableModel);
		table.getTableHeader().setBackground((new Color(24, 24, 28)));
		table.getTableHeader().setFont(FontManager.getManrope(Font.BOLD, 15));
		table.getTableHeader().setForeground(Color.WHITE);
		//table.getTableHeader().setShowGrid(false);
		table.setBackground(new Color(24, 24, 28));
		table.setFont(new Font("Manrope", Font.PLAIN, 14));
		table.setForeground(Color.WHITE);
		table.setRowHeight(55);
		// Tạo renderer để căn giữa nội dung các ô trong cột "Số đêm"
//        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Áp dụng renderer cho cột "Số đêm"
//        table.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);  // Cột thứ 8 (0-indexed)
		//table.setShowGrid(false);
		// Tạo JScrollPane để có thể cuộn
		JTableHeader header = table.getTableHeader();
		header.setDefaultRenderer(new CustomHeaderRenderer(new Color(38, 38, 42), Color.white));
		header.setPreferredSize(new Dimension(header.getPreferredSize().width, 55));
		header.setReorderingAllowed(false);
		JScrollPane scroll = new JScrollPane(table);
		
		// Đặt kích thước và vị trí cho JScrollPane
		scroll.setBounds(30, 380, 1240, 200);
		scroll.setBackground(new Color(24, 24, 28));
		add(scroll);
		//Buttons
		JButton btnConfirm = new JButton("Xác nhận");
		btnConfirm.setBounds(706, 650, 259, 45);
		btnConfirm.setFont(FontManager.getManrope(Font.PLAIN, 15));
		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.setBackground(new Color(66, 99, 235));
		add(btnConfirm);
		
		JButton btnClear = new JButton("Làm mới");
		btnClear.setFont(new Font("Manrope", Font.BOLD, 16));
		btnClear.setForeground(Color.WHITE);
		btnClear.setBounds(1012, 650, 259, 45);
		btnClear.setBackground(new Color(151, 69, 35));
		add(btnClear);
		//formPanel.add(btnConfirm);
	}
	
}
