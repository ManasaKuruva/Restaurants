package com.example.com.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // Remove "Bearer " prefix
            try {
                username = jwtTokenProvider.getUsernameFromToken(jwt);
            } catch (ExpiredJwtException e) {
                // Handle expired token
            }
        }

        // If the username is found, set the authentication
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Here you would load the user details and set the authentication
            // For example, you can load the user from the database and set the security context
            // UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // UsernamePasswordAuthenticationToken authenticationToken =
            //         new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            // authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response); // Continue the filter chain
    }
}
