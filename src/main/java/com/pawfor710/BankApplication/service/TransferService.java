package com.pawfor710.BankApplication.service;

import com.pawfor710.BankApplication.exception.NoMoneyException;
import com.pawfor710.BankApplication.exception.UserNotFoundException;
import com.pawfor710.BankApplication.model.Transfer;
import com.pawfor710.BankApplication.model.User;
import com.pawfor710.BankApplication.repository.TransferRepository;
import com.pawfor710.BankApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TransferService {

    private final TransferRepository transferRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public void saveTransfer(Transfer transfer) throws Exception {
        User sender = userRepository.findById(transfer.getUser().getId())
                .orElseThrow();
        User recipient = userRepository.findById(transfer.getRecipientId())
                .orElseThrow(UserNotFoundException::new);
        if (sender.getBalance().compareTo(transfer.getValue()) != -1 ) {
            sender.setBalance(sender.getBalance().subtract(transfer.getValue()));
            recipient.setBalance(recipient.getBalance().add(transfer.getValue()));
            transfer.setSuccessful(true);
            emailService.sendMailToSenderWhenTransferSuccess(transfer);
            emailService.sendMailToRecipientWhenTransferSuccess(transfer);
            transferRepository.save(transfer);
        } else {
            transfer.setSuccessful(false);
            emailService.sendMailToSenderWhenTransferFailed(transfer);
            throw new NoMoneyException();
        }
    }

    public List<Transfer> getCurrentUserTransfers(Integer userId) {
        return transferRepository.findAllByUserId(userId);
    }

    public List<Transfer> getCurrentUserIncomeTransfers(Integer recipientId) {
        List<Transfer> transfers = transferRepository.findAllByRecipientId(recipientId);
        transfers.removeIf(transfer -> !transfer.isSuccessful());
        return transfers;
    }
}
