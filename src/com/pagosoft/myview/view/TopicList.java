/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.view;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.pagosoft.eventbus.EventBus;
import com.pagosoft.myview.domain.Topic;
import com.pagosoft.myview.events.TopicChangedEvent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 19:54:03
 * To change this template use File | Settings | File Templates.
 */
public class TopicList extends NamedList {
	@Inject
	public TopicList(@Named("Topics") ListModel model, final EventBus eventBus) {
		super("Themen", model);

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				eventBus.fireEvent(new TopicChangedEvent((Topic)getList().getSelectedValue()));
			}
		});
	}
}
