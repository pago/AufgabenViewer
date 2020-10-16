/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.view;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.pagosoft.eventbus.EventBus;
import com.pagosoft.myview.domain.Exercise;
import com.pagosoft.myview.events.ExerciseChangedEvent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 20:54:48
 * To change this template use File | Settings | File Templates.
 */
public class ExerciseList extends NamedList {
	@Inject
	public ExerciseList(@Named("Exercises") ListModel model, final EventBus eventBus) {
		super("Aufgaben", model);

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				eventBus.fireEvent(new ExerciseChangedEvent((Exercise)getList().getSelectedValue()));
			}
		});
	}
}
