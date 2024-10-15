package form;

import customElements.FontManager;

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

        b3.add(b4);

        b1.add(b2);
        b1.add(b3);

        mainBox.add(searchBox);
        mainBox.add(Box.createVerticalStrut(20));
        mainBox.add(b1);

        add(mainBox);
    }
    private Box createFormBox(String label, String placeholder) {
        Box b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(331, 106));
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
        b.setBorder(BorderFactory.createEmptyBorder(0, 36, 35, 36));
        return b;
    }

}
