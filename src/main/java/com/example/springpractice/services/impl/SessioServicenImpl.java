package com.example.springpractice.services.impl;

import com.example.springpractice.dtos.SessionDTO;
import com.example.springpractice.entites.Mentor;
import com.example.springpractice.entites.Session;
import com.example.springpractice.entites.Student;
import com.example.springpractice.entites.Subject;
import com.example.springpractice.exception.SkillMentorException;
import com.example.springpractice.repository.MentorRepository;
import com.example.springpractice.repository.SessionRepository;
import com.example.springpractice.repository.StudentRepository;
import com.example.springpractice.repository.SubjectRepository;
import com.example.springpractice.services.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.springpractice.utils.ValidationUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j


public class SessioServicenImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final StudentRepository studentRepository;
    private final MentorRepository mentorRepository;
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;

    public Session createNewSession(SessionDTO sessionDTO) {
        // Fetch the related entities by their IDs
        try {
            Student student = studentRepository.findById(sessionDTO.getStudentId()).orElseThrow(
                    () -> new SkillMentorException("Student not found", HttpStatus.NOT_FOUND)
            );
            Mentor mentor = mentorRepository.findById(sessionDTO.getMentorId()).orElseThrow(
                    () -> new SkillMentorException("Mentor not found", HttpStatus.NOT_FOUND)
            );
            Subject subject = subjectRepository.findById(sessionDTO.getSubjectId()).orElseThrow(
                    () -> new SkillMentorException("Subject not found", HttpStatus.NOT_FOUND)
            );

            // Checking availability
            ValidationUtils.validateMentorAvailability(mentor, sessionDTO.getSessionAt(), sessionDTO.getDurationMinutes());
            ValidationUtils.validateStudentAvailability(student, sessionDTO.getSessionAt(), sessionDTO.getDurationMinutes());


            // Create and populate the Session entity
//        Session session = new Session();
//        session.setSessionAt(sessionDTO.getSessionAt());
//        session.setDurationMinutes(sessionDTO.getDurationMinutes());
//        session.setSessionStatus(sessionDTO.getSessionStatus());
//        session.setMeetingLink(sessionDTO.getMeetingLink());
//        session.setSessionNotes(sessionDTO.getSessionNotes());
//        session.setStudentReview(sessionDTO.getStudentReview());
//        session.setStudentRating(sessionDTO.getStudentRating());

            // using model mapper
            Session session = modelMapper.map(sessionDTO, Session.class);
            session.setStudent(student);
            session.setMentor(mentor);
            session.setSubject(subject);


            return sessionRepository.save(session);
        } catch (SkillMentorException skillMentorException) {
            log.error("Dependencies not found to map: {}, Failed to create new session", skillMentorException.getMessage());
            throw skillMentorException;
        } catch (Exception exception) {
            log.error("Failed to create session", exception);
            throw new SkillMentorException("Failed to create new session", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll(); // SELECT * FROM sessions
    }

    public Session getSessionById(Long id) {
        return sessionRepository.findById(id).get();
    }

    public Session updateSessionById(Long id, SessionDTO updatedSessionDTO) {
        Session session = sessionRepository.findById(id).get();

        // source -> destination
        modelMapper.map(updatedSessionDTO, session);

        // Update the related entities
        if (updatedSessionDTO.getStudentId() != null) {
            Student student = studentRepository.findById(updatedSessionDTO.getStudentId()).get();
            session.setStudent(student);
        }
        if (updatedSessionDTO.getMentorId() != null) {
            Mentor mentor = mentorRepository.findById(updatedSessionDTO.getMentorId()).get();
            session.setMentor(mentor);
        }
        if (updatedSessionDTO.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(updatedSessionDTO.getSubjectId()).get();
            session.setSubject(subject);
        }

//        // Update other fields
//        if (updatedSessionDTO.getSessionAt() != null) {
//            session.setSessionAt(updatedSessionDTO.getSessionAt());
//        }
//        if (updatedSessionDTO.getDurationMinutes() != null) {
//            session.setDurationMinutes(updatedSessionDTO.getDurationMinutes());
//        }
//        if (updatedSessionDTO.getSessionStatus() != null) {
//            session.setSessionStatus(updatedSessionDTO.getSessionStatus());
//        }
//        if (updatedSessionDTO.getMeetingLink() != null) {
//            session.setMeetingLink(updatedSessionDTO.getMeetingLink());
//        }
//        if (updatedSessionDTO.getSessionNotes() != null) {
//            session.setSessionNotes(updatedSessionDTO.getSessionNotes());
//        }
//        if (updatedSessionDTO.getStudentReview() != null) {
//            session.setStudentReview(updatedSessionDTO.getStudentReview());
//        }
//        if (updatedSessionDTO.getStudentRating() != null) {
//            session.setStudentRating(updatedSessionDTO.getStudentRating());
//        }

        return sessionRepository.save(session);
    }

    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }
}
