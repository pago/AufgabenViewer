/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */
package com.pagosoft.swing;

import javax.swing.*;
import java.awt.*;

/**
 * @author Patrick Gotthardt
 */
public class DropDownIcon implements Icon {
	private Color enabled, disabled;

	public DropDownIcon(Color enabled, Color disabled) {
		this.enabled = enabled;
		this.disabled = disabled;
	}

	public DropDownIcon() {
		this(UIManager.getColor("controlText"), UIManager.getColor("controlDkShadow"));
	}

	public void paintIcon(Component component, Graphics g, int x, int y) {
		JComponent c = (JComponent) component;
		int iconWidth = getIconWidth();

		g.translate(x, y);

		g.setColor(c.isEnabled() ? enabled : disabled);

		g.drawLine(1, 1, 1 + (iconWidth - 3), 1);
		g.drawLine(2, 2, 2 + (iconWidth - 5), 2);
		g.drawLine(3, 3, 3 + (iconWidth - 7), 3);
		g.drawLine(4, 4, 4 + (iconWidth - 9), 4);

		g.translate(-x, -y);
	}

	/**
	 * Created a stub to satisfy the interface.
	 */
	public int getIconWidth() {
		return 10;
	}

	/**
	 * Created a stub to satisfy the interface.
	 */
	public int getIconHeight() {
		return 4;
	}
}
