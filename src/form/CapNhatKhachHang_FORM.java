package form;

import customElements.*;
import dao.KhachHang_DAO;
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

public class CapNhatKhachHang_FORM extends JPanel  implements ActionListener,MouseListener {
    private DefaultTableModel tableModel;
    private JTable table;
    private KhachHang_DAO khachHangDAO;
    private JTextField txtTenKhachHang, txtDiaChi, txtSDT, txtCCCD, txtEmail;
    private JComboBox<String> cmbLoaiPhong, cmbTrangThai;
    public CapNhatKhachHang_FORM() {
        khachHangDAO = new KhachHang_DAO();
        setBackground(new Color(16, 16, 20));
        Box mainBox = Box.createVerticalBox();
        mainBox.add(Box.createVerticalStrut(10));
        // Tim kiem
        JTextField txtSearch = new JTextField("Tìm kiếm tên khách hàng");
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
                if (txtSearch.getText().equals("Tìm kiếm tên khách hàng")) {
                    txtSearch.setText("");
                    txtSearch.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                txtSearch.setBorder(emptyBorder);
                if (txtSearch.getText().isEmpty()) {
                    txtSearch.setForeground(new Color(255, 255, 255, 125));
                    txtSearch.setText("Tìm kiếm tên khách hàng");
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
        searchPanel.add(txtSearch);
        Box searchBox = Box.createHorizontalBox();
        searchBox.add(Box.createHorizontalStrut(0));
        searchBox.add(searchPanel);
        searchBox.add(Box.createGlue());


        // Form
        Box b1 = Box.createVerticalBox();
        Box b2 = Box.createHorizontalBox();
        b2.add(createFormBox("Tên khách hàng", txtTenKhachHang = new JTextField()));
        b2.add(createFormBox("Địa chỉ", txtDiaChi = new JTextField()));
        b2.add(createFormBox("Số điện thoại", txtSDT = new JTextField()));
        b2.add(createFormBox("Email", txtEmail = new JTextField()));
        b2.add(createFormBox("CCCD", txtCCCD = new JTextField()));

        Dimension b2Size = new Dimension(1642, 100);
        b2.setPreferredSize(b2Size);
        b2.setMinimumSize(b2Size);
        b2.setMaximumSize(b2Size);


        Box b3 = Box.createHorizontalBox();
        Box b4 = Box.createHorizontalBox();

        RoundedButton btnSua = createHandleButton("Sửa");
        RoundedButton btnXoa = createHandleButton("Xóa");
        RoundedButton btnLamMoi = createHandleButton("Làm mới");

        b4.add(Box.createHorizontalGlue());
        b4.add(btnSua);
        b4.add(Box.createHorizontalStrut(72));
        b4.add(btnXoa);
        b4.add(Box.createHorizontalStrut(72));
        b4.add(btnLamMoi);
        b4.add(Box.createHorizontalStrut(55));


        b3.add(b4);


        b1.add(b2);
        b1.add(b3);


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


        // Tạo bang
        Box b6 = Box.createHorizontalBox();
        String[] colName = {"Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại", "Email", "CCCD"};
        tableModel = new DefaultTableModel(colName, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane scroll;
        b6.add(scroll = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
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


        mainBox.add(searchBox);
        mainBox.add(Box.createVerticalStrut(20));
        mainBox.add(b1);
        mainBox.add(Box.createVerticalStrut(20));
        mainBox.add(titlePanel);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(b6);
        add(mainBox);

        loadTableData();
        table.addMouseListener(this);
        btnXoa.addActionListener(this);
        btnSua.addActionListener(this);
        btnLamMoi.addActionListener(this);
    }


    private Box createFormBox(String label, JTextField txt) {
        Box b = Box.createVerticalBox();
        Dimension boxSize = new Dimension(332, 110);
        b.setPreferredSize(boxSize);
        b.setMaximumSize(boxSize);
        b.setMaximumSize(boxSize);

        JLabel lbl = new JLabel(label);
        lbl.setFont(FontManager.getManrope(Font.PLAIN, 15));
        lbl.setForeground(Color.WHITE);

        Box lblBox = Box.createHorizontalBox();
        lblBox.setPreferredSize(new Dimension(255, 20));
        lblBox.setMaximumSize(new Dimension(255, 20));
        lblBox.setMinimumSize(new Dimension(255, 20));
        lblBox.add(lbl);

        txt.setBackground(new Color(40, 40, 44));
        Dimension txtFieldSize = new Dimension(260, 45);
        txt.setPreferredSize(txtFieldSize);
        txt.setMaximumSize(txtFieldSize);
        txt.setMinimumSize(txtFieldSize);
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

        b.add(lblBox);
        b.add(Box.createVerticalStrut(6));
        b.add(txt);
        b.setBorder(BorderFactory.createEmptyBorder(0, 0, 35, 72));

        return b;
    }

    private Box createFormBox(String label, JComboBox<String> cmb) {
        Box b = Box.createVerticalBox();
        Dimension boxSize = new Dimension(332, 110);
        b.setPreferredSize(boxSize);
        b.setMaximumSize(boxSize);
        b.setMaximumSize(boxSize);

        JLabel lbl = new JLabel(label);
        lbl.setFont(FontManager.getManrope(Font.PLAIN, 15));
        lbl.setForeground(Color.WHITE);

        Box lblBox = Box.createHorizontalBox();
        lblBox.setPreferredSize(new Dimension(255, 20));
        lblBox.setMaximumSize(new Dimension(255, 20));
        lblBox.setMinimumSize(new Dimension(255, 20));
        lblBox.add(lbl);

        cmb.setBackground(new Color(40, 40, 44));
        Dimension txtFieldSize = new Dimension(260, 45);
        cmb.setPreferredSize(txtFieldSize);
        cmb.setMaximumSize(txtFieldSize);
        cmb.setMinimumSize(txtFieldSize);
        cmb.setFont(FontManager.getManrope(Font.PLAIN, 14));
        cmb.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        b.add(lblBox);
        b.add(Box.createVerticalStrut(6));
        b.add(cmb);
        b.setBorder(BorderFactory.createEmptyBorder(0, 0, 35, 72));

        return b;
    }

    private RoundedButton createHandleButton(String buttonLabel) {
        RoundedButton button = new RoundedButton(buttonLabel, 5);
        Dimension buttonSize = new Dimension(259, 45);
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);
        button.setMinimumSize(buttonSize);
        Color defaultBackground = new Color(80, 80, 88);
        Color hoverBackground = new Color(89, 98, 136);

        button.setBackground(defaultBackground);
        button.setForeground(Color.white);
        button.setFont(FontManager.getManrope(Font.PLAIN, 16));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.addActionListener(this);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverBackground);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(defaultBackground);
            }
        });

