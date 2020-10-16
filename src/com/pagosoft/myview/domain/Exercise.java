package com.pagosoft.myview.domain;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 31.12.2009
 * Time: 15:00:18
 * To change this template use File | Settings | File Templates.
 */
public class Exercise implements Iterable<Document> {
	private String id;
	private String title;
	private PathResolveStrategy resolveStrategy;

	private int versions;
	private int currentVersion;

	public Exercise(String id, String title, PathResolveStrategy resolveStrategy) {
		this.id = id;
		this.title = title;
		this.resolveStrategy = resolveStrategy;

		versions = resolveStrategy.getNumberOfFiles(id);
		if(versions < 1) {
			throw new IllegalArgumentException("Tried to load an exercise ("+id+") that has no documents.");
		}
		currentVersion = 1;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String toString() {
		return getTitle();
	}

	public Document getNextDocument() {
		Document document = resolveStrategy.getDocument(id, currentVersion);
		currentVersion = currentVersion % versions + 1;
		return document;
	}

	public Document getDocument(int num) {
		if(0 < num && num <= getNumberOfDocuments()) {
			return resolveStrategy.getDocument(id, num);
		}
		throw new IllegalArgumentException("num has to be between 1 and "+getNumberOfDocuments()
				+" but is "+num);
	}

	public int getNumberOfDocuments() {
		return versions;
	}

	public Iterator<Document> iterator() {
		return new ExerciseIterator(this);
	}

	private static class ExerciseIterator implements Iterator<Document> {
		private Exercise ex;
		private int current;

		private ExerciseIterator(Exercise ex) {
			this.ex = ex;
			current = 1;
		}

		public boolean hasNext() {
			return current <= ex.getNumberOfDocuments();
		}

		public Document next() {
			return ex.getDocument(current++);
		}

		public void remove() {
			// NOT SUPPORTED
		}
	}
}
