package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class RoundedPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Color backgroundColor;
	private Color borderColor;
	private int cornerRadius = 15;
	private int borderThickness = 2;

	public RoundedPanel(int radius, int borderThickness) {
		cornerRadius = radius;
		this.borderThickness = borderThickness;
	}

	public RoundedPanel(int radius, int borderThickness, Color bgColor) {
		cornerRadius = radius;
		backgroundColor = bgColor;
		this.borderThickness = borderThickness;
	}

	public RoundedPanel(int radius, int borderThickness, Color bgColor, Color borderColor) {
		cornerRadius = radius;
		backgroundColor = bgColor;
		this.borderThickness = borderThickness;
		this.borderColor = borderColor;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension arcs = new Dimension(cornerRadius, cornerRadius);
		int width = getWidth();
		int height = getHeight();
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (backgroundColor != null) {
			graphics.setColor(backgroundColor);
		} else {
			graphics.setColor(getBackground());
		}
		graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);

		if (borderColor != null) {
			graphics.setColor(borderColor);
		}
		if (borderThickness > 0) {
			graphics.setStroke(new java.awt.BasicStroke(borderThickness));
		}
		graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
	}
}
