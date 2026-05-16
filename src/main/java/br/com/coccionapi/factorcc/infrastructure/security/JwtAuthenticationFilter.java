package br.com.coccionapi.factorcc.infrastructure.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.coccionapi.factorcc.adapters.output.ports.UserPort;
import br.com.coccionapi.factorcc.shared.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

        private final JwtUtils jwtUtils;
        private final UserPort userPort;

        @Override
        protected void doFilterInternal(
                        HttpServletRequest request,
                        HttpServletResponse response,
                        FilterChain filterChain) throws ServletException, IOException {

                String path = request.getServletPath();

                if (path.startsWith("/v1/auth")) {
                        filterChain.doFilter(request, response);
                        return;
                }

                final String authHeader = request.getHeader("Authorization");

                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                        filterChain.doFilter(request, response);
                        return;
                }

                String token = authHeader.substring(7);

                String email = jwtUtils.extractEmail(token);

                if (email != null &&
                                SecurityContextHolder.getContext().getAuthentication() == null) {

                        var user = userPort.findUserByEmail(email)
                                        .orElseThrow();

                        if (jwtUtils.isTokenValid(token, user.getEmail())) {

                                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                                user,
                                                null,
                                                List.of(
                                                                new SimpleGrantedAuthority(
                                                                                "ROLE_" + user.getRole())));

                                authToken.setDetails(
                                                new WebAuthenticationDetailsSource()
                                                                .buildDetails(request));

                                SecurityContextHolder.getContext()
                                                .setAuthentication(authToken);
                        }
                }

                filterChain.doFilter(request, response);
        }

}
