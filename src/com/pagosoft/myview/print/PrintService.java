/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.print;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 03.01.2010
 * Time: 19:05:06
 * To change this template use File | Settings | File Templates.
 */
public interface PrintService {
	public void print(PrintJob pageable);
	public boolean showPrintDialog();
}
