package xyz.catuns.auth.jwt.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import xyz.catuns.auth.jwt.token.JwtToken;
import xyz.catuns.auth.jwt.token.JwtTokenGenerator;

import java.io.IOException;

import static xyz.catuns.auth.jwt.JwtConstants.*;

public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

    /**
     * Should be autowired into the security config.
     * http.addFilterAfter(new JwtTokenGeneratorFilter(jwtTokenGenerator), BasicAuthenticationFilter.class)
     */
    private final JwtTokenGenerator jwtTokenGenerator;

    public JwtTokenGeneratorFilter(JwtTokenGenerator jwtTokenGenerator) {
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    /**
     *
     * @param request     The HTTP servlet request
     * @param response    The HTTP servlet response
     * @param filterChain The filter chain for executing other filters
     * @throws ServletException If there's an error during the filter execution
     * @throws IOException      If there's an I/O error during the filter execution
     *
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Environment env = getEnvironment();
            String secret = env.getProperty(JWT_SECRET_KEY, JWT_SECRET_DEFAULT_VALUE);
            JwtToken jwtToken = jwtTokenGenerator.generate(auth, secret);
            response.setHeader(JWT_HEADER, jwtToken.token());
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/api/users/user");
    }
}
