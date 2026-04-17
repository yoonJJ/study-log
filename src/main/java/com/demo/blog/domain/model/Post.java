package com.demo.blog.domain.model;

import java.time.LocalDateTime;

public record Post(
        Long id,
        String title,
        String content,
        String categoryName,
        PostStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
