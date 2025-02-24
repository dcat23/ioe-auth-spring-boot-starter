package xyz.catuns.auth.jwt;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import xyz.catuns.auth.jwt.token.JwtTokenGenerator;
import xyz.catuns.auth.jwt.token.JwtTokenGeneratorImpl;

@AutoConfiguration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfiguration {

    private final JwtProperties jwtProperties;

    public JwtConfiguration(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }


    @Bean
    JwtTokenGenerator jwtTokenGenerator() {
        return new JwtTokenGeneratorImpl(
                jwtProperties.issuer(),
                jwtProperties.expiration()
        );
    }

}
