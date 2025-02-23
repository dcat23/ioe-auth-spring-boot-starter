package xyz.catuns.auth.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("auth.jwt")
public record JwtProperties(

        @DefaultValue("36000000") // 10 hours
        Long tokenExpiration,

        @DefaultValue("username")
        String usernameKey,

        @DefaultValue("authorities")
        String authorityKey,

        @DefaultValue("none")
        String issuer,

        String secret


) {


        /**
         * public static final String JWT_SECRET_KEY = "";
         *     public static final String JWT_SECRET_DEFAULT_VALUE = "fcd9dbeccd33c8119b89a105bb8faa83014a851be6d59dd2db66c40e05f364fc";
         *     public static final String JWT_HEADER = "Authorization";
         */
}
