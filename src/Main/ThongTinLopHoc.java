package Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThongTinLopHoc {
    private JFrame frame;
    private JTextField txtMaLop, txtTenLop, txtGiaoVien;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnThem, btnSua, btnXoa, btnXemDS;
    private JButton btnFirst, btnPrev, btnNext, btnLast;

    private int currentPage = 1;
    private final int rowsPerPage = 4;
    private final Object[][] data = {
            {"DHTH10A", "Đại học tin học 10A", "Cô Khánh"},
            {"DHTH10B", "Đại học tin học 10B", "Thầy Phúc"},
            {"DHTH10C", "Đại học tin học 10C", "Thầy Hải"},
            {"DHTH10D", "Đại học tin học 10D", "Cô Minh"},
            {"DHTH11A", "Đại học tin học 11A", "Cô Thảo"},
            {"DHTH11B", "Đại học tin học 11B", "Thầy Minh"},
            {"DHTH11C", "Đại học tin học 11C", "Thầy Phương"},
            {"DHTH11D", "Đại học tin học 11D", "Cô Lan"},
            {"DHTH12A", "Đại học tin học 12A", "Thầy Bình"},
            {"DHTH12B", "Đại học tin học 12B", "Cô Hoa"},
            {"DHTH12C", "Đại học tin học 12C", "Thầy Tâm"},
            {"DHTH12D", "Đại học tin học 12D", "Cô Hồng"}
    };

    public ThongTinLopHoc() {
        // Khởi tạo JFrame
        frame = new JFrame("Thông tin lớp học");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        // Panel thông tin lớp học
        JPanel panelInfo = new JPanel(new GridLayout(3, 2, 10, 10));
        panelInfo.setBorder(BorderFactory.createTitledBorder("THÔNG TIN LỚP HỌC"));

        txtMaLop = new JTextField();
        txtTenLop = new JTextField();
        txtGiaoVien = new JTextField();

        panelInfo.add(new JLabel("Mã lớp:"));
        panelInfo.add(txtMaLop);
        panelInfo.add(new JLabel("Tên lớp:"));
        panelInfo.add(txtTenLop);
        panelInfo.add(new JLabel("Giáo viên chủ nhiệm:"));
        panelInfo.add(txtGiaoVien);

        frame.add(panelInfo, BorderLayout.NORTH);

        // Bảng danh sách lớp học
        String[] columnNames = {"Mã số lớp", "Tên lớp", "Giáo viên chủ nhiệm"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(scrollPane, BorderLayout.CENTER);

        // Panel các nút điều hướng trang
        JPanel panelNavigation = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnFirst = new JButton("|<");
        btnPrev = new JButton("<");
        btnNext = new JButton(">");
        btnLast = new JButton(">|");

        panelNavigation.add(btnFirst);
        panelNavigation.add(btnPrev);
        panelNavigation.add(new JLabel("Trang " + currentPage));
        panelNavigation.add(btnNext);
        panelNavigation.add(btnLast);

        frame.add(panelNavigation, BorderLayout.NORTH);

        // Panel các nút chức năng
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        panelButtons.add(btnThem);
        panelButtons.add(btnSua);
        panelButtons.add(btnXoa);

        frame.add(panelButtons, BorderLayout.SOUTH);

        // Nút Xem danh sách sinh viên
        btnXemDS = new JButton("Xem danh sách sinh viên của lớp hiện tại");
        btnXemDS.setForeground(Color.RED);

        JPanel panelViewStudents = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelViewStudents.add(btnXemDS);
        frame.add(panelViewStudents, BorderLayout.PAGE_END);

        // Hiển thị dữ liệu trang đầu tiên
        loadPage(currentPage);

        // Sự kiện nút điều hướng
        btnFirst.addActionListener(e -> {
            currentPage = 1;
            loadPage(currentPage);
        });

        btnPrev.addActionListener(e -> {
            if (currentPage > 1) {
                currentPage--;
                loadPage(currentPage);
            }
        });

        btnNext.addActionListener(e -> {
            if (currentPage < getTotalPages()) {
                currentPage++;
                loadPage(currentPage);
            }
        });

        btnLast.addActionListener(e -> {
            currentPage = getTotalPages();
            loadPage(currentPage);
        });

        // Sự kiện nút chức năng
        btnThem.addActionListener(e -> {
            String maLop = txtMaLop.getText();
            String tenLop = txtTenLop.getText();
            String giaoVien = txtGiaoVien.getText();
            if (!maLop.isEmpty() && !tenLop.isEmpty() && !giaoVien.isEmpty()) {
                // Thêm dòng vào bảng và làm mới lại trang
                tableModel.addRow(new Object[]{maLop, tenLop, giaoVien});
                loadPage(currentPage);
                clearTextFields();
            } else {
                JOptionPane.showMessageDialog(frame, "Vui lòng điền đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnSua.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int actualRow = (currentPage - 1) * rowsPerPage + selectedRow;
                data[actualRow][0] = txtMaLop.getText();
                data[actualRow][1] = txtTenLop.getText();
                data[actualRow][2] = txtGiaoVien.getText();
                loadPage(currentPage);
                clearTextFields();
            } else {
                JOptionPane.showMessageDialog(frame, "Vui lòng chọn lớp cần sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnXoa.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int actualRow = (currentPage - 1) * rowsPerPage + selectedRow;
                tableModel.removeRow(selectedRow);
                loadPage(currentPage);
            } else {
                JOptionPane.showMessageDialog(frame, "Vui lòng chọn lớp cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnXemDS.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Chức năng xem danh sách sinh viên hiện tại chưa được triển khai.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        });

        frame.setVisible(true);
    }

    private void loadPage(int page) {
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

    private void clearTextFields() {
        txtMaLop.setText("");
        txtTenLop.setText("");
        txtGiaoVien.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ThongTinLopHoc::new);
    }
}
