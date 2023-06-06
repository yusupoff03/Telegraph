package com.example.telegraph.service.user;

import com.example.telegraph.dto.UserDto;
import com.example.telegraph.entity.user.UserEntity;
import com.example.telegraph.exception.ConflictException;
import com.example.telegraph.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<UserEntity> add(UserDto userDto) {
        UserEntity user=modelMapper.map(userDto, UserEntity.class);
        if(checkUserName(user.getUsername())){
            throw new ConflictException("Username already exist");
        }
       return ResponseEntity.ok(userRepository.save(user));
    }

    @Override
    public UserEntity signIn(String username, String password) {
        return userRepository.findUserEntityByUsernameEqualsAndPasswordEquals(username,password);
    }
    private Boolean checkUserName(String username){
        UserEntity user;
        user=userRepository.findUserEntityByUsernameEquals(username);
        if(user==null){
            return false;
        }
        return true;
    }
}
