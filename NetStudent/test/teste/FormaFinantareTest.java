package teste;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import delphi.netstudent.model.AnStudiu;
import delphi.netstudent.model.FormaFinantare;
import delphi.netstudent.model.Grupe;
import delphi.netstudent.model.Serii;
import delphi.netstudent.model.Specializari;
import delphi.netstudent.model.Student;

public class FormaFinantareTest {
	private List<Student> studenti;
	private FormaFinantare formaFinantare;
	private Student stud;

	@Before
	public void setUp() throws Exception {
		studenti = new ArrayList<Student>();
		formaFinantare = new FormaFinantare("Taxa");
		stud = mock(Student.class);
		Grupe grupa = mock(Grupe.class);
		Specializari specializare = mock(Specializari.class);
		Serii serii = mock(Serii.class);
		AnStudiu an = mock(AnStudiu.class);
		when(an.getNume()).thenReturn("An 2");
		when(grupa.getNume()).thenReturn("1049");
		when(specializare.getDenumire()).thenReturn("Informatica Economica");
		when(serii.getDenumire()).thenReturn("Seria A");
		
		when(stud.getGrupa()).thenReturn(grupa);
		when(stud.getAn_studiu()).thenReturn(an);
		when(stud.getSpecializare()).thenReturn(specializare);
		when(stud.getSeria()).thenReturn(serii);
		
		studenti.add(stud);
	}
	
	@After
    public void tearDown() {
        studenti.clear();
    }

	@Test
	public void testFormaFinantareString() {
		assertEquals("Taxa", formaFinantare.getDenumire());
	}

	@Test
	public void testSetId() {
		try {
			formaFinantare.setId(-17);
			fail("Nu trebuia sa seteze id negativ");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSetIdCorect() {
		formaFinantare.setId(17);
	}

	@Test
	public void testSetDenumireNull() {
		try {
			formaFinantare.setDenumire(null);
			fail("Nu trebuia sa seteze denumire null");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSetDenumireEmpty() {
		try {
			formaFinantare.setDenumire("");
			fail("Nu trebuia sa seteze denumire empty");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSetDenumireCorect() {
		formaFinantare.setDenumire("Buget");
		assertEquals("Buget", formaFinantare.getDenumire());
	}

	@Test
	public void testSetStudenti() {
		formaFinantare.setStudenti(studenti);
		assertEquals(stud, formaFinantare.getStudenti().get(0));
	}
	
	

}
