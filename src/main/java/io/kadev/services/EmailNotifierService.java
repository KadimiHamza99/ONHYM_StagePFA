package io.kadev.services;

public interface EmailNotifierService {
	void notify(String to,String objet,String message);
	void notifyWithAttachement(String to,String objet,String message,String attachement);
}
