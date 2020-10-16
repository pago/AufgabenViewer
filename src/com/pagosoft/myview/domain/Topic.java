package com.pagosoft.myview.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 31.12.2009
 * Time: 14:59:20
 * To change this template use File | Settings | File Templates.
 */
public class Topic implements Iterable<Exercise> {
	private String id;
	private String title;
	private List<Exercise> exercises;

	public Topic(String id, String title) {
		this.id = id;
		this.title = title;
		this.exercises = new ArrayList<Exercise>();
	}

	public Topic(String id, String title, List<Exercise> exercises) {
		this.id = id;
		this.title = title;
		this.exercises = exercises;
	}

	public Iterator<Exercise> iterator() {
		return exercises.iterator();
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getNumberOfExercises() {
		return exercises.size();
	}

	public Exercise getExercise(int index) {
		return exercises.get(index);
	}

	public String toString() {
		return getTitle();
	}
}
