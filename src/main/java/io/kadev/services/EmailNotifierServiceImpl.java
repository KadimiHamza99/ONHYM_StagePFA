package io.kadev.services;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

	@Override
	public void notifyWithAttachement(String to, String objet, String message, String attachement) {
		MimeMessage mail = mailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mail,true);
			mimeMessageHelper.setFrom("hamzakadimi1999@gmail.com");
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(objet);
			mimeMessageHelper.setText(message);
			FileSystemResource fileSystemResource = new FileSystemResource(new File(attachement));
			mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
			this.mailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	

}
