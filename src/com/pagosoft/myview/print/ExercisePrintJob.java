/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.print;

import com.pagosoft.myview.domain.Exercise;

import java.awt.print.PageFormat;
import java.awt.print.Printable;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 03.01.2010
 * Time: 19:19:34
 * To change this template use File | Settings | File Templates.
 */
public class ExercisePrintJob implements PrintJob {
	private Exercise exercise;
	private PageFormat format;
	private int copies;

	public ExercisePrintJob(Exercise exercise, int copies) {
		this.exercise = exercise;
		this.copies = copies;
	}

	public ExercisePrintJob(Exercise exercise) {
		this(exercise, 1);
	}

	public String getJobName() {
		return exercise.getTitle();
	}

	public int getNumberOfPages() {
		return exercise.getNumberOfDocuments();
	}

	public void setFormat(PageFormat format) {
		this.format = format;
	}

	public int getCopies() {
		return copies;
	}

	public PageFormat getPageFormat(int pageIndex) throws IndexOutOfBoundsException {
		return format;
	}

	public Printable getPrintable(int pageIndex) throws IndexOutOfBoundsException {
		return new DocumentPrintable(exercise.getDocument(pageIndex+1));
	}
}
