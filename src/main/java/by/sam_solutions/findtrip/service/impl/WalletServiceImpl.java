package by.sam_solutions.findtrip.service.impl;

import by.sam_solutions.findtrip.controller.dto.BankCardDTO;
import by.sam_solutions.findtrip.controller.dto.UserDTO;
import by.sam_solutions.findtrip.controller.dto.WalletDTO;
import by.sam_solutions.findtrip.exception.WalletBalanceException;
import by.sam_solutions.findtrip.repository.WalletRepository;
import by.sam_solutions.findtrip.repository.entity.WalletEntity;
import by.sam_solutions.findtrip.security.CustomUserDetail;
import by.sam_solutions.findtrip.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;

@Service
public class WalletServiceImpl implements WalletService {

    private final Double MAX_BALANCE = 1_000_000D;

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public WalletDTO findByUserId(Long id) {
        return mapWalletDTO(walletRepository.findByOwnerId(id));
    }

    @Override
    public void replenishBalance(BankCardDTO bankCard, CustomUserDetail currUser) {

        WalletEntity walletEntity = walletRepository.findByOwnerId(currUser.getId());
        Double currBalance = walletEntity.getSum();

        if (currBalance + bankCard.getBalance() < MAX_BALANCE) {
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            currBalance += bankCard.getBalance();
            walletEntity.setSum(Double.parseDouble(decimalFormat.format(currBalance).replace(",",".")));
            walletRepository.save(walletEntity);
        } else {
            throw new WalletBalanceException("Your_wallet_balance_should_not_exceed_1_000_000", bankCard);
        }
    }

    private WalletDTO mapWalletDTO(WalletEntity walletEntity) {
        return new WalletDTO(walletEntity.getId(), walletEntity.getSum(), new UserDTO(walletEntity.getOwner().getId()));
    }
}
