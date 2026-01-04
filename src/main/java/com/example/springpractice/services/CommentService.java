package com.example.springpractice.services;

import com.example.springpractice.dtos.CommentDTO;
import com.example.springpractice.entites.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(CommentDTO commentDTO);
    List<Comment> getAllComments();
    Comment getCommentById(Long id);
    Comment updateCommentById(Long id, CommentDTO updateCommentDTO);
    void deleteComment(Long id);
}
