package com.example.springpractice.controllers;

import com.example.springpractice.dtos.MentorDTO;
import com.example.springpractice.dtos.SubjectDTO;
import com.example.springpractice.entites.Mentor;
import com.example.springpractice.entites.Subject;
import com.example.springpractice.services.MentorService;
import com.example.springpractice.services.MentorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "/api/v1/mentors")
@RequiredArgsConstructor
public class MentorController {
    private final MentorService mentorService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<Mentor> getAllMentors() {
        return mentorService.getAllMentors();
    }

    @GetMapping("{id}")
    public Mentor getMentorById(@PathVariable Long id) {
        return mentorService.getMentorById(id);
    }

    @PostMapping
    public Mentor createMentor( @RequestBody MentorDTO mentorDTO) {
        Mentor mentor = modelMapper.map(mentorDTO, Mentor.class);
        return mentorService.createMentor(mentor);
    }

    @PutMapping("{id}")
    public Mentor updateMentor(@PathVariable Long id,  @RequestBody MentorDTO updatedMentorDTO) {
        Mentor mentor = modelMapper.map(updatedMentorDTO, Mentor.class);
        return mentorService.updateMentorById(id, mentor);
    }

    @DeleteMapping("{id}")
    public void deleteMentor(@PathVariable Long id) {
        mentorService.deleteMentor(id);
    }


}
