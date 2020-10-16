/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.app;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.pagosoft.myview.domain.*;

import java.io.File;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 22:52:57
 * To change this template use File | Settings | File Templates.
 */
public class DomainModule extends AbstractModule {
	private List<Topic> topics;

	public DomainModule(List<Topic> topics) {
		super();
		this.topics = topics;
	}

	@Override
	protected void configure() {
		bind(PathResolveStrategy.class).to(LocalPathResolveStrategy.class);
		bind(ExerciseRepository.class).in(Singleton.class);
		bind(TopicStructureReader.class).to(XMLTopicStructureReader.class);
	}

	@Provides
	List<Topic> provideList() {
		return topics;
	}

	@Provides
	File provideFile() {
		return new File("aufgaben");
	}
}
