package com.example.ChatApp.Service.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null && jwtUtil.validateToken(token)) {
            String userId = jwtUtil.getUserIdFromJWT(token);
            String role = jwtUtil.getUserRole(token);

            User user = new User(userId, "", java.util.List.of(() -> role));
            SecurityContextHolder.getContext().setAuthentication(
                    new JwtAuthenticationToken(user, token, user.getAuthorities())
            );
        }

        chain.doFilter(request, response);
    }
}

