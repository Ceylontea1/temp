package action;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UsersDao;

@WebServlet("/SendMail")
public class SendMail  extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SendMail in!");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String userEmail = request.getParameter("userEmail");
        
        String m_name = request.getParameter("userName");
        String m_email = userEmail;
        String m_title = "testTitle";
        String m_text = "testText";
		
        try {
            String mail_from =  m_name + "<" + m_email + ">";
            String mail_to =    "admin<foryou2127@naver.com>";
            String title =      "hosting.83rpm.com 요청사항 :: " + m_title;
            String contents =   "보낸 사람 :: " + m_name + "&lt;" + m_email + "&gt;<br><br>" + m_title + "<br><br>" + m_text;

            mail_from = new String(mail_from.getBytes("UTF-8"), "UTF-8");
            mail_to = new String(mail_to.getBytes("UTF-8"), "UTF-8");

            Properties props = new Properties();

            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.auth", "true");

            Authenticator auth = new SMTPAuthenticator();
            Session sess = Session.getDefaultInstance(props, auth);
            MimeMessage msg = new MimeMessage(sess);

            msg.setFrom(new InternetAddress(mail_from));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail_to));
            msg.setSubject(title, "UTF-8");
            msg.setContent(contents, "text/html; charset=UTF-8");
            msg.setHeader("Content-type", "text/html; charset=UTF-8");
            
            int result = UsersDao.getInstance().registerCheck(userEmail);
            if(result == 1) {
            	HttpSession session = request.getSession();
            	String emailCheck = "emailCheckOn";
            	session.setAttribute("emailCheck", emailCheck);
            	System.out.println("1!!!!!!");
            	Transport.send(msg);
            	return;      
            } else {
            	System.out.println("2!!!!!!");
            	return;  
            }
            
        } catch (Exception e) {
       
        } finally { 
        
        }
        return;
	}

}