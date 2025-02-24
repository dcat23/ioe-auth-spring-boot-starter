package xyz.catuns.auth.jwt;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import xyz.catuns.auth.jwt.token.JwtTokenGenerator;
import xyz.catuns.auth.jwt.token.JwtTokenGeneratorImpl;


class JwtConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(JwtConfiguration.class);

    @Test
    void shouldContainJwtGeneratorBean() {
        contextRunner.run(context -> {
            Assertions.assertThat(context).hasSingleBean(JwtTokenGenerator.class);
            JwtTokenGenerator tokenGenerator = context.getBean(JwtTokenGenerator.class);
            Assertions.assertThat(tokenGenerator).isInstanceOf(JwtTokenGeneratorImpl.class);
        });
    }
}