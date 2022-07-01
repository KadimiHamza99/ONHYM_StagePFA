package io.kadev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotifierServiceImpl implements EmailNotifierService{

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void notify(String to, String objet, String message) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("hamzakadimi1999@gmail.com");
		mail.setTo(to);
		mail.setSubject(objet);
		mail.setText(message);
		
		this.mailSender.send(mail);
	}

}
