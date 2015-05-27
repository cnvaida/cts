package teste;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import delphi.netstudent.model.Materii;

public class MateriiTest {
	Materii materie;

	@Before
	public void setUp() throws Exception {
		materie = new Materii("Matematica");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMateriiString() {
		assertEquals("Matematica", materie.getDenumire());
	}

	@Test
	public void testSetId() {
		try {
			materie.setId(-1);
			fail("Id negativ");
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testSetDenumireNull() {
		try {
			materie.setDenumire(null);
			fail("Denumire null");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSetDenumireEmpty() {
		try {
			materie.setDenumire("");
			fail("Denumire empty");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSetDenumireCorect() {
		materie.setDenumire("Algebra");
		assertEquals("Algebra", materie.getDenumire());
	}
	
	@Test
	public void testSetIdCorect() {
		materie.setId(1);
		assertEquals(1, materie.getId());
	}

}
