package com.afroditigkotsi.maritimefpaplatform.repository;

import com.afroditigkotsi.maritimefpaplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
