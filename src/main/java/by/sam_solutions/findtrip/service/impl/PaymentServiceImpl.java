package by.sam_solutions.findtrip.service.impl;

import by.sam_solutions.findtrip.controller.dto.OrderCreateUpdateDTO;
import by.sam_solutions.findtrip.exception.WalletIncorrectBalanceException;
import by.sam_solutions.findtrip.repository.WalletRepository;
import by.sam_solutions.findtrip.repository.entity.FlightEntity;
import by.sam_solutions.findtrip.repository.entity.OrderEntity;
import by.sam_solutions.findtrip.repository.entity.UserEntity;
import by.sam_solutions.findtrip.repository.entity.WalletEntity;
import by.sam_solutions.findtrip.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;

@Service
public class PaymentServiceImpl implements PaymentService {


    private WalletRepository walletRepository;

    public PaymentServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Transactional
    @Override
    public boolean payOrder(OrderCreateUpdateDTO orderDTO, FlightEntity flightEntity, UserEntity userEntity) {
        WalletEntity walletEntity = walletRepository.findByOwnerId(userEntity.getId());
        Double mustPay = orderDTO.getCountSeats() * flightEntity.getPrice();
        Double currBalance = walletEntity.getSum();
        if (mustPay < currBalance) {
            currBalance -= mustPay;
            walletEntity.setSum(Double.parseDouble(decimalFormat.format(currBalance).replace(",", ".")));
            walletRepository.save(walletEntity);
        } else {
            throw new WalletIncorrectBalanceException("You need to replenish wallet balance.\nYour balance: " + walletEntity.getSum());
        }
        return true;
    }

    @Transactional
    @Override
    public boolean returnMoney(OrderCreateUpdateDTO orderDTO, FlightEntity flightEntity, UserEntity userEntity) {
        WalletEntity walletEntity = walletRepository.findByOwnerId(userEntity.getId());
        Double mustReturn = orderDTO.getReturnTickets() * flightEntity.getPrice();
        Double currBalance = walletEntity.getSum()+mustReturn;

        walletEntity.setSum(Double.parseDouble(decimalFormat.format(currBalance).replace(",", ".")));
        walletRepository.save(walletEntity);

        return true;
    }

    @Transactional
    @Override
    public boolean returnMoneyForFlightCancellation(FlightEntity flightEntity) {
            WalletEntity walletEntity;
            Double currBalance;
            for (OrderEntity orderEntity : flightEntity.getOrders()) {
                walletEntity = orderEntity.getUser().getWallet();
                currBalance = walletEntity.getSum();
                walletEntity.setSum(currBalance + orderEntity.getFinalCost());
                walletRepository.save(walletEntity);
            }

        return true;
    }

}
