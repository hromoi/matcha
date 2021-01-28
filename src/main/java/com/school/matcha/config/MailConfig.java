package com.school.matcha.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    @Value(value = "${spring.mail.username}")
    private String username;

    @Value(value = "${spring.mail.password}")
    private String password;

    @Value(value = "${spring.mail.host}")
    private String host;

    @Value(value = "${spring.mail.protocol}")
    private String protocol;

    @Value(value = "${spring.mail.port}")
    private int port;

    @Value(value = "${spring.mail.default-encoding}")
    private String defaultEncoding;

    @Value(value = "${spring.mail.properties.mail.smtp.auth}")
    private String mailSmtpAuth;

    @Value(value = "${spring.mail.properties.mail.transport.protocol}")
    private String mailTransportProtocol;

    @Value(value = "${spring.mail.properties.mail.smtp.starttls.enable}")
    private String smtpStarttlsEnable;

    @Value(value = "${spring.mail.properties.mail.smtp.ssl.enable}")
    private String mailSmtpSslEnable;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.setDefaultEncoding(defaultEncoding);
        javaMailSender.setHost(host);
        javaMailSender.setProtocol(protocol);
        javaMailSender.setPort(port);

        Properties javaMailProperties = javaMailSender.getJavaMailProperties();
        javaMailProperties.put("mail.smtp.auth", mailSmtpAuth);
        javaMailProperties.put("mail.transport.protocol", mailTransportProtocol);
        javaMailProperties.put("mail.smtp.starttls.enable", smtpStarttlsEnable);
        javaMailProperties.put("mail.smtp.ssl.enable", mailSmtpSslEnable);
        return javaMailSender;
    }
}
