//package com.example.springpractice.controllers;
//import com.example.springpractice.entites.Comment;
//import com.example.springpractice.services.SentimentService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/comments")
//public class CommentController {
//
//    private final SentimentService sentimentService;
//    private final List<Comment> comments = new ArrayList<>();
//
//    public CommentController(SentimentService sentimentService) {
//        this.sentimentService = sentimentService;
//    }
//
//    // POST comment
//    @PostMapping
//    public Comment addComment(@RequestBody Map<String, String> request) {
//        String text = request.get("text");
//        String sentiment = sentimentService.analyzeSentiment(text);
//
//        Comment comment = new Comment(text, sentiment);
//        comments.add(comment);
//
//        return comment;
//    }
//
//    // GET all comments
//    @GetMapping
//    public List<Comment> getComments() {
//        return comments;
//    }
//}
