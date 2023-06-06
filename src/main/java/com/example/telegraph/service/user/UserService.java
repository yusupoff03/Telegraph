package com.example.telegraph.service.user;

import com.example.telegraph.dto.UserDto;
import com.example.telegraph.entity.user.UserEntity;
import org.springframework.http.ResponseEntity;

public interface UserService {
  ResponseEntity<UserEntity> add(UserDto userDto);
  UserEntity signIn(String username, String password);
}
