package com.julio.demo.config;

import com.julio.demo.model.Token;
import com.julio.demo.service.TokenService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@Component("applicationConfigurations")
public class TokenPropertyLoader implements EnvironmentAware, ApplicationContextInitializer {

    @Autowired
    private TokenService tokenService;

    @Override
    public void setEnvironment(Environment environment) {
        ConfigurableEnvironment configurableEnvironment = (ConfigurableEnvironment) environment;

        Map<String, Object> propertySource = new HashMap<>();
        Token token = tokenService.getToken();
        propertySource.put("bot.token", token.getToken());
        
        configurableEnvironment.getPropertySources().addAfter("systemEnvironment", new MapPropertySource("app-config", propertySource));
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

    }
}
