/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.util;

import com.pagosoft.myview.domain.Document;
import com.pagosoft.myview.domain.LocalDocument;
import com.sun.pdfview.PDFFile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 03.01.2010
 * Time: 19:13:54
 * To change this template use File | Settings | File Templates.
 */
public class Utils {
	public static PDFFile toPDFFile(Document doc) throws IOException {
		File file = ((LocalDocument)doc).getFile();

		RandomAccessFile raf = new RandomAccessFile(file, "r");
		FileChannel channel = raf.getChannel();

		ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY,
			0, channel.size());
		PDFFile pdffile = new PDFFile(buf);
		return pdffile;
	}
}
