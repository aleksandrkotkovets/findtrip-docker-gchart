package by.sam_solutions.findtrip.service;

import by.sam_solutions.findtrip.controller.dto.BankCardDTO;
import by.sam_solutions.findtrip.controller.dto.WalletDTO;
import by.sam_solutions.findtrip.security.CustomUserDetail;

public interface WalletService {
    WalletDTO findByUserId(Long id);

    void replenishBalance(BankCardDTO bankCard, CustomUserDetail currUser);
}
