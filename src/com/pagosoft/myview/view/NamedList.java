/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 19:33:52
 * To change this template use File | Settings | File Templates.
 */
public class NamedList extends JPanel {
	protected JList list;
	public NamedList(String title, ListModel model) {
		super(new MigLayout("wrap 1", "[grow, fill]", "[|grow, fill]"));

		JLabel label = new JLabel(title);
		label.setUI(new EmphasizedLabelUI());
		add(label);
		list = configure(new JList(model));
		add(new JScrollPane(list));
	}

	public JList getList() {
		return list;
	}

	protected JList configure(JList plist) {
		return plist;
	}

	private static class EmphasizedLabelUI extends BasicLabelUI {

    private Color fShadowColor;
    private Color fFocusedTextColor;
    private Color fUnfocusedTextColor;

    public static final Color DEFAULT_EMPHASIS_COLOR = new Color(255, 255, 255, 110);
    public static final Color DEFAULT_FOCUSED_FONT_COLOR = new Color(0x000000);
    public static final Color DEFAULT_UNFOCUSED_FONT_COLOR = new Color(0x3f3f3f);
    public static final Color DEFAULT_DISABLED_FONT_COLOR = new Color(0x3f3f3f);

    /**
     * Creates an {@code EmphasizedLabelUI} using the default colors.
     */
    public EmphasizedLabelUI() {
        this(DEFAULT_FOCUSED_FONT_COLOR, DEFAULT_UNFOCUSED_FONT_COLOR,
                DEFAULT_EMPHASIS_COLOR);
    }

    /**
     * Creates an {@code EmphasizedLabelUI} using the given colors.
     *
     * @param focusedTextColor   the color to draw the text with when the parent
     *                           {@link java.awt.Window} has focus.
     * @param unfocusedTextColor the color to draw the text with when the parent
     *                           {@link java.awt.Window} does not have focus.
     * @param emphasisColor      the color to draw the emphasis text with.
     */
    public EmphasizedLabelUI(Color focusedTextColor, Color unfocusedTextColor, Color emphasisColor) {
        fFocusedTextColor = focusedTextColor;
        fUnfocusedTextColor = unfocusedTextColor;
        fShadowColor = emphasisColor;

    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        c.setOpaque(false);
    }

    @Override
    public void uninstallUI(JComponent c) {
        super.uninstallUI(c);
        // TODO add uninstallation.
    }

    @Override
    protected void paintEnabledText(JLabel label, Graphics g, String s,
                                    int textX, int textY) {
        g.setColor(fShadowColor);
        g.setFont(label.getFont());
        BasicGraphicsUtils.drawStringUnderlineCharAt(g, s, -1, textX, textY + 1);
        g.setColor(isParentWindowFocused(label)
                ? fFocusedTextColor : fUnfocusedTextColor);
        BasicGraphicsUtils.drawStringUnderlineCharAt(g, s, -1, textX, textY);
    }
	public static boolean isParentWindowFocused(Component component) {
			Window window = SwingUtilities.getWindowAncestor(component);
			return window != null && window.isFocused();
		}
	

    @Override
    protected void paintDisabledText(JLabel label, Graphics g, String s, int textX, int textY) {
        // TODO do use a diabled color here.
        g.setColor(fShadowColor);
        g.setFont(label.getFont());
        BasicGraphicsUtils.drawStringUnderlineCharAt(g, s, -1, textX, textY + 1);
        g.setColor(DEFAULT_DISABLED_FONT_COLOR);
        BasicGraphicsUtils.drawStringUnderlineCharAt(g, s, -1, textX, textY);
    }
}

}
