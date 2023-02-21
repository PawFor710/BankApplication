package com.pawfor710.BankApplication.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthenticator extends Authenticator {

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("bankappv1@gmail.com", "jvcurpjudoweplqw");
    }
}
