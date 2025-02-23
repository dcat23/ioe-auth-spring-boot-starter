package xyz.catuns.auth.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import xyz.catuns.auth.jwt.token.GenericJwtToken;
import xyz.catuns.auth.jwt.token.JwtToken;

@AutoConfiguration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfiguration {

    private static final Logger log = LoggerFactory.getLogger(JwtConfiguration.class);
    private final JwtProperties jwtProperties;

    public JwtConfiguration(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }


    @Bean
    JwtToken jwtTokenGenerator() {
        return new GenericJwtToken(
                jwtProperties.issuer(),
                jwtProperties.usernameKey(),
                jwtProperties.authorityKey(),
                jwtProperties.tokenExpiration()
        );
    }

}
