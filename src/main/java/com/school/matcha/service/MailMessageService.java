package com.school.matcha.service;

public interface MailMessageService {
    void sendMail(String from, String to, String body);
}
