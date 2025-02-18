package com.reto.config.core;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.Serializable;

/**
 * AppPostgresConfig to run spring boot app.
 *
 * @author patedwins
 * @version 1.0.0
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "postgresEntityManagerFactory",
        transactionManagerRef = "postgresTransactionManager",
        basePackages = {"com.reto.postgres"}
)
@Primary
public class AppPostgresConfig implements Serializable {

    private static final long serialVersionUID = -4580567702999656285L;

    /**
     * postgresDataSource
     *
     * @return
     */
    @Bean(name = "postgresDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.postgres")
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * postgresEntityManagerFactory
     *
     * @param builder
     * @param postgresDataSource
     * @return
     */
    @Primary
    @Bean(name = "postgresEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                               @Qualifier("postgresDataSource") DataSource postgresDataSource) {
        return builder
                .dataSource(postgresDataSource)
                .packages("com.reto.postgres")
                .build();
    }

    /**
     * postgresTransactionManager
     *
     * @param postgresEntityManagerFactory
     * @return
     */
    @Bean(name = "postgresTransactionManager")
    public PlatformTransactionManager postgresTransactionManager(
            @Qualifier("postgresEntityManagerFactory") EntityManagerFactory postgresEntityManagerFactory) {
        return new JpaTransactionManager(postgresEntityManagerFactory);
    }
}
