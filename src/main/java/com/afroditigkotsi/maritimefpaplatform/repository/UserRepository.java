package com.afroditigkotsi.maritimefpaplatform.repository;

import com.afroditigkotsi.maritimefpaplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
