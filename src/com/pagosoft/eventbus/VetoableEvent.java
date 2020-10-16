package com.pagosoft.eventbus;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 31.12.2009
 * Time: 12:39:59
 * To change this template use File | Settings | File Templates.
 */
public class VetoableEvent<T> extends ApplicationEvent<T> {
	private static boolean veto = false;
	public VetoableEvent(T source) {
		super(source);
	}

	public void doVeto() {
		this.veto = true;
	}

	public boolean isVetoed() {
		return veto;
	}
}
