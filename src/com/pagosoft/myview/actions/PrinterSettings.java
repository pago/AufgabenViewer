/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.actions;

import com.google.inject.Inject;
import com.pagosoft.action.AbstractSystemAction;
import com.pagosoft.myview.print.PrintService;

import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 03.01.2010
 * Time: 21:00:06
 * To change this template use File | Settings | File Templates.
 */
public class PrinterSettings extends AbstractSystemAction {
	private PrintService ps;

	@Inject
	public PrinterSettings(PrintService ps) {
		super("print.settings");
		this.ps = ps;
	}

	public void actionPerformed(ActionEvent e) {
		ps.showPrintDialog();
	}
}
