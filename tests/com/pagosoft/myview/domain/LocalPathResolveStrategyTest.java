package com.pagosoft.myview.domain;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.pagosoft.util.IterationUtil.until;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 31.12.2009
 * Time: 17:41:48
 * To change this template use File | Settings | File Templates.
 */
public class LocalPathResolveStrategyTest {
	@Test
	public void testNumberOfFiles() throws IOException {
		File root = new File("test_tmp");
		root.mkdir();
		File topic = new File(root, "fraction101");
		topic.mkdir();
		for(int i: until(20)) {
			File.createTempFile("test", "foo", topic).createNewFile();
		}

		// GIVEN
		PathResolveStrategy strat = new LocalPathResolveStrategy(root);

		// THEN
		assertEquals(20, strat.getNumberOfFiles("fraction101"));

		for(File f: topic.listFiles()) {
			f.delete();
		}
		topic.delete();
		root.delete();
	}

	@Test(expected = IllegalStateException.class)
	public void testGetDocumentDoesNotReturnInvalidDocuments() {
		// GIVEN
		PathResolveStrategy strat = new LocalPathResolveStrategy(new File("aufgaben"));

		// WHEN
		// accessing not existing document
		Document path = strat.getDocument("test___foobar", 1);

		// THEN
		// throws an exception
		fail("should have thrown an exception");
	}
}
