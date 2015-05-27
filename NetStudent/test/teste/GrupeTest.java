package teste;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import delphi.netstudent.model.AnStudiu;
import delphi.netstudent.model.Grupe;
import delphi.netstudent.model.Serii;
import delphi.netstudent.model.Specializari;

public class GrupeTest {
	Grupe grupa;
	Serii serie;

	@Before
	public void setUp() throws Exception {
		serie = new Serii("Seria A", new Specializari("Informatica Economica"), new AnStudiu("II"));
		grupa = new Grupe("1049", serie);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testGrupeStringSerii() {
		assertEquals("Informatica Economica", serie.getSpecializare().getDenumire());
	}

	@Test
	public void testSetId() {
		try {
			grupa.setId(-75);
			fail("Trebuia sa arunce o exceptie");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSetIdCorect() {
		grupa.setId(897);
		assertEquals(897, grupa.getId());
	}

	@Test
	public void testSetNumeNull() {
		try {
			grupa.setNume(null);
			fail("Nume null");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSetNumeEmpty() {
		try {
			grupa.setNume("");
			fail("Nume empty");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSetNumeCorect() {
		grupa.setNume("1049");
		assertEquals("1049", grupa.getNume());
	}

	@Test
	public void testSetSerieNull() {
		try {
			grupa.setSerie(null);
			fail("Serie null");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSerieCorecta() {
		grupa.setSerie(serie);
		assertEquals(serie, grupa.getSerie());
	}

}
