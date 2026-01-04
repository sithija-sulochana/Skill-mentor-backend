package com.example.springpractice.services.impl;

import com.example.springpractice.entites.Mentor;
import com.example.springpractice.entites.Subject;
import com.example.springpractice.repository.MentorRepository;
import com.example.springpractice.repository.SubjectRepository;
import com.example.springpractice.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private ModelMapper modelMapper;
    private final MentorRepository mentorRepository;

    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll(); // Query - SELECT * FROM subject
    }
    public Subject createSubject( Long mentorId, Subject subject){
        Mentor mentor = mentorRepository.findById(mentorId).get();
        subject.setMentor(mentor);
        return subjectRepository.save(subject); // INSERT
    }

    public Subject getSubjectById(Long id){
        return  subjectRepository.findById(id).get();
    }

    public Subject updateSubjectById(Long id,Subject updatedSubject){
        Subject subject = subjectRepository.findById(id).get();
        subject.setSubjectName(updatedSubject.getSubjectName());
        subject.setDescription(updatedSubject.getDescription());
        return subjectRepository.save(subject);
    }

    public void deleteSubject(Long id){
        subjectRepository.deleteById(id);
    }
}
