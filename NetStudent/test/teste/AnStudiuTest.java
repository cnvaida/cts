package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import delphi.netstudent.model.AnStudiu;

public class AnStudiuTest {
	private AnStudiu anStudiu;
	
	@Before
	public void setUp() throws Exception {
		anStudiu = new AnStudiu("Anul 2");
	}

	@Test
	public void testAnStudiuString() {
		assertEquals("Anul 2", anStudiu.getNume());
	}

	@Test
	public void testSetNumeNull() {
		try {
			anStudiu.setNume(null);
			fail("Nu trebuia sa permita setarea unui nume null");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSetNumeGol() {
		try {
			anStudiu.setNume("");
			fail("Nu trebuia sa permita setarea unui nume null");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSetNumeCorect() {
		anStudiu.setNume("II");
		assertEquals("II", anStudiu.getNume());
	}

}
