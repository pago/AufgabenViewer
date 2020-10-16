/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.print;

import com.pagosoft.myview.domain.Exercise;
import com.pagosoft.myview.domain.LocalPathResolveStrategy;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.File;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 03.01.2010
 * Time: 19:36:36
 * To change this template use File | Settings | File Templates.
 */
public class DefaultPrintServiceTest {
	//@Test
	public void testPrint() {
		PrinterJob pjob = PrinterJob.getPrinterJob();
		PrintService ps = new DefaultPrintService(pjob);
		if(tryToUseXPSPrintService(pjob) || ps.showPrintDialog()) {
			Exercise ex = new Exercise("fraction101", "101 Brüche umwandeln",
					new LocalPathResolveStrategy(new File("aufgaben")));
			//*
			ExercisePrintJob pages = new ExercisePrintJob(ex);
			assertEquals(20, pages.getNumberOfPages());
			ps.print(pages);//*/
			//ps.print(new DocumentPrintable(ex.getNextDocument()));
			assertTrue(true);
		}
	}

	private boolean tryToUseXPSPrintService(PrinterJob pjob) {
		for(javax.print.PrintService s: PrinterJob.lookupPrintServices()) {
			if(s.getName().contains(" XPS ")) {
				try {
					pjob.setPrintService(s);
				} catch (PrinterException e) {
					continue;
				}
				return true;
			}
		}
		return false;
	}

	@Test
	public void testExercisePageable() {
		PrinterJob pjob = PrinterJob.getPrinterJob();
		PrintService ps = new DefaultPrintService(pjob);
		Exercise ex = new Exercise("fraction101", "101 Brüche umwandeln",
				new LocalPathResolveStrategy(new File("aufgaben")));
		ExercisePrintJob pages = new ExercisePrintJob(ex);
		assertEquals(20, pages.getNumberOfPages());

		BufferedImage bi = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
		for(int i = 0; i < pages.getNumberOfPages(); i++) {
			Printable p = pages.getPrintable(i);
			try {
				int expected = Printable.PAGE_EXISTS;
				assertEquals(expected, p.print(bi.createGraphics(), pjob.getPageFormat(null), i));
			} catch (PrinterException e) {
				fail();
			}
		}
	}
}
