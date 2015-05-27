package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import delphi.netstudent.exceptions.InvalidEmailException;
import delphi.netstudent.exceptions.PasswordTooSmallException;
import delphi.netstudent.model.Admin;

public class AdminTest {
	private Admin administrator;
	private static FileReader reader;
	private static BufferedReader bf;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reader = new FileReader("parole.txt");
		bf = new BufferedReader(reader);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bf.close();
		reader.close();
	}
	

	@Before
	public void setUp() throws Exception {
		administrator = new Admin("Catalin", "parola", "a.3_76@yahoo.com", 1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdminStringStringStringInt() {
		assertEquals("Catalin, parola, a.3_76@yahoo.com, 1", administrator.toString());
	}

	@Test
	public void testSetUsernameNull() {
		try {
			administrator.setUsername(null);
			fail("Nu se genereaza exceptie pe username null");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSetUsernameEmpty() {
		try {
			administrator.setUsername("");
			fail("Nu se genereaza exceptie pe username null");
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testSetPasswordNull() {
		try {
			administrator.setPassword(null);
			fail("Nu trebuia sa treaca");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSetPasswordEmpty() {
		try {
			administrator.setPassword("");
			fail("Nu trebuia sa treaca");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSetPasswordLessThan6() {
		try {
			administrator.setPassword("abcda");
			fail("Nu trebuia sa treaca");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testParolaCorect() {
		try {
			administrator.setPassword("123456");
			assertEquals("123456", administrator.getPassword());
		} catch (IllegalArgumentException e) {
			fail("Desi corecta parola apare null sau empty");
		} catch (PasswordTooSmallException e) {
			fail("Desi corecta parola apare ca avand mai putin de 6 caractere");
		}
	}

	@Test
	public void testSetEmailNull() {
		try {
			administrator.setEmail(null);
			fail("Nu trebuia sa treaca cu email null");
		} catch (Exception e) {
		
		}
	}
	
	@Test
	public void testSetEmailGol() {
		try {
			administrator.setEmail("");
			fail("Nu trebuia sa treaca cu email gol");
		} catch (Exception e) {
		
		}
	}
	
	@Test
	public void testSetEmailFormatInvalid() {
		try {
			administrator.setEmail("a.3_7$yahoo.com");
			fail("Nu trebuia sa treaca cu email invalid");
		} catch (Exception e) {
		
		}
	}
	
	@Test
	public void testSetEmailCorect() {
		try {
			administrator.setEmail("catalin.vaida@stud.ase.ro");
			assertEquals("Nu s-a setat o adresa de email valida!", "catalin.vaida@stud.ase.ro", administrator.getEmail());
		} catch (InvalidEmailException e) {
			fail("Nu s-a setat o adresa de email valida!");
		}
	}


	@Test
	public void testSetPrivilegiu() {
		try {
			administrator.setPrivilegiu(15);
			fail("Nu trebuia sa schimbe statusul administratorului");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testFisier() {
		String linie = null;
		try {
			while ((linie = bf.readLine()) != null) {
				//System.out.println(linie);
				try {
					administrator.setPassword(linie);
					fail("Nici o parola nu este valida");
				} catch (Exception e) {
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
