package ManHinh;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import GUI.FontManager;
import GUI.RoundedPanel;

public class ThongTinChung extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public ThongTinChung() {
		setBackground(new Color(16, 16, 20));
        setLayout(new BorderLayout());

        RoundedPanel infoBox1 = createInfoBox(4, "PHÒNG ĐẾN TRONG NGÀY", new Color(10, 213, 157, 179));
        RoundedPanel infoBox2 = createInfoBox(2, "PHÒNG ĐI TRONG NGÀY", new Color(255, 191, 91, 179));
        RoundedPanel infoBox3 = createInfoBox(4, "PHÒNG CÓ KHÁCH", new Color(116, 185, 255, 179), "10%");
        RoundedPanel infoBox4 = createInfoBox(6, "KHÁCH ĐANG Ở", new Color(255, 118, 117, 179));

        Box infoBoxContainer = Box.createHorizontalBox();
        infoBoxContainer.add(infoBox1);
        infoBoxContainer.add(Box.createHorizontalStrut(14));
        infoBoxContainer.add(infoBox2);
        infoBoxContainer.add(Box.createHorizontalStrut(14));
        infoBoxContainer.add(infoBox3);
        infoBoxContainer.add(Box.createHorizontalStrut(14));
        infoBoxContainer.add(infoBox4);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setOpaque(false);
        northPanel.add(Box.createVerticalStrut(35));
        northPanel.add(infoBoxContainer);
        northPanel.add(Box.createVerticalStrut(55));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Thống kê hôm nay");
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
        JScrollPane scroll;
		String[] tableHeaders = "Mã đặt phòng;Phòng;Tên khách;Ngày đến;Ngày đi;Số đêm;Doanh thu".split(";");
		DefaultTableModel tableModel;
		JTable table;
		tableModel = new DefaultTableModel(tableHeaders, 0);
        centerPanel.add(titlePanel);
        centerPanel.add(Box.createVerticalStrut(8));
        centerPanel.add(scroll = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
	}
	
	private RoundedPanel createInfoBox(int number, String infoTitle, Color boxColor) {
        return createInfoBox(number, infoTitle, boxColor, null);
    }
	
	private RoundedPanel createInfoBox(int number, String infoTitle, Color boxColor, String percent) {
		RoundedPanel infoBox = new RoundedPanel(10, 0, boxColor);
	    infoBox.setLayout(new BoxLayout(infoBox, BoxLayout.Y_AXIS));
		JLabel numberLabel = new JLabel(String.valueOf(number));
		JLabel titleLabel = new JLabel(infoTitle);
		numberLabel.setForeground(Color.WHITE);
		titleLabel.setForeground(Color.WHITE);
		numberLabel.setFont(FontManager.getManrope(Font.BOLD, 64));
		titleLabel.setFont(FontManager.getManrope(Font.BOLD, 24));
		Box box1 = Box.createHorizontalBox();
		Box box2 = Box.createHorizontalBox();
		box1.add(Box.createHorizontalStrut(15));	
		box1.add(numberLabel);
		box1.setAlignmentX(LEFT_ALIGNMENT);
		box2.add(Box.createHorizontalStrut(15));	
		box2.add(titleLabel);
		box2.setAlignmentX(LEFT_ALIGNMENT);
		
		if (percent != null) {
			JLabel percentLabel = new JLabel(percent);
			percentLabel.setForeground(Color.WHITE);
			percentLabel.setFont(FontManager.getManrope(Font.BOLD, 24));
			box2.add(Box.createHorizontalGlue());
			box2.add(percentLabel);
			box2.add(Box.createHorizontalStrut(15));			
		}
		
		
		infoBox.add(Box.createVerticalStrut(68));
		infoBox.add(box1);
		infoBox.add(Box.createVerticalStrut(5));
		infoBox.add(box2);
		infoBox.setOpaque(false);
		infoBox.setPreferredSize(new Dimension(400, 200));
	    infoBox.setMinimumSize(new Dimension(400, 200));
	    infoBox.setMaximumSize(new Dimension(400, 200));
		return infoBox;
	}
}
