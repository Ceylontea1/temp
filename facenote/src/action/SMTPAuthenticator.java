package action;

import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;

public class SMTPAuthenticator extends Authenticator {

	public SMTPAuthenticator() {
		super();
	}
	public PasswordAuthentication getPasswordAuthentication() {
		String username = "smoker0907";
		String password = "smoker09070";
		return new PasswordAuthentication(username, password);
	}
}
