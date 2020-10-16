/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.actors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 04.01.2010
 * Time: 23:05:49
 * To change this template use File | Settings | File Templates.
 */
public abstract class Actor<T> {
	private BlockingQueue<T> queue;
	private Thread thread;

	protected Actor() {
		queue = new LinkedBlockingQueue<T>();
		thread = new Thread(new MessageBox<T>(this, queue));
	}

	protected abstract void act(T message);

	public final void start() {
		thread.start();
	}

	public final void interrupt() {
		thread.interrupt();
	}

	public final boolean isAlive() {
		return thread.isAlive();
	}

	public boolean send(T message) {
		return queue.add(message);
	}

	public void sendAndWait(T message) throws InterruptedException {
		queue.put(message);
	}

	public boolean hasEmptyQueue() {
		return queue.isEmpty();
	}

	private static class MessageBox<T> implements Runnable {
		private Actor<T> actor;
		private BlockingQueue<T> queue;

		private MessageBox(Actor<T> actor, BlockingQueue<T> queue) {
			this.actor = actor;
			this.queue = queue;
		}

		public void run() {
			while(true) {
				try {
					T msg = queue.take();
					actor.act(msg);
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}
}
