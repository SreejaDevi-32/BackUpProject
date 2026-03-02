package com.virtusa.telecom.user.user_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.telecom.user.user_service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	boolean existsByEmailHashOrPhoneHash(String emailHash, String phoneHash);

    Optional<User> findByEmailHash(String emailHash);
}
