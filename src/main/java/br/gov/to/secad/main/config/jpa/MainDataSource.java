package br.gov.to.secad.main.config.jpa;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author wandyer.silva
 */
@SuppressWarnings("Duplicates")
@Configuration
@ComponentScan(basePackages = "br.gov.to.secad")
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryMain",
        transactionManagerRef = "transactionManagerMain",
        basePackages =
                {"br.gov.to.secad.main.repository"})
public class MainDataSource {

    private final Environment env;

    @Autowired
    public MainDataSource(Environment env) {
        this.env = env;
    }

    /**
     * DataSource Portal
     *
     * @return .
     */
    @Bean
    public DataSource dataSourceMain() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("dbUnicet.driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("dbUnicet.url"));
        dataSource.setUsername(env.getRequiredProperty("dbUnicet.username"));
        dataSource.setPassword(env.getRequiredProperty("dbUnicet.password"));

        // Pool de Conexões
        dataSource.setInitialSize(Integer.valueOf(env.getRequiredProperty("pool.initialSize")));
        dataSource.setMaxTotal(Integer.valueOf(env.getRequiredProperty("pool.maxTotal")));
        dataSource.setMaxIdle(Integer.valueOf(env.getRequiredProperty("pool.maxIdle")));
        dataSource.setMinIdle(Integer.valueOf(env.getRequiredProperty("pool.minIdle")));
        dataSource.setMaxWaitMillis(Integer.valueOf(env.getRequiredProperty("pool.maxWaitMillis")));
        dataSource.setDefaultQueryTimeout(Integer.valueOf(env.getRequiredProperty("pool.defaultQueryTimeout")));
        dataSource.setValidationQuery((env.getRequiredProperty("pool.validationQuery")));
        dataSource.setValidationQueryTimeout(Integer.valueOf(env.getRequiredProperty("pool.validationQueryTimeout")));
        dataSource.setRemoveAbandonedTimeout(Integer.valueOf(env.getRequiredProperty("pool.removeAbandonedTimeout")));

        return dataSource;
    }

    /**
     * EntityManagerFactory Portal
     *
     * @return .
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryMain() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSourceMain());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(
                "br.gov.to.secad.main.domain");

        entityManagerFactoryBean.setPersistenceUnitName("sgsPU");

        //JPA Properties
        entityManagerFactoryBean.setJpaProperties(jpaProperties());

        return entityManagerFactoryBean;
    }

    /**
     * TransactionManager Portal
     * @return .
     */
    @Bean
    public JpaTransactionManager transactionManagerMain() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryMain().getObject());
        return transactionManager;
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.enable_lazy_load_no_trans", env.getRequiredProperty("hibernate.enable-lazy-load-no-trans"));
        return properties;
    }

}
