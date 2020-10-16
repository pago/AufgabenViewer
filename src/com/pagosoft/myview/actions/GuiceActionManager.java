/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.actions;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.pagosoft.action.ActionManager;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 03.01.2010
 * Time: 20:46:27
 * To change this template use File | Settings | File Templates.
 */
public class GuiceActionManager extends ActionManager {
	private Injector injector;

	@Inject
	public GuiceActionManager(Injector injector) {
		this.injector = injector;
	}

	@Override
	public <K extends Action> K get(Class<K> kClass) {
		return injector.getInstance(kClass);
	}
}
