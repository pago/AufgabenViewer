/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.print;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 03.01.2010
 * Time: 19:25:18
 * To change this template use File | Settings | File Templates.
 */
public interface PrintJob extends Pageable {
	public String getJobName();
	public void setFormat(PageFormat format);
	public int getCopies();
}
