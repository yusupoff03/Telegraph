package com.example.telegraph.repository;

import com.example.telegraph.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findUserEntityByUsernameEquals(String username);
    UserEntity findUserEntityByUsernameEqualsAndPasswordEquals(String username,String password);
    UserEntity findUserEntityById(UUID id);
}
