/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.app;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.pagosoft.myview.print.AsyncPrintService;
import com.pagosoft.myview.print.DefaultPrintService;
import com.pagosoft.myview.print.PrintService;
import com.pagosoft.myview.print.PrintServiceDelegate;

import java.awt.print.PrinterJob;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 03.01.2010
 * Time: 20:42:36
 * To change this template use File | Settings | File Templates.
 */
public class PrintModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(PrintService.class).annotatedWith(PrintServiceDelegate.class).to(DefaultPrintService.class).in(Singleton.class);
		bind(PrintService.class).to(AsyncPrintService.class).in(Singleton.class);
		bind(BlockingQueue.class).to(LinkedBlockingQueue.class);
	}

	@Provides
	PrinterJob providePrinterJob() {
		return PrinterJob.getPrinterJob();
	}
}
