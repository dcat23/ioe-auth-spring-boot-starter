package xyz.catuns.auth.jwt.token;

import java.util.Date;

public record JwtToken(
        String token,
        Date expiration
) {
}
