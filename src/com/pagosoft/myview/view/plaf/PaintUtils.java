/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.view.plaf;

import com.pagosoft.plaf.PgsUtils;

import javax.swing.*;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 03.01.2010
 * Time: 21:44:31
 * To change this template use File | Settings | File Templates.
 */
public class PaintUtils {
	public static void paintModernButton(AbstractButton b, Graphics g) {
		paintModernButtonBackground(b, g);
		paintModernButtonBorder(b, g);
	}

	public static void paintModernButtonBackground(AbstractButton b, Graphics g) {
		Graphics2D gfx = (Graphics2D) g;
		int height = b.getHeight();
		int width = b.getWidth();

		// Rollover ?
		if (b.isEnabled() && b.getModel().isRollover()) {
			Paint p = new GradientPaint(0, 0, new Color(1f, 1f, 1f, .2f),
					0, height/2, new Color(1f, 1f, 1f, 0f));
			gfx.setPaint(p);
			gfx.fillRect(2, 2, width-4, height/2);
		}
	}

	public static void paintModernButtonBackground(Graphics g, int x, int y, int width, int height) {
		Graphics2D gfx = (Graphics2D) g;
		Paint p = new GradientPaint(0, 0, new Color(1f, 1f, 1f, .2f),
				0, height/2, new Color(1f, 1f, 1f, 0f));
		gfx.setPaint(p);
		gfx.fillRect(x, y, width, height);
	}

	public static void paintModernButtonBorder(AbstractButton b, Graphics g) {
		Graphics2D gfx = (Graphics2D) g;
		int height = b.getHeight();
		int width = b.getWidth();
		int radius = 4;

		// Rollover ?
		if (b.isEnabled() && b.getModel().isRollover()) {
			// outer border
			Paint p = new GradientPaint(0, 0, new Color(9, 61, 82, 50),
					0, height, new Color(9, 61, 82, 150));
			gfx.setPaint(p);
			PgsUtils.installAntialiasing(gfx);
			gfx.drawRoundRect(0, 0, width-1, height-1, radius+1, radius+1);

			// inner border
			p = new GradientPaint(0, 0, new Color(1f, 1f, 1f, .7f),
					0, height, new Color(1f, 1f, 1f, .4f));
			gfx.setPaint(p);
			gfx.drawRoundRect(1, 1, width-3, height-3, radius, radius);

			PgsUtils.uninstallAntialiasing(gfx);
		}
	}

	public static void paintModernButtonPressed(Graphics graphics, AbstractButton b) {
		Graphics2D gfx = (Graphics2D) graphics;

		int height = b.getHeight();
		int width = b.getWidth();
		int radius = 4;

		// outer border
		Paint p = new GradientPaint(0, 0, new Color(9, 61, 82, 50),
				0, height, new Color(9, 61, 82, 150));
		gfx.setPaint(p);
		PgsUtils.installAntialiasing(gfx);
		gfx.drawRoundRect(0, 0, width-1, height-1, radius+1, radius+1);

		// inner border
		Color start = Color.getHSBColor(360f/197f, .89f, .32f);
		start = new Color(start.getRed(), start.getGreen(), start.getBlue(), 50);
		Color end = Color.getHSBColor(360f/197f, .89f, .32f);
		end = new Color(end.getRed(), end.getGreen(), end.getBlue(), 150);
		p = new GradientPaint(0, 0, start, 0, height, end);
		gfx.setPaint(p);
		gfx.drawRoundRect(1, 1, width-3, height-3, radius, radius);

		PgsUtils.uninstallAntialiasing(gfx);
	}

	public static void paintModernButtonText(Graphics g, AbstractButton c, Rectangle rect, String s) {
		int textX = rect.x, textY = rect.y;
		int mnemIndex = c.getDisplayedMnemonicIndex();
		Color fShadowColor = new Color(0x111111);


		g.setColor(fShadowColor);
		g.setFont(c.getFont());
		FontMetrics fm = g.getFontMetrics(c.getFont());
		PgsUtils.installAntialiasing(g);
		BasicGraphicsUtils.drawStringUnderlineCharAt(g, s, mnemIndex, textX, textY + fm.getAscent() + 1);
		g.setColor(c.isEnabled() ? c.getForeground() : new Color(0xCCCCCC));
		BasicGraphicsUtils.drawStringUnderlineCharAt(g, s, mnemIndex, textX, textY + fm.getAscent());
		PgsUtils.uninstallAntialiasing(g);
	}
}
