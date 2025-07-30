package com.tecdesoftware.market.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

//OncePerRequestFilter: garantiza que el filtro se ejecute una sola vez por solicitud HTTP.
@Component // Marca esta clase como un filtro que Spring usará
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    //Este método intercepta cada solicitud HTTP al backend.
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Extrae la cabecera Authorization (si existe)
        String authHeader = request.getHeader("Authorization");

        // Si comienza con 'Bearer ', entonces es un token JWT
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7); // Elimina 'Bearer ' y deja solo el token

            // Si el token es válido, se extrae el correo y se configura el usuario autenticado
            if (jwtUtil.validateToken(jwt)) {
                String correo = jwtUtil.extractUsername(jwt);

                // Crea un token de autenticación y lo coloca en el contexto de Spring
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(correo, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continúa con el flujo de filtros
        filterChain.doFilter(request, response);
    }
}