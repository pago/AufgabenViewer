/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.domain;

import com.google.inject.Inject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 16:05:35
 * To change this template use File | Settings | File Templates.
 */
public class XMLTopicStructureReader implements TopicStructureReader {
	private List<Topic> topics;
	private ExerciseRepository repository;

	@Inject
	public XMLTopicStructureReader(List<Topic> topics, ExerciseRepository repository) {
		this.topics = topics;
		this.repository = repository;
	}

	public void read(InputStream structure) {
		try {
			SAXParserFactory.newInstance().newSAXParser().parse(structure, new DefinitionHandler(topics, repository));
		} catch (SAXException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		} catch (ParserConfigurationException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

	private static class DefinitionHandler extends DefaultHandler {
		private StringBuilder content;
		private List<Topic> topics;
		private ExerciseRepository repository;
		private TopicBuilder tBuilder;
		private String currentExerciseId;

		private DefinitionHandler(List<Topic> topics, ExerciseRepository repository) {
			this.topics = topics;
			this.repository = repository;

			content = new StringBuilder();
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			if("topic".equals(qName)) {
				tBuilder = TopicBuilder.create(attributes.getValue("id"), attributes.getValue("title"));
			} else if("exercise".equals(qName)) {
				currentExerciseId = attributes.getValue("id");
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			if("topic".equals(qName)) {
				topics.add(tBuilder.toTopic());
			} else if("exercise".equals(qName)) {
				tBuilder.add(repository.get(currentExerciseId, content.toString().trim()));
				content.delete(0, content.length());
				currentExerciseId = null;
			}
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			content.append(ch, start, length);
		}
	}
}
