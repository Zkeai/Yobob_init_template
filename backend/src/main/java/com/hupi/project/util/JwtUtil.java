package com.hupi.project.util;

import com.hupi.project.common.ErrorCode;
import com.hupi.project.exception.BusinessException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    private static long time= 1000*60*60*24;

    private  static Key key= Keys.secretKeyFor(SignatureAlgorithm.HS256);
    /**
     * 创建token
     * @param account
     * @param role
     * @return
     */
    public static String createToken(String account, String role){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
                .claim("username",account)
                .claim("role",role)
                .setSubject("hupi-init")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(key)
                .compact();
        return jwtToken;
    }

    /**
     * 解析token
     * @param jwtToken jwtToken
     */
    public static boolean verifyToken(String jwtToken){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken).getBody();
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
