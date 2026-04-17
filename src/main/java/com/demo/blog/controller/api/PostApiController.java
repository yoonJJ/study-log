package com.demo.blog.controller.api;

import com.demo.blog.common.api.ApiResponse;
import com.demo.blog.domain.dto.PostUpsertRequest;
import com.demo.blog.domain.model.Post;
import com.demo.blog.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostApiController {

    private final PostService postService;

    public PostApiController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "게시글 목록 조회")
    @GetMapping
    public ApiResponse<List<Post>> findAll() {
        return ApiResponse.ok(postService.findAll());
    }

    @Operation(summary = "게시글 상세 조회")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Post>> findById(@PathVariable Long id) {
        return postService.findById(id)
                .map(post -> ResponseEntity.ok(ApiResponse.ok(post)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.fail("게시글을 찾을 수 없습니다.")));
    }

    @Operation(summary = "게시글 생성")
    @PostMapping
    public ResponseEntity<ApiResponse<Post>> create(@Valid @RequestBody PostUpsertRequest request) {
        Post post = postService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(post));
    }

    @Operation(summary = "게시글 수정")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Post>> update(@PathVariable Long id, @Valid @RequestBody PostUpsertRequest request) {
        return postService.update(id, request)
                .map(post -> ResponseEntity.ok(ApiResponse.ok(post)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.fail("게시글을 찾을 수 없습니다.")));
    }

    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        if (!postService.delete(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.fail("게시글을 찾을 수 없습니다."));
        }
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}
