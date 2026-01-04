package com.example.springpractice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SkillMentorException extends RuntimeException {
    private final HttpStatus status;

    public SkillMentorException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}

// In exception handling, you just need to pass the message you need to send (ERROR or warn) and the suitable status code

/*
EX - If you need to inform about the message and the status code :

  @PostMapping
    public Mentor createMentor( @RequestBody MentorDTO mentorDTO) {
        try {
            Mentor mentor = modelMapper.map(mentorDTO, Mentor.class);
            return mentorService.createMentor(mentor);
        } catch (Exception exception) {
            System.out.println("Mentor create failed!"+exception.getMessage());
            //Map the message we need to send and the status code to the SkillMentorException
            throw new SkillMentorException("Failed to create a new mentor", HttpStatus.CONFLICT);
        }

    }


*
* */

