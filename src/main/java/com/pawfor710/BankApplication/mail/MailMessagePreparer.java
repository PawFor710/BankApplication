package com.pawfor710.BankApplication.mail;

import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class MailMessagePreparer {

    public MimeMessage prepareTextMessageObject(String recipient, String subject, String content) throws MessagingException {
        MimeMessage textMessage = prepareMessageObject(recipient, subject);
        textMessage.setText(content);
        return textMessage;
    }

    private MimeMessage prepareMessageObject(String recipient, String subject) throws MessagingException {
        //prepare configuration
        Properties properties = MailConfiguration.getConfiguration();
        MailAuthenticator authenticator = new MailAuthenticator();

        //create session
        Session session = Session.getInstance(properties, authenticator);

        //prepare message
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setSubject(subject, "UTF-8");
        PasswordAuthentication passwordAuthentication = authenticator.getPasswordAuthentication();
        mimeMessage.setFrom(passwordAuthentication.getUserName());
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        return mimeMessage;
    }
}
