package com.example.telegraph.controller;

import com.example.telegraph.dto.UserDto;
import com.example.telegraph.entity.user.UserEntity;
import com.example.telegraph.exception.AuthenticationFailedException;
import com.example.telegraph.exception.RequestValidationException;
import com.example.telegraph.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping(value = "/sign-up")
    public ResponseEntity<UserEntity> signUp(@Valid @RequestBody UserDto userDto,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return userService.add(userDto);
    }

    @GetMapping(value = "/sign-in")
    public ResponseEntity<UserEntity> signIn(
            @RequestParam String username,
            @RequestParam String password) {
        UserEntity user;
            user=userService.signIn(username,password);
            if(user==null){
                throw new AuthenticationFailedException("Invalid Username or password");
            }
       return ResponseEntity.ok(user);
    }
}
