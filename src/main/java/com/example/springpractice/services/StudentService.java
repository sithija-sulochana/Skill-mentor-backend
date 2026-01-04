package com.example.springpractice.services;

import com.example.springpractice.entites.Student;

import java.util.List;

public interface StudentService {

    Student createNewStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(Integer id);
    Student updateStudentById(Integer id, Student updatedStudent);
    void deleteStudent(Integer id);
}