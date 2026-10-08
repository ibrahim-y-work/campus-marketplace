package com.campusmarketplace.marketplace.repository;

import com.campusmarketplace.marketplace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository  extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsById(UUID id);

}
