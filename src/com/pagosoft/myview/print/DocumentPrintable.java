/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.print;

import com.pagosoft.myview.domain.Document;
import com.pagosoft.myview.util.Utils;
import com.sun.pdfview.PDFFile;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 03.01.2010
 * Time: 19:10:18
 * To change this template use File | Settings | File Templates.
 */
public class DocumentPrintable implements Printable {
	private Document doc;

	public DocumentPrintable(Document doc) {
		this.doc = doc;
	}

	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		try {
			PDFFile file = Utils.toPDFFile(doc);
			Printable p = new PDFPrintable(file);
			return p.print(graphics, pageFormat, 0);
		} catch (IOException e) {
			return NO_SUCH_PAGE;
		}
	}
}
