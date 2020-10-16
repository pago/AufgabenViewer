/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.actions;

import com.google.inject.Inject;
import com.pagosoft.action.AbstractSystemAction;
import com.pagosoft.eventbus.ApplicationListener;
import com.pagosoft.eventbus.EventBus;
import com.pagosoft.myview.domain.Exercise;
import com.pagosoft.myview.events.ExerciseChangedEvent;
import com.pagosoft.myview.print.ExercisePrintJob;
import com.pagosoft.myview.print.PrintService;

import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 03.01.2010
 * Time: 22:21:10
 * To change this template use File | Settings | File Templates.
 */
public class PrintExercise extends AbstractSystemAction implements ApplicationListener<ExerciseChangedEvent> {
	private PrintService ps;
	private Exercise ex;

	@Inject
	public PrintExercise(EventBus eventBus, PrintService ps) {
		super("print.exercise");
		this.ps = ps;
		eventBus.add(this, ExerciseChangedEvent.class);
		setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		ps.print(new ExercisePrintJob(ex));
	}

	public void handleEvent(ExerciseChangedEvent event) {
		ex = event.getSource();
		setEnabled(ex != null);
	}
}
