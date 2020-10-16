package com.pagosoft.myview.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 31.12.2009
 * Time: 15:02:00
 * To change this template use File | Settings | File Templates.
 */
public class TopicTest {
	@Test
	public void testTopicIsIterable() {
		// GIVEN
		// a topic with two exercises
		String[] expected = { "Brüche als Division", "Brüche als Division mit Rest" };
		PathResolveStrategy resolveStrategy = createMock(PathResolveStrategy.class);
		expect(resolveStrategy.getNumberOfFiles("fraction101")).andReturn(1);
		expect(resolveStrategy.getNumberOfFiles("fraction102")).andReturn(1);
		replay(resolveStrategy);
		Topic topic = TopicBuilder.create("fraction", "Brüche")
			.add(new Exercise("fraction101", expected[0], resolveStrategy))
			.add(new Exercise("fraction102", expected[1], resolveStrategy))
			.toTopic();

		// WHEN
		// iterating over all exercises
		// AND
		// collecting their titles
		int i = 0;
		List<String> titles = new ArrayList<String>();
		for(Exercise ex: topic) {
			i++;
			titles.add(ex.getTitle());
		}

		// THEN
		assertEquals("Topic should have been iterated", 2, i);
		assertArrayEquals(expected, titles.toArray(new String[titles.size()]));
	}

	@Test
	public void testEmptyTopicIsNotIterable() {
		// given
		// an empty topic
		Topic topic = new Topic("fraction", "Brüche");
		int i = 0;
		// when
		// iterating over all exercises
		for(Exercise ex: topic) {
			i++;
		}
		// then
		assertEquals("Topic should not have been iterated", 0, i);
	}
}
