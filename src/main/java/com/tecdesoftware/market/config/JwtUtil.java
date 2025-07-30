package com.tecdesoftware.market.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    // Clave secreta usada para firmar digitalmente los tokens
    private final String SECRET_KEY = "eO0jhFz67uKp8Wx93jsLP!4oMr9qBfAQ";

    // Genera un token válido por 3 minutos con el correo del usuario como 'subject'
    public String generateToken(String correo) {
        return Jwts.builder()
                .setSubject(correo)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000)) // 3 min
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extrae el 'subject' del token (correo electrónico)
    public String extractUsername(String token) {
        //Analiza el token
        return Jwts.parser()
                //Le dice al .parser() “asegúrate que este token fue generado por mí”
                .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                //Termina de construir el parser configurado. Ya está listo para usarse.
                .build()
                //Lee el token que le pasas y verifica su firma.
                .parseSignedClaims(token)
                // Extrae la parte del contenido (el “cuerpo”) del token. Aquí está la info como el correo, fechas, etc.
                .getPayload()
                //Obtiene el subject del token, que en este caso es el correo electrónico del usuario.
                .getSubject();
    }

    // Valida que el token esté bien formado y no haya expirado
    public boolean validateToken(String token) {
        try {
            extractUsername(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}