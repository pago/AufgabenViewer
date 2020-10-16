/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.view;

import com.google.inject.Inject;
import com.pagosoft.eventbus.ApplicationListener;
import com.pagosoft.eventbus.EventBus;
import com.pagosoft.myview.domain.Exercise;
import com.pagosoft.myview.domain.Topic;
import com.pagosoft.myview.events.TopicChangedEvent;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 20:10:09
 * To change this template use File | Settings | File Templates.
 */
public class TopicListModel extends AbstractListModel {
	private Topic topic;

	@Inject
	public TopicListModel(final EventBus eventBus) {
		this.topic = null;

		eventBus.add(TopicChangedEvent.class, new ApplicationListener<TopicChangedEvent>() {
			public void handleEvent(TopicChangedEvent event) {
				replaceTopic(event.getSource());
			}
		});
	}

	private void replaceTopic(Topic t) {
		fireIntervalRemoved(this, 0, getSize());
		topic = t;
		fireIntervalAdded(this, 0, getSize());
	}

	public int getSize() {
		return topic != null ? topic.getNumberOfExercises() : 0;
	}

	public Exercise getElementAt(int index) {
		return topic != null ? topic.getExercise(index) : null;
	}
}
