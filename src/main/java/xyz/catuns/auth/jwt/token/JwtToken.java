package xyz.catuns.auth.jwt.token;

import org.springframework.security.core.Authentication;

public interface JwtToken {
    String generate(Authentication auth, String jwtSecret);
    Authentication validate(String token, String jwtSecret);
}
