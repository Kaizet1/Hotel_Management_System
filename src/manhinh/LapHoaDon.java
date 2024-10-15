package manhinh;

import customelement.CustomCellRenderer;
import customelement.CustomHeaderRenderer;
import customelement.FontManager;
import customelement.RoundedPanel;

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
import javax.swing.table.TableColumn;


public class LapHoaDon extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextField txttimkiem;
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton btnlaphd;
    private JLabel day;
    private DefaultTableModel tableModel1;
    private JTable table1;
    private JLabel lbhoten1;
    private JLabel lbsophong;
    private JLabel lbsophong1;
    private JLabel lbsdt;
    private JLabel lbsdt1;
    private JLabel lbcccd;
    private JLabel lbcccd1;
    private JLabel lbngaynhan;
    private JLabel lbngaynhan1;
    private JLabel lbngaytra;
    private JLabel lbngaytra1;
    private JLabel lbdiachi;
    private JLabel lbdiachi1;
    private JLabel lbmasothue;
    private JLabel lbmasothue1;
    private Font f;
    private Font f1;


    public LapHoaDon() {
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
        bbutton.add(btnlaphd = new JButton("Lập hóa đơn"));
        btnlaphd.setFont(FontManager.getManrope(Font.PLAIN, 16));
        btnlaphd.setForeground(Color.WHITE);
        btnlaphd.setBackground(new Color(66, 99, 235));
        btnlaphd.setOpaque(false);
        btnlaphd.setPreferredSize(new Dimension(200, 50));
        btnlaphd.setMinimumSize(new Dimension(200, 50));
        btnlaphd.setMaximumSize(new Dimension(200, 50));
        btnlaphd.addActionListener(this);
        b.add(bbutton);
        add(b, BorderLayout.CENTER);

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
        titleDialog.setFont(new Font("Montserrat", Font.BOLD, 32));

        JLabel day = new JLabel("Ngày 27 tháng 09 năm 2024");
        day.setForeground(Color.WHITE);
        day.setFont(new Font("Montserrat", Font.PLAIN, 16));
        Box bheader = Box.createHorizontalBox();
        pheader.add(titleDialog);
        pheader.add(day);
        bheader.add(pheader);
        bdialog.add(bheader);
        bdialog.add(Box.createVerticalStrut(10)); // Khoảng cách giữa header và các thành phần khác

        // Tạo JPanel cho thông tin khách hàng
        JPanel customerInfoPanel = new JPanel();
        customerInfoPanel.setForeground(Color.WHITE);
        customerInfoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        customerInfoPanel.setBackground(new Color(40, 40, 44));
        customerInfoPanel.setBorder(BorderFactory.createTitledBorder(""));
        customerInfoPanel.setPreferredSize(new Dimension(700, 200));
        customerInfoPanel.setMinimumSize(new Dimension(700, 200));
        customerInfoPanel.setMaximumSize(new Dimension(700, 200));
        // Thêm các thông tin khách hàng
        Box bcustomerInfo = Box.createVerticalBox();
        //hoten
        Box bhoten = Box.createHorizontalBox();
        JLabel lbhoten;
        bhoten.add(lbhoten = new JLabel("Họ tên khách hàng:"));
        bhoten.add(lbhoten1 = new JLabel("Cao Thành Đông"));
        bhoten.setAlignmentX(LEFT_ALIGNMENT);
        bhoten.add(Box.createHorizontalStrut(200));
        bhoten.add(lbsophong = new JLabel("Số phòng:"));
        bhoten.add(lbsophong1 = new JLabel("A001"));
        bcustomerInfo.add(bhoten);
        bcustomerInfo.add(Box.createVerticalStrut(15));
        //sdt
        Box bsodt = Box.createHorizontalBox();
        bsodt.add(lbsdt = new JLabel("Số điện thoại:"));
        bsodt.add(lbsdt1 = new JLabel("0909123456"));
        bsodt.add(Box.createHorizontalStrut(270));
        bsodt.add(lbcccd = new JLabel("Số CCCD:"));
        bsodt.add(lbcccd1 = new JLabel("01524001238"));
        bsodt.setAlignmentX(LEFT_ALIGNMENT);
        bcustomerInfo.add(bsodt);
        bcustomerInfo.add(Box.createVerticalStrut(15));
        //ngay
        Box bngay = Box.createHorizontalBox();
        bngay.add(lbngaynhan = new JLabel("Ngày nhân phòng:"));
        bngay.add(lbngaynhan1 = new JLabel("25/09/2024"));
        bngay.add(Box.createHorizontalStrut(243));
        bngay.add(lbngaytra = new JLabel("Ngày trả phòng:"));
        bngay.add(lbngaytra1 = new JLabel("27/09/2024"));
        bngay.setAlignmentX(LEFT_ALIGNMENT);
        bcustomerInfo.add(bngay);
        bcustomerInfo.add(Box.createVerticalStrut(15));
        //diachi
        Box bdiachi = Box.createHorizontalBox();
        bdiachi.add(lbdiachi = new JLabel("Địa chỉ:"));
        bdiachi.add(lbdiachi1 = new JLabel("480/16, Hàng Ngang, Hà Nội"));
        bdiachi.setAlignmentX(LEFT_ALIGNMENT);
        bcustomerInfo.add(bdiachi);
        bcustomerInfo.add(Box.createVerticalStrut(15));
        //masothue
        Box bmasothue = Box.createHorizontalBox();
        bmasothue.add(lbmasothue = new JLabel("Mã số thuế:"));
        bmasothue.add(lbmasothue1 = new JLabel("0101010123456"));
        bmasothue.setAlignmentX(LEFT_ALIGNMENT);
        bcustomerInfo.add(bmasothue);

        f = new Font("Montserrat", Font.BOLD, 16);
        f1 = new Font("Montserrat", Font.PLAIN, 16);
        lbhoten.setForeground(Color.WHITE);
        lbhoten.setFont(f);
        lbhoten1.setForeground(Color.WHITE);
        lbhoten1.setFont(f1);
        lbsophong1.setForeground(Color.WHITE);
        lbsophong1.setFont(f1);
        lbsophong.setForeground(Color.WHITE);
        lbsophong.setFont(f);
        lbsdt1.setForeground(Color.WHITE);
        lbsdt1.setFont(f1);
        lbsdt.setForeground(Color.WHITE);
        lbsdt.setFont(f);
        lbcccd1.setForeground(Color.WHITE);
        lbcccd1.setFont(f1);
        lbcccd.setForeground(Color.WHITE);
        lbcccd.setFont(f);
        lbngaynhan1.setForeground(Color.WHITE);
        lbngaynhan1.setFont(f1);
        lbngaynhan.setForeground(Color.WHITE);
        lbngaynhan.setFont(f);
        lbngaytra.setForeground(Color.WHITE);
        lbngaytra.setFont(f);
        lbngaytra1.setForeground(Color.WHITE);
        lbngaytra1.setFont(f1);
        lbdiachi.setForeground(Color.WHITE);
        lbdiachi.setFont(f);
        lbdiachi1.setForeground(Color.WHITE);
        lbdiachi1.setFont(f1);
        lbmasothue.setForeground(Color.WHITE);
        lbmasothue.setFont(f);
        lbmasothue1.setForeground(Color.WHITE);
        lbmasothue1.setFont(f1);

        bcustomerInfo.add(Box.createHorizontalStrut(10));

        Box CusInfo = Box.createHorizontalBox();
        CusInfo.add(Box.createHorizontalStrut(0));
        CusInfo.add(bcustomerInfo);
        CusInfo.add(Box.createHorizontalGlue());
        customerInfoPanel.add(CusInfo);

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
        bdialog.add(scroll1 = new JScrollPane(table1 = new JTable(tableModel1), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        table1.setRowHeight(55);
        table1.setBackground(new Color(40, 40, 44));
        table1.setForeground(Color.WHITE);
        table1.setFont(FontManager.getManrope(Font.PLAIN, 15));

        JTableHeader header1 = table1.getTableHeader();
        header1.setDefaultRenderer(new CustomHeaderRenderer(new Color(88, 84, 92), Color.white));
        header1.setPreferredSize(new Dimension(header1.getPreferredSize().width, 55));
        header1.setFont(FontManager.getManrope(Font.PLAIN, 15));
        header1.setReorderingAllowed(false);

        scroll1.setPreferredSize(new Dimension(700, 400));
        scroll1.setMinimumSize(new Dimension(700, 400));
        scroll1.setMaximumSize(new Dimension(700, 400));
        scroll1.setBorder(BorderFactory.createEmptyBorder(9, 9, 9, 9));
        scroll1.getViewport().setOpaque(false);
        scroll1.setViewportBorder(null);
        // Tổng tiền
        Box btotal = Box.createHorizontalBox();
        JLabel totalLabel = new JLabel("Tổng tiền: 905.000đ", SwingConstants.RIGHT);
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setFont(new Font("Montserrat", Font.PLAIN, 16));
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
        btnXacNhan.setPreferredSize(new Dimension(150, 40));
        btnXacNhan.setBackground(new Color(74, 74, 66));
        btnXacNhan.setForeground(Color.WHITE);
        btnXacNhan.setFont(f1);

        JButton btnXuatHD = new JButton("Xuất hóa đơn");
        btnXuatHD.setPreferredSize(new Dimension(150, 40));
        btnXuatHD.setBackground(new Color(51, 70, 50));
        btnXuatHD.setForeground(Color.WHITE);
        btnXuatHD.setFont(f1);

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
