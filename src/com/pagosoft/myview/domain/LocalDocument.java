/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 02.01.2010
 * Time: 14:18:06
 * To change this template use File | Settings | File Templates.
 */
public class LocalDocument implements Document {
	private File file;

	public LocalDocument(File file) {
		this.file = file;
		if(!file.exists()) {
			throw new IllegalStateException("There should not be a Document for a non-existent file!");
		}
	}

	public File getFile() {
		return file;
	}

	public URL getPath() {
		try {
			return file.toURI().toURL();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public InputStream openStream() {
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
		}
		// really can't happen as we throw
		// an exception if the file does not
		// exist in the constructor
		return null;
	}
}
