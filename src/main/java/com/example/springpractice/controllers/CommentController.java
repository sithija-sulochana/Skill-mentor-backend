package com.example.springpractice.controllers;

import com.example.springpractice.dtos.CommentDTO;
import com.example.springpractice.entites.Comment;
import com.example.springpractice.services.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    // Create comment
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CommentDTO commentDTO) {
        log.info("Request to create comment");
        Comment createdComment = commentService.createComment(commentDTO);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    //  Get all comments
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        log.info("Request to get all comments");
        return ResponseEntity.ok(commentService.getAllComments());
    }

    //  Get comment by id
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        log.info("Request to get comment with id={}", id);
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    // Update comment
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable Long id,
            @RequestBody CommentDTO commentDTO) {

        log.info("Request to update comment id={}", id);
        return ResponseEntity.ok(commentService.updateCommentById(id, commentDTO));
    }

    //  Delete comment
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long id) {
        log.info("Request to delete comment id={}", id);
        commentService.deleteComment(id);
    }
}
