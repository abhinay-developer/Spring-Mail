package com.abhi.springboot.mail;

import java.io.FileNotFoundException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.abhi.springboot.mail.service.EmailSenderService;

@SpringBootApplication
public class SpringBootMailApplication {

	@Autowired
	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMailApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail1() {

		emailSenderService.sendSimpleEmail("<toMail>", "Spring Boot", "Learn Spring Boot in 100 steps");
	}
	
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException, FileNotFoundException {

		emailSenderService.sendEmailWithAttachment("<to Mail>", "Attachment", "Attachment","E:\\Users\\sai\\Downloads\\CSharpNotesForProfessionals.pdf");
	}
}
