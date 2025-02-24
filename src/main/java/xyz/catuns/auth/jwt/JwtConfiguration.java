package xyz.catuns.auth.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import xyz.catuns.auth.jwt.token.JwtTokenGeneratorImpl;
import xyz.catuns.auth.jwt.token.JwtTokenGenerator;

@AutoConfiguration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfiguration {

    private static final Logger log = LoggerFactory.getLogger(JwtConfiguration.class);
    private final JwtProperties jwtProperties;

    public JwtConfiguration(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }


    @Bean
    JwtTokenGenerator jwtTokenGenerator() {
        return new JwtTokenGeneratorImpl(
                jwtProperties.issuer(),
                jwtProperties.tokenExpiration()
        );
    }

}
