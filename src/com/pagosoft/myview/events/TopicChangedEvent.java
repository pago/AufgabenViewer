/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.events;

import com.pagosoft.eventbus.ApplicationEvent;
import com.pagosoft.myview.domain.Topic;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 20:53:14
 * To change this template use File | Settings | File Templates.
 */
public class TopicChangedEvent extends ApplicationEvent<Topic> {
	public TopicChangedEvent(Topic source) {
		super(source);
	}
}
