package com.demo.demo.Service;

import com.demo.demo.Entity.Student;
import com.demo.demo.Model.LoginRequest;
import com.demo.demo.Model.StudentModel;
import com.demo.demo.Repository.StudentRepository;
import com.demo.demo.utility.Studentutility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;

@Service
public class StudentService {
    @Autowired
    private  StudentRepository studentRepository;
    @Autowired
    private Studentutility studentutility;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(StudentModel studentModel) {
        Student student = new Student();
        student.setEmail(studentModel.getEmail());
        student.setDepartment(studentModel.getDepartment());
        student.setUsername(studentModel.getUsername());
        student.setPassword(passwordEncoder.encode(studentModel.getPassword()));
        studentRepository.save(student);

    }




    public String userLogin(LoginRequest loginRequest) throws ValidationException {
        Student student;
        student = studentRepository.findByEmail(loginRequest.getEmail());
        if (student == null) {
            throw new ValidationException("User not found with given email Id");
        }
        return studentutility.generate(student);

    }
}
