package com.example.springpractice.services;

import com.example.springpractice.dtos.PaymentDTO;
import com.example.springpractice.entites.Payment;

import java.util.List;

public interface PaymentService {
    Payment createPayment(PaymentDTO paymentDTO);
    List<Payment> getAllPayment();
    Payment getPaymentById(Integer id);
    Payment updatePayment(Integer id, PaymentDTO updatePaymentDTO);
    void deletePayment(Integer id);
}


