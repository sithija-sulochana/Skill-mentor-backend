package com.example.springpractice.services;


import com.example.springpractice.entites.Mentor;
import com.example.springpractice.entites.Subject;
import com.example.springpractice.repository.MentorRepository;
import com.example.springpractice.repository.SubjectRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SubjectService {



    public List<Subject> getAllSubjects();
    public Subject createSubject( Long mentorId, Subject subject);

    public Subject getSubjectById(Long id);

    public Subject updateSubjectById(Long id,Subject updatedSubject);

    public void deleteSubject(Long id);
}
