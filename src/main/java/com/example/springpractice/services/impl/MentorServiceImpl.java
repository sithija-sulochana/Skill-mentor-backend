package com.example.springpractice.services.impl;

import com.example.springpractice.entites.Mentor;
import com.example.springpractice.exception.SkillMentorException;
import com.example.springpractice.repository.MentorRepository;
import com.example.springpractice.services.MentorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MentorServiceImpl implements MentorService {
    private final MentorRepository mentorRepository;
    private final ModelMapper modelMapper;

    public Page<Mentor> getAllMentors(Pageable pageable){
        try{
            return mentorRepository.findAll(pageable);
        } catch (Exception exception) {
            log.error("Failed to get all mentors",exception);
            throw new SkillMentorException("Failed to get all mentors", HttpStatus.CONFLICT);
        }

    }

    public Mentor createMentor(Mentor mentor){

        try{

        }catch (Exception exception){
            log.error("Failed to create a new mentor",exception);
            throw new SkillMentorException("Failed to create a new mentor",HttpStatus.CONFLICT);
        }
        return mentorRepository.save(mentor);
    }

    public Mentor getMentorById(Long id){
        try {
            return mentorRepository.findById(id)
                    .orElseThrow(() ->
                            new SkillMentorException("Mentor not found", HttpStatus.NOT_FOUND)
                    ); // If there is a fault in finding mentors' id, it will throw the following error (orElseThrow(..))
        }catch (SkillMentorException skillMentorException){
            // Same as the following try blog exception, but it will also display the error in the console.
//            System.out.println("Error getting mentor "+skillMentorException.getMessage());
            log.error("Mentor not found",skillMentorException); // Loggers help throw the error message with timestamp and show it in the console as well
            throw new SkillMentorException("Mentor not found ", HttpStatus.NOT_FOUND);


        }
        catch (Exception exception){
            // If there is no fault in the client side, it will throw the following error that marked as a server error
            System.out.println("Mentor can't find "+exception.getMessage());
            throw new SkillMentorException("Mentor can't find ", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    public Mentor updateMentorById(Long id, Mentor updatedMentor){

        try{
            Mentor mentor = mentorRepository.findById(id)
                    .orElseThrow(
                            ()->new SkillMentorException("Mentor not found",HttpStatus.NOT_FOUND)
                    );
            modelMapper.map(updatedMentor, mentor);
            return mentorRepository.save(mentor);
        } catch (SkillMentorException skillMentorException) {

            log.warn("Mentor not found with id: {} to update",id,skillMentorException);
            throw new SkillMentorException("Mentor not found", HttpStatus.NOT_FOUND);


        }catch (Exception exception) {

            log.error("Error updating mentor", exception);
            throw new SkillMentorException("Failed to update mentor", HttpStatus.INTERNAL_SERVER_ERROR);


        }

    }
    public void deleteMentor(Long id) {
        try {
            mentorRepository.deleteById(id);
        } catch (Exception exception) {
            log.error("Failed to delete mentor with id {}", id, exception);
            throw new SkillMentorException("Failed to delete mentor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
