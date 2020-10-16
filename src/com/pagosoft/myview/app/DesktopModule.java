/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.app;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.swing.EventListModel;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.pagosoft.action.ActionManager;
import com.pagosoft.eventbus.EventBus;
import com.pagosoft.myview.actions.GuiceActionManager;
import com.pagosoft.myview.domain.Topic;
import com.pagosoft.myview.view.*;

import javax.swing.*;

import static com.google.inject.name.Names.named;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 21:57:14
 * To change this template use File | Settings | File Templates.
 */
public class DesktopModule extends AbstractModule {
	private EventList<Topic> topics;

	public DesktopModule(EventList<Topic> topics) {
		super();
		this.topics = topics;
	}

	@Override
	protected void configure() {
		// define standard objects
		bind(EventBus.class).in(Singleton.class);

		// define exercises list
		bind(JComponent.class).annotatedWith(named("ExerciseList")).to(ExerciseList.class);
		bind(ListModel.class).annotatedWith(named("Exercises")).to(TopicListModel.class);

		// define topic list
		bind(JComponent.class).annotatedWith(named("TopicList")).to(TopicList.class);

		// define pdf-viewer
		//bind(JComponent.class).annotatedWith(named("PDFViewer")).to(IcePDFViewerPanel.class);
		bind(JComponent.class).annotatedWith(named("PDFViewer")).to(PDFViewerPanel.class);

		// define toolbar
		bind(JToolBar.class).to(MyViewToolBar.class);

		bind(ActionManager.class).to(GuiceActionManager.class);

		// define application
		bind(MyView.class);
	}

	@Provides @Named("Topics")
	ListModel provideListModel() {
		return new EventListModel<Topic>(topics);
	}
}
