package com.softserve.marathon.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class JwtTool {

    private String accessTokenKey;
    private int accessTokenValidTimeInMinutes;

    @Autowired
    public JwtTool(@Value("${tokenKey}") String accessTokenKey,
                   @Value("${accessTokenTime}") int accessTokenValidTimeInMinutes) {
        this.accessTokenKey = accessTokenKey;
        this.accessTokenValidTimeInMinutes = accessTokenValidTimeInMinutes;
    }

    public String getTokenFromHttpServletRequest(HttpServletRequest request) {
        return Optional
                .ofNullable(request.getHeader("Authorization"))
                .filter(authHeader -> authHeader.startsWith("Bearer "))
                .map(token -> token.substring(7))
                .orElse(null);
    }

    public String getAccessTokenKey() {
        return accessTokenKey;
    }

    public String createToken(String email, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("authorities", roles);
        Date now = new Date();
        Calendar expires = Calendar.getInstance();
        expires.setTime(now);
        expires.add(Calendar.MINUTE, accessTokenValidTimeInMinutes);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expires.getTime())
                .signWith(SignatureAlgorithm.HS256, accessTokenKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(accessTokenKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.info("Invalid Jwt Signature");
            return false;
        } catch (MalformedJwtException ex) {
            log.info("Invalid Jwt token");
            return false;
        } catch (ExpiredJwtException ex) {
            log.info("Expired jwt token");
            return false;
        } catch (UnsupportedJwtException ex) {
            log.info("Unsupported Jwt token");
            return false;
        } catch (IllegalArgumentException ex) {
            log.info("Jwt claims string is empty");
            return false;
        }
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser()
                .setSigningKey(accessTokenKey)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}
