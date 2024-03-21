package com.santanna.picpaydesafio.service;

import com.santanna.picpaydesafio.dto.UserDTO;
import com.santanna.picpaydesafio.domain.user.User;
import com.santanna.picpaydesafio.domain.user.UserType;
import com.santanna.picpaydesafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServcie {
    @Autowired
    private UserRepository userRepository;

    public void validateTransactional(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuário LOJISTA não apto a realizar a transação");
        }

        if (sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Usuário com saldo unsuficiente");
        }

    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }
    public User findUserById (Long id) throws Exception {
    return this.userRepository.findById(id).orElseThrow(()-> new Exception("Usuário não foi encontrado"));
    }

    public void saveUser(User user){
         this.userRepository.save(user);
    }

    public User createUser(UserDTO userData) {
        User newUser = new User(userData);
        this.saveUser(newUser);
        return newUser;
    }
}
