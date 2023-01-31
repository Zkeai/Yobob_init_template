package com.hupi.project.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Test {
//1000*60*60*24
    private long time= 1000*60*5;

    private Key key= Keys.secretKeyFor(SignatureAlgorithm.HS256);


    @org.junit.Test
    public void jwt() throws InterruptedException {
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
                .claim("username","zkeai")
                .claim("role","admin")
                .setSubject("admin")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(key)
                .compact();
                System.out.println(jwtToken);
        try {

            Claims body=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken).getBody();
            System.out.println( body.get("username"));
            System.out.println( body.get("role"));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println( simpleDateFormat.format(body.getExpiration()));
        } catch (JwtException e) {

            System.out.println(e);
        }





    }

}
