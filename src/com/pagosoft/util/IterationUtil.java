/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.util;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 14:56:46
 * To change this template use File | Settings | File Templates.
 */
public class IterationUtil {
	public static Iterable<Integer> until(int i) {
		return range(0, i);
	}

	public static Iterable<Integer> range(int from, int to) {
		return new RangeIterable(from, to);
	}

	private static class RangeIterable implements Iterable<Integer>, Iterator<Integer> {
		private int to;
		private int current;

		private RangeIterable(int from, int to) {
			this.to = to;
			current = from;
		}

		public Iterator<Integer> iterator() {
			return this;
		}

		public boolean hasNext() {
			return current < to;
		}

		public Integer next() {
			return current++;
		}

		public void remove() {
		}
	}
}
