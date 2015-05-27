package teste;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Before;
import org.junit.Test;

import delphi.netstudent.util.SendEmail;

public class SendEmailTest {
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testSendEmailDateIncorecte() {
		try {
			SendEmail.trimiteEmail("cn.vaida92@gmail.com", "parola", "Subiect", "Mesaj", new String[] {"a.3_76@yahoo.com"});
			fail("Trebuia sa arunce o exceptie de autentificare!");
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testSendEmailArgumenteNull() {
		try {
			SendEmail.trimiteEmail(null, null, null, null, null);
			fail("Trebuia sa arunce o exceptie de runtime!");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testEmailArrayGol() {
		try {
			SendEmail.trimiteEmail("cn.vaida92@gmail.com", "parola", "Subiect", "Mesaj", new String[] {});
			fail("Trebuia sa arunce o exceptie de runtime: array gol!");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testSendEmailArgumenteValide() throws AddressException, MessagingException {
		SendEmail.trimiteEmail("cont.proba2015@gmail.com", "CalitateSoftware", "Subiect", "Mesaj", new String[] {"a.3_76@yahoo.com"});
	}
	
}
