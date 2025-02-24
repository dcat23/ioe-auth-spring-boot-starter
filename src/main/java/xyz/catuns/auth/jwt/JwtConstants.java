package xyz.catuns.auth.jwt;

public final class JwtConstants {

    public static final String JWT_AUTHORITY_KEY = "authorities";
    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_USERNAME_KEY = "username";
    public static final String JWT_SECRET_DEFAULT_VALUE = "JwtTokenGenerator.randomSecureSecret() P.S. this does not mean anything, just a long string";
    public static final String JWT_SECRET_KEY = "auth.jwt.secret";
}