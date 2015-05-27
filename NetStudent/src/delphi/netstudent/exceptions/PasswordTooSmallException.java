package delphi.netstudent.exceptions;

public class PasswordTooSmallException extends Exception {

	private static final long serialVersionUID = 1L;

	public PasswordTooSmallException(String mesaj) {
		super(mesaj);
	}
}
