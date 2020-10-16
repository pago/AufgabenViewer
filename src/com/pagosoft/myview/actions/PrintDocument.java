/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.actions;

import com.google.inject.Inject;
import com.pagosoft.action.AbstractSystemAction;
import com.pagosoft.eventbus.ApplicationListener;
import com.pagosoft.eventbus.EventBus;
import com.pagosoft.myview.domain.Document;
import com.pagosoft.myview.events.DocumentChangedEvent;
import com.pagosoft.myview.print.DocumentPrintJob;
import com.pagosoft.myview.print.PrintService;

import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 03.01.2010
 * Time: 20:52:05
 * To change this template use File | Settings | File Templates.
 */
public class PrintDocument extends AbstractSystemAction implements ApplicationListener<DocumentChangedEvent> {
	private PrintService ps;
	private Document doc;

	@Inject
	public PrintDocument(EventBus eventBus, PrintService ps) {
		super("print.document");
		this.ps = ps;
		eventBus.add(this, DocumentChangedEvent.class);
		setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		ps.print(new DocumentPrintJob(doc));
	}

	public void handleEvent(DocumentChangedEvent event) {
		doc = event.getSource();
		setEnabled(doc != null);
	}
}
