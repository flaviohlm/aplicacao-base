package br.gov.to.secad.main.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import java.io.IOException;

/**
 * @author wandyer.silva
 */
@Configuration
@ComponentScan
public class AppConfig {

    private static Logger logger = LogManager.getLogger();

    public AppConfig () {

    }

    /**
     * Método para buscar as propriedades do application.yml e armazenar no Environment da applicação
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public static PropertySourcesPlaceholderConfigurer yamlPropertySourceLoader(ConfigurableApplicationContext configurableApplicationContext, ApplicationContext applicationContext) {
        YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
        PropertySource<?> defaultProperties = null;
        PropertySource<?> profileProperties = null;

        Resource resource = applicationContext.getResource("/WEB-INF/application.yml");

        String activeProfile = configurableApplicationContext.getEnvironment().getActiveProfiles()[0];
        logger.info("Active Profile: " + activeProfile);

        try {
            defaultProperties = sourceLoader.load("defaultProperties", resource, null);
            profileProperties = sourceLoader.load("profileProperties", resource, activeProfile);
        } catch (IOException e) {
            logger.error(e);
        }
        configurableApplicationContext.getEnvironment().getPropertySources().addFirst(defaultProperties);
        configurableApplicationContext.getEnvironment().getPropertySources().addFirst(profileProperties);

        return new PropertySourcesPlaceholderConfigurer();
    }
}
