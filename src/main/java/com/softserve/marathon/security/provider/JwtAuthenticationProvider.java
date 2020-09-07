package com.softserve.marathon.security.provider;

import com.softserve.marathon.security.jwt.JwtTool;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class JwtAuthenticationProvider implements AuthenticationProvider {

    private JwtTool jwtTool;

    public JwtAuthenticationProvider(JwtTool jwtTool) {
        this.jwtTool = jwtTool;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = Jwts.parser()
                .setSigningKey(jwtTool.getAccessTokenKey())
                .parseClaimsJws(authentication.getName())
                .getBody().getSubject();
        @SuppressWarnings({"unchecked, rawtype"})
        List<String> authorities = (List<String>) Jwts.parser()
                .setSigningKey(jwtTool.getAccessTokenKey())
                .parseClaimsJws(authentication.getName())
                .getBody()
                .get("authorities");
        return new UsernamePasswordAuthenticationToken(
                email,
                "",
                authorities.stream().map(SimpleGrantedAuthority::new).collect(toList())
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationFilter.class);
    }
}
