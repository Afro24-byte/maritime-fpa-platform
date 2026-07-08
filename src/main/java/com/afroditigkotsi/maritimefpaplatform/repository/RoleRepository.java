package com.afroditigkotsi.maritimefpaplatform.repository;

import com.afroditigkotsi.maritimefpaplatform.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByName(String name);

}
