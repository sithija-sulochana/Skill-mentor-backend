package com.example.springpractice.controllers;

import com.example.springpractice.dtos.MentorDTO;
import com.example.springpractice.dtos.SubjectDTO;
import com.example.springpractice.entites.Mentor;
import com.example.springpractice.entites.Subject;
import com.example.springpractice.services.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final ModelMapper modelMapper;
    private final SubjectService subjectService;

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("{id}")
    public Subject getSubjectById(@PathVariable Long id) {
        return subjectService.getSubjectById(id);
    }



    @PostMapping
    public Subject createSubject( @RequestBody SubjectDTO subjectDTO) {
        Subject subject = modelMapper.map(subjectDTO, Subject.class);
        return subjectService.createSubject(subjectDTO.getMentorId(), subject);
    }

    @PutMapping("{id}")
    public Subject updateSubject(@PathVariable Long id, @RequestBody SubjectDTO updatedSubjectDTO) {
        Subject subject = modelMapper.map(updatedSubjectDTO, Subject.class);
        return subjectService.updateSubjectById(id, subject);
    }

    @DeleteMapping("{id}")
    public void deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
    }





}
