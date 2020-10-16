/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.view.plaf;

import com.pagosoft.plaf.*;
import com.pagosoft.plaf.themes.VistaTheme;
import com.pagosoft.swing.DropDownIcon;
import com.pagosoft.swing.MarginIcon;
import com.pagosoft.swing.PopupButton;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 03.01.2010
 * Time: 16:40:21
 * To change this template use File | Settings | File Templates.
 */
public class ModernToolBarUI extends PgsToolBarUI {
	public static ComponentUI createUI(JComponent c) {
		return new ModernToolBarUI();
	}

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		c.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		((JToolBar)c).setFloatable(false);
	}

	@Override
	public void paint(Graphics graphics, JComponent b) {
		if(b.isOpaque()) {
			Graphics2D gfx = (Graphics2D) graphics;
			int width = b.getWidth();
			int height = b.getHeight();
			Paint p = new LinearGradientPaint(0, 0, 0, height,
					new float[] {0, .5f, .51f, 1f},
					colors(0x84ADC0, 0x3F809D, 0x0B5476, 0x5099AA));
			gfx.setPaint(p);
			gfx.fillRect(0, 0, width, height);

			p = new LinearGradientPaint(0, 0, 0, height-1,
					new float[] {0, .5f, .51f, 1f},
					colors(0xB4CED8, 0x83A9C1, 0x57829E, 0x93C0CE));
			gfx.setPaint(p);
			gfx.drawRect(0, 0, width-1, height-1);
		}
	}

	@Override
	protected void setToolBarUI(JComponent c) {
		if(c instanceof JButton) {
			((JButton)c).setUI(new ModernToolBarButtonUI());
		} else if(c instanceof PopupButton) {
			((PopupButton)c).setUI(new ModernToolBarPopupButtonUI());
		}
	}

	private static Color[] colors(int ... cs) {
		Color[] colors = new Color[cs.length];
		for(int i = 0; i < cs.length; i++) {
			colors[i] = new Color(cs[i]);
		}
		return colors;
	}

	private static class ModernToolBarPopupButtonUI extends PgsToggleButtonUI implements MouseMotionListener {
		private boolean isAboveArrow = false;

		protected void installListeners(AbstractButton b) {
			super.installListeners(b);

			b.addMouseMotionListener(this);
		}

		@Override
		public void installDefaults(AbstractButton b) {
			super.installDefaults(b);
			b.setBorderPainted(false);
			b.setOpaque(false);
			b.setFocusPainted(false);
			b.setForeground(Color.WHITE);
			b.setMargin(new Insets(1, 4, 1, 4));

			PopupButton pb = (PopupButton) b;
			pb.setDropDownIcon(new MarginIcon(new Insets(0, 4, 0, 4),
					new DropDownIcon(Color.WHITE, Color.LIGHT_GRAY)));
		}

		@Override
		public void update(Graphics graphics, JComponent c) {
			PaintUtils.paintModernButtonBorder((AbstractButton)c, graphics);

			PopupButton b = (PopupButton)c;
			// Draw line
			if(b.getDefaultActionListener() != null && !b.getModel().isSelected() && b.isEnabled()
					&& b.getModel().isRollover() && isAboveArrow) {
				int width = c.getWidth();
				int height = c.getHeight();
				Icon ico = b.getDropDownIcon();
				int x = width - ico.getIconWidth();

				Graphics2D gfx = (Graphics2D) graphics;
				Paint p = new GradientPaint(0, 0, new Color(9, 61, 82, 50),
						0, height, new Color(9, 61, 82, 150));
				gfx.setPaint(p);
				gfx.drawLine(x, 2, x, height-4);

				p = new GradientPaint(0, 0, new Color(1f, 1f, 1f, .7f),
					0, height, new Color(1f, 1f, 1f, .4f));
				gfx.setPaint(p);
				gfx.drawLine(x-1, 2, x-1, height-4);

				PaintUtils.paintModernButtonBackground(gfx, x+1, 2, ico.getIconWidth(), height/2);
			} else {
				PaintUtils.paintModernButtonBackground(b, graphics);
			}

			super.paint(graphics, c);
		}

		@Override
		protected void paintButtonPressed(Graphics graphics, AbstractButton b) {
			PaintUtils.paintModernButtonPressed(graphics, b);
		}

		@Override
		protected void paintText(Graphics g, JComponent c, Rectangle rect, String s) {
			PaintUtils.paintModernButtonText(g, (AbstractButton)c, rect, s);
		}

		public void mouseDragged(MouseEvent e) {}

		public void mouseMoved(MouseEvent e) {
			boolean temp = ((PopupButton)e.getSource()).isAboveArrow(e.getPoint());
			if(temp != isAboveArrow) {
				isAboveArrow = temp;
				((PopupButton)e.getSource()).repaint();
			}
		}
	}

	private static class ModernToolBarButtonUI extends PgsButtonUI {
		@Override
		public void installDefaults(AbstractButton b) {
			super.installDefaults(b);
			b.setBorderPainted(false);
			b.setOpaque(false);
			b.setFocusPainted(false);
			b.setForeground(Color.WHITE);
			b.setMargin(new Insets(1, 4, 1, 4));
		}

		@Override
		public void update(Graphics graphics, JComponent c) {
			PaintUtils.paintModernButton((AbstractButton)c, graphics);
			super.paint(graphics, c);
		}

		@Override
		protected void paintButtonPressed(Graphics graphics, AbstractButton b) {
			PaintUtils.paintModernButtonPressed(graphics, b);
		}

		@Override
		protected void paintText(Graphics g, JComponent c, Rectangle rect, String s) {
			PaintUtils.paintModernButtonText(g, (AbstractButton) c, rect, s);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PlafOptions.setCurrentTheme(new VistaTheme());
				PlafOptions.setAsLookAndFeel();

				JFrame frm = new JFrame("Test");
				frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				JToolBar tb = new JToolBar();
				tb.setFloatable(false);
				tb.setUI(new ModernToolBarUI());
				tb.add(new JButton("Drucken"));
				frm.add(tb, BorderLayout.PAGE_START);

				frm.setSize(500, 600);
				frm.setVisible(true);
			}
		});
	}
}
