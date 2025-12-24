package com.example.springpractice.services;

import com.example.springpractice.entites.Mentor;
import com.example.springpractice.repository.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MentorService {
    private final MentorRepository mentorRepository;
    private final ModelMapper modelMapper;

    public List<Mentor> getAllMentors(){
        return mentorRepository.findAll();
    }

    public Mentor createMentor(Mentor mentor){
        return mentorRepository.save(mentor);
    }

    public Mentor getMentorById(Long id){
        return mentorRepository.findById(id).get();
    }
    public Mentor updateMentorById(Long id, Mentor updatedMentor){
        Mentor mentor = mentorRepository.findById(id).get();
        modelMapper.map(updatedMentor, mentor);
        return mentorRepository.save(mentor);
    }
    public void deleteMentor(Long id){
        mentorRepository.deleteById(id);
    }

}
