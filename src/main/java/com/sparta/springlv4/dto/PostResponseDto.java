package com.sparta.springlv4.dto;

import com.sparta.springlv4.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostResponseDto {
    private long id;
    private String title;
    private String contents;
    private String username;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentList;
    // password는 보여주지 않도록 함


    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.username = post.getUsername();
        this.createAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.commentList = post.getCommentList().stream()
                .map(CommentResponseDto::new).collect(Collectors.toList());
    }
}
