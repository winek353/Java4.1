package uj.jwzp.w4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    private final String fileName = "movies.txt";

    @Bean(name="fileName")
    public String getP(){return fileName;}

}
