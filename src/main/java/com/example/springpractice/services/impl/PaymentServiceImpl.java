package com.example.springpractice.services.impl;

import com.example.springpractice.dtos.PaymentDTO;
import com.example.springpractice.entites.Payment;
import com.example.springpractice.entites.Session;
import com.example.springpractice.entites.Student;
import com.example.springpractice.exception.SkillMentorException;
import com.example.springpractice.repository.PaymentRepository;
import com.example.springpractice.repository.SessionRepository;
import com.example.springpractice.repository.StudentRepository;
import com.example.springpractice.services.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j

public class PaymentServiceImpl implements PaymentService {

    private final SessionRepository sessionRepository;
    private final StudentRepository studentRepository;
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    public Payment createPayment(PaymentDTO paymentDTO) {
        Session session = sessionRepository.findById(paymentDTO.getSessionId().longValue()).orElseThrow(
                ()-> new SkillMentorException("Session not found", HttpStatus.NOT_FOUND)
        );
        Student student = studentRepository.findById(paymentDTO.getSessionId())
                .orElseThrow(
                        ()->new SkillMentorException("Student not found",HttpStatus.NOT_FOUND)
                );

        Payment payment = modelMapper.map(paymentDTO,Payment.class);
        payment.setSession(session);
        payment.setStudent(student);

        return paymentRepository.save(payment);

    }


    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }


    public Payment getPaymentById(Integer id) {
        return paymentRepository.findById(id)
                .orElseThrow(
                        ()->new SkillMentorException("Payment not found ", HttpStatus.NOT_FOUND)
                );


    }


    public Payment updatePayment(Integer id, PaymentDTO updatePaymentDTO) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(
                        ()->new SkillMentorException("Payment not found", HttpStatus.NOT_FOUND)
                );
        if(updatePaymentDTO.getNote() != null){
            payment.setNote(updatePaymentDTO.getNote());
        }
        if(updatePaymentDTO.getSessionId() != null){
            Session session = sessionRepository.findById(updatePaymentDTO.getSessionId().longValue())
                    .orElseThrow(() ->
                            new SkillMentorException("Session not found", HttpStatus.NOT_FOUND));
            payment.setSession(session);
        }
        if (updatePaymentDTO.getSessionId() != null) {
            var student = studentRepository.findById(updatePaymentDTO.getStudentId())
                    .orElseThrow(() ->
                            new SkillMentorException("Student not found", HttpStatus.NOT_FOUND));
            payment.setStudent(student);
        }
        log.info("Updated comment id ={}",id);
        return paymentRepository.save(payment);
    }


    public void deletePayment(Integer id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(
                        ()-> new SkillMentorException("Payment not found",HttpStatus.NOT_FOUND)
                );
        paymentRepository.delete(payment);
    }
}
