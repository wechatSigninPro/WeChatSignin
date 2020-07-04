package com.nenu.edu.server.util;

import com.nenu.edu.server.enumeration.ResponseCode;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.property.JwtProperties;
import com.nenu.edu.server.service.BaseLogService;
import com.nenu.edu.server.exception.AuthException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Liang Jiayue
 * @Description:Jwt工具类
 */
@Component
public class JwtUtil extends BaseLogService {

    private static final String CLAIM_KEY_ID = "id";
    private static final String CLAIM_KEY_CREATED = "created";

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    protected Class getType() {
        return JwtUtil.class;
    }

    /**
     * 生成token
     *
     * @param user
     * @return
     */
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_ID, user.getId());
        Date currentTime = new Date();
        claims.put(CLAIM_KEY_CREATED, currentTime);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpired() * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    /**
     * 从token中获取用户编号
     *
     * @param token
     * @return
     */
    public Long getUserIdFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.info(e.getMessage());
            throw new AuthException(ResponseCode.AUTH_FAILED_TOKEN_EXPIRED);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new AuthException(ResponseCode.AUTH_FAILED_TOKEN_ERROR);
        }
        return claims.get(CLAIM_KEY_ID, Long.class);
    }

}