package com.demo.demo.Controller;

import com.demo.demo.Model.LoginRequest;
import com.demo.demo.Model.StudentModel;
import com.demo.demo.Service.StudentService;
import com.demo.demo.utility.Studentutility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private Studentutility studentutility;

    @PostMapping("/SignUp")
    public ResponseEntity<?> addStudent(@RequestBody StudentModel studentModel){
        studentService.addStudent(studentModel);
        return new ResponseEntity<>("Saved", HttpStatus.OK);
    }

    @PostMapping(value = "/userlogin")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest) throws ValidationException {

            String token = studentService.userLogin(loginRequest);
            return new ResponseEntity<>(token, HttpStatus.OK);

    }
    @GetMapping("/get")
    public ResponseEntity<?> response(@RequestHeader(value = "Authorization",defaultValue = " ")String auth) throws Exception {
        studentutility.verify(auth);
        return new ResponseEntity<>("okkk",HttpStatus.OK);
    }



}
