/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.domain;

import org.junit.Test;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 13:01:49
 * To change this template use File | Settings | File Templates.
 */
public class ExerciseRepositoryTest {
	@Test
	public void testGetHonorsBothParameters() throws Exception {
		// GIVEN
		// a PathResolveStrategy
		// AND
		// an ExerciseRepository
		PathResolveStrategy strat = createMock(PathResolveStrategy.class);
		expect(strat.getNumberOfFiles("foo")).andReturn(1);
		expect(strat.getNumberOfFiles("bar")).andReturn(1);
		expect(strat.getNumberOfFiles("foo")).andReturn(1);
		replay(strat);
		ExerciseRepository repo = new ExerciseRepository(strat);

		// WHEN
		// fetching two exercises with varying IDs (but equal titles)
		Exercise a = repo.get("foo", "Foobar");
		Exercise b = repo.get("bar", "Foobar");
		// AND
		// with varying titles (but equal IDs)
		Exercise c = repo.get("foo", "Bar");

		// THEN
		// they are expected to be different objects
		assertNotSame(a, b);
		assertNotSame(a, c);
	}

	@Test
	public void testGetReturnsTheSameObjectForAGivenID() throws Exception {
		// GIVEN
		// a PathResolveStrategy
		// AND
		// an ExerciseRepository
		PathResolveStrategy strat = createMock(PathResolveStrategy.class);
		expect(strat.getNumberOfFiles("foo")).andReturn(1);
		replay(strat);
		ExerciseRepository repo = new ExerciseRepository(strat);

		// WHEN
		// fetching the same exercise twice
		Exercise a = repo.get("foo", "Foobar");
		Exercise b = repo.get("foo", "Foobar");

		// THEN
		// they are expected to be the same object
		assertSame("Both instances of Exercise 'foo' are expected to be the same object", a, b);
	}
}