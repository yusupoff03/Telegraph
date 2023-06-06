package com.example.telegraph.service.post;

import com.example.telegraph.dto.PostDto;
import com.example.telegraph.entity.PostEntity;
import com.example.telegraph.entity.user.UserEntity;
import com.example.telegraph.exception.DataNotFoundException;
import com.example.telegraph.repository.PostRepository;
import com.example.telegraph.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<List<PostEntity>> userPosts(UUID ownerId,int page,int size) {
        Pageable pageable=PageRequest.of(page,size);
        List<PostEntity> posts=postRepository.findPostEntitiesByOwnerId(ownerId,pageable);
        if(posts.isEmpty()){
            throw new DataNotFoundException("Not found");
        }
        return ResponseEntity.ok(posts);
    }

    @Override
    public PostEntity addPost(PostDto postDto, UUID ownerId) {
        PostEntity post = modelMapper.map(postDto, PostEntity.class);
        UserEntity user = userRepository.findUserEntityById(ownerId);
        String name=user.getName();
        String s = name.replaceAll(" ", "-");
        String link=s+"-"+LocalDate.now();
        List<PostEntity> list = postRepository.findPostEntityByLinkContains(link);
        if(list.isEmpty()){
            post.setLink(link);
        }else {
            String link1=link+list.size()+1;
        }
        post.setOwner(user);
        return postRepository.save(post);
    }

    @Override
    public ResponseEntity<List<PostEntity>> searchByTitle(String title,int page,int size) {
        Pageable pageable= PageRequest.of(page,size);
        List<PostEntity> posts=postRepository.findPostEntitiesByTitleContainingIgnoreCaseOrderByTitleAsc(pageable,title);
        if(posts.isEmpty()){
            throw new DataNotFoundException("Not Found");
        }
        return ResponseEntity.ok(posts);
    }

    @Override
    public ResponseEntity<List<PostEntity>> getAll(int page, int size, Boolean sortByName) {
        if(sortByName){
            Sort sort=Sort.by(Sort.Direction.ASC,"title");
            Pageable pageable=PageRequest.of(page,size,sort);
            List<PostEntity> content = postRepository.findAll(pageable).getContent();
            if(content.isEmpty()){
                throw new DataNotFoundException("Not found");
            }
            return ResponseEntity.ok(content);
        }
        Pageable pageable =PageRequest.of(page, size);
        List<PostEntity> content=postRepository.findAll(pageable).getContent();
        if(content.isEmpty()){
            throw new DataNotFoundException("Not found");
        }
        return ResponseEntity.ok(content);
    }
}
