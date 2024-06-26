package com.santanna.picpaydesafio.repository;

import com.santanna.picpaydesafio.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{

    Optional<User>findUserByDocument(String document);
}
