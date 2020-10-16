/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.view;

import com.google.inject.Inject;
import com.pagosoft.action.ActionManager;
import com.pagosoft.myview.actions.PrintDocument;
import com.pagosoft.myview.actions.PrintExercise;
import com.pagosoft.myview.actions.PrinterSettings;
import com.pagosoft.myview.view.plaf.ModernToolBarUI;
import com.pagosoft.swing.PopupButton;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 03.01.2010
 * Time: 20:49:04
 * To change this template use File | Settings | File Templates.
 */
public class MyViewToolBar extends JToolBar {
	@Inject
	public MyViewToolBar(ActionManager am) {
		setUI(new ModernToolBarUI());

		PopupButton print = new PopupButton(am.get(PrintDocument.class));
		JPopupMenu menu = print.getPopup();
		menu.add(am.get(PrintDocument.class));
		menu.add(am.get(PrintExercise.class));
		menu.addSeparator();
		menu.add(am.get(PrinterSettings.class));
		add(print);
	}
}
