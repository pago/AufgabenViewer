/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.domain;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 14:09:40
 * To change this template use File | Settings | File Templates.
 */
public interface Document {
	public InputStream openStream();
	public URL getPath();
}
