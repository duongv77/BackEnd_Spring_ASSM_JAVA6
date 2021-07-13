package duong.dev.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;

@Component
public class SendMail {
	
	
	public void senMaiChaoMung(String email , String password) {
		String noiDung = "Bạn đã đăng kí tài khoản thành công với "
				+"Username: "+email+ " Password: "+password
				+" .Chào mừng bạn đến với Dưỡng Đẹp Trai code không bug";
		SenMail(email, noiDung);
		
	}
	
	public boolean SenMail(String nguoiNhan, String noiDung) {
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,new Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			String username = "duongdeptrai.0hutthuoc@gmail.com";
			String password = "22042001d";
			return new PasswordAuthentication(username, password);
		}
		});
		
		MimeMessage message = new MimeMessage(session);
		
		try {
			
			message.setFrom(new InternetAddress("duongdeptrai.0hutthuoc@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, nguoiNhan);
			message.setSubject("Dưỡng Đẹp Trai", "utf-8");
			message.setText(noiDung , "utf-8","html");
			message.setReplyTo(message.getFrom()); 
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
