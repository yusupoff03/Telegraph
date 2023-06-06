package com.example.telegraph.controller;

import com.example.telegraph.dto.PostDto;
import com.example.telegraph.entity.PostEntity;
import com.example.telegraph.exception.DataNotFoundException;
import com.example.telegraph.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;

    @GetMapping(value = "/my-posts/{userId}")
    public ResponseEntity<List<PostEntity>> myPosts(
            @PathVariable UUID userId,
            @RequestParam int page,
            @RequestParam int size
    ) {
      return postService.userPosts(userId,page,size);
    }

    @PostMapping(value = "/add-post/{userId}")
    public ResponseEntity<PostEntity> addPost(
            @PathVariable UUID userId,
            @RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.addPost(postDto, userId));
    }
    @GetMapping(value = "/search")
    public ResponseEntity<List<PostEntity>> search(
            @RequestParam String title,
            @RequestParam int page,
            @RequestParam int size
    ){
      return postService.searchByTitle(title,page,size);
    }
}
