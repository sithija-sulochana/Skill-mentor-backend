package com.example.springpractice.services;

import com.example.springpractice.entites.Mentor;
import com.example.springpractice.entites.Mentor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MentorService {
    Mentor createMentor(Mentor mentor);
    Page<Mentor> getAllMentors(Pageable pageable);
    Mentor getMentorById(Long id);
    Mentor updateMentorById(Long id, Mentor updatedMentor);
    void deleteMentor(Long id);
}
