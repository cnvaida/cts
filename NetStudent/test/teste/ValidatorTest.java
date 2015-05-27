package teste;

import static org.junit.Assert.*;

import org.junit.Test;

import delphi.netstudent.exceptions.InvalidEmailException;
import delphi.netstudent.util.Validator;

public class ValidatorTest {

	@Test
	public void testIsValidEmailStringNull() {
		try {
			Validator.isValidEmail(null);
			fail("Trebuia sa dea o exceptie.");
		} catch (Exception ex) {
			assertTrue(true);
		}
	}

	@Test
	public void testIsValidStringCorect() throws InvalidEmailException {
		boolean result = Validator.isValidEmail("a.3_76@yahoo.com");
		assertEquals(true, result);
	}
}
