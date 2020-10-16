/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.domain;

import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 12:52:02
 * To change this template use File | Settings | File Templates.
 */
public class ExerciseRepository {
	private Map<Object, Exercise> cache;
	private PathResolveStrategy strategy;

	@Inject
	public ExerciseRepository(PathResolveStrategy strategy) {
		this.strategy = strategy;
		cache = new HashMap<Object, Exercise>();
	}

	public Exercise get(String id, String title) {
		Object key = key(id, title);
		Exercise ex = cache.get(key);
		if(ex == null) {
			ex = new Exercise(id, title, strategy);
			cache.put(key, ex);
		}
		return ex;
	}

	private Object key(String id, String title) {
		return new StringBuilder().append(id).append(title).toString();
	}
}
