package com.example.springpractice.repository;

import com.example.springpractice.entites.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepository extends JpaRepository<Mentor,Long> {
}
