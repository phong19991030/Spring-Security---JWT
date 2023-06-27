/**
 * 
 */
package com.example.websocket.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.websocket.security.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author PhongNc 10:11:37 AM : Jun 26, 2023
 */
@Component
@Slf4j
public class JwtTokenProvider {
	@Value("${jwt.secret}")
	private String JWT_SECRET;
	@Value("${jwt.expiration}")
	private int JWT_EXPIRATION;

	// Create JSON WEB TOKKEN from user info
	public String generateToken (CustomUserDetails customUserDetails) {
		Date now = new Date();
		Date dateExpired = new Date(now.getTime()+JWT_EXPIRATION);
		
		// Create JSON WEB TOKKEN from userName
		return Jwts.builder().setSubject(customUserDetails.getUsername()).setIssuedAt(now)
				.setExpiration(dateExpired).signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
	}
	
	// get user info from jwt 
	public String getUserNameFromJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
		// get username from claims
		String userName = claims.getSubject();
		return userName;
	}
	
	// Validate info of jwt 
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
			return true;
		}catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
		return false;
	}
	
}
