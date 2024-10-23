package form;

import customElements.CustomCellRenderer;
import customElements.CustomHeaderRenderer;
import customElements.FontManager;
import customElements.RoundedPanel;
import entity.KhachHang;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DatDichVu_FORM extends  JPanel implements ActionListener, MouseListener {
    private  JButton btnDatDV;
    private JTable table;
    private  DefaultTableModel tableModel;
    private DefaultTableModel tableModel1;
    private JTable table1;
    private Font f1;
    private Component buttonPanel;
    private JButton btnThem;
    private DefaultTableModel tableModel2;
    private JTable table2;
    private JButton btnLamMoi;
    private JButton btnXacNhan;


    public DatDichVu_FORM() {
       setBackground(new Color(16, 16, 20));
        Box b = Box.createVerticalBox();
        b.add(Box.createVerticalStrut(10));

        // Tim kiem
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

        // Tieu de
        b.add(Box.createVerticalStrut(20));
        JLabel titleLabel = new JLabel("Danh sách phòng đã đặt");
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

        // Tạo bang
        Box b2 = Box.createHorizontalBox();
        String[] colName = {"Mã đặt phòng", "Loại phòng", "Tên phòng", "Phòng", "Trạng thái", "Tên khách", "Ngày đến", "Ngày đi", "Số đêm"};
        Object[][] data = {
                {"PDP2024-001", "STAN", "Phòng đơn", "A001", "Đang sử dụng", "Cao Thành Đông", "25/09/2024", "27/09/2024", 2},
                {"PDP2024-002", "SUIT", "Phòng đôi", "B002", "Đang sử dụng", "Trần Văn Hậu", "25/09/2024", "26/09/2024", 1},
                {"PDP2024-003", "DELU", "Phòng gia đình", "C004", "Đã đặt trước", "Trần Thế Gian", "24/09/2024", "25/09/2024", 1},
                {"PDP2024-004", "SUPE", "Phòng VIP", "D002", "Đã đặt trước", "Huỳnh Kim Đảm", "26/09/2024", "28/09/2024", 2},

                {"PDP2024-001", "STAN", "Phòng đơn", "A001", "Đang sử dụng", "Cao Thành Đông", "25/09/2024", "27/09/2024", 2},
                {"PDP2024-002", "SUIT", "Phòng đôi", "B002", "Đang sử dụng", "Trần Văn Hậu", "25/09/2024", "26/09/2024", 1},
                {"PDP2024-003", "DELU", "Phòng gia đình", "C004", "Đã đặt trước", "Trần Thế Gian", "24/09/2024", "25/09/2024", 1},
                {"PDP2024-004", "SUPE", "Phòng VIP", "D002", "Đã đặt trước", "Huỳnh Kim Đảm", "26/09/2024", "28/09/2024", 2},
        };
        tableModel = new DefaultTableModel(data, colName);
        JScrollPane scroll;
        b2.add(scroll = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        table.setBackground(new Color(24, 24, 28));
        table.setForeground(Color.WHITE);
        table.setFont(FontManager.getManrope(Font.PLAIN, 16));
        table.setRowHeight(55);

        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new CustomHeaderRenderer(new Color(38, 38, 42), Color.white));
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 55));
        header.setReorderingAllowed(false);

        CustomCellRenderer cellRenderer = new CustomCellRenderer();
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setCellRenderer(cellRenderer);
        }

        scroll.setBounds(30, 380, 1642, 200);
        scroll.setBorder(null);
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);
        b.add(b2);
        b.add(Box.createVerticalStrut(300));

        // Tạo nut
        Box bbutton = Box.createHorizontalBox();
        bbutton.add(Box.createHorizontalStrut(1400));
        bbutton.add(btnDatDV = new JButton("Đặt dịch vụ"));
        btnDatDV.setFont(FontManager.getManrope(Font.PLAIN, 16));
        btnDatDV.setForeground(Color.WHITE);
        btnDatDV.setBackground(new Color(66, 99, 235));
        btnDatDV.setOpaque(false);
        btnDatDV.setPreferredSize(new Dimension(200, 50));
        btnDatDV.setMinimumSize(new Dimension(200, 50));
        btnDatDV.setMaximumSize(new Dimension(200, 50));
        btnDatDV.addActionListener(this);
        b.add(bbutton);
        add(b, BorderLayout.CENTER);

        btnDatDV.addActionListener(this);
        table.addMouseListener(this);

    }

    public void phieuDatDichVu() {
        // Tạo JDialog hiển thị hóa đơn
        JDialog dialog = new JDialog();
        dialog.setSize(1564, 880);
        dialog.setLayout(new BorderLayout());
        f1 = new Font("Montserrat", Font.PLAIN, 16);

        // Thiết lập màu nền cho JDialog
        dialog.getContentPane().setBackground(new Color(31,31,32,255));
        Box bdialog = Box.createHorizontalBox();
        Box bLeft = Box.createVerticalBox();
        bLeft.add(Box.createVerticalStrut(10));
        bLeft.setPreferredSize(new Dimension(950, 880));
        bLeft.setMinimumSize(new Dimension(950, 880));
        bLeft.setMaximumSize(new Dimension(950, 880));
        JTextField searchField1 = new JTextField("Tìm kiếm tên dịch vụ");
        Border emptyBorder = BorderFactory.createEmptyBorder(13, 52, 12, 0);
        searchField1.setBounds(0, 0, 280, 45);
        searchField1.setBorder(emptyBorder);
        searchField1.setBackground(new Color(40, 40, 44));
        searchField1.setForeground(new Color(255, 255, 255, 125));
        searchField1.setFont(FontManager.getManrope(Font.PLAIN, 15));
        CompoundBorder combinedBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(83, 152, 255)), emptyBorder);
        searchField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                searchField1.setBorder(combinedBorder);
                if (searchField1.getText().equals("Tìm kiếm tên dịch vụ")) {
                    searchField1.setText("");
                    searchField1.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                searchField1.setBorder(emptyBorder);
                if (searchField1.getText().isEmpty()) {
                    searchField1.setForeground(new Color(255, 255, 255, 125));
                    searchField1.setText("Tìm kiếm tên dịch vụ");
                }
            }
        });

        JLabel searchIcon1 = new JLabel(new ImageIcon("imgs/TimKiemIcon.png"));
        searchIcon1.setBounds(12, 12, 24, 24);

        JPanel searchPanel1 = new JPanel();
        searchPanel1.setOpaque(false);
        searchPanel1.setLayout(null);
        Dimension searchPanelSize = new Dimension(280, 45);
        searchPanel1.setPreferredSize(searchPanelSize);
        searchPanel1.setMinimumSize(searchPanelSize);
        searchPanel1.setMaximumSize(searchPanelSize);

        searchPanel1.add(searchIcon1);
        searchPanel1.add(searchField1);
        Box bsearch = Box.createHorizontalBox();
        bsearch.add(Box.createHorizontalStrut(0));
        bsearch.add(searchPanel1);
        bsearch.add(Box.createGlue());
        bLeft.add(bsearch);
        bLeft.add(Box.createVerticalStrut(20));

        // Tieu de
        bLeft.add(Box.createVerticalStrut(10));
        JLabel titleLabel1 = new JLabel("Danh sách dịch vụ");
        titleLabel1.setFont(FontManager.getManrope(Font.BOLD, 16));
        titleLabel1.setForeground(Color.white);

        RoundedPanel titlePanel1 = new RoundedPanel(10, 0, new Color(27, 112, 213));
        titlePanel1.setPreferredSize(new Dimension(950, 50));
        titlePanel1.setMinimumSize(new Dimension(950, 50));
        titlePanel1.setMaximumSize(new Dimension(950, 50));
        titlePanel1.setOpaque(false);
        titlePanel1.setLayout(new BoxLayout(titlePanel1, BoxLayout.X_AXIS));

        titlePanel1.add(Box.createHorizontalStrut(15));
        titlePanel1.add(titleLabel1);
        titlePanel1.add(Box.createHorizontalGlue());
        bLeft.add(titlePanel1);
        bLeft.add(Box.createVerticalStrut(5));

        // Tạo bang
        bLeft.add(Box.createVerticalStrut(10));
        Box bTable1 = Box.createHorizontalBox();
        String[] colName1 = {"Mã dịch vụ", "Tên dịch vụ", "Giá dịch vụ", "Đơn vị tính", "Tồn kho"};
        Object[][] data1 = {
                {1, "Gà hấp xả", "200.000", "Con", 50},
                {2, "Pepsi", "15.000", "Lon", 1000},
                {3, "Nước suối", "10.000", "Chai", 1000},
                {4, "Massage", "150.000", "Giờ", 5}
        };
        tableModel1 = new DefaultTableModel(data1, colName1);
        JScrollPane scroll1;
        bTable1.add(scroll1 = new JScrollPane(table1 = new JTable(tableModel1), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        table1.setBackground(new Color(24, 24, 28));
        table1.setForeground(Color.WHITE);
        table1.setFont(FontManager.getManrope(Font.PLAIN, 16));
        table1.setRowHeight(55);

        JTableHeader header1 = table1.getTableHeader();
        header1.setDefaultRenderer(new CustomHeaderRenderer(new Color(38, 38, 42), Color.white));
        header1.setPreferredSize(new Dimension(header1.getPreferredSize().width, 55));
        header1.setReorderingAllowed(false);

        CustomCellRenderer cellRenderer1 = new CustomCellRenderer();
        for (int i = 0; i < table1.getColumnCount(); i++) {
            TableColumn column = table1.getColumnModel().getColumn(i);
            column.setCellRenderer(cellRenderer1);
        }

        scroll1.setBounds(30, 380, 940, 700);
        scroll1.setBorder(null);
        scroll1.getViewport().setOpaque(false);
        scroll1.setViewportBorder(null);
        bLeft.add(bTable1);
        bLeft.add(Box.createVerticalStrut(300));

        // Tạo nut
        Box bbutton1 = Box.createHorizontalBox();
        bbutton1.add(Box.createHorizontalStrut(700));
        bbutton1.add(btnThem = new JButton("Thêm"));
        btnThem.setFont(FontManager.getManrope(Font.PLAIN, 16));
        btnThem.setForeground(Color.WHITE);
        btnThem.setBackground(new Color(74, 74, 66, 100));
        btnThem.setOpaque(false);
        btnThem.setPreferredSize(new Dimension(150, 40));
        btnThem.setMinimumSize(new Dimension(150, 40));
        btnThem.setMaximumSize(new Dimension(150, 40));
        btnThem.addActionListener(this);
        bLeft.add(Box.createVerticalStrut(10));
        bLeft.add(bbutton1);

        Box bRight = Box.createVerticalBox();
        bRight.add(Box.createVerticalStrut(65));
        bRight.setPreferredSize(new Dimension(563, 880));
        bRight.setMinimumSize(new Dimension(563, 880));
        bRight.setMaximumSize(new Dimension(563, 880));
        JPanel pheader = new JPanel();
        pheader.setLayout(new BoxLayout(pheader, BoxLayout.Y_AXIS));
        pheader.setBackground(new Color(16, 16, 20));
        pheader.setBackground(new Color(40, 40, 44));
        // Thêm tiêu đề và ngày tháng vào JPanel
        JLabel titleDialog = new JLabel("Phiếu đặt dịch vụ");
        titleDialog.setForeground(Color.WHITE);
        titleDialog.setFont(new Font("Montserrat", Font.BOLD, 24));

        Box bheader = Box.createHorizontalBox();
        pheader.add(titleDialog);
        bheader.add(pheader);
        bRight.add(bheader);
        bRight.add(Box.createVerticalStrut(50));
        Box bTable2 = Box.createHorizontalBox();
        String[] colName2 = {"Tên dịch vụ","Số lượng", "Thành tiền"};
        Object[][] data2 = {
                {"Gà hấp xả", 2, "400.000"},
                {"Pepsi", 5, "75.000"},
                {"Nước suối", 10, "100.000"},
                {"Massage", 1, "150.000"}
        };
        tableModel2 = new DefaultTableModel(data2, colName2);
        JScrollPane scroll2;
        bTable2.add(scroll2 = new JScrollPane(table2 = new JTable(tableModel2), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        table2.setBackground(new Color(24, 24, 28));
        table2.setForeground(Color.WHITE);
        table2.setFont(FontManager.getManrope(Font.PLAIN, 16));
        table2.setRowHeight(55);

        JTableHeader header2 = table2.getTableHeader();
        header2.setDefaultRenderer(new CustomHeaderRenderer(new Color(38, 38, 42), Color.white));
        header2.setPreferredSize(new Dimension(header1.getPreferredSize().width, 55));
        header2.setReorderingAllowed(false);

        CustomCellRenderer cellRenderer2 = new CustomCellRenderer();
        for (int i = 0; i < table2.getColumnCount(); i++) {
            TableColumn column = table2.getColumnModel().getColumn(i);
            column.setCellRenderer(cellRenderer2);
        }

        scroll2.setBounds(30, 380, 563, 700);
        scroll2.setBorder(null);
        scroll2.getViewport().setOpaque(false);
        scroll2.setViewportBorder(null);
        bRight.add(bTable2);
        bRight.add(Box.createVerticalStrut(300));

        // Tạo nut
        Box bbutton2 = Box.createHorizontalBox();
        bbutton2.add(Box.createHorizontalStrut(200));
        bbutton2.add(btnLamMoi = new JButton("Làm mới"));
        btnLamMoi.setFont(FontManager.getManrope(Font.PLAIN, 16));
        btnLamMoi.setForeground(Color.WHITE);
        btnLamMoi.setBackground(new Color(151, 69, 35, 100));
        btnLamMoi.setOpaque(false);
        btnLamMoi.setPreferredSize(new Dimension(150, 40));
        btnLamMoi.setMinimumSize(new Dimension(150, 40));
        btnLamMoi.setMaximumSize(new Dimension(150, 40));
        btnLamMoi.addActionListener(this);
        bbutton2.add(Box.createHorizontalStrut(20));
        bbutton2.add(btnXacNhan = new JButton("Xác nhận"));
        btnXacNhan.setFont(FontManager.getManrope(Font.PLAIN, 16));
        btnXacNhan.setForeground(Color.WHITE);
        btnXacNhan.setBackground(new Color(51, 70, 50, 100));
        btnXacNhan.setOpaque(false);
        btnXacNhan.setPreferredSize(new Dimension(150, 40));
        btnXacNhan.setMinimumSize(new Dimension(150, 40));
        btnXacNhan.setMaximumSize(new Dimension(150, 40));
        btnXacNhan.addActionListener(this);
        bRight.add(Box.createVerticalStrut(10));
        bRight.add(bbutton2);

        // Thêm Box chính vào JDialog
        bdialog.add(Box.createHorizontalStrut(10));
        bdialog.add(bLeft);
        bdialog.add(Box.createHorizontalStrut(20));
        bdialog.add(bRight);
        dialog.add(bdialog, BorderLayout.CENTER);

        // Đặt JDialog xuất hiện ở giữa màn hình
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setUndecorated(true);
        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
phieuDatDichVu();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
