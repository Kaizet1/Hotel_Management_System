package ManHinh;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import GUI.CustomCellRenderer;
import GUI.CustomHeaderRenderer;
import GUI.FontManager;
import GUI.RoundedPanel;

public class TraCuu extends JPanel {
    private static final long serialVersionUID = 1L;

    public TraCuu() {
        setBackground(new Color(16, 16, 20));
        setLayout(new BorderLayout());
      
        

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
        
        
        JPanel listFrame1 = createListFrame();
		
        RoundedPanel titlePanel2 = createTitlePanel("Danh sách khách hàng");
		DefaultTableModel tableModel2 = createTableModel("Mã phòng;Tên phòng;Giá phòng;Số người;Mô tả".split(";"));
        JScrollPane scrollTable2 = createScrollTable(tableModel2);
        RoundedPanel titlePanel3 = createTitlePanel("Danh sách dịch vụ");
		DefaultTableModel tableModel3 = createTableModel("Mã phòng;Tên phòng;Giá phòng;Số người;Mô tả".split(";"));
        JScrollPane scrollTable3 = createScrollTable(tableModel3);
        RoundedPanel titlePanel4 = createTitlePanel("Danh sách hóa đơn");
		DefaultTableModel tableModel4 = createTableModel("Mã phòng;Tên phòng;Giá phòng;Số người;Mô tả".split(";"));
        JScrollPane scrollTable4 = createScrollTable(tableModel4);
        RoundedPanel titlePanel5 = createTitlePanel("Danh sách nhân viên");
		DefaultTableModel tableModel5 = createTableModel("Mã phòng;Tên phòng;Giá phòng;Số người;Mô tả".split(";"));
        JScrollPane scrollTable5 = createScrollTable(tableModel5);
        Object[] row1 = { "DP001", "101", "Nguyen Van A", "2023-10-01", "2023-10-03" };
		Object[] row2 = { "DP002", "102", "Tran Thi B", "2023-10-02", "2023-10-04" };
		Object[] row3 = { "DP003", "103", "Le Van C", "2023-10-01", "2023-10-02" };
//		tableModel1.addRow(row1);
//		tableModel1.addRow(row2);
//		tableModel1.addRow(row3);

		

		
        
        JPanel mainPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(listFrame1);
//        centerPanel.add(scrollTable1);
        centerPanel.add(titlePanel2);
        centerPanel.add(scrollTable2);
        centerPanel.add(titlePanel3);
        centerPanel.add(scrollTable3);
        centerPanel.add(titlePanel4);
        centerPanel.add(scrollTable4);
        centerPanel.add(titlePanel5);
        centerPanel.add(scrollTable5);
        
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(23, 10, 0, 0));
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        JScrollPane scroll = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
        scroll.getViewport().setOpaque(false);
//        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(10, Integer.MAX_VALUE));

        add(scroll);
    }
    
    private RoundedPanel createTitlePanel(String titleText) {
    	JLabel titleLabel = new JLabel(titleText);
		titleLabel.setFont(FontManager.getManrope(Font.BOLD, 16));
		titleLabel.setForeground(Color.white);
    	Dimension titlePanelSize = new Dimension(1635, 50);
    	RoundedPanel titlePanel = new RoundedPanel(10, 0, new Color(27, 112, 213));
    	titlePanel.setAlignmentX(LEFT_ALIGNMENT);
		titlePanel.setPreferredSize(titlePanelSize);
		titlePanel.setMinimumSize(titlePanelSize);
		titlePanel.setMaximumSize(titlePanelSize);
		titlePanel.setOpaque(false);
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));

		titlePanel.add(Box.createHorizontalStrut(15));
		titlePanel.add(titleLabel);
		titlePanel.add(Box.createHorizontalGlue());
    	return titlePanel;
    }
    
    private DefaultTableModel createTableModel(String[] tableHeaders) {
		DefaultTableModel tableModel = new DefaultTableModel(tableHeaders, 0) {
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return tableModel;
    }
    
    private JScrollPane createScrollTable(DefaultTableModel tableModel) {
    	JTable table1 = new JTable(tableModel);
		table1.setBackground(new Color(24, 24, 28));
		table1.setForeground(Color.WHITE);
		table1.setFont(FontManager.getManrope(Font.PLAIN, 14));
		table1.setRowHeight(55);

		JTableHeader header1 = table1.getTableHeader();
		header1.setDefaultRenderer(new CustomHeaderRenderer(new Color(38, 38, 42), Color.white));
		header1.setPreferredSize(new Dimension(header1.getPreferredSize().width, 55));
		header1.setReorderingAllowed(false);

		CustomCellRenderer cellRenderer = new CustomCellRenderer();
        for (int i = 0; i < table1.getColumnCount(); i++) {
            TableColumn column = table1.getColumnModel().getColumn(i);
            column.setCellRenderer(cellRenderer);
        }
		// Thiết lập JScrollPane
		JScrollPane scrollTable = new JScrollPane(table1, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollTable.setAlignmentX(LEFT_ALIGNMENT);
		scrollTable.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		scrollTable.getViewport().setOpaque(false);
		scrollTable.setViewportBorder(null);
		scrollTable.setPreferredSize(new Dimension(1635, 230));
		scrollTable.setMinimumSize(new Dimension(1635, 230));
		scrollTable.setMaximumSize(new Dimension(1635, 230));
		return scrollTable;
    }
    
    private JPanel createListFrame() {
    	JPanel listFrame = new JPanel();
    	Dimension listFrameSize = new Dimension(1635, 310);
    	listFrame.setOpaque(false);
    	listFrame.setPreferredSize(listFrameSize);
    	listFrame.setMaximumSize(listFrameSize);
    	listFrame.setMinimumSize(listFrameSize);
    	listFrame.setLayout(new BoxLayout(listFrame, BoxLayout.Y_AXIS));
    	RoundedPanel titlePanel = createTitlePanel("Danh sách phòng");
		DefaultTableModel tableModel = createTableModel("Mã phòng;Tên phòng;Giá phòng;Số người;Mô tả".split(";"));
        JScrollPane scrollTable = createScrollTable(tableModel);
        listFrame.add(titlePanel);
        listFrame.add(scrollTable);
    	return listFrame;
    }
}