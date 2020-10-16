package com.pagosoft.app;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.pagosoft.eventbus.EventBus;
import com.pagosoft.eventbus.VetoableEvent;

import javax.swing.*;

public abstract class Application {
	@Inject
	EventBus eventBus;


	// Template methods for life cycle
	protected void initialize(String[] args) {}
	protected abstract void startup();
	protected void shutdown() {}

	public static void launch(final Class<? extends Application> app, final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Application inst = null;
				try {
					inst = app.newInstance();
					inst.eventBus = new EventBus();
					inst.initialize(args);
					inst.startup();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void launch(final Class<? extends Application> app, final String[] args, final Module ... modules) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Injector injector = Guice.createInjector(modules);
				Application inst = injector.getInstance(app);
				inst.initialize(args);
				inst.startup();
			}
		});
	}

	public final void exit() {
		ShutdownEvent shutdown = new ShutdownEvent(this);
		eventBus.fireSwingEvent(shutdown);
		if(!shutdown.isVetoed()) {
			shutdown();
			end();
		}
	}

	protected void end() {
		Runtime.getRuntime().exit(0);
	}

	public static final class ShutdownEvent extends VetoableEvent<Application> {
		public ShutdownEvent(Application source) {
			super(source);
		}
	}
}
