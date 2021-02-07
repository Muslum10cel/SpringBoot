package com.pact.carddatabase.service;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationService {

	private static final long EXPIRATION_TIME = 864_000_000;
	private static final String SIGN_IN_KEY = "SecretKey";
	private static final String PREFIX = "Bearer";

	public static void addToken(HttpServletResponse res, String username) {
		String jwtToken = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SIGN_IN_KEY).compact();

		res.addHeader("Authorization", PREFIX + " " + jwtToken);
		res.addHeader("Access-Control-Expose-Headers", "Authorization");
	}
	
	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token != null) {
			String user = Jwts.parser().setSigningKey(SIGN_IN_KEY).parseClaimsJws(token.replace(PREFIX, "")).getBody().getSubject();
			if(user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
			}
		}
		return null;
	}

}
