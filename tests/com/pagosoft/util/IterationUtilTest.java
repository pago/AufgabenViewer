/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.util;

import org.junit.Test;

import static com.pagosoft.util.IterationUtil.range;
import static com.pagosoft.util.IterationUtil.until;
import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 15:06:34
 * To change this template use File | Settings | File Templates.
 */
public class IterationUtilTest {
	@Test
	public void testUntil() throws Exception {
		int i = 0;
		for(int j: until(10)) {
			i++;
		}
		assertEquals(10, i);		
	}

	@Test
	public void testRange() throws Exception {
		int i = 2;
		int v = 0;
		for(int j: range(2, 10)) {
			v += i - j;
			i++;
		}
		assertEquals(0, v); 
	}
}
