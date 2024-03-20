package com.santanna.picpaydesafio.repository;

import com.santanna.picpaydesafio.domain.transactional.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionalRepository extends JpaRepository<Transaction, Long> {

}
