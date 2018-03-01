package org.janus.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TokenUtil {
	
    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expiration}")
    private Long expiration;
	
	/**
	 * 生成token
	 * @param userCode
	 * @return
	 */
	public String generateToken(String userCode){
		return Jwts.builder().setSubject(userCode)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
	/**
	 * 从token中获取用户帐号
	 * @param token
	 * @return
	 */
	public String getUsercodeFromToken(String token){
		String userCode = null;
			final Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
			userCode = claims.getSubject();

		return userCode;
	}

	/**
	 * 获取token过期时间
	 * @param token
	 * @return
	 */
    public Date getExpirationDateFromToken(String token) {
        Date expiration = null;
        try {
            final Claims claims = Jwts.parser()
            		.setSigningKey(secret)
            		.parseClaimsJws(token)
            		.getBody();
            expiration = claims.getExpiration();
        } catch (Exception e) {
            log.error("getExpirationDateFromToken", e);
        }
        return expiration;
    }	
    
    
	
}
