package com.example.telegraph.service.post;

import com.example.telegraph.dto.PostDto;
import com.example.telegraph.entity.PostEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface PostService {
    ResponseEntity<List<PostEntity>>userPosts(UUID ownerId,int page,int size);
    PostEntity addPost(PostDto postDto,UUID ownerId);
    ResponseEntity<List<PostEntity>> searchByTitle(String title,int page,int size);
    ResponseEntity<List<PostEntity>>getAll(int page,int size,Boolean sortByName);

}
