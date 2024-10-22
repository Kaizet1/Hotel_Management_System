package form;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import customElements.*;
import dao.KhachHang_DAO;
import dao.LoaiPhong_DAO;
import dao.Phong_DAO;
import entity.KhachHang;
import entity.LoaiPhong;
import org.jdesktop.swingx.JXDatePicker;

public class DatPhong_FORM extends JPanel {
    private JTextField txtNgayDat;
    private int currentPage = 1;
    private final int rowsPerPage = 5;
    DefaultTableModel tableModel;
    Object[][] data = {{"DP001", "101", "Nguyen Van A", "2023-10-01", "2023-10-03", 2, 2000000},
            {"DP002", "102", "Tran Thi B", "2023-10-02", "2023-10-04", 2, 2200000},
            {"DP003", "103", "Le Van C", "2023-10-01", "2023-10-02", 1, 1100000},
            {"DP004", "104", "Pham Thi D", "2023-10-03", "2023-10-06", 3, 3300000},
            {"DP005", "105", "Doan Van E", "2023-10-05", "2023-10-07", 2, 2000000},
            {"DP006", "106", "Hoang Thi F", "2023-10-01", "2023-10-05", 4, 4000000},
            {"DP007", "107", "Nguyen Van G", "2023-10-06", "2023-10-08", 2, 2100000},
            {"DP008", "108", "Le Thi H", "2023-10-04", "2023-10-06", 2, 2300000},
            {"DP009", "109", "Pham Van I", "2023-10-07", "2023-10-09", 2, 2400000},
            {"DP010", "110", "Tran Thi J", "2023-10-08", "2023-10-11", 3, 3500000},
            {"DP008", "108", "Le Thi H", "2023-10-04", "2023-10-06", 2, 2300000},
            {"DP009", "109", "Pham Van I", "2023-10-07", "2023-10-09", 2, 2400000},
            {"DP010", "110", "Tran Thi J", "2023-10-08", "2023-10-11", 3, 3500000},};
    private JLabel pageNumber;
    DateTimePicker dateTimeNgayDen, dateTimeNgayDi;
    JXDatePicker dateNgayDat, dateNgaySinh;
    JComboBox<String> cmbLoaiPhong, cmbSoPhong;
    JTextField txtSDT, txtTenKhachHang, txtEmail, txtCCCD, txtTenPhong, txtSoKhach;

    public DatPhong_FORM() {
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
        Box boxForm1 = createFormBox("Ngày đặt", dateNgayDat = new JXDatePicker());
        Box boxForm2 = createFormBox("Ngày đến", dateTimeNgayDen = new DateTimePicker());
        Box boxForm3 = createFormBox("Ngày đi", dateTimeNgayDi = new DateTimePicker());
        Box boxForm4 = createFormBox("Số điện thoại", txtSDT = new JTextField());
        Box boxForm5 = createFormBox("Ngày sinh", dateNgaySinh = new JXDatePicker());
        Box boxForm6 = createFormBox("Tên khách hàng", txtTenKhachHang = new JTextField());
        Box boxForm7 = createFormBox("Email", txtEmail = new JTextField());
        Box boxForm8 = createFormBox("CCCD", txtCCCD = new JTextField());
        Box boxForm9 = createFormBox("Loại phòng", cmbLoaiPhong = new JComboBox<>(new String[]{"Chọn"}));
        Box boxForm10 = createFormBox("Số phòng", cmbSoPhong = new JComboBox<>());
        Box boxForm11 = createFormBox("Tên phòng", txtTenPhong = new JTextField());
        Box boxForm12 = createFormBox("Số khách", txtSoKhach = new JTextField());
        dateTimeNgayDen.addActionListener(e -> handleChonLoaiPhong());
        dateTimeNgayDi.addActionListener(e -> handleChonLoaiPhong());
        cmbSoPhong.setEnabled(false);
        cmbLoaiPhong.addActionListener(e -> handleChonLoaiPhong());
        cmbSoPhong.addActionListener(e -> updateTenPhong((String) cmbSoPhong.getSelectedItem()));
        txtSDT.addActionListener(e -> handleSearchCustomer());
        txtSDT.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                handleSearchCustomer();
            }
        });

        txtTenPhong.setEditable(false);
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
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(null);
        scroll.setPreferredSize(new Dimension(1642, 330));
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);


        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.add(titlePanel);
        centerPanel.add(scroll);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.setOpaque(false);
        JButton submitButton = createButton("Xác nhận", new Color(66, 99, 235));
        JButton refreshButton = createButton("Làm mới", new Color(151, 69, 35));
        southPanel.add(submitButton);
        southPanel.add(refreshButton);
        add(northPanel, BorderLayout.NORTH);
