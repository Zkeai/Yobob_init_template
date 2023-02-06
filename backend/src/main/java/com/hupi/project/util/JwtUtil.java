package com.hupi.project.util;

import com.hupi.project.model.entity.CheckResult;
import com.hupi.project.model.entity.User;
import com.hupi.project.service.UserService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.security.Key;

import java.util.Date;
import java.util.UUID;
@Component
public class JwtUtil {
    private static long time= 1000*60*60*24;


    private  static Key key= Keys.secretKeyFor(SignatureAlgorithm.HS256);


    @Resource
    private  UserService userService;
    /**
     * 创建token
     * @param account
     * @return
     */
    public static String createToken(String account){
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
                .setId(account)
                .setIssuedAt(now)
                .setIssuer("hupi")     // 签发者
                .setSubject(account)
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(key)
                .compact();
        return jwtToken;
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

    public static CheckResult validateJWT(String jwtStr) {
        CheckResult checkResult = new CheckResult();
        Claims claims = null;
        try {
            claims = getAllClaimsFromToken(jwtStr);
            checkResult.setSuccess(true);
            checkResult.setClaims(claims);
        } catch (ExpiredJwtException e) {
            checkResult.setErrCode(4001);
            checkResult.setSuccess(false);
        } catch (SignatureException e) {
            checkResult.setErrCode(4002);
            checkResult.setSuccess(false);
        } catch (Exception e) {
            checkResult.setErrCode(4002);
            checkResult.setSuccess(false);
        }
        return checkResult;
    }

    /**
     * 从token中解析出载荷内容
     * @param jwtToken jwtToken
     */
    public static Claims getAllClaimsFromToken(String jwtToken){
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken).getBody();
    }

}
