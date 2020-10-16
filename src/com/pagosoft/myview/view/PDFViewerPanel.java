/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.view;

import com.google.inject.Inject;
import com.pagosoft.eventbus.ApplicationListener;
import com.pagosoft.eventbus.EventBus;
import com.pagosoft.myview.domain.Document;
import com.pagosoft.myview.events.DocumentChangedEvent;
import com.pagosoft.myview.events.ExerciseChangedEvent;
import com.pagosoft.myview.util.Utils;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.ByteBuffer;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 23:16:48
 * To change this template use File | Settings | File Templates.
 */
public class PDFViewerPanel extends JPanel {
	@Inject
	public PDFViewerPanel(final EventBus eventBus) {
		super(new BorderLayout());

		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(new Color(0x676767), 20));
		setPreferredSize(new Dimension(700, 900));

		// fix potential repaint issues
		final PagePanel viewer = new PagePanel();
		viewer.setBackground(Color.WHITE);

		add(viewer, BorderLayout.CENTER);

		eventBus.add(ExerciseChangedEvent.class, new ApplicationListener<ExerciseChangedEvent>() {
			public void handleEvent(ExerciseChangedEvent event) {
				//load a pdf from a byte buffer
				Document doc = event.getSource().getNextDocument();
				eventBus.fireSwingEvent(new DocumentChangedEvent(doc));
				
				try {
					PDFFile pdffile = Utils.toPDFFile(doc);

					// show the first page
					PDFPage page = pdffile.getPage(0);
					viewer.showPage(page);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
