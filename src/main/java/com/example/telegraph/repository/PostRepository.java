package com.example.telegraph.repository;

import com.example.telegraph.entity.PostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, UUID> {
  List<PostEntity>findPostEntitiesByOwnerId(UUID ownerId,Pageable pageable);
  List<PostEntity> findPostEntitiesByTitleContainingIgnoreCaseOrderByTitleAsc(Pageable pageable, String title);
  List<PostEntity> findPostEntityByLinkContains(String link);
}
