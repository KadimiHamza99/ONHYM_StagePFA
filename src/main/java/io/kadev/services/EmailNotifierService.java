package io.kadev.services;

import javax.mail.MessagingException;

public interface EmailNotifierService {
	void notify(String to,String objet,String message);
	void notifyWithAttachement(String to,String objet,String message,String attachement);
}
