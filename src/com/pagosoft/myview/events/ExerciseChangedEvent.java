/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.events;

import com.pagosoft.eventbus.ApplicationEvent;
import com.pagosoft.myview.domain.Exercise;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 21:05:10
 * To change this template use File | Settings | File Templates.
 */
public class ExerciseChangedEvent extends ApplicationEvent<Exercise> {
	public ExerciseChangedEvent(Exercise source) {
		super(source);
	}
}
