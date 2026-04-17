package com.demo.blog.domain.dto;

import com.demo.blog.domain.model.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PostUpsertRequest(
        @NotBlank(message = "제목은 필수입니다.")
        @Size(max = 100, message = "제목은 100자 이하여야 합니다.")
        String title,
        @NotBlank(message = "본문은 필수입니다.")
        String content,
        @NotBlank(message = "카테고리는 필수입니다.")
        @Size(max = 30, message = "카테고리는 30자 이하여야 합니다.")
        String categoryName,
        @NotNull(message = "상태는 필수입니다.")
        PostStatus status
) {
}
