package com.example.telegraph.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
   private String name;
   @NotBlank(message = "username cannot be empty or blank")
   @NotNull(message = "username cannot be null")
   private String username;
   @NotBlank(message = "password cannot be blank or empty")
   @NotNull(message = "password cannot be null")
   private String password;

}
