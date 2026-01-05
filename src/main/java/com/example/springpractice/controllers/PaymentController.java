package com.example.springpractice.controllers;

import com.example.springpractice.dtos.PaymentDTO;
import com.example.springpractice.entites.Payment;
import com.example.springpractice.services.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentDTO paymentDTO){
        Payment createPayment = paymentService.createPayment(paymentDTO);
        return new ResponseEntity<>(createPayment, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayment(){
        return ResponseEntity.ok(paymentService.getAllPayment());

    }
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Integer id){
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Integer id, PaymentDTO paymentDTO){
        return ResponseEntity.ok(paymentService.updatePayment(id,paymentDTO));

    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Integer id){
        paymentService.deletePayment(id);
    }

}
