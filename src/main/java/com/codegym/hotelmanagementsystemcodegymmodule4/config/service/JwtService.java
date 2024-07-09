package com.codegym.hotelmanagementsystemcodegymmodule4.config.service;

import com.codegym.hotelmanagementsystemcodegymmodule4.config.UserPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

/*TODO: s-step5 update JwtService*/
@Service
public class JwtService {

    private static final String SECRET_KEY = "123456789987654321123456789987654321123456789";
    private static final long EXPIRE_TIME_ONE_DAY = 86400000L;

    public String generateTokenLogin(Authentication authentication) {
        // Extract user principal from the authentication object
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        /*
         * TODO: Implement a mechanism to use a stronger cryptographic algorithm
         *   Use HS256 algorithm for other applications to be able to use it
         */
        // Build and sign the JWT token
        /*TODO: builder-> set noi dung cho payload*/
        return Jwts.builder().setSubject((userPrincipal.getUsername())) // Set the subject (username)
                .setIssuedAt(new Date(System.currentTimeMillis())) // Set the issue time
                .setExpiration(new Date((new Date()).getTime() + EXPIRE_TIME_ONE_DAY))  // Set the expiration time
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Sign the token with the secret key and HS256 algorithm
                .compact(); // Return the compact token string
    }

    private SecretKey getSignInKey() {
        // Decode the secret key from a base64 string
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        // Create a HMAC SHA key using the decoded key bytes
        // The HMAC SHA algorithm is used for signing the JWT tokens
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token -> Message: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token -> Message: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token -> Message: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty -> Message: " + e.getMessage());
        }
        return false;
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getSubject();
    }
}
