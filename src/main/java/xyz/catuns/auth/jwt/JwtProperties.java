package xyz.catuns.auth.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("auth.jwt")
public record JwtProperties(

        @DefaultValue("36000000") // 10 hours
        Long expiration,
        @DefaultValue("https://auth.catuns.xyz/issuer-not-set")
        String issuer,
        @DefaultValue("DO_NOT_USE_THIS_RANDOM_SECRET") // todo: should default value be set at all?
        String secret
) {

}
