package xyz.catuns.auth.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("auth.jwt")
public record JwtProperties(

        @DefaultValue("36000000") // 10 hours
        Long expiration,
        @DefaultValue("https://catuns.xyz")
        String issuer,
        @DefaultValue("DO_NOT_USE_THIS_RANDOM_SECRET")
        String secret

) {

}
