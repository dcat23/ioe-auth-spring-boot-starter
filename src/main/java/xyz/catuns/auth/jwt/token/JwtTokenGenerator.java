package xyz.catuns.auth.jwt.token;

import org.springframework.security.core.Authentication;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * wip:
 * Autowire this class into the Security config,
 * then initialize the auth filter constructor
 *
 */
public interface JwtTokenGenerator {
    JwtToken generate(Authentication auth, String jwtSecret);
    Authentication validate(String token, String jwtSecret);

    static String randomSecureSecret() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] secret = new byte[32]; // 256 bits
        secureRandom.nextBytes(secret);
        return Base64.getEncoder().encodeToString(secret);
    }
}
