package com.demo.demo.utility;

import com.demo.demo.Entity.Student;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class Studentutility {

    private static String privatekey = "THIS_IS_PRIVATE";
    public String generate(Student student){
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject("Login Authentication")
                .claim("email" , student.getEmail())
                .claim("department",student.getDepartment())
                .claim("password" ,student.getPassword())
                .claim("username" ,student.getUsername())
                .setIssuedAt( new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 120 * 1000))
                .signWith(SignatureAlgorithm.HS512,privatekey)
                .compact();
    }
    public void verify(String Authorization) throws Exception {
        try {

            Jwts.parser().setSigningKey(privatekey).parseClaimsJws(Authorization);
        }catch (Exception e){
            throw new Exception();
        }
    }

}


