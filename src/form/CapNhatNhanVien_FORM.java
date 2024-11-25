package form;

import customElements.*;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import entity.NhanVien;
import entity.PhieuDatPhong;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Date;

public class CapNhatNhanVien_FORM extends JPanel implements Openable, ActionListener {
    private final JTextField txtHoTen;
    private final JComboBox<String> cmbChucVu;
    private final JTextField txtHSL, txtSoDT, txtDiaChi, txtLuong, txtEmail;
    private DefaultTableModel tableModel;
    private final JXDatePicker dateNgaySinh, dateNgayVaoLam;
    private JTextField txtSearch;
    private NhanVien_DAO NhanVien_DAO;
    public void open() {
        NhanVien_DAO = new NhanVien_DAO();
        ArrayList<NhanVien> dsNV = NhanVien_DAO.getDSNhanVien();
        loadTableData(dsNV);
//        loadLoaiPhong();
    }
    public CapNhatNhanVien_FORM() {
        setLayout(new BorderLayout());
        setBackground(new Color(16, 16, 20));
        // north
        JPanel northPanel = new JPanel();
        northPanel.setOpaque(false);
        northPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        Box northPanelBox = Box.createVerticalBox();
        // Tim kiem
        txtSearch = new JTextField("Tìm kiếm");
        Border emptyBorder = BorderFactory.createEmptyBorder(13, 52, 12, 0);
        txtSearch.setBounds(0, 0, 280, 45);
        txtSearch.setBorder(emptyBorder);
        txtSearch.setBackground(new Color(40, 40, 44));
        txtSearch.setForeground(new Color(255, 255, 255, 125));
        txtSearch.setFont(FontManager.getManrope(Font.PLAIN, 15));
        CompoundBorder combinedBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(83, 152, 255)), emptyBorder);
        txtSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtSearch.setBorder(combinedBorder);
                if (txtSearch.getText().equals("Tìm kiếm")) {
                    txtSearch.setText("");
                    txtSearch.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                txtSearch.setBorder(emptyBorder);
                if (txtSearch.getText().isEmpty()) {
                    txtSearch.setForeground(new Color(255, 255, 255, 125));
                    txtSearch.setText("Tìm kiếm");
                }
            }
        });
        txtSearch.addActionListener(e -> handleTimKiem());
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
        searchPanel.add(txtSearch);
        Box searchBox = Box.createHorizontalBox();
        searchBox.add(Box.createHorizontalStrut(0));
        searchBox.add(searchPanel);
        searchBox.add(Box.createGlue());


        JPanel form = new JPanel();
        form.setPreferredSize(new Dimension(1400, 350));
        form.setOpaque(false);
        // BoxForm
        Box boxForm1 = createFormBox("Họ tên", txtHoTen = new JTextField());
        Box boxForm2 = createFormBox("Chức vụ", cmbChucVu = new JComboBox<>(new String[]{"Chọn", "Lễ tân", "Quản lý"}));
        Box boxForm3 = createFormBox("Ngày sinh",dateNgaySinh = new JXDatePicker());
        Box boxForm4 = createFormBox("Ngày vào làm",dateNgayVaoLam = new JXDatePicker());
        Box boxForm5 = createFormBox("Số điện thoại", txtSoDT = new JTextField());
        Box boxForm6 = createFormBox("Địa chỉ", txtDiaChi = new JTextField());
        Box boxForm7 = createFormBox("Email", txtEmail = new JTextField());
        Box boxForm8 = createFormBox("Lương cơ bản", txtLuong = new JTextField());
        Box boxForm9 = createFormBox("Hệ số lương", txtHSL = new JTextField());
        txtHSL.setEditable(false);

