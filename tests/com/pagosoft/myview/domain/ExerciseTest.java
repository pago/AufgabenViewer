package com.pagosoft.myview.domain;

import org.junit.*;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 31.12.2009
 * Time: 15:38:19
 * To change this template use File | Settings | File Templates.
 */
public class ExerciseTest {
	@Test
	public void testGetDocument() {
		// GIVEN
		// an Exercise with a PathResolveStrategy
		Document document = createMock(Document.class);
		PathResolveStrategy strat = createMock(PathResolveStrategy.class);
		expect(strat.getNumberOfFiles("foo")).andReturn(2).anyTimes();
		expect(strat.getDocument("foo", 2)).andReturn(document);
		replay(strat);

		Exercise ex = new Exercise("foo", "Foobar", strat);
		assertSame(document, ex.getDocument(2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetDocumentThrowsExceptionWhenNumberIsBelow1() {
		// GIVEN
		// an Exercise with a PathResolveStrategy
		PathResolveStrategy strat = createMock(PathResolveStrategy.class);
		expect(strat.getNumberOfFiles("foo")).andReturn(2).anyTimes();
		replay(strat);

		Exercise ex = new Exercise("foo", "Foobar", strat);
		Document document = ex.getDocument(0);
		fail("Exception should have been thrown");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetDocumentThrowsExceptionWhenNumberIsOutOfRange() {
		// GIVEN
		// an Exercise with a PathResolveStrategy
		PathResolveStrategy strat = createMock(PathResolveStrategy.class);
		expect(strat.getNumberOfFiles("foo")).andReturn(2).anyTimes();
		replay(strat);

		Exercise ex = new Exercise("foo", "Foobar", strat);
		Document document = ex.getDocument(3);
		fail("Exception should have been thrown");
	}

	@Test
	public void testGetNextDocument() {
		// GIVEN
		// a PathResolveStrategy
		PathResolveStrategy strat = createMock(PathResolveStrategy.class);
		expect(strat.getNumberOfFiles("fraction101")).andReturn(2);

		Document doc1 = new MockDocument();
		Document doc2 = new MockDocument();

		expect(strat.getDocument("fraction101", 1)).andReturn(doc1);
		expect(strat.getDocument("fraction101", 2)).andReturn(doc2);
		expect(strat.getDocument("fraction101", 1)).andReturn(doc1);

		replay(strat);

		// AND
		// an exercise
		Exercise ex = new Exercise("fraction101", "Fraction101", strat);

		// THEN
		assertSame(doc1, ex.getNextDocument());
		assertSame(doc2, ex.getNextDocument());
		assertSame(doc1, ex.getNextDocument());
	}

	@Test
	public void testIterator() {
		// GIVEN
		// a PathResolveStrategy
		PathResolveStrategy strat = createMock(PathResolveStrategy.class);
		expect(strat.getNumberOfFiles("fraction101")).andReturn(2);

		Document doc1 = new MockDocument();
		Document doc2 = new MockDocument();
		Document[] expected = { doc1, doc2 };

		expect(strat.getDocument("fraction101", 1)).andReturn(doc1);
		expect(strat.getDocument("fraction101", 2)).andReturn(doc2);

		replay(strat);

		// AND
		// an exercise
		Exercise ex = new Exercise("fraction101", "Fraction101", strat);

		// THEN
		List<Document> docs = new ArrayList<Document>();
		for(Document doc: ex) {
			docs.add(doc);
		}
		assertArrayEquals(expected, docs.toArray());
	}

	static class MockDocument implements Document {
		public InputStream openStream() {
			return null;
		}

		public URL getPath() {
			return null;
		}
	}
}
