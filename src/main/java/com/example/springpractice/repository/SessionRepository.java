package com.example.springpractice.repository;

import com.example.springpractice.entites.Session;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.function.Supplier;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {

}
