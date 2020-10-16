/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.print;

import com.google.inject.Inject;
import com.pagosoft.actors.Actor;
import com.pagosoft.app.Application;
import com.pagosoft.eventbus.ApplicationListener;
import com.pagosoft.eventbus.EventBus;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 04.01.2010
 * Time: 19:19:45
 * To change this template use File | Settings | File Templates.
 */
public class AsyncPrintService implements PrintService, ApplicationListener<Application.ShutdownEvent> {
	private PrintService ps;
	private Printer printer;

	@Inject
	public AsyncPrintService(@PrintServiceDelegate PrintService ps, EventBus eventBus) {
		this.ps = ps;

		eventBus.add(Application.ShutdownEvent.class, this);
		printer = new Printer(ps, eventBus);
		printer.start();
	}

	public void print(PrintJob job) {
		boolean success = printer.send(job);
		if(!success) {
			new Thread(new AddLater(job, printer)).start();
		}
	}

	public boolean showPrintDialog() {
		return ps.showPrintDialog();
	}

	public void handleEvent(Application.ShutdownEvent event) {
		if(!printer.hasEmptyQueue()) {
			int result = JOptionPane.showConfirmDialog(null, "Es sind noch nicht alle Druckaufträge abgeschlossen." +
					" Sind Sie sicher, dass Sie die Anwendung beenden möchten?");
			if(result != JOptionPane.OK_OPTION) {
				event.doVeto();
			} else {
				printer.interrupt();
			}
		}
	}

	private static class Printer extends Actor<PrintJob> {
		private PrintService ps;
		private EventBus eventBus;

		private Printer(PrintService ps, EventBus eventBus) {
			this.ps = ps;
			this.eventBus = eventBus;
		}

		@Override
		protected void act(PrintJob job) {
			String msg = String.format("Drucke %s", job.getJobName());
			eventBus.fireSwingEvent(new PrintJobEvent(msg, false));
			ps.print(job);
			eventBus.fireSwingEvent(new PrintJobEvent(
					String.format("Drucken von %s abgeschlossen", job.getJobName()), true));
		}
	}

	private static class AddLater implements Runnable {
		PrintJob job;
		private Printer printer;

		private AddLater(PrintJob job, Printer printer) {
			this.job = job;
			this.printer = printer;
		}

		public void run() {
			try {
				printer.sendAndWait(job);
			} catch (InterruptedException e) {
				// don't care
			}
		}
	}
}
