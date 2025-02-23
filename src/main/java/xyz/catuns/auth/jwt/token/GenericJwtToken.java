package xyz.catuns.auth.jwt.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

public class GenericJwtToken implements JwtToken {
    private final String JWT_ISSUER;
    private final String JWT_USERNAME_KEY;
    private final String JWT_AUTHORITY_KEY;
    private final long JWT_TOKEN_EXPIRATION;


    public GenericJwtToken(String jwtIssuer, String jwtUsernameKey, String jwtAuthorityKey, long jwtTokenExpiration) {
        JWT_ISSUER = jwtIssuer;
        JWT_USERNAME_KEY = jwtUsernameKey;
        JWT_AUTHORITY_KEY = jwtAuthorityKey;
        JWT_TOKEN_EXPIRATION = jwtTokenExpiration;
    }

    public String generate(Authentication auth, String jwtSecret) {
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder().issuer(JWT_ISSUER).subject(auth.getName())
                .claim(JWT_USERNAME_KEY, auth.getName())
                .claim(JWT_AUTHORITY_KEY, extractAuthorities(auth))
                .issuedAt(new Date())
                .expiration(expiration())
                .signWith(secretKey).compact();
    }

    public Authentication validate(String token, String jwtSecret) {
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token).getPayload();
        String username = String.valueOf(claims.get(JWT_USERNAME_KEY));
        String authorities = String.valueOf(claims.get(JWT_AUTHORITY_KEY));
        return new UsernamePasswordAuthenticationToken(username, null,
                AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
    }

    private Date expiration() {
        long nowMillis = System.currentTimeMillis();
        return new Date(nowMillis + JWT_TOKEN_EXPIRATION);
    }

    private String extractAuthorities(Authentication auth) {
        return auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }
}
