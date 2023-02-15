package com.pawfor710.BankApplication.repository;


import com.pawfor710.BankApplication.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {

    List<Transfer> findAllByUserId(Integer userId);
    List<Transfer> findAllByRecipientId(Integer recipientId);
}
