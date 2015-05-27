package delphi.netstudent.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import delphi.netstudent.exceptions.InvalidEmailException;

public class Validator {
	public static boolean isValidEmail(String email) throws InvalidEmailException {
		if (email == null || email.length() == 0 || email.equalsIgnoreCase("")) {
			throw new InvalidEmailException("Adresa de email nu este valida");
		}
		
		boolean result = false;
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(email);
		boolean matchFound = m.matches();
		if (!matchFound) {
		    throw new InvalidEmailException("Adresa email invalida!");
		} else {
			result = true;
		}
		return result;
	}
}
