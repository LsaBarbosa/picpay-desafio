package com.santanna.picpaydesafio.repository;

import com.santanna.picpaydesafio.domain.transactional.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionalRepository extends JpaRepository<Transactional, Long> {

}
