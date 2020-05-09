package com.sam_solutions.findtrip.service;

import com.sam_solutions.findtrip.controller.dto.BankCardDTO;
import com.sam_solutions.findtrip.controller.dto.WalletDTO;
import com.sam_solutions.findtrip.security.CustomUserDetail;

public interface WalletService {
    WalletDTO findByUserId(Long id);

    void replenishBalance(BankCardDTO bankCard, CustomUserDetail currUser);
}
