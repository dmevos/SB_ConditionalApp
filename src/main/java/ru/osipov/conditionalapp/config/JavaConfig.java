package ru.osipov.conditionalapp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.osipov.conditionalapp.profile.DevProfile;
import ru.osipov.conditionalapp.profile.ProductionProfile;
import ru.osipov.conditionalapp.profile.SystemProfile;

@Configuration
public class JavaConfig {

    @Bean
    @ConditionalOnProperty(name = "osipov.profile.dev", havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(name = "osipov.profile.dev", havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }

}
