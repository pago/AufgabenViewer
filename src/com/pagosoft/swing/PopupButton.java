/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */
/*
 * Copyright 2005 Patrick Gotthardt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pagosoft.swing;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

/**
 * @author Patrick Gotthardt
 */
public class PopupButton extends JToggleButton {
	private JPopupMenu popup;
	private Icon dropDownIcon;
	private boolean popupVisible;
	private boolean arrowPressed;
	private ActionListener defaultActionListener;

	public PopupButton() {
		super();
		initialize();
	}

	public PopupButton(Icon icon) {
		super(icon);
		initialize();
	}

	public PopupButton(String text) {
		super(text);
		initialize();
	}

	public PopupButton(Action a) {
		super(a);
		initialize();
	}

	public PopupButton(String text, Icon icon) {
		super(text, icon);
		initialize();
	}

	@Override
	public void setAction(Action a) {
		super.setAction(a);
		listenerList.remove(ActionListener.class, a);
		setDefaultActionListener(a);
	}

	private void initialize() {
		popup = new JPopupMenu();
		popupVisible = false;

		popup.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				setPopupVisible(true);
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				setPopupVisible(false);
			}

			public void popupMenuCanceled(PopupMenuEvent e) {
				setPopupVisible(false);
			}
		});

		addMouseListener(new MouseHandler());

		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActionListener defaultActionListener = getDefaultActionListener();
				if(isArrowPressed() || defaultActionListener == null) {
					togglePopupVisible();
				} else {
					defaultActionListener.actionPerformed(e);
					setSelected(false);
				}
			}
		});
	}

	public boolean isPopupVisible() {
		return popupVisible;
	}

	public void setPopupVisible(boolean popupVisible) {
		this.popupVisible = popupVisible;
		setSelected(popupVisible);
	}

	public JPopupMenu getPopup() {
		return popup;
	}

	public void setPopup(JPopupMenu popup) {
		this.popup = popup;
	}

	public Icon getDropDownIcon() {
		if(dropDownIcon == null) {
			setDropDownIcon(new MarginIcon(new Insets(0, 2, 0, 2), new DropDownIcon()));
		}
		return dropDownIcon;
	}

	public void setDropDownIcon(Icon dropDownIcon) {
		this.dropDownIcon = dropDownIcon;
	}

	public boolean isArrowPressed() {
		return arrowPressed;
	}

	public void setArrowPressed(boolean arrowPressed) {
		this.arrowPressed = arrowPressed;
	}

	public ActionListener getDefaultActionListener() {
		return defaultActionListener;
	}

	public void setDefaultActionListener(ActionListener defaultActionListener) {
		this.defaultActionListener = defaultActionListener;
	}

	public Component add(Component c) {
		return popup.add(c);
	}

	public void togglePopupVisible() {
		if(popup.isShowing()) {
			popup.setVisible(false);
		} else {
			popup.show(this, 0, getHeight());
		}
	}

	public void setMargin(Insets m) {
		Icon ico = getDropDownIcon();
		m = new Insets(m.top, m.left, m.bottom, m.right + ico.getIconWidth());
		super.setMargin(m);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// lets paint that icon...
		int width = getWidth();
		int height = getHeight();
		Icon ico = getDropDownIcon();
		ico.paintIcon(this, g, width - ico.getIconWidth(), (height/2)-(ico.getIconHeight()/2));
	}

	public boolean isAboveArrow(Point p) {
		return (getWidth() - getDropDownIcon().getIconWidth()) < p.x;
	}

	private class MouseHandler extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			setArrowPressed(isAboveArrow(e.getPoint()));
		}
	}
}
