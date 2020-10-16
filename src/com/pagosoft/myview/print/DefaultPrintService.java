/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.print;

import com.google.inject.Inject;

import java.awt.print.*;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 03.01.2010
 * Time: 19:27:19
 * To change this template use File | Settings | File Templates.
 */
public class DefaultPrintService implements PrintService {
	private PrinterJob pjob;
	private PageFormat pfDefault;

	@Inject
	public DefaultPrintService(PrinterJob pjob) {
		this.pjob = pjob;
		pfDefault = pjob.defaultPage();
        Paper defaultPaper = new Paper();

        defaultPaper.setImageableArea(0, 0, defaultPaper.getWidth(), defaultPaper.getHeight());
        pfDefault.setPaper(defaultPaper);
	}

	public void print(PrintJob pageable) {
		pjob.setJobName(pageable.getJobName());
		pjob.setCopies(pageable.getCopies());

		// validate the page against the chosen printer to correct
		// paper settings and margins
		pfDefault = pjob.validatePage(pfDefault);
		pageable.setFormat(pfDefault);
		pjob.setPageable(pageable);

		try {
			pjob.print();
		} catch (PrinterException exc) {
			System.out.println(exc);
		}
	}

	public boolean showPrintDialog() {
		return pjob.printDialog();
	}
}
