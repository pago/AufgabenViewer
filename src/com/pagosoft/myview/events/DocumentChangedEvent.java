/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.events;

import com.pagosoft.eventbus.ApplicationEvent;
import com.pagosoft.myview.domain.Document;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 03.01.2010
 * Time: 20:57:30
 * To change this template use File | Settings | File Templates.
 */
public class DocumentChangedEvent extends ApplicationEvent<Document> {
	public DocumentChangedEvent(Document source) {
		super(source);
	}
}
