/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.view;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.pagosoft.app.Application;
import com.pagosoft.app.SingleFrameApplication;
import com.pagosoft.myview.app.DesktopModule;
import com.pagosoft.myview.app.DomainModule;
import com.pagosoft.myview.app.PrintModule;
import com.pagosoft.myview.domain.Topic;
import com.pagosoft.myview.domain.TopicStructureReader;
import com.pagosoft.plaf.PlafOptions;
import com.pagosoft.plaf.themes.VistaTheme;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 19:06:45
 * To change this template use File | Settings | File Templates.
 */
public class MyView extends SingleFrameApplication {
	private JComponent topics;
	private JComponent exercises;
	private JComponent pdfViewer;
	private JToolBar toolBar;

	@Inject
	public MyView(@Named("TopicList") JComponent topics, @Named("ExerciseList") JComponent exercises,
			   @Named("PDFViewer") JComponent pdfViewer, JToolBar toolBar) {
		this.topics = topics;
		this.exercises = exercises;
		this.pdfViewer = pdfViewer;
		this.toolBar = toolBar;
	}

	@Override
	protected void configureFrame(JFrame frame) {
		frame.setTitle("µViewer 1.0 - Pagosoft");

		JPanel sidebar = new JPanel(new MigLayout("wrap 1", "[grow, fill]", "[|fill, grow]"));
		sidebar.add(topics);
		sidebar.add(exercises);
		frame.add(sidebar, BorderLayout.LINE_START);
		frame.add(pdfViewer);
		frame.add(toolBar, BorderLayout.PAGE_START);

		frame.pack();
	}

	public static void main(String[] args) {
		EventList<Topic> topics = new BasicEventList<Topic>();

		// set lookandfeel
		try {
			PlafOptions.setCurrentTheme(new VistaTheme());
			PlafOptions.setOfficeScrollBarEnabled(false);
			PlafOptions.setAsLookAndFeel();
		} catch(Exception e) {
			e.printStackTrace();
		}

		// start application UI
		Application.launch(MyView.class, args, new DesktopModule(topics), new PrintModule());

		// start domain logic
		Injector injector = Guice.createInjector(new DomainModule(topics));
		TopicStructureReader reader = injector.getInstance(TopicStructureReader.class);
		InputStream is = null;
		try {
			is = new FileInputStream(new File(injector.getInstance(File.class), "Structure.xml"));
			reader.read(is);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