//		add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> handleSubmit());
        refreshButton.addActionListener(e -> handleRefresh());

        loadLoaiPhong();
    }

    private void handleChonLoaiPhong() {
        String selectedLoaiPhong = (String) cmbLoaiPhong.getSelectedItem();
        if (!selectedLoaiPhong.equals("Chọn")) {
            updateSoPhongList(selectedLoaiPhong);
            cmbSoPhong.setEnabled(true);
        } else {
            txtTenPhong.setText("");
            cmbSoPhong.removeAllItems();
            cmbSoPhong.setEnabled(false);
        }
    }

    private void updateSoPhongList(String loaiPhong) {
        // Lấy thời gian đến và đi
        Date ngayDen = dateTimeNgayDen.getDate();
        Date ngayDi = dateTimeNgayDi.getDate();

        // Làm sạch danh sách số phòng trước
        cmbSoPhong.removeAllItems();
        cmbSoPhong.addItem("Chọn");

        // Lấy danh sách số phòng tương ứng với loại phòng
        Phong_DAO phongDAO = new Phong_DAO();
        ArrayList<String> dsSoPhong = phongDAO.getSoPhongByLoaiPhong(loaiPhong);

        // Lấy danh sách các số phòng đã được đặt trong khoảng thời gian này
        ArrayList<String> dsSoPhongDaDat = phongDAO.getSoPhongDaDat(ngayDen, ngayDi);

        // Chỉ thêm những số phòng chưa được đặt
        for (String soPhong : dsSoPhong) {
            if (!dsSoPhongDaDat.contains(soPhong)) {
                cmbSoPhong.addItem(soPhong);
            }
        }
    }

