package com.example.telegraph.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    @NotBlank(message = "Empty content")
    @NotNull(message = "name cannot be null")
    private String name;
    @NotBlank(message = "Empty content")
    @NotNull(message = "title cannot be null")
    private String title;
    @NotBlank(message = "Empty content")
    private String body;
}
