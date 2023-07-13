package com.sparta.springlv4.controller;

import com.sparta.springlv4.dto.Status;
import com.sparta.springlv4.security.UserDetailsImpl;
import com.sparta.springlv4.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikeController {
    private final LikeService likeService;

    // 게시글 좋아요 API
    @PostMapping("/like/post/{id}")
    public Status likePost(@PathVariable Long id,
                           @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.likePost(id, userDetails.getUser());
    }

    // 게시글 좋아요 취소 API
    @DeleteMapping("/like/post/{id}")
    public Status deleteLikePost(@PathVariable Long id,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.deleteLikePost(id, userDetails.getUser());
    }


    // 댓글 좋아요 API
    @PostMapping("/like/comment/{id}")
    public Status likeComment(@PathVariable Long id,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.likeComment(id, userDetails.getUser());
    }

    // 댓글 좋아요 취소 API
    @DeleteMapping("/like/comment/{id}")
    public Status deleteLikeComment(@PathVariable Long id,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.deleteLikeComment(id, userDetails.getUser());
    }


}
