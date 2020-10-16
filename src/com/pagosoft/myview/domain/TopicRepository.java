/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.domain;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.event.ListEventListener;
import ca.odell.glazedlists.event.ListEventPublisher;
import ca.odell.glazedlists.util.concurrent.ReadWriteLock;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 22:20:44
 * To change this template use File | Settings | File Templates.
 */
public class TopicRepository implements EventList<Topic> {
	private EventList<Topic> delegate;
	protected TopicRepository() {
		delegate = new BasicEventList<Topic>();
	}

	public void addListEventListener(ListEventListener<? super Topic> listEventListener) {
		delegate.addListEventListener(listEventListener);
	}

	public void removeListEventListener(ListEventListener<? super Topic> listEventListener) {
		delegate.removeListEventListener(listEventListener);
	}

	public ReadWriteLock getReadWriteLock() {
		return delegate.getReadWriteLock();
	}

	public ListEventPublisher getPublisher() {
		return delegate.getPublisher();
	}

	public void dispose() {
		delegate.dispose();
	}

	public int size() {
		return delegate.size();
	}

	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	public boolean contains(Object o) {
		return delegate.contains(o);
	}

	public Iterator<Topic> iterator() {
		return delegate.iterator();
	}

	public Object[] toArray() {
		return delegate.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return delegate.toArray(a);
	}

	public boolean add(Topic topic) {
		return delegate.add(topic);
	}

	public boolean remove(Object o) {
		return delegate.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return delegate.containsAll(c);
	}

	public boolean addAll(Collection<? extends Topic> c) {
		return delegate.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends Topic> c) {
		return delegate.addAll(index, c);
	}

	public boolean removeAll(Collection<?> c) {
		return delegate.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return delegate.retainAll(c);
	}

	public void clear() {
		delegate.clear();
	}

	public boolean equals(Object o) {
		return delegate.equals(o);
	}

	public int hashCode() {
		return delegate.hashCode();
	}

	public Topic get(int index) {
		return delegate.get(index);
	}

	public Topic set(int index, Topic element) {
		return delegate.set(index, element);
	}

	public void add(int index, Topic element) {
		delegate.add(index, element);
	}

	public Topic remove(int index) {
		return delegate.remove(index);
	}

	public int indexOf(Object o) {
		return delegate.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return delegate.lastIndexOf(o);
	}

	public ListIterator<Topic> listIterator() {
		return delegate.listIterator();
	}

	public ListIterator<Topic> listIterator(int index) {
		return delegate.listIterator(index);
	}

	public List<Topic> subList(int fromIndex, int toIndex) {
		return delegate.subList(fromIndex, toIndex);
	}
}