//    private void updateSoPhongList(String loaiPhong) {
//        // Làm sạch danh sách số phòng trước
//        cmbSoPhong.removeAllItems();
//        cmbSoPhong.addItem("Chọn");
//        // Thêm các số phòng tương ứng với loại phòng
//        // Giả sử bạn có một phương thức để lấy số phòng theo loại phòng
//        Phong_DAO phongDAO = new Phong_DAO();
//        ArrayList<String> dsSoPhong = phongDAO.getSoPhongByLoaiPhong(loaiPhong);
//
//        for (String soPhong : dsSoPhong) {
//            cmbSoPhong.addItem(soPhong);
//        }
//    }

    private void updateTenPhong(String soPhong) {
        Phong_DAO phongDAO = new Phong_DAO();
        String tenPhong = phongDAO.getTenPhongBySoPhong(soPhong);
        txtTenPhong.setText(tenPhong);
    }

    private void handleSearchCustomer() {
        String sdt = txtSDT.getText().trim();

        // Kiểm tra nếu số điện thoại không trống
        if (!sdt.isEmpty()) {
            KhachHang_DAO khachHangDAO = new KhachHang_DAO();
            KhachHang khachHang = khachHangDAO.searchKhachHangBangSDT(sdt);

            // Nếu tìm thấy khách hàng, cập nhật thông tin
            if (khachHang != null) {
                txtTenKhachHang.setText(khachHang.getHoTen());
//				txtNgaySinh.setText(khachHang.getNgaySinh()); // Đảm bảo rằng định dạng ngày là đúng

                txtEmail.setText(khachHang.getEmail());
                txtCCCD.setText(khachHang.getcCCD());
            }
        }
    }

    private void loadLoaiPhong() {
        LoaiPhong_DAO loaiPhongDAO = new LoaiPhong_DAO();
        ArrayList<LoaiPhong> dsLoaiPhong = loaiPhongDAO.getDSLoaiPhong();

        for (LoaiPhong loaiPhong : dsLoaiPhong) {
            cmbLoaiPhong.addItem(loaiPhong.getTenLoaiPhong());
        }

    }

    private JButton createButton(String buttonLabel, Color buttonColor) {
        JButton button = new JButton(buttonLabel);
        button.setBackground(buttonColor);
        button.setForeground(Color.white);
        button.setPreferredSize(new Dimension(259, 45));
        button.setFont(FontManager.getManrope(Font.PLAIN, 16));
        return button;
    }

    private Box createFormBox(String label, JTextField txt) {
        Box b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(331, 106));
        JLabel lbl = new JLabel(label);
        lbl.setFont(FontManager.getManrope(Font.PLAIN, 15));
        lbl.setForeground(Color.WHITE);
        lbl.setPreferredSize(new Dimension(259, 20));
        lbl.setMaximumSize(new Dimension(259, 20));
        lbl.setMinimumSize(new Dimension(259, 20));
        txt.setBackground(new Color(40, 40, 44));
        txt.setForeground(Color.white);
        txt.setFont(FontManager.getManrope(Font.PLAIN, 14));
        Border emptyBorder = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        CompoundBorder combinedBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(83, 152, 255)), emptyBorder);
        txt.setBorder(emptyBorder);

        txt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txt.setBorder(combinedBorder);
            }

            @Override
            public void focusLost(FocusEvent e) {
                txt.setBorder(emptyBorder);
            }
        });
        b.add(lbl);
        b.add(Box.createVerticalStrut(6));
        b.add(txt);
        b.setBorder(BorderFactory.createEmptyBorder(0, 36, 35, 36));
        return b;
    }

    private Box createFormBox(String label, JComboBox<String> cmb) {
        Box b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(331, 106));
        Box lblBox = Box.createHorizontalBox();
        lblBox.setPreferredSize(new Dimension(331, 20));
        lblBox.setMaximumSize(new Dimension(331, 20));
        lblBox.setMinimumSize(new Dimension(331, 20));
        JLabel lbl = new JLabel(label);
        lbl.setFont(FontManager.getManrope(Font.PLAIN, 15));
        lbl.setForeground(Color.WHITE);
        lbl.setPreferredSize(new Dimension(259, 20));
        lbl.setMaximumSize(new Dimension(259, 20));
        lbl.setMinimumSize(new Dimension(259, 20));
        cmb.setFont(FontManager.getManrope(Font.PLAIN, 14));
        Border emptyBorder = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        cmb.setBorder(emptyBorder);

        lblBox.add(Box.createHorizontalStrut(5));
        lblBox.add(lbl);
        lblBox.add(Box.createHorizontalGlue());
        b.add(lblBox);
        b.add(Box.createVerticalStrut(6));
        b.add(cmb);
        b.setBorder(BorderFactory.createEmptyBorder(0, 36, 35, 36));
        return b;
    }

    private Box createFormBox(String label, DateTimePicker dateTime) {
        Date date = new Date();
        Box b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(335, 106));
        Box lblBox = Box.createHorizontalBox();
        lblBox.setPreferredSize(new Dimension(335, 20));
        lblBox.setMaximumSize(new Dimension(335, 20));
        lblBox.setMinimumSize(new Dimension(335, 20));
        JLabel lbl = new JLabel(label);
        lbl.setFont(FontManager.getManrope(Font.PLAIN, 15));
        lbl.setForeground(Color.WHITE);
        dateTime.setPreferredSize(new Dimension(331, 45));
        dateTime.setMaximumSize(new Dimension(331, 45));
        dateTime.setMinimumSize(new Dimension(331, 45));
        dateTime.setFont(FontManager.getManrope(Font.PLAIN, 14));
        Border emptyBorder = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        dateTime.setBorder(emptyBorder);
        dateTime.setFormats(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM));
        dateTime.setTimeFormat(DateFormat.getTimeInstance(DateFormat.MEDIUM));

        dateTime.setDate(date);

        lblBox.add(Box.createHorizontalStrut(10));
        lblBox.add(lbl);
        lblBox.add(Box.createHorizontalGlue());
        b.add(lblBox);
        b.add(Box.createVerticalStrut(6));
        b.add(dateTime);
        b.setBorder(BorderFactory.createEmptyBorder(0, 36, 35, 36));
        return b;
    }

    private Box createFormBox(String label, JXDatePicker datePicker) {
        Date date = new Date();
        Box b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(335, 106));
        Box lblBox = Box.createHorizontalBox();
        lblBox.setPreferredSize(new Dimension(335, 20));
        lblBox.setMaximumSize(new Dimension(335, 20));
        lblBox.setMinimumSize(new Dimension(335, 20));
        JLabel lbl = new JLabel(label);
        lbl.setFont(FontManager.getManrope(Font.PLAIN, 15));
        lbl.setForeground(Color.WHITE);
        datePicker.setPreferredSize(new Dimension(331, 45));
        datePicker.setMaximumSize(new Dimension(331, 45));
        datePicker.setMinimumSize(new Dimension(331, 45));
        datePicker.setFont(FontManager.getManrope(Font.PLAIN, 14));
        Border emptyBorder = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        datePicker.setBorder(emptyBorder);
        datePicker.setFormats("MM/dd/yyyy");
        datePicker.setDate(date);
        JFormattedTextField dateTextField = datePicker.getEditor();
        dateTextField.setBackground(new Color(40, 40, 44));
        dateTextField.setForeground(Color.white);

        lblBox.add(Box.createHorizontalStrut(10));
        lblBox.add(lbl);
        lblBox.add(Box.createHorizontalGlue());
        b.add(lblBox);
        b.add(Box.createVerticalStrut(6));
        b.add(datePicker);
        b.setBorder(BorderFactory.createEmptyBorder(0, 36, 35, 36));
        return b;
    }


    private void handleSubmit() {
        // Lấy giá trị từ các trường nhập liệu
        Date ngayDat = dateNgayDat.getDate();
        Date ngayDen = dateTimeNgayDen.getDate();
        Date ngayDi = dateTimeNgayDi.getDate();
        String sdt = txtSDT.getText();
        Date ngaySinh = dateNgaySinh.getDate();
        String tenKhachHang = txtTenKhachHang.getText();
        String email = txtEmail.getText();
        String cccd = txtCCCD.getText();
        String loaiPhong = (String) cmbLoaiPhong.getSelectedItem();
        String soPhong = (String) cmbSoPhong.getSelectedItem();
        String tenPhong = txtTenPhong.getText();
        String soKhach = txtSoKhach.getText();

        // In ra thông tin
        System.out.println("Ngày đặt: " + ngayDat);
        System.out.println("Ngày đến: " + ngayDen);
        System.out.println("Ngày đi: " + ngayDi);
        System.out.println("Số điện thoại: " + sdt);
        System.out.println("Ngày sinh: " + ngaySinh);
        System.out.println("Tên khách hàng: " + tenKhachHang);
        System.out.println("Email: " + email);
        System.out.println("CCCD: " + cccd);
        System.out.println("Loại phòng: " + loaiPhong);
        System.out.println("Số phòng: " + soPhong);
        System.out.println("Tên phòng: " + tenPhong);
        System.out.println("Số khách: " + soKhach);
    }

    // Phương thức xử lý sự kiện refresh
    private void handleRefresh() {
        // Làm sạch tất cả các trường nhập liệu
        dateNgayDat.setDate(new Date()); // Đặt lại ngày đặt về hiện tại
        dateTimeNgayDen.setDate(new Date()); // Đặt lại ngày đến về hiện tại
        dateTimeNgayDi.setDate(new Date()); // Đặt lại ngày đi về hiện tại
        dateNgaySinh.setDate(new Date());
        txtSDT.setText("");
        txtTenKhachHang.setText("");
        txtEmail.setText("");
        txtCCCD.setText("");
        cmbLoaiPhong.setSelectedIndex(0); // Đặt lại chỉ số về mặc định
        cmbSoPhong.setSelectedIndex(0); // Đặt lại chỉ số về mặc định
        txtTenPhong.setText("");
        txtSoKhach.setText("");
    }
}
