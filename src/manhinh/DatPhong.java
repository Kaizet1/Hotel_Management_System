package manhinh;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import customelement.CustomCellRenderer;
import customelement.CustomHeaderRenderer;
import customelement.FontManager;
import customelement.RoundedPanel;

public class DatPhong extends JPanel {
	private JTextField txtNgayDat;
	private int currentPage = 1;
	private final int rowsPerPage = 5;
	DefaultTableModel tableModel;
	Object[][] data = { { "DP001", "101", "Nguyen Van A", "2023-10-01", "2023-10-03", 2, 2000000 },
			{ "DP002", "102", "Tran Thi B", "2023-10-02", "2023-10-04", 2, 2200000 },
			{ "DP003", "103", "Le Van C", "2023-10-01", "2023-10-02", 1, 1100000 },
			{ "DP004", "104", "Pham Thi D", "2023-10-03", "2023-10-06", 3, 3300000 },
			{ "DP005", "105", "Doan Van E", "2023-10-05", "2023-10-07", 2, 2000000 },
			{ "DP006", "106", "Hoang Thi F", "2023-10-01", "2023-10-05", 4, 4000000 },
			{ "DP007", "107", "Nguyen Van G", "2023-10-06", "2023-10-08", 2, 2100000 },
			{ "DP008", "108", "Le Thi H", "2023-10-04", "2023-10-06", 2, 2300000 },
			{ "DP009", "109", "Pham Van I", "2023-10-07", "2023-10-09", 2, 2400000 },
			{ "DP010", "110", "Tran Thi J", "2023-10-08", "2023-10-11", 3, 3500000 },
			{ "DP008", "108", "Le Thi H", "2023-10-04", "2023-10-06", 2, 2300000 },
			{ "DP009", "109", "Pham Van I", "2023-10-07", "2023-10-09", 2, 2400000 },
			{ "DP010", "110", "Tran Thi J", "2023-10-08", "2023-10-11", 3, 3500000 }, };
	private JLabel pageNumber;

//	private static final long serialVersionUID = 1L;
//	private JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, 
//		lbl7, lbl8, lbl9, lbl10, lbl11, lbl12;
//	private JTextField tf1, tf2, tf3, tf4, 
//		tf5 ,tf6, tf7, tf8, tf11;
//	JComboBox tf10;
//	JComboBox tf9;
//	//private JLabel star;
//	private DefaultTableModel tableModel;
//	private JTable table;
//	private JLabel star;
	public DatPhong() {
		setLayout(new BorderLayout());
		setBackground(new Color(16, 16, 20));
		// north
		JPanel northPanel = new JPanel();
		northPanel.setOpaque(false);
		northPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		JPanel form = new JPanel();
		form.setPreferredSize(new Dimension(1400, 350));
		form.setOpaque(false);
		// BoxForm
		Box boxForm1 = createFormBox("Ngày đặt", "");
		Box boxForm2 = createFormBox("Ngày đến", "");
		Box boxForm3 = createFormBox("Ngày đi", "");
		Box boxForm4 = createFormBox("Tên khách hàng", "");
		Box boxForm5 = createFormBox("Ngày sinh", "");
		Box boxForm6 = createFormBox("Số điện thoại", "");
		Box boxForm7 = createFormBox("Email", "");
		Box boxForm8 = createFormBox("CCCD", "");
		Box boxForm9 = createFormBox("Loại phòng", "");
		Box boxForm10 = createFormBox("Số phòng", "");
		Box boxForm11 = createFormBox("Tên phòng", "");
		Box boxForm12 = createFormBox("Số khách", "");
		form.add(boxForm1);
		form.add(boxForm2);
		form.add(boxForm3);
		form.add(boxForm4);
		form.add(boxForm5);
		form.add(boxForm6);
		form.add(boxForm7);
		form.add(boxForm8);
		form.add(boxForm9);
		form.add(boxForm10);
		form.add(boxForm11);
		form.add(boxForm12);
		northPanel.add(form);
		// center
		JLabel titleLabel = new JLabel("Lịch sử đặt phòng");
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
		

		String[] tableHeaders = "Mã đặt phòng;Phòng;Tên khách;Ngày đến;Ngày đi;Số đêm;Doanh thu".split(";");
		tableModel = new DefaultTableModel(tableHeaders, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// Thiết lập màu nền cho JTable
		JTable table = new JTable(tableModel);
		table.setBackground(new Color(24, 24, 28));
		table.setForeground(Color.WHITE);
		table.setFont(FontManager.getManrope(Font.PLAIN, 14));
		table.setRowHeight(55);

		// Thiết lập header của bảng
		JTableHeader header = table.getTableHeader();
		header.setDefaultRenderer(new CustomHeaderRenderer(new Color(38, 38, 42), Color.white));
		header.setPreferredSize(new Dimension(header.getPreferredSize().width, 55));
		header.setReorderingAllowed(false);

		CustomCellRenderer cellRenderer = new CustomCellRenderer();
		for (int i = 0; i < table.getColumnCount(); i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			column.setCellRenderer(cellRenderer);
		}
		// Thiết lập JScrollPane
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBorder(null);
		scroll.setPreferredSize(new Dimension(1642, 330));
		scroll.getViewport().setOpaque(false);
		scroll.setViewportBorder(null);
		Dimension navButtonSize = new Dimension(35, 35);
		Color navButtonColor = Color.black;
		Color pageNumberColor = new Color(27, 112, 213);
		JButton prevButton = new JButton(new ImageIcon("imgs/prevIcon.png"));
		prevButton.setPreferredSize(navButtonSize);
		prevButton.setBackground(navButtonColor);
		RoundedPanel pagePanel = new RoundedPanel(5, 0, new Color(255, 255, 255, 0), pageNumberColor);
		pagePanel.setOpaque(false);
		pageNumber = new JLabel("1", JLabel.CENTER);
		pageNumber.setFont(FontManager.getManrope(Font.BOLD, 14));
		pageNumber.setForeground(pageNumberColor);
		pagePanel.setPreferredSize(new Dimension(29, 29));
		pagePanel.add(pageNumber);
		JButton nextButton = new JButton(new ImageIcon("imgs/nextIcon.png"));
		nextButton.setPreferredSize(navButtonSize);
		nextButton.setBackground(navButtonColor);

		prevButton.addActionListener(e -> {
			if (currentPage > 1) {
				currentPage--;
				loadPage(currentPage);
			}
		});

		nextButton.addActionListener(e -> {
			if (currentPage < getTotalPages()) {
				currentPage++;
				loadPage(currentPage);
			}
		});

		JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		navPanel.setOpaque(false);
		navPanel.setPreferredSize(new Dimension(1655, 50));
		navPanel.setMaximumSize(new Dimension(1655, 50));
		navPanel.setMinimumSize(new Dimension(1655, 50));
		navPanel.add(prevButton);
		navPanel.add(pagePanel);
		navPanel.add(nextButton);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		centerPanel.add(titlePanel);
		centerPanel.add(scroll);
		centerPanel.add(navPanel);

		JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		southPanel.setOpaque(false);
		JButton submitButton = createButton("Xác nhận", new Color(66, 99, 235));
		JButton refreshButton = createButton("Làm mới", new Color(151, 69, 35));
		southPanel.add(submitButton);
		southPanel.add(refreshButton);
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		loadPage(1);
	}

	private JButton createButton(String buttonLabel, Color buttonColor) {
		JButton button = new JButton(buttonLabel);
		button.setBackground(buttonColor);
		button.setForeground(Color.white);
		button.setPreferredSize(new Dimension(259, 45));
		button.setFont(FontManager.getManrope(Font.PLAIN, 16));
		return button;
	}

	private Box createFormBox(String label, String placeholder) {
		Box b = Box.createVerticalBox();
		b.setPreferredSize(new Dimension(331, 106));
		JLabel lbl = new JLabel(label);
		lbl.setFont(FontManager.getManrope(Font.PLAIN, 15));
		lbl.setForeground(Color.WHITE);
		lbl.setPreferredSize(new Dimension(259, 20));
		lbl.setMaximumSize(new Dimension(259, 20));
		lbl.setMinimumSize(new Dimension(259, 20));
//		lbl.setAlignmentX(LEFT_ALIGNMENT);
//		lbl.setBorder(BorderFactory.createLineBorder(Color.pink));
		JTextField text = new JTextField(placeholder);
		text.setBackground(new Color(40, 40, 44));
		b.add(lbl);
		b.add(Box.createVerticalStrut(6));
		b.add(text);
		b.setBorder(BorderFactory.createEmptyBorder(0, 36, 35, 36));
		return b;
	}

	private void loadPage(int page) {
		pageNumber.setText(String.valueOf(currentPage));
		tableModel.setRowCount(0);
		int start = (page - 1) * rowsPerPage;
		int end = Math.min(start + rowsPerPage, data.length);
		for (int i = start; i < end; i++) {
			tableModel.addRow(data[i]);
		}
	}

	private int getTotalPages() {
		return (int) Math.ceil((double) data.length / rowsPerPage);
	}
}
