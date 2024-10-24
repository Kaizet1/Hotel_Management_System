package form;

import customElements.*;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import dao.PhieuDatPhong_DAO;
import entity.KhachHang;
import entity.PhieuDatPhong;

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

public class HuyDatPhong_FORM extends JPanel implements ActionListener, Openable {
    private DefaultTableModel tableModel;
    private JTable table;
    private PhieuDatPhong_DAO phieuDatPhongDao;
    private JButton btnHuyDatPhong;
    @Override
    public void open() {
       loadTableData();
    }
    public HuyDatPhong_FORM() {
        setBackground(new Color(16, 16, 20));
        Box b = Box.createVerticalBox();
        b.add(Box.createVerticalStrut(10));

        // Tim kiem
        JTextField searchField = new JTextField("Tìm kiếm tên khách hàng");
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
                if (searchField.getText().equals("Tìm kiếm tên khách hàng")) {
                    searchField.setText("");
                    searchField.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                searchField.setBorder(emptyBorder);
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(new Color(255, 255, 255, 125));
                    searchField.setText("Tìm kiếm tên khách hàng");
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
        JLabel titleLabel = new JLabel("Danh sách đặt trước");
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
        String[] headers = {"Mã phiếu đặt phòng", "Số phòng", "Tên khách hàng", "Ngày đặt", "Ngày đến", "Ngày đi", "Nhân viên tạo phiếu"};
        tableModel = new DefaultTableModel(headers, 0);
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
        bbutton.add(btnHuyDatPhong = new JButton("Hủy đặt phòng"));
        btnHuyDatPhong.setFont(FontManager.getManrope(Font.PLAIN, 16));
        btnHuyDatPhong.setForeground(Color.WHITE);
        btnHuyDatPhong.setBackground(new Color(66, 99, 235));
        btnHuyDatPhong.setOpaque(false);
        btnHuyDatPhong.setPreferredSize(new Dimension(200, 50));
        btnHuyDatPhong.setMinimumSize(new Dimension(200, 50));
        btnHuyDatPhong.setMaximumSize(new Dimension(200, 50));
        btnHuyDatPhong.addActionListener(this);
        b.add(bbutton);
        add(b, BorderLayout.CENTER);
    }
    private void loadTableData(){
        tableModel.setRowCount(0);
        phieuDatPhongDao = new PhieuDatPhong_DAO();
        ArrayList<PhieuDatPhong> dsPhieuDatPhong = phieuDatPhongDao.getDSPhieuDatPhong();
        KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
        khachHang_DAO.getDSKhachHang();
        NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
        nhanVien_DAO.getDSNhanVien();
        for (PhieuDatPhong pdp : dsPhieuDatPhong) {
            tableModel.addRow(new Object[]{
                    pdp.getMaPDP(),
                    pdp.getPhong().getMaPhong(),
                    khachHang_DAO.timKiem(pdp.getKhachHang().getMaKH()).getHoTen(),
                    pdp.getNgayDat(),
                    pdp.getNgayDen(),
                    pdp.getNgayDi(),
                    nhanVien_DAO.timKiem(pdp.getNhanVien().getMaNV()).getHoTen()
            });
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnHuyDatPhong) {
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu đặt phòng để hủy!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String maPDP = (String) tableModel.getValueAt(selectedRow, 0);
            System.out.println(maPDP);
            if (phieuDatPhongDao.huyDatPhong(maPDP)) {
                JOptionPane.showMessageDialog(this, "Đã hủy đặt phòng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);


                loadTableData();
            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi hủy đặt phòng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
