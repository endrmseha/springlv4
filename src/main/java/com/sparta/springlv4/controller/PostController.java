package com.sparta.springlv4.controller;

import com.sparta.springlv4.dto.ApiResult;
import com.sparta.springlv4.dto.PostRequestDto;
import com.sparta.springlv4.dto.PostResponseDto;
import com.sparta.springlv4.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class PostController {

    private final PostService postService;

    // Post 작성 API
    @PostMapping("/api/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request) { // 객체 형식으로 넘어오기 때문에 RequestBody를 사용
        return postService.createPost(requestDto, request);
    }

    // 전체 Post 조회 API
    @GetMapping("/api/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    // 선택 Post 조회 API
    @GetMapping("/api/posts/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // Post 수정 API
    @PutMapping("/api/posts/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.updatePost(id, requestDto, request);
    }

    @DeleteMapping("/api/posts/{id}")
    public ApiResult deletePost(@PathVariable Long id, HttpServletRequest request) {
        postService.deletePost(id, request);
        return new ApiResult("게시글 삭제 성공", HttpStatus.OK.value()); // 게시글 삭제 성공시 ApiResult Dto를 사용하여 성공 메세지와 statusCode를 띄움
    }
}
