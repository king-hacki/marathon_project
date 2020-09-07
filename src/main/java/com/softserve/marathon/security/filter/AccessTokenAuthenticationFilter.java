package com.softserve.marathon.security.filter;

import com.softserve.marathon.security.jwt.JwtTool;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AccessTokenAuthenticationFilter extends OncePerRequestFilter {

    private JwtTool jwtTool;
    private AuthenticationManager authenticationManager;

    public AccessTokenAuthenticationFilter(JwtTool jwtTool, AuthenticationManager authenticationManager) {
        this.jwtTool = jwtTool;
        this.authenticationManager = authenticationManager;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String token = jwtTool.getTokenFromHttpServletRequest(httpServletRequest);
        if (token != null) {
            try {
                Authentication authentication = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(token, null));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (ExpiredJwtException e) {
                log.error("Token has expired " + token);
            } catch (Exception e) {
                log.error("Access denied with token " + e.getMessage());
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
