package customelement;

import gui.GiaoDienChinh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SubMenuPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private RoundedButton selectedSubMenu = null;

    public SubMenuPanel(String[][] subItems, CardLayout cardLayout, JPanel centerPanel, RoundedButton parentMenuButton, GiaoDienChinh parentFrame) {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(5));
        int index = 0;
        for (String[] item : subItems) {
            ImageIcon subMenuIcon = index == subItems.length - 1 ? new ImageIcon("imgs/LastSubMenuIcon.png")
                    : new ImageIcon("imgs/SubMenuIcon.png");
            RoundedButton subMenuButton = new RoundedButton(item[0], subMenuIcon, 0);
            subMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
            subMenuButton.setIconTextGap(4);
            subMenuButton.setFont(FontManager.getManrope(Font.PLAIN, 15));
            subMenuButton.setPreferredSize(new Dimension(230, 50));
            subMenuButton.setMaximumSize(new Dimension(230, 50));
            subMenuButton.setMinimumSize(new Dimension(230, 50));
            subMenuButton.setBackground(new Color(34, 34, 38));
            subMenuButton.setForeground(new Color(148, 148, 148));
            subMenuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            subMenuButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (selectedSubMenu != subMenuButton) {
                        subMenuButton.setBackground(new Color(45, 45, 50));
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (selectedSubMenu != subMenuButton) {
                        subMenuButton.setBackground(new Color(34, 34, 38));
                    }
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                	if (parentFrame.selectedButton != parentMenuButton) {
                        parentFrame.selectedButton.setBackground(new Color(24, 24, 28));
                        parentFrame.selectedButton = parentMenuButton;
                        parentFrame.selectedButton.setBackground(new Color(91, 122, 249));
                        parentFrame.updateButtonColor();
                    }
                	if (parentFrame.pageLabel.getText() != item[0].toUpperCase()) {
                		parentFrame.pageLabel.setText(item[0].toUpperCase());
                	}
                    if (selectedSubMenu != subMenuButton) {
                        if (selectedSubMenu != null) {
                            selectedSubMenu.setForeground(new Color(148, 148, 148));
                        }

                        selectedSubMenu = subMenuButton;
                        selectedSubMenu.setBackground(new Color(34, 34, 38));
                        selectedSubMenu.setForeground(new Color(91, 122, 249));
                        cardLayout.show(centerPanel, item[1]);
                    }

                    // Cập nhật nút cha trong GiaoDienChinh
                    
                }
            });

            add(subMenuButton);
            index++;
        }
    }

	public RoundedButton getSelectedSubMenu() {
		return selectedSubMenu;
	}

	public void setSelectedSubMenu(RoundedButton selectedSubMenu) {
		this.selectedSubMenu = selectedSubMenu;
	}
    
    
}