//        dateTimeNgayDen.addActionListener(e -> handleChonLoaiPhong());
//        dateTimeNgayDi.addActionListener(e -> handleChonLoaiPhong());
//        cmbSoPhong.setEnabled(false);
//        cmbLoaiPhong.addActionListener(e -> handleChonLoaiPhong());
//        cmbSoPhong.addActionListener(e -> updateTenPhong((String) cmbSoPhong.getSelectedItem()));
//        txtCCCD.addActionListener(e -> handleSearchCustomer());
//        txtCCCD.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusLost(FocusEvent e) {
//                handleSearchCustomer();
//            }
//        });

        form.add(boxForm1);
        form.add(boxForm2);
        form.add(boxForm3);
        form.add(boxForm4);
        form.add(boxForm5);
        form.add(boxForm6);
        form.add(boxForm7);
        form.add(boxForm8);
        form.add(boxForm9);

        northPanelBox.add(searchBox);
        northPanelBox.add(Box.createVerticalStrut(10));
        northPanelBox.add(form);
        northPanelBox.setPreferredSize(new Dimension(1642, 380));
        northPanel.add(northPanelBox);

        //center

        JLabel titleLabel = new JLabel("Danh sách nhân viên");
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


        String[] headers = {"Mã nhân viên", "Họ tên", "Chức vụ", "Ngày sinh", "Ngày vào làm", "SDT", "Địa chỉ", "Email", "Lương cơ bản", "Hệ số lương"};
        tableModel = new DefaultTableModel(headers, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        JTable table = new JTable(tableModel);
        table.setBackground(new Color(24, 24, 28));
        table.setForeground(Color.WHITE);
        table.setFont(FontManager.getManrope(Font.PLAIN, 14));
        table.setRowHeight(55);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                if (row >= 0) {
//                    String maNV = (String) tableModel.getValueAt(row, 0);
                    String hoTen = (String) tableModel.getValueAt(row, 1);
                    String chucVu = (String) tableModel.getValueAt(row, 2);
                    Date ngaySinh = (Date) tableModel.getValueAt(row, 3);
                    Date ngayVaoLam = (Date) tableModel.getValueAt(row, 4);
                    String soDT = (String) tableModel.getValueAt(row, 5);
                    String diaChi = (String) tableModel.getValueAt(row, 6);
                    String email = (String) tableModel.getValueAt(row, 7);

                    Double luongCoBan = (Double) tableModel.getValueAt(row, 8);
                    Double heSoLuong = (Double) tableModel.getValueAt(row, 9);

                    txtHoTen.setText(hoTen);
                    cmbChucVu.setSelectedItem(chucVu);
                    dateNgaySinh.setDate(ngaySinh);
                    dateNgayVaoLam.setDate(ngayVaoLam);
                    txtSoDT.setText(soDT);
                    txtDiaChi.setText(diaChi);
                    txtEmail.setText(email);
                    txtLuong.setText(String.valueOf(luongCoBan));
                    txtHSL.setText(String.valueOf(heSoLuong));
                }
            }
        });
        cmbChucVu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chucVu = (String) cmbChucVu.getSelectedItem();
                if (chucVu.equals("Lễ tân")) {
                    txtLuong.setText("5000000");
                    txtHSL.setText("2.5");
                    txtLuong.setEditable(false);
                    txtHSL.setEditable(false);
                } else {
                    txtLuong.setEditable(true);
                    txtHSL.setEditable(true);
                    txtLuong.setText("");
                    txtHSL.setText("");
                }
            }
        });
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new CustomHeaderRenderer(new Color(38, 38, 42), Color.white));
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 55));
        header.setReorderingAllowed(false);

        CustomCellRenderer cellRenderer = new CustomCellRenderer();
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setCellRenderer(cellRenderer);
        }

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(null);
        scroll.setPreferredSize(new Dimension(1642, 330));
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);


        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.add(titlePanel);
        centerPanel.add(scroll);

        //southPanel
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.setOpaque(false);
        JButton addButton = createButton("Thêm", new Color(10, 123, 66));
        JButton deleteButton = createButton("Xóa", new Color(255, 117, 142));
        JButton updateButton = createButton("Sửa", new Color(151, 114, 35));
        JButton refreshButton = createButton("Làm mới", new Color(89, 38, 136, 242));
        addButton.addActionListener(e -> addNV());
        refreshButton.addActionListener(e -> clearInputFields());

        southPanel.add(addButton);
        southPanel.add(deleteButton);
        southPanel.add(updateButton);
        southPanel.add(refreshButton);

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

    }
    private void handleTimKiem() {
        String tuKhoa = txtSearch.getText().trim().toLowerCase();
        NhanVien_DAO = new NhanVien_DAO();
        ArrayList<NhanVien> dsNV = NhanVien_DAO.getDSNhanVien();
        if (tuKhoa.equals("tìm kiếm") || tuKhoa.isEmpty()) {
            loadTableData(dsNV);
            return;
        }
        ArrayList<NhanVien> ketQuaTimKiem = new ArrayList<>();
        KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
        khachHang_DAO.getDSKhachHang();

        for (NhanVien nv : dsNV) {
            String maNV = nv.getMaNV().toLowerCase();
            String tenNhanVien = String.valueOf(khachHang_DAO.timKiem(nv.getHoTen()));
            String soDT = nv.getSoDT().toLowerCase();

            if (maNV.contains(tuKhoa) || tenNhanVien.contains(tuKhoa) || soDT.contains(tuKhoa)) {
                ketQuaTimKiem.add(nv);
            }
        }

        loadTableData(ketQuaTimKiem);
    }
    private void loadTableData(ArrayList<NhanVien> danhSach){
        tableModel.setRowCount(0);
        NhanVien_DAO = new NhanVien_DAO();
        NhanVien_DAO.getDSNhanVien();
        for (NhanVien nv : danhSach) {
            tableModel.addRow(new Object[]{
                    nv.getMaNV(),
                    nv.getHoTen(),
                    nv.getChucVu(),
                    nv.getNgaySinh(),
                    nv.getNgayVaoLam(),
                    nv.getSoDT(),
                    nv.getDiaChi(),
                    nv.getEmail(),
                    nv.getLuongCoBan(),
                    nv.getHeSoLuong()
            });
        }
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
    private JButton createButton(String buttonLabel, Color buttonColor) {
        JButton button = new JButton(buttonLabel);
        button.setBackground(buttonColor);
        button.setForeground(Color.white);
        button.setPreferredSize(new Dimension(259, 45));
        button.setFont(FontManager.getManrope(Font.PLAIN, 16));
        return button;
    }
    private void addNV() {
        // Lấy thông tin từ các JTextField và JComboBox
        String hoTen = txtHoTen.getText().trim();
        String chucVu = (String) cmbChucVu.getSelectedItem();
        Date ngaySinh = dateNgaySinh.getDate();
        Date ngayVaoLam = dateNgayVaoLam.getDate();
        String soDT = txtSoDT.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String email = txtEmail.getText().trim();
        String luongCoBanStr = txtLuong.getText().trim();
        String heSoLuongStr = txtHSL.getText().trim();

        if (ngaySinh == null || !is18YearsOld(ngaySinh)) {
            JOptionPane.showMessageDialog(this, "Nhân viên phải đủ 18 tuổi.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (ngayVaoLam == null || !ngayVaoLam.after(ngaySinh)) {
            JOptionPane.showMessageDialog(this, "Ngày vào làm phải lớn hơn ngày sinh.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String maNV = generateMaNV(ngayVaoLam);

        if (chucVu.equals("Lễ tân")) {
            txtLuong.setText("5000000");
            txtHSL.setText("2.5");
            txtLuong.setEditable(false);
            txtHSL.setEditable(false);
        } else {
            if (luongCoBanStr.isEmpty() || heSoLuongStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập lương cơ bản và hệ số lương.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        NhanVien nhanVien = new NhanVien(maNV, hoTen, chucVu, soDT, diaChi, email, ngaySinh, ngayVaoLam, Double.parseDouble(luongCoBanStr), Double.parseDouble(heSoLuongStr));
        NhanVien_DAO.themNV(nhanVien);
        addToTableModel(nhanVien);
        JOptionPane.showMessageDialog(this, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        loadTableData(NhanVien_DAO.getDSNhanVien());
        clearInputFields();
        ArrayList<NhanVien> dsNV = NhanVien_DAO.getDSNhanVien();
        loadTableData(dsNV);
    }

    private boolean is18YearsOld(Date birthDate) {
        Date today = new Date();
        long ageInMillis = today.getTime() - birthDate.getTime();
        long ageInYears = ageInMillis / (1000L * 60 * 60 * 24 * 365);
        return ageInYears >= 18;
    }

    // Tạo mã nhân viên
    private String generateMaNV(Date ngayVaoLam) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(ngayVaoLam);
        int year = cal.get(java.util.Calendar.YEAR);

        String randomChars = String.format("%04d", (int) (Math.random() * 10000));

        return String.valueOf(year).substring(2) + randomChars;
    }

    private void addToTableModel(NhanVien nhanVien) {
        tableModel.addRow(new Object[]{
                nhanVien.getMaNV(),
                nhanVien.getHoTen(),
                nhanVien.getChucVu(),
                nhanVien.getSoDT(),
                nhanVien.getDiaChi(),
                nhanVien.getEmail(),
                nhanVien.getNgaySinh(),
                nhanVien.getNgayVaoLam(),
                nhanVien.getLuongCoBan(),
                nhanVien.getHeSoLuong()
        });
    }
    private void clearInputFields() {
        txtHoTen.setText("");
        cmbChucVu.setSelectedIndex(0); // Chọn lại mục "Chọn"
        dateNgaySinh.setDate(null);
        dateNgayVaoLam.setDate(null);
        txtSoDT.setText("");
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtLuong.setText("");
        txtHSL.setText("");
        txtLuong.setEditable(true);
        txtHSL.setEditable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
