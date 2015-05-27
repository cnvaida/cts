package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import delphi.netstudent.model.Anunt;

public class AnuntTest {

	Anunt anunt;

	@Before
	public void setUp() throws Exception {
		anunt = new Anunt("mesaj");
	}

	@Test
	public void testAnuntString() {
		assertEquals("mesaj", anunt.getAnunt());
	}

	@Test
	public void testSetIdNegativ() {
		try {
			anunt.setId(-4);
			fail("Idul nu poate fi negativ");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSetIdCorect() {
		anunt.setId(17897);
	}

	@Test
	public void testSetAnuntNull() {
		try {
			anunt.setAnunt(null);
			fail("Anunt null");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSetAnuntGol() {
		try {
			anunt.setAnunt("");
			fail("Anunt null");
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testSetAnuntCorect() {
		anunt.setAnunt("mesaj...");
		assertEquals("mesaj...", anunt.getAnunt());
	}
}
