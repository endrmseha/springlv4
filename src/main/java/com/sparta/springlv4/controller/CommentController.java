package com.sparta.springlv4.controller;

import com.sparta.springlv4.dto.CommentRequestDto;
import com.sparta.springlv4.dto.CommentResponseDto;
import com.sparta.springlv4.dto.Status;
import com.sparta.springlv4.security.UserDetailsImpl;
import com.sparta.springlv4.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/comment/{id}")
    public CommentResponseDto createCommnet(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl details) {
        return commentService.createComment(id, requestDto, details.getUser());
    }

    // 댓글 수정 API
    @PutMapping("/comment/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl details){
        return commentService.updateComment(id, commentRequestDto, details.getUser());
    }

    // 댓글 삭제 API
    @DeleteMapping("/comment/{id}")
    public Status deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(id, userDetails.getUser());
    }

}
