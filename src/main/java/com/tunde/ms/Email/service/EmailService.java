package com.tunde.ms.Email.service;

import com.tunde.ms.Email.enums.StatusEmail;
import com.tunde.ms.Email.model.Email;
import com.tunde.ms.Email.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {
    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(Email email){
        email.setSendDateEmail(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            javaMailSender.send(message);


            email.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            this.emailRepository.save(email);
        }
    }
}
