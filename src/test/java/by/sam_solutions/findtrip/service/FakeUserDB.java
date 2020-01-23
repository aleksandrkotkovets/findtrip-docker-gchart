package by.sam_solutions.findtrip.service;

import by.sam_solutions.findtrip.repository.entity.OrderEntity;
import by.sam_solutions.findtrip.repository.entity.RoleEntity;
import by.sam_solutions.findtrip.repository.entity.UserEntity;
import by.sam_solutions.findtrip.repository.entity.WalletEntity;

import java.util.*;

public class FakeUserDB {
    private List<UserEntity> persistUsers = new LinkedList<>();

    public FakeUserDB() {
        Set<OrderEntity> orders = Set.of(new OrderEntity(), new OrderEntity(), new OrderEntity());
        persistUsers.add(new UserEntity("client","client@gmail.ru","clientclient","Client","Client","Client","375(33)3885668",orders,new WalletEntity(),new RoleEntity("ROLE_CLIENT")));
        persistUsers.add(new UserEntity("client1","client1@gmail.ru","client1client1","ClientOne","ClientOne","ClientOne","375(33)3885668",orders,new WalletEntity(),new RoleEntity("ROLE_CLIENT")));
        persistUsers.add(new UserEntity("client2","client2@gmail.ru","client2client2","ClientTwo","ClientTwo","ClientTwo","375(33)3995668",orders,new WalletEntity(),new RoleEntity("ROLE_CLIENT")));
        persistUsers.add(new UserEntity("client3","client3@gmail.ru","client3client3","ClientThree","ClientThree","ClientThree","375(33)3005668",orders,new WalletEntity(),new RoleEntity("ROLE_CLIENT")));
      }

    public List<UserEntity> getAllUsers() {
        return persistUsers;
    }

    public UserEntity getById(int id) {
        return persistUsers.get(id);
    }
}
