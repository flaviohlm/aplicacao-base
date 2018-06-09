package br.gov.to.secad.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.util.Properties;

/**
 * @author wandyer.silva
 */
@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", true);
        mailProperties.put("mail.smtp.starttls.enable", false);
        mailProperties.put("mail.mime.charset", "UTF-8");
        mailProperties.put("mail.transport.protocol", "smtp");

        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost("mail.secad.to.gov.br");
        mailSender.setPort(25);
        mailSender.setUsername("portaldoservidor@secad.to.gov.br");
        mailSender.setPassword("secad321");
        return mailSender;
    }

    @Bean
    public FreeMarkerConfigurationFactoryBean freemarkerConfigFactory() {
        FreeMarkerConfigurationFactoryBean freeMakerConfig = new FreeMarkerConfigurationFactoryBean();
        freeMakerConfig.setTemplateLoaderPath("classpath:templates/");
        freeMakerConfig.setDefaultEncoding("UTF-8");
        return freeMakerConfig;
    }

}