        return button;
    }


    private void loadTableData() {
        ArrayList<KhachHang> dsKhachHang = khachHangDAO.getDSKhachHang();
        for (KhachHang kh : dsKhachHang) {
            tableModel.addRow(new Object[]{
                    kh.getMaKH(),
                    kh.getHoTen(),
                    kh.getDiaChi(),
                    kh.getSdt(),
                    kh.getEmail(),
                    kh.getcCCD()
            });
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        RoundedButton btn = (RoundedButton) e.getSource();
        if (btn.getText().equals("Sửa")) {
            updateKhachHang();
        } else if (btn.getText().equals("Xóa")) {
            removeKhachHang();
        } else if (btn.getText().equals("Làm mới")) {
            refreshFields();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       int row = table.getSelectedRow();
       txtTenKhachHang.setText(tableModel.getValueAt(row, 1).toString());
       txtDiaChi.setText(tableModel.getValueAt(row, 2).toString());
       txtSDT.setText(tableModel.getValueAt(row, 3).toString());
       txtEmail.setText(tableModel.getValueAt(row, 4).toString());
       txtCCCD.setText(tableModel.getValueAt(row, 5).toString());
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
    public void updateKhachHang() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chọn khách hàng để sửa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Get data from text fields
        String tenKhachHang = txtTenKhachHang.getText();
        String diaChi = txtDiaChi.getText();
        String sdt = txtSDT.getText();
        String email = txtEmail.getText();
        String cccd = txtCCCD.getText();

        // Perform validation here...

        KhachHang khachHang = new KhachHang();
        // Assuming you have a method to get MaKH from the table
        khachHang.setMaKH((String) tableModel.getValueAt(selectedRow, 0));
        khachHang.setHoTen(tenKhachHang);
        khachHang.setDiaChi(diaChi);
        khachHang.setSdt(sdt);
        khachHang.setEmail(email);
        khachHang.setcCCD(cccd);

        // Call DAO method to update the database
        khachHangDAO.updateKhachHang(khachHang.getMaKH(), khachHang);
        JOptionPane.showMessageDialog(this,"Sua thanh cong");
        loadTableData(); // Refresh the table

    }

    public void removeKhachHang() {

            int row = table.getSelectedRow();
            if(row!=-1) {
                int confirm = JOptionPane.showConfirmDialog(this, "Ban co chac muon xoa khon?","Xoa",JOptionPane.YES_NO_OPTION);
                if(confirm == JOptionPane.YES_OPTION) {
                    tableModel.removeRow(row);
                }else {
                    JOptionPane.showMessageDialog(this, "Chua chon dong de xoa");
                }
            }

        loadTableData(); // Refresh the table
    }

    private void refreshFields() {
        txtTenKhachHang.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtCCCD.setText("");
        txtTenKhachHang.requestFocus();
    }
}
