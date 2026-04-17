package com.demo.blog.service;

import com.demo.blog.domain.dto.PostUpsertRequest;
import com.demo.blog.domain.model.Post;
import com.demo.blog.domain.model.PostStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class InMemoryPostService implements PostService {

    private final AtomicLong sequence = new AtomicLong(1L);
    private final Map<Long, Post> store = new ConcurrentHashMap<>();

    public InMemoryPostService() {
        Post seed = new Post(
                sequence.getAndIncrement(),
                "첫 번째 학습 글",
                "프로젝트 전체 구조를 먼저 만든 뒤 기능을 채워 나갑니다.",
                "Spring",
                PostStatus.PUBLISHED,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        store.put(seed.id(), seed);
    }

    @Override
    public List<Post> findAll() {
        return store.values().stream()
                .sorted(Comparator.comparing(Post::createdAt).reversed())
                .toList();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Post create(PostUpsertRequest request) {
        LocalDateTime now = LocalDateTime.now();
        Post post = new Post(
                sequence.getAndIncrement(),
                request.title(),
                request.content(),
                request.categoryName(),
                request.status(),
                now,
                now
        );
        store.put(post.id(), post);
        return post;
    }

    @Override
    public Optional<Post> update(Long id, PostUpsertRequest request) {
        Post existing = store.get(id);
        if (existing == null) {
            return Optional.empty();
        }
        Post updated = new Post(
                existing.id(),
                request.title(),
                request.content(),
                request.categoryName(),
                request.status(),
                existing.createdAt(),
                LocalDateTime.now()
        );
        store.put(id, updated);
        return Optional.of(updated);
    }

    @Override
    public boolean delete(Long id) {
        return store.remove(id) != null;
    }
}
