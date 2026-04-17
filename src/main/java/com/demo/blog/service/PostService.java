package com.demo.blog.service;

import com.demo.blog.domain.dto.PostUpsertRequest;
import com.demo.blog.domain.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> findAll();

    Optional<Post> findById(Long id);

    Post create(PostUpsertRequest request);

    Optional<Post> update(Long id, PostUpsertRequest request);

    boolean delete(Long id);
}
