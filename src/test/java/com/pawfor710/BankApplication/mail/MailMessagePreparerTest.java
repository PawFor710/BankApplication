package com.pawfor710.BankApplication.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailMessagePreparerTest {

    @Autowired
    public MailMessagePreparer mailMessagePreparer;

    @Test
    void sendMail() {
        try {
            MimeMessage message = mailMessagePreparer.prepareTextMessageObject(
                    "pawfor710@gmail.com", "test", "Test");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}