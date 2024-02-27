package netology.springboot.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import netology.springboot.profile.DevProfile;
import netology.springboot.profile.ProductionProfile;
import netology.springboot.profile.SystemProfile;

@Configuration
public class ProfileConfiguration {
    @Bean
    @ConditionalOnProperty(prefix = "netology", name = "profile", havingValue = "development")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(prefix = "netology", name = "profile", havingValue = "production")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}