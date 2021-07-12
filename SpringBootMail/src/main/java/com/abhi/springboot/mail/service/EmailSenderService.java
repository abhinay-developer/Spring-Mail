package com.abhi.springboot.mail.service;

import java.io.File;
import java.io.FileNotFoundException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendSimpleEmail(String toEmail, String body, String subject) {

		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("from mail");
			message.setTo(toEmail);
			message.setText(body);
			message.setSubject(subject);

			javaMailSender.send(message);
			System.out.println("Mail Sent Succesfully");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendEmailWithAttachment(String toEmail, String body, String subject, String attachment)
			throws MessagingException, FileNotFoundException {

		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

			messageHelper.setFrom("from mail");
			messageHelper.setTo(toEmail);
			messageHelper.setText(body);
			messageHelper.setSubject(subject);

			FileSystemResource fileResource = new FileSystemResource(new File(attachment));

			messageHelper.addAttachment(fileResource.getFilename(), fileResource);
			javaMailSender.send(mimeMessage);
			System.out.println("Email Send Sucessfully with attachment");

		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}
