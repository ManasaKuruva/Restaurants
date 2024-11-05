package com.example.com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("${JWT_SECRET:defaultSecretKey}")
    private String jwtSecret;

    @Value("${JWT_EXPIRATION:3600}")
    private Long jwtExpiration;  // Make sure the type matches the expected value

    public String getJwtSecret() {
        return jwtSecret;
    }

    public Long getJwtExpiration() {
        return jwtExpiration;
    }

    // Other methods or configurations
}
