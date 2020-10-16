/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.view;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.swing.EventListModel;
import com.pagosoft.eventbus.EventBus;
import com.pagosoft.myview.domain.Exercise;
import com.pagosoft.myview.domain.PathResolveStrategy;
import com.pagosoft.myview.domain.Topic;
import com.pagosoft.myview.domain.TopicBuilder;
import org.easymock.EasyMock;
import org.junit.Test;

import javax.swing.*;

import java.lang.reflect.InvocationTargetException;

import static junit.framework.Assert.assertEquals;
import static org.easymock.EasyMock.*;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 20:01:14
 * To change this template use File | Settings | File Templates.
 */
public class TopicListTest {
	@Test
	public void testListSynchronzing() throws InvocationTargetException, InterruptedException {
		PathResolveStrategy strat = createMock(PathResolveStrategy.class);
		expect(strat.getNumberOfFiles(EasyMock.<String>anyObject())).andReturn(20).anyTimes();
		replay(strat);
		Topic topicA = TopicBuilder.create("a", "A")
				.add(new Exercise("a1", "a1", strat))
				.add(new Exercise("a2", "a2", strat))
				.toTopic();
		Topic topicB = TopicBuilder.create("b", "B")
				.add(new Exercise("b1", "b1", strat))
				.add(new Exercise("b2", "b2", strat))
				.add(new Exercise("b3", "b3", strat))
				.toTopic();

		EventBus eventBus = new EventBus();
		ListModel model = new EventListModel<Topic>(GlazedLists.eventListOf(topicA, topicB));
		final TopicListModel topicModel = new TopicListModel(eventBus);
		TopicList list = new TopicList(model, eventBus);
		list.getList().setSelectedIndex(0);

		assertEquals(2, topicModel.getSize());
		assertEquals("a1", topicModel.getElementAt(0).getId());

		list.getList().setSelectedIndex(1);
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				assertEquals(3, topicModel.getSize());
				assertEquals("b1", topicModel.getElementAt(0).getId());
			}
		});

	}
}
