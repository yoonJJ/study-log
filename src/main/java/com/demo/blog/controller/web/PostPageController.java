package com.demo.blog.controller.web;

import com.demo.blog.domain.dto.PostUpsertRequest;
import com.demo.blog.domain.model.PostStatus;
import com.demo.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostPageController {

    private final PostService postService;

    public PostPageController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "posts/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("postForm", new PostUpsertRequest("", "", "", PostStatus.DRAFT));
        model.addAttribute("statuses", PostStatus.values());
        model.addAttribute("isEdit", false);
        return "posts/form";
    }

    @GetMapping("/ai-tool-call")
    public String aiToolCallArticle() {
        return "forward:/tool-call.html";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("postForm") PostUpsertRequest request, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", PostStatus.values());
            model.addAttribute("isEdit", false);
            return "posts/form";
        }
        Long id = postService.create(request).id();
        return "redirect:/posts/" + id;
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        return postService.findById(id)
                .map(post -> {
                    model.addAttribute("post", post);
                    return "posts/detail";
                })
                .orElse("redirect:/posts");
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        return postService.findById(id)
                .map(post -> {
                    model.addAttribute("postId", id);
                    model.addAttribute("postForm", new PostUpsertRequest(
                            post.title(),
                            post.content(),
                            post.categoryName(),
                            post.status()
                    ));
                    model.addAttribute("statuses", PostStatus.values());
                    model.addAttribute("isEdit", true);
                    return "posts/form";
                })
                .orElse("redirect:/posts");
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("postForm") PostUpsertRequest request, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("postId", id);
            model.addAttribute("statuses", PostStatus.values());
            model.addAttribute("isEdit", true);
            return "posts/form";
        }
        postService.update(id, request);
        return "redirect:/posts/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        postService.delete(id);
        return "redirect:/posts";
    }
}
