/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.domain;

import org.easymock.EasyMock;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.*;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertArrayEquals;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 16:02:47
 * To change this template use File | Settings | File Templates.
 */
public class XMLTopicStructureReaderTest {
	@Test
	public void testRead() {
		// GIVEN
		PathResolveStrategy strat = createMock(PathResolveStrategy.class);
		expect(strat.getNumberOfFiles(EasyMock.<String>anyObject())).andReturn(20).anyTimes();
		replay(strat);

		ExerciseRepository repo = new ExerciseRepository(strat);
		List<Topic> topics = new ArrayList<Topic>();

		TopicStructureReader reader = new XMLTopicStructureReader(topics, repo);
		String[] expectedIDs = { "fraction101", "fraction102", "fraction103", "foo1", "foo2" };

		// WHEN
		InputStream is = null;
		try {
			is = XMLTopicStructureReaderTest.class.getResourceAsStream("Structure.xml");
			reader.read(is);
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}

		// THEN
		assertEquals(2, topics.size());
		assertEquals("fraction", topics.get(0).getId());
		assertEquals(3, topics.get(0).getNumberOfExercises());
		List<String> ids = new ArrayList<String>();
		for(Topic t: topics) {
			for(Exercise ex: t) {
				ids.add(ex.getId());
			}
		}
		assertArrayEquals(expectedIDs, ids.toArray());
	}
}
