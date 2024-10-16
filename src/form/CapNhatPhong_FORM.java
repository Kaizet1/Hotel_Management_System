package form;

import customElements.FontManager;
import customElements.RoundedButton;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CapNhatPhong_FORM extends JPanel {

    public CapNhatPhong_FORM() {
        setBackground(new Color(16, 16, 20));
        Box mainBox = Box.createVerticalBox();
        mainBox.add(Box.createVerticalStrut(10));
        // Tim kiem
        JTextField txtSearch = new JTextField("Tìm kiếm");
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
        b2.add(createFormBox("Tên phòng", ""));
        b2.add(createFormBox("Loại phòng", ""));
        b2.add(createFormBox("Giá phòng", ""));
        b2.add(createFormBox("Số người", ""));
//        b2.add(Box.createHorizontalGlue());
//        Dimension b2Size = new Dimension(b2.getPreferredSize().width + 300, b2.getPreferredSize().height);
//        b2.setPreferredSize(b2Size);
//        b2.setMaximumSize(b2Size);
//        b2.setMinimumSize(b2Size);
//        b2.setAlignmentX(LEFT_ALIGNMENT/);
        b2.add(Box.createHorizontalStrut(50));

        b2.setBorder(BorderFactory.createLineBorder(Color.pink));
        Box b3 = Box.createHorizontalBox();
        Box b4 = Box.createVerticalBox();
        JLabel lblMota = new JLabel("Mô tả");
        lblMota.setFont(FontManager.getManrope(Font.PLAIN, 15));
        lblMota.setForeground(Color.WHITE);
        lblMota.setPreferredSize(new Dimension(259, 20));
        lblMota.setMaximumSize(new Dimension(259, 20));
        lblMota.setMinimumSize(new Dimension(259, 20));
        JTextArea txaMoTa = new JTextArea();
        Dimension txaMoTaSize = new Dimension(1252, 220);
        txaMoTa.setPreferredSize(txaMoTaSize);
        txaMoTa.setMaximumSize(txaMoTaSize);
        txaMoTa.setMinimumSize(txaMoTaSize);
        txaMoTa.setBackground(new Color(40, 40, 44));

        b4.add(lblMota);
        b4.add(txaMoTa);
        Box b5 = Box.createVerticalBox();
        JPanel btnThem = createHandleButton("Thêm");
        JPanel btnSua = createHandleButton("Sửa");
        JPanel btnXoa = createHandleButton("Xóa");
        JPanel btnLamMoi = createHandleButton("Làm mới");

        b5.add(Box.createVerticalStrut(20));
        b5.add(btnThem);
        b5.add(btnSua);
        b5.add(btnXoa);
        b5.add(btnLamMoi);


        b3.add(b4);
        b3.add(Box.createHorizontalStrut(80));
        b3.add(b5);

        b1.add(b2);
        b1.add(b3);
        searchBox.setBorder(BorderFactory.createLineBorder(Color.pink));
        mainBox.add(searchBox);
        mainBox.add(Box.createVerticalStrut(20));
        mainBox.add(b1);
        add(mainBox);
    }

    private Box createFormBox(String label, String placeholder) {
        Box b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(295, 106));
        JLabel lbl = new JLabel(label);
        lbl.setFont(FontManager.getManrope(Font.PLAIN, 15));
        lbl.setForeground(Color.WHITE);
        lbl.setPreferredSize(new Dimension(259, 20));
        lbl.setMaximumSize(new Dimension(259, 20));
        lbl.setMinimumSize(new Dimension(259, 20));
        JTextField text = new JTextField(placeholder);
        text.setBackground(new Color(40, 40, 44));
        b.add(lbl);
        b.add(Box.createVerticalStrut(6));
        b.add(text);
        b.setBorder(BorderFactory.createEmptyBorder(0, 0 , 35, 72));
        return b;
    }

    private JPanel createHandleButton(String buttonLabel) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        RoundedButton button = new RoundedButton(buttonLabel, 0);
        Dimension buttonSize = new Dimension(259, 45);
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);
        button.setMinimumSize(buttonSize);
        button.setBackground(new Color(80, 80, 88));
        button.setForeground(Color.white);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        buttonPanel.add(button);
        return buttonPanel;
    }
}