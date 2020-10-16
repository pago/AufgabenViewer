/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.print;

import com.pagosoft.myview.domain.Document;

import java.awt.print.PageFormat;
import java.awt.print.Printable;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 04.01.2010
 * Time: 21:23:16
 * To change this template use File | Settings | File Templates.
 */
public class DocumentPrintJob implements PrintJob {
	private Document doc;
	private PageFormat format;
	private int copies;

	public DocumentPrintJob(Document doc, int copies) {
		this.doc = doc;
		this.copies = copies;
	}

	public DocumentPrintJob(Document doc) {
		this(doc, 1);
	}

	public String getJobName() {
		return doc.getPath().getFile();
	}

	public void setFormat(PageFormat format) {
		this.format = format;
	}

	public int getCopies() {
		return copies;
	}

	public int getNumberOfPages() {
		return 1;
	}

	public PageFormat getPageFormat(int pageIndex) throws IndexOutOfBoundsException {
		return format;
	}

	public Printable getPrintable(int pageIndex) throws IndexOutOfBoundsException {
		return new DocumentPrintable(doc);
	}
}
