/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.print;

import com.pagosoft.eventbus.ApplicationEvent;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 04.01.2010
 * Time: 22:01:06
 * To change this template use File | Settings | File Templates.
 */
public class PrintJobEvent extends ApplicationEvent<String> {
	private boolean done;
	public PrintJobEvent(String source, boolean done) {
		super(source);
		this.done = done;
	}

	public boolean isDone() {
		return done;
	}
}
