package com.santanna.picpaydesafio.service;

import com.santanna.picpaydesafio.domain.dto.TransactionDTO;
import com.santanna.picpaydesafio.domain.transactional.Transaction;
import com.santanna.picpaydesafio.domain.user.User;
import com.santanna.picpaydesafio.repository.TransactionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionalService {

    @Autowired
    private UserServcie userServcie;
    @Autowired
    private TransactionalRepository transactionalRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void createTransactional(TransactionDTO transactionDTO) throws Exception {
        User sender = this.userServcie.findUserById(transactionDTO.senderId());
        User receiver = this.userServcie.findUserById(transactionDTO.receiverId());
        userServcie.validateTransactional(sender, transactionDTO.value());

        boolean isAuthorized = this.authorizeTransactional(sender, transactionDTO.value());
        if (!isAuthorized) {
            throw new Exception("Trazação não autorizada");
        }
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDTO.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimeStamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

        this.transactionalRepository.save(newTransaction);
        this.userServcie.saveUser(sender);
        this.userServcie.saveUser(receiver);
    }

    public boolean authorizeTransactional(User sender, BigDecimal value) {
        String url = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(url, Map.class);
        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;
    }
}
