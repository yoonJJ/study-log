package com.demo.blog.repository;

import com.demo.blog.domain.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    List<Post> findAll();

    Optional<Post> findById(Long id);

    Post save(Post post);

    void deleteById(Long id);
}
