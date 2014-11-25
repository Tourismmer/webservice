package com.tourismmer.app.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.tourismmer.app.constants.Constants;
import com.tourismmer.app.model.User;


public class EmailService {
	
	public static void sendEmail(String emailReceiver, String nameReceiver, String message, String subject) throws EmailException {
		
//		SimpleEmail email = new SimpleEmail();
		HtmlEmail email = new HtmlEmail();
		
		// Configurando Servidor SMTP
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setSSL(true);
		
		// Setando dados do email
		email.setFrom("tourismmer@gmail.com","Tourismmer");
		email.setAuthentication("tourismmer@gmail.com", "ffw$2014");
		email.addTo(emailReceiver, nameReceiver);
		// email.addCc("someone2@somedomain.com");  A secretaria terá que escolher enviar ou não copia
		
		email.setSubject(subject);
		email.setHtmlMsg(message);
		
		// Enviando email
		email.send();
		
	}
	
	public static void sendEmailPassRecover(User user) throws EmailException {
		StringBuffer message = new StringBuffer();
		
		message.append("<div style=\"font-size: 16px; width:300px;margin-left: auto; margin-right: auto\">");
		message.append("<img width=\"200px\" alt=\"tourismmer\" src=\"" + Constants.URL_SERVER + "img/image.jpg\">");
		message.append("<br /><br />");
		message.append("Ola, ");
		message.append(user.getName());
		message.append(", <br />");
		message.append("Para criar uma nova senha <a href=\"" + Constants.URL_SERVER + "passRecover.html?id=");
		message.append(EncryptDecryptRSA.encrypt(Util.getString(user.getId())));
		message.append("\">clique aqui!</a>");
		message.append("<br /><br /><br />");
		message.append("Atenciosamente, equipe Tourismmer.");
		message.append("</div>");
		
		sendEmail(user.getEmail(), user.getName(), message.toString(), "Tourismmer - Recuperar Senha");
	}
	
	

}
