package com.example.springpractice.security;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtValidator jwtUtil;
    //  BASE VALIDATOR

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain)
            throws ServletException, IOException {

        String token = extractToken(request); // The actual token 

        if (token != null && jwtUtil.validateToken(token)) { //Check whether the token is not empty and it is valid.
            String username = jwtUtil.extractUsername(token); //Extract the username
            List<String> roles = jwtUtil.extractRoles(token); // Extract the role
 
            List<GrantedAuthority> authorities = roles != null ?    // Convert roles to Spring Security format
                    roles.stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))  // ROLE_Admin, ROLE_User
                            .collect(Collectors.toList()) :  // Otherwise, can't use ---> @PreAuthorize("hasRole('Admin')")

                    new ArrayList<>();

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authorities); 
            SecurityContextHolder.getContext().setAuthentication(authentication); //Store authentication in SecurityContext
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) { // If the token has a value and it starts with "bearer", split the pure JWT token without "bearer."
            return bearerToken.substring(7); 
        }
        return null;
    }
}
