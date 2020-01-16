package by.sam_solutions.findtrip.exception;

import by.sam_solutions.findtrip.controller.dto.BankCardDTO;

public class WalletBalanceException extends RuntimeException {

    private BankCardDTO bankCardDTO;

    public WalletBalanceException(String s, BankCardDTO bankCard) {
        super(s);
        this.bankCardDTO = bankCard;
    }

    public BankCardDTO getBankCardDTO() {
        return bankCardDTO;
    }
}
