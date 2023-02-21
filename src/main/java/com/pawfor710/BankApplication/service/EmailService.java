package com.pawfor710.BankApplication.service;

import com.pawfor710.BankApplication.mail.MailMessagePreparer;
import com.pawfor710.BankApplication.model.Transfer;
import com.pawfor710.BankApplication.model.User;
import com.pawfor710.BankApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final MailMessagePreparer mailMessagePreparer;
    private final UserRepository userRepository;

    public void sendMailToSenderWhenTransferSuccess(Transfer transfer) {
        User recipient = userRepository.findById(transfer.getRecipientId()).orElseThrow();
        try {
            MimeMessage message = mailMessagePreparer.prepareTextMessageObject(
                    transfer.getUser().getEmail(), "Transfer success",
                    "Hello!\n" +
                            "\n" +
                            "Your transfer was successful!\n" +
                            "You sent " + transfer.getValue() + " PLN to account with number\n"
                            + recipient.getAccountNumber() + ".\n" +
                            "\n" +
                            "Greetings\n" + "BankApp Team!"
                    );
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void sendMailToRecipientWhenTransferSuccess(Transfer transfer) {
        User recipient = userRepository.findById(transfer.getRecipientId()).orElseThrow();
        User sender = userRepository.findById(transfer.getUser().getId()).orElseThrow();
        try {
            MimeMessage message = mailMessagePreparer.prepareTextMessageObject(
                    recipient.getEmail(), "Transfer receive",
                    "Hello!\n" +
                            "\n" +
                            "You receive a transfer!\n" +
                            "You get " + transfer.getValue() + " PLN from account with number\n"
                                + sender.getAccountNumber() + ".\n" +
                            "\n" +
                            "Greetings\n" + "BankApp Team!"
            );
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void sendMailToSenderWhenTransferFailed(Transfer transfer) {
        User recipient = userRepository.findById(transfer.getRecipientId()).orElseThrow();
        try {
            MimeMessage message = mailMessagePreparer.prepareTextMessageObject(
                    transfer.getUser().getEmail(), "Transfer failed",
                    "Hello!\n" +
                            "\n" +
                            "Your transfer failed!\n" +
                            "You were trying to send " + transfer.getValue() + " PLN to account with number\n"
                            + recipient.getAccountNumber() + ".\n" + "Transfer value was bigger then your balance.\n" +
                            "\n" +
                            "Greetings\n" + "BankApp Team!"
            );
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
