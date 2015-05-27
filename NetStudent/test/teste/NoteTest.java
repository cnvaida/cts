package teste;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import delphi.netstudent.model.AnStudiu;
import delphi.netstudent.model.Grupe;
import delphi.netstudent.model.Materii;
import delphi.netstudent.model.Note;
import delphi.netstudent.model.Serii;
import delphi.netstudent.model.Specializari;
import delphi.netstudent.model.Student;

public class NoteTest {
	
	Note nota;
	Student stud;

	@Before
	public void setUp() throws Exception {
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

		nota = new Note(9, new Materii("Geografie"), stud);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNoteIntStringMateriiStudent() {
		assertEquals(9, nota.getNota());
		assertEquals("Geografie", nota.getMaterie().getDenumire());
		assertEquals(stud, nota.getStudent());
	}

	@Test
	public void testSetIdNota() {
		try {
			nota.setIdNota(-87);
			fail("Trebuia sa dea oe exceptie");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSetIdNotaCorect() {
		nota.setIdNota(15);
	}

	@Test
	public void testSetNotaCorect() {
		nota.setNota(1);
	}
	
	@Test
	public void testSetNota() {
		try {
			nota.setNota(11);
			fail("Nota gresita, trebuia sa dea o exceptie");
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testSetCalificativCorect() {
		nota.setCalificativ("Admis");
	}
	
	@Test
	public void testSetCalificativNull() {
		try {
			nota.setCalificativ(null);
			fail("trebuia sa dea o exceptie");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSetCalificativEmpty() {
		try {
			nota.setCalificativ("");
			fail("trebuia sa dea o exceptie");
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testSetMaterie() {
		try {
			nota.setMaterie(null);
			fail("Trebuia sa dea exceptie");
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testSetStudent() {
		try {
			nota.setStudent(null);
			fail("Trebuia sa dea exceptie");
		} catch (Exception e) {
			
		}
	}

}
