package com.pagosoft.myview.domain;

import com.google.inject.Inject;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 31.12.2009
 * Time: 17:36:56
 * To change this template use File | Settings | File Templates.
 */
public class LocalPathResolveStrategy implements PathResolveStrategy {
	private File root;

	@Inject
	public LocalPathResolveStrategy(File root) {
		this.root = root;
	}

	public Document getDocument(String id, int num) {
		File f = new File(getRoot(id), String.format("%s_%d.pdf", id, num));
		return new LocalDocument(f);
	}

	private File getRoot(String id) {
		return new File(root, id);
	}

	public int getNumberOfFiles(String id) {
		return getRoot(id).listFiles().length;
	}
}
