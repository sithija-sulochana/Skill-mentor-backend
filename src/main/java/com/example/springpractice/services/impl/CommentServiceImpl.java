package com.example.springpractice.services.impl;

import com.example.springpractice.dtos.CommentDTO;
import com.example.springpractice.entites.Comment;
import com.example.springpractice.entites.Session;
import com.example.springpractice.exception.SkillMentorException;
import com.example.springpractice.repository.CommentRepository;
import com.example.springpractice.repository.SessionRepository;
import com.example.springpractice.repository.StudentRepository;
import com.example.springpractice.services.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final SessionRepository sessionRepository;
    private final StudentRepository studentRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Override
    public Comment createComment(CommentDTO commentDTO) {

        Session session = sessionRepository.findById(commentDTO.getSessionId())
                .orElseThrow(() ->
                        new SkillMentorException("Session not found", HttpStatus.NOT_FOUND));

        var student = studentRepository.findById(commentDTO.getStudentId())
                .orElseThrow(() ->
                        new SkillMentorException("Student not found", HttpStatus.NOT_FOUND));

        Comment comment = modelMapper.map(commentDTO, Comment.class);
        comment.setSession(session);
        comment.setStudent(student);

        log.info("Creating comment for sessionId={} by studentId={}",
                session.getId(), student.getId());

        session.setCommentCount(session.getCommentCount()+1);
        return commentRepository.save(comment);

    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() ->
                        new SkillMentorException("Comment not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Comment updateCommentById(Long id, CommentDTO updateCommentDTO) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() ->
                        new SkillMentorException("Comment not found", HttpStatus.NOT_FOUND));

        // Update text
        if (updateCommentDTO.getText() != null) {
            comment.setText(updateCommentDTO.getText());
        }

        // Update session (optional)
        if (updateCommentDTO.getSessionId() != null) {
            Session session = sessionRepository.findById(updateCommentDTO.getSessionId())
                    .orElseThrow(() ->
                            new SkillMentorException("Session not found", HttpStatus.NOT_FOUND));
            comment.setSession(session);
        }

        // Update student (optional)
        if (updateCommentDTO.getStudentId() != null) {
            var student = studentRepository.findById(updateCommentDTO.getStudentId())
                    .orElseThrow(() ->
                            new SkillMentorException("Student not found", HttpStatus.NOT_FOUND));
            comment.setStudent(student);
        }

        log.info("Updated comment id={}", id);
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() ->
                        new SkillMentorException("Comment not found", HttpStatus.NOT_FOUND));

        commentRepository.delete(comment);
        log.info("Deleted comment id={}", id);
    }
}
