package com.pagosoft.app;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class SingleFrameApplication extends Application {
	protected abstract void configureFrame(JFrame frame);

	protected JFrame frame;

	@Override
	protected void startup() {
		frame = createFrame();
		setDefaultCloseOperation();
		configureFrame(frame);
		frame.setVisible(true);
	}

	protected JFrame createFrame() {
		JFrame frm = new JFrame();
		return frm;
	}

	protected void setDefaultCloseOperation() {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
	}

	@Override
	protected void shutdown() {
		super.shutdown();
		frame.setVisible(false);
	}
}
