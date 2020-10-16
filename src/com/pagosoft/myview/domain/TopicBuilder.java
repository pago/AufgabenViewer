/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 12:37:50
 * To change this template use File | Settings | File Templates.
 */
public class TopicBuilder {
	public static TopicBuilder create(String id, String title) {
		return new TopicBuilder(id, title);
	}

	private List<Exercise> exercises;
	private String id, title;

	private TopicBuilder(String id, String title) {
		this.id = id;
		this.title = title;
		exercises = new ArrayList<Exercise>();
	}

	public TopicBuilder add(Exercise ex) {
		exercises.add(ex);
		return this;
	}

	public Topic toTopic() {
		return new Topic(id, title, Collections.unmodifiableList(exercises));
	}
}
