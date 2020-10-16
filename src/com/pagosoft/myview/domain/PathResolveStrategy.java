package com.pagosoft.myview.domain;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 31.12.2009
 * Time: 15:23:12
 * To change this template use File | Settings | File Templates.
 */
public interface PathResolveStrategy {
	public Document getDocument(String id, int num);
	public int getNumberOfFiles(String id);
}
