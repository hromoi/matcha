package com.school.matcha.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
@AllArgsConstructor
public class MailMessageServiceImpl implements MailMessageService {
    private final JavaMailSender javaMailSender;

    @Override
    @Async
    public void sendMail(String from, String to, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Confirmation your email");
        message.setText(body);
        javaMailSender.send(message);
    }
}
