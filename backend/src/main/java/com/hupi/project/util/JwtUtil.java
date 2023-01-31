package com.hupi.project.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

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
     * 检查token是否过期
     * @param token
     * @return
     */
    public static boolean isTokenExpiration(String token) {
        Claims claims = getAllClaimsFromToken(token);
        if (null == claims) {
            return true;
        }
        if (null == claims.getExpiration()) {
            return true;
        } else {
            return claims.getExpiration().before(new Date());
        }

    }



    /**
     * 从token中解析出载荷内容
     * @param token jwtToken
     * @param key 参数key
     */
    public static String getClaimFromToken(String token,String key) {
        Claims claims = getAllClaimsFromToken(token);
        assert claims != null;
        return (String) claims.get(key);
    }


    /**
     * 从token中解析出载荷内容
     * @param jwtToken jwtToken
     */
    public static Claims getAllClaimsFromToken(String jwtToken){
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken).getBody();
        } catch (JwtException e) {
            return null;
        }
    }



}
