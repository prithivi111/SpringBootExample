package org.example.service;

import org.example.model.StudentModel;
import org.springframework.stereotype.Service;

@Service
public class BasicService {
    public int test() {
        int data = 10;
        return 100/data;
    }

    public StudentModel fetchStudentData() {
        StudentModel student = new StudentModel();
        student.setStudentId(1);
        student.setStudentName("ABC");
        return student;
    }
}