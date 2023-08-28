package com.example.ProjectGoods.repository;

import org.springframework.data.jpa.repository.Query;
import com.example.ProjectGoods.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    //@Query("SELECT count(u) FROM User u WHERE u.email = ?1")
//    Integer existsByEmail(String email);
    @Query("SELECT case when count(s) > 0 THEN true else false end from User s where s.email = ?1")
    Boolean existsByEmail(String email);
}
