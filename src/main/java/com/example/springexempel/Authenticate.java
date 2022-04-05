package com.example.springexempel;

import com.example.springexempel.models.Customer;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class Authenticate {

    private String someonesToken;

    public String generateToken(Customer cus){

       String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),SignatureAlgorithm.HS256.getJcaName());


        //get claims
       String name = cus.getName();
       String mail = cus.getEmail();


        //get time
        Instant now = Instant.now();


        String jwtsToken = Jwts.builder()
                .claim("name", name)
                .claim("email", mail)
                .setSubject(name)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(10l,ChronoUnit.MINUTES)))
                .signWith(hmacKey)
                .compact();


        someonesToken = jwtsToken;
        return jwtsToken;


    }

    public static String parseJwt(String jwtString) {
        String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";


        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());

        String subject = "";
        try{
            Jws<Claims> jwt = Jwts.parserBuilder()
                    .setSigningKey(hmacKey)
                    .build()
                    .parseClaimsJws(jwtString);
            String s = jwt.getBody().getSubject();

            return jwt.toString();
        } catch ( ExpiredJwtException e){
            return  "token expired";
        } catch ( SignatureException e){
            return "nope";
        }


    }



}
