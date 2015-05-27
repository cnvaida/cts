package delphi.netstudent.util;

import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	public static boolean sendEmail(String from, String password, String subject, String message, String to[]) {
		boolean result = false;
		try {
			trimiteEmail(from, password, subject, message, to);
			result = true;
		} catch (AddressException e) {
			
		} catch (MessagingException e) {
			
		}
		return result;
	}
	
	public static void trimiteEmail(String from, String password, String subject, String message, String to[]) throws AddressException, MessagingException {
		if (from == null || password == null || subject == null || message == null || to == null) {
			throw new RuntimeException("Argumente null");
		}
		
		if (to.length == 0) {
			throw new RuntimeException("Array destinatari are 0 elemente");
		}
		
		String host = "smtp.gmail.com";
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");
		
		Session session = Session.getDefaultInstance(props,null);
		MimeMessage mimeMessage = new MimeMessage(session);
	
		mimeMessage.setFrom(new InternetAddress(from));
		InternetAddress[] toAddress = new InternetAddress[to.length];
		for (int i = 0; i < toAddress.length; i++) {
			toAddress[i] = new InternetAddress(to[i]);
		}
		
		for (int i = 0; i < toAddress.length; i++) {
			mimeMessage.setRecipient(RecipientType.TO, toAddress[i]);
		}
		
		mimeMessage.setSubject(subject);
		mimeMessage.setText(message);
		Transport transport = session.getTransport();
		transport.connect(host,from,password);
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		transport.close();
	}
}
